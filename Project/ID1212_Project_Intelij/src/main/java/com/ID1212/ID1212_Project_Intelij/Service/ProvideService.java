package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.QueuePostRepository;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Service layer
 */
@Service
public class ProvideService {

    private final QueuePostRepository queuePostRepository;

    @Autowired
    public ProvideService(QueuePostRepository queuePostRepository){
        this.queuePostRepository=queuePostRepository;
    }

    public List<QueuePost> getQueuePosts(){
        return queuePostRepository.findAll();
    }

    public void addNewQueuePost(QueuePost queuePost){   //get it to save it to database
        System.out.println(queuePost);
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
