package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.Conversation;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMember;
import com.ID1212.ID1212_Project_Intelij.Models.GroupMemberCompositeKey;
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


    @PostMapping(value = "/group_member_by_conId")
    public Object findGroupMemberByConversationID(@RequestBody Long conID){
        return groupMemberService.findGroupMemberByConversationID(conID);
    }

    @PostMapping(value = "/group_member_by_userID")
    public Object findGroupMemberByUserID(@RequestBody User user){

        System.out.println("this is the composite key: " + user.toString());
        System.out.println("This does work");
        Long userId = user.getId();
        Collection<GroupMember> memberOfTheseGroups = groupMemberService.findGroupMemberByUserID(userId);
        if(user == null){
            HashMap<String, String> map = new HashMap<>();
            map.put("id", "CompositeKey does not exist");
            return map;
        }else{

            return memberOfTheseGroups;
        }
    }

    //Might not need this one
    @PostMapping(name = "/new_conversation")
    public String createNewConversation(@RequestBody String convName){
        return conversationService.createConversation(convName);
    };
}
