package edu.ynu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication13000 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication13000.class, args);
    }
}
