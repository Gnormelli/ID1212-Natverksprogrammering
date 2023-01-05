package com.ID1212.ID1212_Project_Intelij.Models;

import javax.persistence.*;

@Entity
@Table(name = "conversation")
public class Conversation {
    @Id
    @SequenceGenerator(
            name = "conversation_sequence",
            sequenceName = "conversation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "conversation_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    Long id;
    @Column(
            name = "conversation_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String name;

    protected Conversation(){}
    public Conversation(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
