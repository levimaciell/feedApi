package com.grupofive.demo.feed.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupofive.demo.feed.entities.Feed;
import com.grupofive.demo.feed.repositories.FeedRepository;
import com.grupofive.demo.post.services.PostService;

@Service
public class FeedService {
    
    @Autowired
    FeedRepository repository;

    @Autowired
    PostService service;

    //maybe associate feed and post to a user, so we can see who made the post
    public void createFeed(){
        repository.save(new Feed());
    }

    public Feed readPost(String id){
        return repository.findById(id).get();
    }

    //Is it needed?
    /*public void updatePost(){

    }*/

    public void deleteFeed(String id){
        repository.deleteById(id);
    }

}
