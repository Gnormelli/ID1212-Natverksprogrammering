package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMember;
import com.ID1212.ID1212_Project_Intelij.Models.User;
import com.ID1212.ID1212_Project_Intelij.Service.ConversationService;
import com.ID1212.ID1212_Project_Intelij.Service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/")
public class GroupMemberController {

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    ConversationService conversationService;

    @PostMapping( "/update_membership")
    public Object updateMemebership(@RequestBody User user) {

        return null;
    }
    @PostMapping( "/group_member_by_conId")
    public Object findGroupMemberByConversationID(@RequestBody Conversation conversation){
        Long conID = conversation.getId();
        Collection<GroupMember> collectionOfGroupMembers =
                groupMemberService.findGroupMemberByConversationID(conID);
        if(collectionOfGroupMembers.isEmpty()){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "Conversation does not exist");
            return map;
        }else{
            return collectionOfGroupMembers;
        }
    }

    @PostMapping( "/conversations_by_userID")
    public Object findConversationsByUserID(@RequestBody User user){
        Long userId = user.getId();
        Collection<GroupMember> collectionOfConversations =
                groupMemberService.findConversationsByUserID(userId);
        if(collectionOfConversations.isEmpty()){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "User does not exist");
            return map;
        }else{
            return collectionOfConversations;
        }
    }

    //Not priority right now
    @PostMapping("/new_conversation")
    public String createNewConversation(@RequestBody String convName){
        return conversationService.createConversation(convName);
    };
}
