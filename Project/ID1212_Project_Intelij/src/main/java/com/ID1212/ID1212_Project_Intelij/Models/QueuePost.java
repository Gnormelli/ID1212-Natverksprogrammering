package com.ID1212.ID1212_Project_Intelij.Models;

// If one uses Spring Boot 3.0.0
// import jakarta.persistence.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity(name = "QueuePost")
@Table()
public class QueuePost {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;

    @Column(
            name = "from_user",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String fromUser;

    @Column(
            name = "post_text",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String postText;
    @Column(
            name = "posted_date_time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    LocalDateTime postedAt;

    protected QueuePost(){}

    public QueuePost(String fromUser, String postText, LocalDateTime postedAt) {
        this.fromUser = fromUser;
        this.postText = postText;
        this.postedAt = postedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public LocalDateTime getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(LocalDateTime sentDateTime) {
        this.postedAt = sentDateTime;
    }

    @Override
    public String toString() {
        return "QueuePost{" +
                "id=" + id +
                ", fromUser='" + fromUser + '\'' +
                ", postText='" + postText + '\'' +
                ", sentDateTime=" + postedAt +
                '}';
    }
}
