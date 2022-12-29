package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Service.ProfilePictureService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class ProfilePictureController {
    private final com.ID1212.ID1212_Project_Intelij.Service.ProfilePictureService ProfilePictureService;

    public ProfilePictureController(ProfilePictureService profilePictureService) {
        this.ProfilePictureService = profilePictureService;
    }

    @GetMapping("/get_profile_picture")
    public Object login(@RequestBody ProfilePicture profilePicture) {
        ProfilePicture e = profilePictureService.

    return null;


    }




}

