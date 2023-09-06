package com.grupofive.demo.post.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.services.PostService;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    

    @Autowired
    private PostService service;

    @PostMapping
    public void createPost(@RequestBody PostCreationDto post){
        service.createPost(post);
    }

    @GetMapping(value = "/{id}")
    public Post retrievePost(@PathVariable Long id){
        return service.retrievePost(id);
    }

}
