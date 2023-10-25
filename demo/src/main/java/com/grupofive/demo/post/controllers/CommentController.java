package com.grupofive.demo.post.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupofive.demo.post.dto.CommentDto.PostCommentDto;
import com.grupofive.demo.post.dto.CommentDto.PostCommentUpdateDto;
import com.grupofive.demo.post.entities.Comment;
import com.grupofive.demo.post.services.CommentService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/posts/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    public void createComment(@RequestBody PostCommentDto comment){
        service.createComment(comment);
    }

    @GetMapping
    public List<Comment> retrieveAllComments(){
        return service.retrieveAllComments();
    }
    
    @GetMapping("/{id}")
    public Comment retrieveComments(@PathVariable String id){
        return service.retrieveComment(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable String id){
        service.deleteComment(id);
    }

    @PutMapping
    public Comment updateComment(@RequestBody PostCommentUpdateDto comment){
        return service.updateComment(comment);
    }
}
