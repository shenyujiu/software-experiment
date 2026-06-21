package edu.ynu.controller;

import edu.ynu.entity.User;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RefreshScope
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        // 返回包含 11001 端口标识的用户信息，以便展示负载均衡效果
        return new User(id, "User_" + id + " (from Provider: 11001)", "password" + id);
    }

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return "Added user: " + user.getUserName() + " (by Provider: 11001)";
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User user) {
        return "Updated user: " + user.getUserId() + " (by Provider: 11001)";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        return "Deleted user: " + id + " (by Provider: 11001)";
    }
}
