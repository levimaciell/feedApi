package com.grupofive.demo.feed.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupofive.demo.feed.entities.Feed;
import com.grupofive.demo.feed.services.FeedService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/api/feeds")
public class FeedController {
    
    @Autowired
    FeedService service;

    @PostMapping
    public void createFeed(){
        service.createFeed();
    }

    @GetMapping(value = "/{id}")
    public Feed retrieveFeed(@PathVariable String id){
        return service.readPost(id);
    }
    
    @DeleteMapping(value = "/{id}")
    public void deleteFeed(@PathVariable String id){
        service.deleteFeed(id);
    }

}
