package com.grupofive.demo.post.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.dto.PostUpdateDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.services.PostService;

import java.util.List;

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
    public Post retrievePost(@PathVariable String id){
        return service.retrievePost(id);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @PutMapping
    public void updatePost(@RequestBody PostUpdateDto postUpdate){
        service.updatePost(postUpdate);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable String id){
        service.deletePost(id);
    }

}
