package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface QueuePostRepository extends JpaRepository<QueuePost, Long> {

  //  @Query("SELECT q FROM QueuePost q WHERE q.id = ?1")
    Optional<QueuePost> findQueuePostById(Long id);

}
