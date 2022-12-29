package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import com.ID1212.ID1212_Project_Intelij.Service.ProfilePictureService;
import com.ID1212.ID1212_Project_Intelij.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/")
public class LoginController {
    private final UserService userService;
    private final ProfilePictureService ppService;

    public LoginController(UserService userService, ProfilePictureService ppService) {
        this.userService = userService;
        this.ppService = ppService;
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
    public String createUser(@RequestBody User user){
        return ppService.getProfilePictureFromDB("1").toString();
//        user.setProfilePicture(profilePicture);
//        String whatHappend = userService.createUser(user);
//        if(whatHappend.equals("Already a user by that username")){
//            return "We did not log in";
//        }else{
//            return "We logged in";
//        }
    }

    @GetMapping("/logout") //wont be used
    public String logout() {
        return "Logged out";
    }

//    @PostMapping("/postQueue")
//    public Object registerNewQueuePost(@RequestBody QueuePost queuePost){
//
//        // service.addNewQueuePost(queuePost);
//        String username = queuePost.getLocation();
//        String password = queuePost.getComment();
//        service.getQueuePosts();
//        if(true){ //somthing with service.getQuePost()
//            return service.getUsernamesAndPassword(username, password);
//        }else{
//            return "by";
//        }
}

