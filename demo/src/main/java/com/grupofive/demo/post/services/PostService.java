package com.grupofive.demo.post.services;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
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
        if(post == null || post.getPostMessage().isBlank())
            throw new PostServiceException("Post Message is empty!");

        //Create empty post. Let spring take care of the id
        Post postCreate = new Post();
        postCreate.setMessage(post.getPostMessage());

        repository.save(postCreate);
    }

    public Post retrievePost(Long id){
        if(id == null)
            throw new PostServiceException("Given id is null!");
        try{
            return repository.findById(id).get();
        }
        catch(NoSuchElementException e){
            throw new PostServiceException("Post not found in database");
        }
    }

    @Transactional
    public void updatePost(PostUpdateDto postUpdate){
        if(postUpdate.getChangeId() == null)
            throw new PostServiceException("Given id is null!");
        
        if(!repository.existsById(postUpdate.getChangeId()))
            throw new PostServiceException("Post to update not found");

        if(postUpdate.getChangeMessage() == null || postUpdate.getChangeMessage().isBlank())
            throw new PostServiceException("New message to be changed is either null or empty");

        Post post = repository.getReferenceById(postUpdate.getChangeId());
        post.setMessage(postUpdate.getChangeMessage());
        repository.save(post);
    }
    
    @Transactional
    public void deletePost(Long id){

        if(id == null)
            throw new PostServiceException("Id is null");

        if(!repository.existsById(id))
            throw new PostServiceException("Post not found for deletion");

        repository.deleteById(id);
    }

    //Test only function
    public PostRepository getRepository(){
        return repository;
    }
}
