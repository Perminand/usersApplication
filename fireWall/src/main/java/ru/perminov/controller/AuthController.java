package ru.perminov.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.perminov.model.User;
import ru.perminov.service.UserService;

@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }


    @PostMapping("/new-user")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "User is saved";
    }
}