package ynu.edu.filter;

import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import reactor.core.publisher.Mono;
import ynu.edu.config.GatewayAuthProperties;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    private final GatewayAuthProperties authProperties;

    public AuthGlobalFilter(GatewayAuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    @Override
    public Mono<Void> filter(org.springframework.web.server.ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        if (!authProperties.isEnabled()
                || HttpMethod.OPTIONS.equals(request.getMethod())
                || isExcluded(path)) {
            return chain.filter(exchange);
        }

        String headerValue = request.getHeaders().getFirst(authProperties.getHeaderName());
        String expectedValue = authProperties.getHeaderPrefix() + authProperties.getValidToken();

        if (!StringUtils.hasText(headerValue) || !expectedValue.equals(headerValue)) {
            return unauthorized(exchange.getResponse(), path);
        }

        return chain.filter(exchange);
    }

    private boolean isExcluded(String path) {
        List<String> excludePaths = authProperties.getExcludePaths();
        for (String excludePath : excludePaths) {
            if (PATH_MATCHER.match(excludePath, path)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> unauthorized(ServerHttpResponse response, String path) {
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String body = """
                {"code":401,"message":"未通过网关统一认证，请在请求头中携带合法令牌","path":"%s"}
                """.formatted(path).trim();
        DataBuffer dataBuffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
    }

    @Override
    public int getOrder() {
        return -100;
    }
}
