package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.ConversationRepository;
import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    ConversationRepository conversationRepository;

    public Conversation getConversationById(Long convID){
        return conversationRepository.findConversationById(convID);
    }

    /**
     * Creates instance in table for conversations
     * @return String that conversation has been created.
     */
    public String createConversation(String convName){
        Optional<Conversation> fromDatabase = Optional.ofNullable(
                conversationRepository.findConversationByName(convName));
        if(fromDatabase.isPresent()){
            return "Conversation already exist";

        }else
            conversationRepository.save(new Conversation(convName));
        return "User created";
    }
}
