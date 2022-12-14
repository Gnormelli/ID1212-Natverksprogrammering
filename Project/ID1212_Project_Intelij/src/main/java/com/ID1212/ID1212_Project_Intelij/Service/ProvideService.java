package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer
 */
@Service
public class ProvideService {

    public List<QueuePost> getQueuePosts(){
        return List.of(
                new QueuePost(
                        1L,
                        "This is my location",
                        "I have commented this",
                        true,
                        false
                ));
    }
}
