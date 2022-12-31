package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMember;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMemberCompositeKey;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface GroupMemberRepository extends JpaRepository<GroupMember, GroupMemberCompositeKey> {

    GroupMember findGroupMemberByCompositeKey(GroupMemberCompositeKey compositeKey);

    Collection<GroupMember> findGroupMembersByCompositeKey_User_Id(Long id);
    Collection<GroupMember> findGroupMembersByCompositeKey_Conversation_Id(Long id);
//    GroupMember findGroupMemberByConversation_IdAndUser_Id(Long convID, Long userID);

//    Collection<GroupMember> findGroupMembersByConversation_Id(Long convID);
//    Collection<GroupMember> findGroupMembersByUser_Id(Long userID);
}
