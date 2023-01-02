package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import com.ID1212.ID1212_Project_Intelij.Service.ProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class QueuePostController {

    /**
     * Injected to through autowiring
     * @param service
     */
    @Autowired
    ProvideService service;

    /**
     * Uses RestController and set to GetMapping to make it a RestFull endpoint
     * @return QueuePost JSON
     */
    @GetMapping("/get_posts")
    public List<QueuePost> getQueuePosts(){
        return service.getQueuePosts();
    }

    @PostMapping("/send_post")
    public Object registerNewQueuePost(@RequestBody QueuePost queuePost){
        queuePost.setPostedAt(LocalDateTime.now());
        service.addNewQueuePost(queuePost);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "Post has been sent");
        return map;
    }
}
