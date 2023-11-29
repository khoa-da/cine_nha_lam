package com.fap.cinanhalam.controller;

import com.fap.cinanhalam.dto.PostDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(value="post")
    public ListOutput showPosts() {
        ListOutput result = new ListOutput();
        result.setListResult(postService.findAllWithStatusIsTrue());
        return  result;
    }

    @GetMapping(value="staff/post")
    public ListOutput showPostsForStaff() {
        ListOutput result = new ListOutput();
        result.setListResult(postService.findAll());
        return  result;
    }

    @PostMapping(value="staff/post")
    public PostDTO createPost(@RequestBody PostDTO model){
        return (PostDTO) postService.save(model);
    }


    @PutMapping(value="staff/post/{id}")
    public PostDTO updatePost(@RequestBody PostDTO model,@PathVariable("id") long id){
        model.setId(id);
        return (PostDTO) postService.save(model);
    }

    @DeleteMapping(value = "staff/post/{id}")
    public void changeStatus(@RequestBody @PathVariable("id") Long id){
        postService.changeStatus(id);
    }

}
