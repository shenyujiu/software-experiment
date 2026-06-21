package ynu.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/consumer")
    public Map<String, Object> consumerFallback() {
        return Map.of(
                "code", 503,
                "message", "consumer-service 暂时不可用，已触发网关降级返回",
                "service", "consumer-service"
        );
    }

    @GetMapping("/fallback/provider")
    public Map<String, Object> providerFallback() {
        return Map.of(
                "code", 503,
                "message", "provider-service 暂时不可用，已触发网关降级返回",
                "service", "provider-service"
        );
    }
}
