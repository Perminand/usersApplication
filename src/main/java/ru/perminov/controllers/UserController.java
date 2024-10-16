package ru.perminov.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class UserController {
    @GetMapping("/welcome")
    public String welcome() {
        return "Привет! Добро пожаловать!";
    }
}