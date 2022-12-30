package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import com.ID1212.ID1212_Project_Intelij.Service.ProfilePictureService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/")
public class ProfilePictureController {
    private final ProfilePictureService ppService;

    public ProfilePictureController(ProfilePictureService ppService) {
        this.ppService = ppService;

    }

    @GetMapping("/get_profile_picture")
    public List<ProfilePicture> pictures() {
        return ppService.getAllProfilePictureFromDB();
    }




}

