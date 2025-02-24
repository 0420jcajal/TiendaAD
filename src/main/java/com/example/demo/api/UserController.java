package com.example.demo.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.models.User;
import com.example.demo.requests.UserCreationRequest;
import com.example.demo.services.UserService;


@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins="*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService= userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserCreationRequest userCreationRequest){
        return userService.createUser(userCreationRequest);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUser(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id){
        userService.removeUser(id);
    }

    @GetMapping("/getall")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}
