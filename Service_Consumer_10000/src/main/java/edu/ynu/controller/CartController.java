package edu.ynu.controller;

import edu.ynu.entity.User;
import edu.ynu.feign.UserFeignClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("/get/{id}")
    @Bulkhead(name = "bulkhead", type = Bulkhead.Type.SEMAPHORE, fallbackMethod = "getUserByIdDown")
    public User getUserById(@PathVariable("id") Integer id) {
        System.out.println("调用成功");
        return userFeignClient.getUserById(id);
    }

    public User getUserByIdDown(Integer id, Exception e){
        e.printStackTrace();
        String message = "获取用户" + id + "信息的服务当前被熔断，因此方法降级";
        System.out.println(message);
        return new User();
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
