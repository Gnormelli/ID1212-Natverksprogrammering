package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface QueuePostRepository extends JpaRepository<QueuePost, Long> {

    @Query(value = "SELECT * FROM queue_post ORDER BY posted_date_time DESC LIMIT 3",
            nativeQuery = true)
    List<QueuePost> findLastTenRecords();
}
