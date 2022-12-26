package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.User;
import com.ID1212.ID1212_Project_Intelij.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(User user){
        System.out.println(user);
        return "redirect: /home";
    }

    @PostMapping("/perform_login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request) {
        // authenticate the user and handle the login request
        return "redirect:/home";
    }
    @GetMapping("/logout")
    public String logout() {
        return "Logged out";
    }
}
