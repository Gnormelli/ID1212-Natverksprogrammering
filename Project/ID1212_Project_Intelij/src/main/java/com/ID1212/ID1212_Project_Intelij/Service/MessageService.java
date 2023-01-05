package com.ID1212.ID1212_Project_Intelij.Service;

import com.ID1212.ID1212_Project_Intelij.DataAccess.MessageRepository;
import com.ID1212.ID1212_Project_Intelij.Models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
private final MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createNewMessage(Message message) {
        message.setSentDateTime(LocalDateTime.now());
        messageRepository.save(message);
    }
    public List <Message> getMessagesByConversationId(Long id) {
        List<Message> allMessagesFromRepository = messageRepository.findAll();
        List<Message> result = allMessagesFromRepository.stream().filter(msg ->
                msg.getFk_conversation().getId().equals(id)).collect(Collectors.toList());
        return result;
    }
}
