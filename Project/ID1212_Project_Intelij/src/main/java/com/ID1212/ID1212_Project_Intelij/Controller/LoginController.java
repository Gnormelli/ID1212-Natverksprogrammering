package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import com.ID1212.ID1212_Project_Intelij.Service.ProfilePictureService;
import com.ID1212.ID1212_Project_Intelij.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    ProfilePictureService ppService;

    @PostMapping("/update_profile_picture")
    public Object updateProfilePicture(@RequestBody User user) {

        userService.updateUserProfilePicture(user.getUsername(), user.getProfilePicture().getId());


        HashMap<String, String> map = new HashMap<>();
        map.put("id", "Done");
        return map;
    }

    @PostMapping("/perform_login")
    public Object login(@RequestBody User user) {

        String username = user.getUsername();
        String password = user.getPassword();
        User usr = userService.canWeLogIn(username, password);

        if(usr == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "Bad credentials");
            return map;
        }else{
            //return usr;

            return usr;
        }
    }
    @PostMapping(value = "/create_user")
    public Object createUser(@RequestBody User user){

        ProfilePicture profilePicture = ppService.getProfilePictureFromDB(1L);
        user.setProfilePicture(profilePicture);

        Object userWIthID = userService.createUser(user);
        HashMap<String, String> map = new HashMap<>();
        if(userWIthID == null){
            map.put("id", "Alredy a user by that name");
            return map;
        }else {

            return userWIthID;
        }

    }
}

