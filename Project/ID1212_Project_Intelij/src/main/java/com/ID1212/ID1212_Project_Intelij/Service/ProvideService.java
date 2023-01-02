package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.QueuePostRepository;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Service layer
 */
@Service
public class ProvideService {

    @Autowired
    QueuePostRepository queuePostRepository;

    public List<QueuePost> getQueuePosts(){
       return queuePostRepository.findLastTenRecords();
    }

    public void addNewQueuePost(QueuePost queuePost) {   //get it to save it to database
        queuePostRepository.save(queuePost);
    }

}
