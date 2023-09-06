package com.grupofive.demo.post.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.exceptions.PostServiceException;
import com.grupofive.demo.post.repositories.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    

    public void createPost(PostCreationDto post){
        if(post.getPostMessage() == null || post.getPostMessage().isEmpty())
            throw new PostServiceException("Post Message is empty!");

        //Create empty post. Let spring take care of the id
        Post postCreate = new Post();
        postCreate.setMessage(post.getPostMessage());

        repository.save(postCreate);
    }

    public Post retrievePost(UUID id){
        return repository.findById(id).get();
    }

}
