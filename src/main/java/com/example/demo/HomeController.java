package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello from Spring MVC!");
        return "home";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "greet";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}