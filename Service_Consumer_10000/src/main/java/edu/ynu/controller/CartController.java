package edu.ynu.controller;

import edu.ynu.entity.User;
import edu.ynu.feign.UserFeignClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userFeignClient.getUserById(id);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return userFeignClient.addUser(user);
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        return userFeignClient.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        return userFeignClient.deleteUser(id);
    }
}
