package com.ID1212.ID1212_Project_Intelij.Controller;


import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.Message;
import com.ID1212.ID1212_Project_Intelij.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/")
public class MessageController {
    @Autowired
    MessageService messageService;

    @PostMapping("/send_message")
    public Object takeNewMessage(@RequestBody Message message){
        messageService.createNewMessage(message);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "Message sent");
        return map;
    }

    @PostMapping("/get_message")
    public Object getMessages(@RequestBody Message message){
        System.out.println(message.toString());
        List<Message> conversationMessages = messageService.getMessagesByConversationId(message.getFk_conversation().getId());
        return conversationMessages;
    }

}
