package com.grupofive.demo.post.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
        assertThrows(PostServiceException.class, () -> testService.createPost(null));
        assertThrows(PostServiceException.class, () -> testService.createPost(""));

        //Test if it has been saved in db
        String postMessage = "Test post";
        testService.createPost(postMessage);
        List<Post> list = tRepository.findByMessage(postMessage);
        
        assertEquals(1, list.size(), "List should only have one element, since it was only sucessfully inserted once");
        assertEquals(postMessage, list.get(0).getMessage(), "The first and only element does not match the given string");

        //Test if it has an id
        assertNotNull(list.get(0), "The element does not have an id.\nUUID: " + list.get(0).getId());
    }
}
