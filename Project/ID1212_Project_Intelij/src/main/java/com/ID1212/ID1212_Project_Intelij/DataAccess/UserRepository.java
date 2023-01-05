package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Can be Optional<User>
    User findByUsername(String username);



    UserDetails findByUsernameAndPassword(String username, String password);


    Optional<QueuePost> findQueuePostByUsername(String username);
}
