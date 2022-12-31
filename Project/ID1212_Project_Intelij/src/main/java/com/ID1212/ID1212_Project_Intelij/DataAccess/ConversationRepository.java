package com.ID1212.ID1212_Project_Intelij.DataAccess;

import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Conversation findConversationById(Long id);

    Conversation findConversationByName(String name);

}
