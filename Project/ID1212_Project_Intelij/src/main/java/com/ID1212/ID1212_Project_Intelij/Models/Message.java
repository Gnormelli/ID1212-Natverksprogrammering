package com.ID1212.ID1212_Project_Intelij.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
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
            name = "message_text",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String messageText;
    @Column(
            name = "sent_date_time",
            nullable = false,
            columnDefinition = "TIMESTAMP"
    )
    LocalDateTime sentDateTime;
    @ManyToOne
    @JoinColumn(name = "fk_id_conversation")
    Conversation fk_conversation;

    protected Message(){}
    public Message(String fromUser,
                   String messageText,
                   LocalDateTime sentDateTime,
                   Conversation fk_conversation) {
        this.fromUser = fromUser;
        this.messageText = messageText;
        this.sentDateTime = sentDateTime;
        this.fk_conversation = fk_conversation;
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

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    public Conversation getFk_conversation() {
        return fk_conversation;
    }

    public void setFk_conversation(Conversation fk_conversation) {
        this.fk_conversation = fk_conversation;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromUser='" + fromUser + '\'' +
                ", messageText='" + messageText + '\'' +
                ", sentDateTime=" + sentDateTime +
                ", fk_conversation=" + fk_conversation +
                '}';
    }
}
