package com.grupofive.demo.feed.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.entities.Post;
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

    public Feed addPostInFeed(String id, PostCreationDto post) {
        Feed feed = repository.findById(id).get();

//        service.createPost(post);

        Post newPost = new Post();
        newPost.setMessage(post.getPostMessage());

        feed.addPostInFeedList(newPost);

        service.createPost(post);

        return feed;
    }

    // @Transactional
   // public Feed addPostInFeed(Feed feedUpdate, PostCreationDto post) {
       // if(feedUpdate.getFeedId() == null || feedUpdate.getFeedId().isBlank())
     //       throw new FeedServiceException("Given id is either null or blank", HttpStatus.BAD_REQUEST);
    //    Feed oldFeed = repository.findById(feedUpdate.getFeedId()).get();

  //      service.createPost(post);

//        Post newPost = new Post();
//        newPost.setMessage(post.getPostMessage());

  //      oldFeed.addPostInFeedList(newPost);

//        PostCreationDto post = new PostCreationDto();
//        post.setPostMessage(message);
//        service.createPost(post);

//        Post newPost = new Post();
//        newPost.setMessage(post.getPostMessage());

//        newPost.setMessage(post.getPostMessage());

//        oldFeed.addPostInFeedList(newPost);

 //       return oldFeed;


   // }
}
