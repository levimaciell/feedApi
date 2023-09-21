package com.grupofive.demo.feed.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grupofive.demo.feed.entities.Feed;
import com.grupofive.demo.feed.exceptions.FeedServiceException;
import com.grupofive.demo.feed.repositories.FeedRepository;
import com.grupofive.demo.post.services.PostService;

import jakarta.transaction.Transactional;

@Service
public class FeedService {


    @Autowired
    FeedRepository repository;

    @Autowired
    PostService service;

    //maybe associate feed and post to a user, so we can see who made the post
    @Transactional
    public void createFeed(){
        repository.save(new Feed());
    }

    public List<Feed> getAllFeeds() {
        var listaFeeds = repository.findAll().stream().toList();
        return listaFeeds;
    }

    public Feed readFeed(String id){

        //Checking if id is null or blank
        if(id == null || id.isBlank())
            throw new FeedServiceException("Given id is either null or blank", HttpStatus.BAD_REQUEST);

        try{
            //If throws NoSuchElementExecption, feed is not found
            Feed feed = repository.findById(id).get();
            return feed;
        }
        catch(NoSuchElementException e){
            throw new FeedServiceException("Post with id '" + id + "' not found", HttpStatus.NOT_FOUND);
        }
    }

    //Is it needed?
    /*public void updatePost(){

    }*/

    @Transactional
    public void deleteFeed(String id){

        //Checking if id is null or blank
        if(id == null || id.isBlank())
            throw new FeedServiceException("Given id is either null or blank", HttpStatus.BAD_REQUEST);

        //Checking if feed is in db
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
        else{
            throw new FeedServiceException("Post with id '" + id + "' not found", HttpStatus.NOT_FOUND);
        }
    }

}
