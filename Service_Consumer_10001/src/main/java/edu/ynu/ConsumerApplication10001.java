package edu.ynu;

import edu.ynu.config.TwoTimeLoadBalancerConfig;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
//@LoadBalancerClient(name = "provider-service", configuration = TwoTimeLoadBalancerConfig.class)
public class ConsumerApplication10001 {
    public static void main(String[] args){
        SpringApplication.run(ConsumerApplication10001.class, args);
    }
}
