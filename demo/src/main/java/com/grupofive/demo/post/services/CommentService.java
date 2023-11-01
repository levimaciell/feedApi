package com.grupofive.demo.post.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grupofive.demo.post.dto.CommentDto.PostCommentDto;
import com.grupofive.demo.post.dto.CommentDto.PostCommentUpdateDto;
import com.grupofive.demo.post.entities.Comment;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.exceptions.CommentServiceException;
import com.grupofive.demo.post.repositories.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private PostService postService;

    @Transactional
    public void createComment(PostCommentDto comment){

        if(comment == null || comment.getPostID().isBlank()) {
            throw new CommentServiceException("PostID is not informed", HttpStatus.BAD_REQUEST);
        }
        if(comment.getComment().isBlank()) {
            throw new CommentServiceException("The comment is blank", HttpStatus.BAD_REQUEST);
        }

        Post post = postService.retrievePost(comment.getPostID());

        Comment addComment = new Comment(comment.getComment(), post);
        repository.save(addComment);

    }

    public Comment retrieveComment(String commentId){

        if(commentId == null || commentId.isBlank())
            throw new CommentServiceException("The commentId is not informed", HttpStatus.BAD_REQUEST);

        return repository.findById(commentId).orElseThrow(() -> 
        new CommentServiceException("Comment not found", HttpStatus.NOT_FOUND));

    }

    public List<Comment> retrieveAllComments(){
        return repository.findAll();
    }

    @Transactional
    public void deleteComment(String commentId){

        if(commentId == null || commentId.isBlank())
            throw new CommentServiceException("The commentId is not informed", HttpStatus.BAD_REQUEST);

        if(repository.existsById(commentId))
            repository.deleteById(commentId);
        else
            throw new CommentServiceException("Comment not found", HttpStatus.NOT_FOUND);
        
    }

    @Transactional
    public Comment updateComment(PostCommentUpdateDto update){

        if(update == null) throw new CommentServiceException("Update info is null!", HttpStatus.BAD_REQUEST);
        if(update.getCommentId().isBlank()) throw new CommentServiceException("The comment id is blank!", HttpStatus.BAD_REQUEST);
        if(update.getMessage().isBlank()) throw new CommentServiceException("The message is blank!", HttpStatus.BAD_REQUEST);

        Comment comment = repository.findById(update.getCommentId()).orElseThrow(()->
        new CommentServiceException("The comment was not found", HttpStatus.NOT_FOUND));

        comment.setComment(update.getMessage());
        repository.save(comment);
        return comment;

    }
    
}
