package com.grupofive.demo.post.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupofive.demo.post.dto.PostCommentDto;
import com.grupofive.demo.post.dto.PostCommentUpdateDto;
import com.grupofive.demo.post.entities.Comment;
import com.grupofive.demo.post.repositories.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
    //TODO: Check the idea of having a map, to hide the real id in the database of frontend

    @Autowired
    private CommentRepository repository;

    @Transactional
    public void createComment(PostCommentDto comment){

        //TODO:Check for errors
        Comment addComment = new Comment(comment.getPostID(), comment.getComment());
        repository.save(addComment);

    }

    public PostCommentDto retrieveComment(String commentId){
        //TODO:Check for errors

        Comment comment = repository.findById(commentId).get();
        return new PostCommentDto(comment.getPostId(), comment.getComment());
    }

    public List<PostCommentDto> retrieveAllComments(){

        //TODO:Check for errors
        //TODO:Include id in db for proper deletion
        return repository.findAll().stream().map((x)-> new PostCommentDto(x.getPostId(), x.getComment())).toList();
    }

    public void deleteComment(String commentId){
        //TODO:Check for errors
        repository.deleteById(commentId);
        
    }
    public Comment updateComment(PostCommentUpdateDto update){
        //TODO:Check for errors

        Comment comment = repository.findById(update.getCommentId()).get();
        comment.setComment(update.getMessage());
        repository.save(comment);
        return comment;

    }
    
}
