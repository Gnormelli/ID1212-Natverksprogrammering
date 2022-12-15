package com.ID1212.ID1212_Project_Intelij.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity(name = "QueuePost")
@Table(
        name = "QueuePost",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "queuePost_location_unique",
                        columnNames = "location"
                )
        }
)
public class QueuePost {
    /**
     * Map QueuePost to database
     */
    @Id
    @SequenceGenerator(
            name = "queuepost_sequence",
            sequenceName = "queuepost_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "queuepost_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;


    @Column(
            name = "location",
            nullable = false,
            columnDefinition = "TEXT"

    )
    private String location;


    @Column(
            name = "comment",
            columnDefinition = "TEXT"
    )
    private String comment;


    @Column(
            name = "help",
            nullable = false,
            columnDefinition = "BOOLEAN"
    )
    private Boolean help;


    @Column(
            name = "present",
            columnDefinition = "TEXT"
    )
    private Boolean present;


    @Column(
            name = "localDateTime",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalDateTime localDateTime;



    protected QueuePost() {
    }

    /**
     * Queue post by students
     * @param id from db
     * @param location submitted by student
     * @param comment submitted by student
     * @param help checkbox ticked by student
     * @param present checkbox ticked by student
     * localDateTime time and date queue post passed by student
     */
    public QueuePost(Long id,
                     String location,
                     String comment,
                     Boolean help,
                     Boolean present
                     ) {
        this.id = id;
        this.location = location;
        this.comment = comment;
        this.help = help;
        this.present = present;
        this.localDateTime = LocalDateTime.now();
    }

    /**
     * Without id because generated by postgres db
     * @param location submitted by student
     * @param comment submitted by student
     * @param help checkbox ticked by student
     * @param present checkbox ticked by student
     * localDateTime time and date queue post passed by student
     */
    public QueuePost(String location,
                     String comment,
                     Boolean help,
                     Boolean present) {
        this.location = location;
        this.comment = comment;
        this.help = help;
        this.present = present;
        this.localDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getHelp() {
        return help;
    }

    public Boolean getPresent() {
        return present;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setHelp(Boolean help) {
        this.help = help;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "QueuePost{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", comment='" + comment + '\'' +
                ", help=" + help +
                ", present=" + present +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
