package edu.ynu.feign;

import edu.ynu.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "provider-service")
public interface UserFeignClient {

    @GetMapping("/user/get/{id}")
    User getUserById(@PathVariable("id") Integer id);

    @PostMapping("/user/add")
    String addUser(@RequestBody User user);

    @PutMapping("/user/update")
    String updateUser(@RequestBody User user);

    @DeleteMapping("/user/delete/{id}")
    String deleteUser(@PathVariable("id") Integer id);
}
