package com.grupofive.demo.post.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupofive.demo.post.dto.PostDto.PostCreationDto;
import com.grupofive.demo.post.dto.PostDto.PostUpdateDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.services.PostService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    

    @Autowired
    private PostService service;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void createPost(@RequestBody PostCreationDto post, @RequestHeader("Authorization") String header){

        String token = header.replace("Bearer ", "");
        
        service.createPost(post, token);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/{id}")
    public Post retrievePost(@PathVariable String id){

        return service.retrievePost(id);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping
    public Post updatePost(@RequestBody PostUpdateDto postUpdate){

        return service.updatePost(postUpdate);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping(value = "/{id}")
    public void deletePost(@PathVariable String id){

        service.deletePost(id);
    }

}