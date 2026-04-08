package edu.ynu.controller;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import edu.ynu.entity.User;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;
    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        List<ServiceInstance> instances = discoveryClient.getInstances("provider-service");
        ServiceInstance instance = instances.get(0);
        String url = "http://provider-service/user/get/" + id;
        return restTemplate.getForObject(url, User.class);
    }
}
