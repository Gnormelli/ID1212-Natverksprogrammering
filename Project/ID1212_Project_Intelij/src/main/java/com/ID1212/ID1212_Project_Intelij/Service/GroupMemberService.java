package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.GroupMemberRepository;
import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMember;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMemberCompositeKey;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class GroupMemberService {
    @Autowired
    GroupMemberRepository groupMemberRepository;

    public GroupMember findGroupMember(GroupMemberCompositeKey compositeKey){
        return groupMemberRepository.findGroupMemberByCompositeKey(compositeKey);
    }

    public Collection<GroupMember> findGroupMemberByConversationID(Long conID ){
        return groupMemberRepository.findGroupMembersByCompositeKey_Conversation_Id(conID);
    }

    public Collection<GroupMember> findGroupMemberByUserID(Long userId){
        return groupMemberRepository.findGroupMembersByCompositeKey_User_Id(userId);
    }
}
