package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.QueuePostRepository;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer
 */
@Service
public class ProvideService {

    private final QueuePostRepository queuePostRepository;

    @Autowired
    public ProvideService(
            QueuePostRepository queuePostRepository
                          ){
        this.queuePostRepository=queuePostRepository;
    }

    public List<QueuePost> getQueuePosts(){
        return queuePostRepository.findAll();
    }

    public void addNewQueuePost(QueuePost queuePost) {   //get it to save it to database
        Optional<QueuePost> postById = queuePostRepository.findQueuePostById(queuePost.getId());
        if (postById.isPresent()) {
            throw new IllegalArgumentException("Id taken");
        } else {
            queuePostRepository.save(queuePost);
            System.out.println(queuePost);
        }
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
