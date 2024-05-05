package com.example.buyusername.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.buyusername.entity.User;
import com.example.buyusername.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/")
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/get-username")
    public String getusername() {
        return "getusername";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/my-username")
    public String getMethodName() {
        return "myusername";
    }

    @PostMapping("/get-username")
    public String checkUsername(User user, RedirectAttributes redirectAttributes) {
        boolean usernameExists = userService.usernameExists(user.getUsername());
        
        if (usernameExists) {
            redirectAttributes.addFlashAttribute("message", "This '" + user.getUsername() + "' username is taken! choose another one.");
            return "redirect:/get-username";
        } else {
            redirectAttributes.addFlashAttribute("message", "You can have '" + user.getUsername() + "' username");
            return "redirect:/register";
        }
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, RedirectAttributes redirectAttributes) {
        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("message", "Password didn't match!");
            return "redirect:/register";
        }

        // Check if username already exists
        if (userService.usernameExists(username)) {
            redirectAttributes.addFlashAttribute("message", "This '" + username + "' username is taken! choose another one.");
            return "redirect:/register";
        }

        // If username doesn't exist and passwords match, proceed with registration
        User user = new User(username.toLowerCase(), password);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("user", user);
        redirectAttributes.addFlashAttribute("message", "Registration successful!");
        return "redirect:/my-username";
    }
}
