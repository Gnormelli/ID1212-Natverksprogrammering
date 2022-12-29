package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Service.ProfilePictureService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/")
public class ProfilePictureController {
    private final ProfilePictureService ppService;

    public ProfilePictureController(ProfilePictureService ppService) {
        this.ppService = ppService;

    }

    @GetMapping("/get_profile_picture")
    public Object login(@RequestBody Long id) {
        if(true){
            return "hi";
        }
        ProfilePicture p = ppService.getProfilePictureFromDB(id);
        String picture = p.getPicture();
        HashMap<String, String> map = new HashMap<>();
        map.put("picture", picture);

        return map;


    }




}

