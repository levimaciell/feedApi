package com.grupofive.demo.post.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.exceptions.PostServiceException;
import com.grupofive.demo.post.repositories.PostRepository;

@SpringBootTest
public class PostServiceTest { 

    @Autowired
    private PostService testService;
    @Autowired
    private PostRepository tRepository;


    @Test
    @DirtiesContext
    void testCreatePost() {
        //PostService testService = new PostService();
        
        //Test if throws exception with empty message or null
        PostCreationDto testPost = new PostCreationDto("");
        PostCreationDto testPost2 = new PostCreationDto("                        ");
        assertThrows(PostServiceException.class, () -> testService.createPost(null));
        assertThrows(PostServiceException.class, () -> testService.createPost(testPost));
        assertThrows(PostServiceException.class, () -> testService.createPost(testPost2));


        //Test if it has been saved in db
        PostCreationDto postMessage = new PostCreationDto("Test post");
        testService.createPost(postMessage);
        List<Post> list = tRepository.findAll();
        
        assertEquals(1, list.size(), "List/Db should only have one element, since it was only sucessfully inserted once");
        assertEquals(postMessage.getPostMessage(), list.get(0).getMessage(), "The first and only element does not match the given string");

        //Test if it has an id
        assertNotNull(list.get(0), "The element does not have an id.\nUUID: " + list.get(0).getId());
    }

    @Test
    @DirtiesContext
    void testRetrievePost(){

        //Checking if context is dirty
        assertEquals(0L,tRepository.count(), "Context could be dirty. NumberOfPosts: " + tRepository.count());
        
        //Create a new post to db, and check if it is there
        testService.createPost(new PostCreationDto("Test post"));
        Long count = tRepository.count();
        assertEquals(1L,count, "There should be only one Post in db, but there is actually " + count);

        //Retrieve post from service, and see if the message matches the one given above
        Post retrieved = testService.retrievePost(1L);
        assertEquals("Test post", retrieved.getMessage());

        //Retrieve post with null id
        PostServiceException ex1 = assertThrows(PostServiceException.class, () -> testService.retrievePost(null));
        assertEquals("Given id is null!", ex1.getMessage());

        //Retrieve non existent post in db. Check if throws PostServiceException
        PostServiceException ex2 = assertThrows(PostServiceException.class, () -> testService.retrievePost(90L));
        assertEquals("Post not found in database", ex2.getMessage());
    }





}
