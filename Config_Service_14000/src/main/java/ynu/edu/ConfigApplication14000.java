package ynu.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigApplication14000 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication14000.class, args);
    }
}
