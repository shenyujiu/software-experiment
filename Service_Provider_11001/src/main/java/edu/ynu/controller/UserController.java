package edu.ynu.controller;

import edu.ynu.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return new User(id, "User_" + id, "password" + id);
    }
}
