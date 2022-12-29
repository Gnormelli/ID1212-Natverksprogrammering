package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Service.GroupMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GroupMemberController {

    @Autowired
    GroupMemberService groupMemberService;

    @PostMapping(value = "group_member_id")
    public Object findGroupMemberBy(@RequestBody Long id){
        return groupMemberService.findIdUser(id);
    }
}
