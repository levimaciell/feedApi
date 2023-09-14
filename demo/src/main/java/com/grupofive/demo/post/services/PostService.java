package com.grupofive.demo.post.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.dto.PostUpdateDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.exceptions.PostServiceException;
import com.grupofive.demo.post.repositories.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    
    @Transactional
    public void createPost(PostCreationDto post){
        if(post == null || post.getPostMessage().isBlank()) {
            throw new PostServiceException("Post Message is empty!", HttpStatus.BAD_REQUEST);
        }

        //Create empty post. Let spring take care of the id
        Post postCreate = new Post();
        postCreate.setMessage(post.getPostMessage());

        repository.save(postCreate);
    }

    public Post retrievePost(String id){
        if(id == null)
            throw new PostServiceException("Given id is null!", HttpStatus.BAD_REQUEST);
        try{
            Post post = repository.findById(id);
            return post;
        }
        catch(NoSuchElementException e){
            throw new PostServiceException("Post not found in database", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void updatePost(PostUpdateDto postUpdate){
        if(postUpdate.getChangeId() == null)
            throw new PostServiceException("Given id is null!", HttpStatus.BAD_REQUEST);
        
        if(!repository.existsById(postUpdate.getChangeId()))
            throw new PostServiceException("Post to update not found", HttpStatus.NOT_FOUND);

        if(postUpdate.getChangeMessage() == null || postUpdate.getChangeMessage().isBlank())
            throw new PostServiceException("New message to be changed is either null or empty", HttpStatus.BAD_REQUEST);

        Post post = repository.getReferenceById(postUpdate.getChangeId());
        post.setMessage(postUpdate.getChangeMessage());
        repository.save(post);
    }
    
    @Transactional
    public void deletePost(Long id){

        if(id == null)
            throw new PostServiceException("Id is null", HttpStatus.BAD_REQUEST);

        if(!repository.existsById(id))
            throw new PostServiceException("Post not found for deletion", HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    
}
