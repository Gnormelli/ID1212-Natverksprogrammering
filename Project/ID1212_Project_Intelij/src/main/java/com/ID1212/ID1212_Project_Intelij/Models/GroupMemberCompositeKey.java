package com.ID1212.ID1212_Project_Intelij.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GroupMemberCompositeKey implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_user_info")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_conversation")
    private Conversation conversation;

    protected GroupMemberCompositeKey(){}

    public GroupMemberCompositeKey(User user, Conversation conversation) {
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
    public int hashCode() {
        return Objects.hash(user, conversation);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        }else {
            GroupMemberCompositeKey aKey = (GroupMemberCompositeKey) obj;
            return Objects.equals(user, aKey.getClass()) &&
                    Objects.equals(conversation, aKey.getClass());
        }
    }
}
