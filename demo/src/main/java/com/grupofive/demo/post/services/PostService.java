package com.grupofive.demo.post.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.exceptions.PostServiceException;
import com.grupofive.demo.post.repositories.PostRepository;

public class PostService {

    @Autowired
    private PostRepository repository;
    

    public void createPost(String postMessage){
        if(postMessage == null || postMessage.isEmpty())
            throw new PostServiceException("Post Message is empty!");

        //Create empty post. Let spring take care of the id
        Post post = new Post();
        post.setMessage(postMessage);

        repository.save(post);
    }
}
