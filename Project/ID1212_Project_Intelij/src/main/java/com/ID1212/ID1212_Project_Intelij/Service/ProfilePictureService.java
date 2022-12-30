package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.ProfilePictureRepository;
import com.ID1212.ID1212_Project_Intelij.DataAccess.QueuePostRepository;
import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer
 */
@Service
public class ProfilePictureService {

    private final ProfilePictureRepository profilePictureRepository;

    @Autowired
    public ProfilePictureService(ProfilePictureRepository profilePictureRepository)
    {
        this.profilePictureRepository=profilePictureRepository;
    }



    public ProfilePicture getProfilePictureFromDB(Long id){
        return profilePictureRepository.findProfilePictureById(id);
    }

    public List<ProfilePicture> getAllProfilePictureFromDB(){
        return profilePictureRepository.findAll();
    }


    /**
    public List<QueuePost> getQueuePosts(){
        return List.of(
                new QueuePost(
                        1L,
                        "This is my location",
                        "I have commented this",
                        true,
                        false
                ));
    }*/
}
