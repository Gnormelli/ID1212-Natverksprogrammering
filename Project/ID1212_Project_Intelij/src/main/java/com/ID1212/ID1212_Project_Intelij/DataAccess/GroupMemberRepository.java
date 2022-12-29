package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<Conversation, Long> {
    GroupMember findByIdUser(Long id);
    Conversation findByIdConversation(Long id);
}
