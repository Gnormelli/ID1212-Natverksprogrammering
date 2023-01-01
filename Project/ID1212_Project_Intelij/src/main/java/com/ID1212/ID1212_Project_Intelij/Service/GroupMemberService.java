package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.GroupMemberRepository;
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

    public Collection<User> findGroupMemberByConversationID(Long conID ){
        Collection<GroupMember> collectionOfGroupMembers =
                groupMemberRepository.findGroupMembersByCompositeKey_Conversation_Id(conID);

        Collection<User> userCollection = new ArrayList<>();
        for(GroupMember groupMember : collectionOfGroupMembers){
             userCollection.add(groupMember.getCompositeKey().getUser());
        }
        return userCollection;
    }

    public Collection<GroupMember> findConversationsByUserID(Long userId){
        return groupMemberRepository.findGroupMembersByCompositeKey_User_Id(userId);
    }

    public String updateMembership(Long userId, Long conversationID){



        return "hi";
    }
}
