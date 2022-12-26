package com.ID1212.ID1212_Project_Intelij.Controller;

import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import com.ID1212.ID1212_Project_Intelij.Service.ProvideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/")
public class QueuePostController {
    private final ProvideService service;

    /**
     * Injected to through autowiring
     * @param service
     */
    @Autowired
    public QueuePostController(ProvideService service) {
        this.service = service;
    }

    /**
     * Uses RestController and set to GetMapping to make it a RestFull endpoint
     * @return QueuePost JSON
     */
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getQueue")
    public List<QueuePost> getQueuePosts(){
        return service.getQueuePosts();
    }

    @PostMapping("/postQueue")
    public void registerNewQueuePost(@RequestBody QueuePost queuePost){
        service.addNewQueuePost(queuePost);
    }
}
