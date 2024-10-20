package ru.perminov.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.perminov.models.User;
import ru.perminov.services.AppService;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SecurityController {
    private AppService service;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }


    @PostMapping("/new-user")
    public String addUser(@RequestBody User user) {
        service.addUser(user);
        return "User is saved";
    }
}
