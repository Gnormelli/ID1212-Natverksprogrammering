package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
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



    @PostMapping("/perform_login")
    public Object login(@RequestBody User user) {

        String username = user.getUsername();
        String password = user.getPassword();
        return userService.getUsernamesAndPassword(username, password);



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

