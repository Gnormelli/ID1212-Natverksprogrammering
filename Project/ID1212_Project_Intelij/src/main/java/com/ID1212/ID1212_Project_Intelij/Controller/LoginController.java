package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String login(User user){
        System.out.println(user);
        return "redirect: /home";
    }
    @GetMapping("/logout")
    public String logout() {
        return "Logged out";
    }
}
