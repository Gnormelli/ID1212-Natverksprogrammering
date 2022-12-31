package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.MessageRepository;
import com.ID1212.ID1212_Project_Intelij.Models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MessageService {
private final MessageRepository messageRepository;


    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

//    public Collection<Message> getAllMessagesForConversationID(Long convId){
//        return messageRepository.findMessagesByFk_conversationIs(convId);
//    }

    public void createNewMessage(Message message) {
        messageRepository.save(message);
    }
}
