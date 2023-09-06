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
    PostService testService;

    @Test
    @DirtiesContext
    void testCreatePost() {
        //PostService testService = new PostService();
        PostRepository tRepository = testService.getRepository();
        
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
    void testRetrievePost(){

    }





}
