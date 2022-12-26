package com.ID1212.ID1212_Project_Intelij.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "Logged in";
    }
    @GetMapping("/logout")
    public String logout() {
        return "Logged out";
    }
}
