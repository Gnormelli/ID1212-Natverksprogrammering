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
    public Object updateMembership(@RequestBody GroupMember groupMember) {
        System.out.println(groupMember.toString());

        String entryResult= groupMemberService.updateMembership(groupMember);

        HashMap<String, String> map = new HashMap<>();
        map.put("id", "Done: " + entryResult);
        return map;

    }
    @PostMapping( "/group_member_by_conId")
    public Object findGroupMemberByConversationID(@RequestBody Conversation conversation){
        Long conID = conversation.getId();
        Collection<String> collectionOfUserNamesInGroup =
                groupMemberService.findGroupMemberByConversationID(conID);
        if(collectionOfUserNamesInGroup.isEmpty()){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "Conversation does not have members");
            return map;
        }else{
            return collectionOfUserNamesInGroup;
        }
    }

    @PostMapping( "/conversations_by_userID")
    public Object findConversationsByUserID(@RequestBody User user){
        Long userId = user.getId();
        Collection<Conversation> collectionOfConversations =
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
