package com.ID1212.ID1212_Project_Intelij.Models;
import javax.persistence.*;

@Entity
@Table(name = "profile_picture")
public class ProfilePicture {

    @Id
    @SequenceGenerator(
            name = "picture_sequence",
            sequenceName = "picture_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "picture_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;
    @Column(
            name = "picture",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String picture;
    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String title;

    protected  ProfilePicture(){
    }
    public ProfilePicture(String picture, String title) {
        this.picture = picture;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ProfilePicture{" +
                "id=" + id +
                ", picture='" + picture + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
