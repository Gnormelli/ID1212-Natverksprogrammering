package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.GroupMemberRepository;
import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMember;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMemberCompositeKey;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class GroupMemberService {
    @Autowired
    GroupMemberRepository groupMemberRepository;

    public GroupMember findGroupMember(GroupMemberCompositeKey compositeKey){
        return groupMemberRepository.findGroupMemberByCompositeKey(compositeKey);
    }

    public Collection<String> findGroupMemberByConversationID(Long conID ){
        Collection<GroupMember> collectionOfGroupMembers =
                groupMemberRepository.findGroupMembersByCompositeKey_Conversation_Id(conID);

        Collection<String> userNameCollection = new ArrayList<>();
        for(GroupMember groupMember : collectionOfGroupMembers){
            userNameCollection.add(groupMember.getCompositeKey().getUser().getUsername());
        }
        return userNameCollection;
    }

    public Collection<Conversation> findConversationsByUserID(Long userId){
        Collection<GroupMember> groupMemberCollection =
                groupMemberRepository.findGroupMembersByCompositeKey_User_Id(userId);

        Collection<Conversation> conversations = new ArrayList<>();
        for(GroupMember groupMember: groupMemberCollection){
            conversations.add(groupMember.getCompositeKey().getConversation());
        }
        return conversations;
    }

    public String updateMembership(Long userId, Long conversationID){



        return "hi";
    }
}
