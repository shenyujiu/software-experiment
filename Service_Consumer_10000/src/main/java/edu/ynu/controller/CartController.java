package edu.ynu.controller;

import edu.ynu.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        // 使用服务名进行调用，因为配置了 @LoadBalanced
        String url = "http://provider-service/user/get/" + id;
        return restTemplate.getForObject(url, User.class);
    }
}
