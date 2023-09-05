package com.grupofive.demo.post.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.grupofive.demo.post.exceptions.PostServiceException;

public class PostServiceTest {

    private PostService testService = new PostService();

    @Test
    void testCreatePost() {
        
        //Test if throws exception with empty message or null
        assertThrows(PostServiceException.class, () -> testService.createPost(null));
        assertThrows(PostServiceException.class, () -> testService.createPost(""));

        //Test if it has been saved in db
        //Test if it has an id
    }
}
