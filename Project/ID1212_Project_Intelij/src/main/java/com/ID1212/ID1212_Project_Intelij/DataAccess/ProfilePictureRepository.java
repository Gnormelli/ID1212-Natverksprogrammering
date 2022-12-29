package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.ProfilePicture;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, Long> {



    ProfilePicture findProfilePictureById(String id);
}
