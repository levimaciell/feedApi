package com.grupofive.demo.feed.controllers;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupofive.demo.feed.entities.Feed;
import com.grupofive.demo.feed.services.FeedService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/feeds")
public class FeedController {
    
    @Autowired
    FeedService service;

    @Autowired
    PostService servicePost;

    @PostMapping
    public void createFeed(){
        service.createFeed();
    }

    @GetMapping
    public List<Feed> getAllFeeds() {
        return service.getAllFeeds();
    }

    @PutMapping(value = "/{id}")
    public Feed addPostInFeed(@PathVariable String id, @RequestBody PostCreationDto post) {
        return service.addPostInFeed(id, post);
    }
    //public Feed addPostInFeed(@RequestBody Feed feedUpdate, @RequestBody PostCreationDto post) {
  //      return service.addPostInFeed(feedUpdate, post);
//    }

    @GetMapping(value = "/{id}")
    public Feed retrieveFeed(@PathVariable String id){
        return service.readFeed(id);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteFeed(@PathVariable String id){
        service.deleteFeed(id);
    }

}
