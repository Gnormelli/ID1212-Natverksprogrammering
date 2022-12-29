package com.ID1212.ID1212_Project_Intelij.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_member")
@IdClass(GroupMember.class)
public class GroupMember implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_user_info")
    User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_id_conversation")
   Conversation conversation;

    protected GroupMember(){

    }

    public GroupMember(User user, Conversation conversation) {
        this.user = user;
        this.conversation = conversation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "user=" + user +
                ", conversation=" + conversation +
                '}';
    }
}
