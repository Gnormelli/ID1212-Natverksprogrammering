package com.ID1212.ID1212_Project_Intelij.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "group_member")
public class GroupMember implements Serializable {
    @EmbeddedId
    @Column(name = "group_member_composite_key")
    GroupMemberCompositeKey compositeKey;
    protected GroupMember(){

    }

    public GroupMember(GroupMemberCompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }

    public GroupMemberCompositeKey getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(GroupMemberCompositeKey compositeKey) {
        this.compositeKey = compositeKey;
    }


    @Override
    public String toString() {
        return "GroupMember{" +
                "compositeKey=" + compositeKey +
                '}';
    }
    //    @Id
//    @ManyToOne
//    @JoinColumn(name = "id_user_info")
//    User user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "id_conversation")
//    Conversation conversation;
//
//    protected GroupMember(){
//
//    }
//
//    public GroupMember(User user, Conversation conversation) {
//        this.user = user;
//        this.conversation = conversation;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Conversation getConversation() {
//        return conversation;
//    }
//
//    public void setConversation(Conversation conversation) {
//        this.conversation = conversation;
//    }
//
//    @Override
//    public String toString() {
//        return "GroupMember{" +
//                "user=" + user +
//                ", conversation=" + conversation +
//                '}';
//    }
}
