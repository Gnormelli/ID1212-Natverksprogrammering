package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.DataAccess.RoleRepository;
import com.ID1212.ID1212_Project_Intelij.Models.Role;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import com.ID1212.ID1212_Project_Intelij.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserService userService;

    @GetMapping(value = "home")
    public String home(){
        return "Home";
    }

    @PostMapping(value = "addrole")
    public Role role(@RequestBody Role role){
        return roleRepository.save(role);
    }

    @PostMapping(value = "createUser")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

}
