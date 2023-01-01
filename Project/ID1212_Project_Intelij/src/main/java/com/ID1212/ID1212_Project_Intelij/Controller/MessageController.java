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

@RestController
@RequestMapping("/")
public class MessageController {
    @Autowired
    MessageService messageService;

//    @PostMapping(name = "/messages_from_conv")
//    public Object getMessagesFromConversationID(@RequestBody Conversation conversation){
//        Long conID = conversation.getId();
//        Collection<Message> messages = messageService.getAllMessagesForConversationID(conID);
//        if(messages.isEmpty()){
//            HashMap<String, String> map = new HashMap<>();
//            map.put("id", "Conversation does not exist");
//            return map;
//        }else{
//            return messages;
//        }
//
//    }

    @PostMapping("/send_message")
    public Object takeNewMessage(@RequestBody Message message){
        messageService.createNewMessage(message);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "Message sent");
        return map;
    }

    @PostMapping("/get_message")
    public Object getMessages(@RequestBody Message message){
        messageService.getMessagesByConversationId(message.getId());


        HashMap<String, String> map = new HashMap<>();
        map.put("id", "Hold");
        return map;
    }


    @PostMapping("/new_message")
    public Object createNewMessage(@RequestBody Message message){
//        if(message.getMessageText() == null){
//            HashMap<String, String> map = new HashMap<>();
//            map.put("id", "message was not posted.");
//            return map;
//        }else{
//            LocalDateTime dateTime = LocalDateTime.now();
//            message.setSentDateTime(dateTime);
//            messageService.createNewMessage(message);
//            return "message was added";
//        }
        return null;
    }
}
