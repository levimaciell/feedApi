package com.grupofive.demo.post.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.grupofive.demo.post.dto.PostCreationDto;
import com.grupofive.demo.post.dto.PostUpdateDto;
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

    @Test
    @DirtiesContext
    void testUpdatePost(){

        //Checking if context is dirty
        assertEquals(0L,tRepository.count(), "Context could be dirty. NumberOfPosts: " + tRepository.count());
        
        //Create a new post to db, and check if it is there
        testService.createPost(new PostCreationDto("This is a message BEFORE update"));
        Long count = tRepository.count();
        assertEquals(1L,count, "There should be only one Post in db, but there is actually " + count);

        //Retrieve post and see if it is there
        Post testPost = testService.retrievePost(1L);
        assertEquals("This is a message BEFORE update", testPost.getMessage());

        //Trying to update with a null changeId non existent changeId
        PostUpdateDto nullId = new PostUpdateDto(null, "This is a message AFTER update");
        PostUpdateDto notExistentId = new PostUpdateDto(56L, "This is a message AFTER update");

        PostServiceException ex = assertThrows(PostServiceException.class, () -> testService.updatePost(nullId));
        assertEquals("Given id is null!", ex.getMessage());
        ex = assertThrows(PostServiceException.class, () -> testService.updatePost(notExistentId));
        assertEquals("Post to update not found", ex.getMessage());


        //Trying to update with a null String or a empty string
        PostUpdateDto nullMsg = new PostUpdateDto(1L, null);
        PostUpdateDto blankMsg = new PostUpdateDto(1L, "");

        ex = assertThrows(PostServiceException.class, () -> testService.updatePost(nullMsg));
        assertEquals("New message to be changed is either null or empty", ex.getMessage());
        ex = assertThrows(PostServiceException.class, () -> testService.updatePost(blankMsg));
        assertEquals("New message to be changed is either null or empty", ex.getMessage());

        //Try to update and see if id is same and message is changed
        PostUpdateDto p1 = new PostUpdateDto(1L, "This is a message AFTER update");
        testService.updatePost(p1);

        assertEquals(1, tRepository.count(), "After update, there should be only one post, but "
        + tRepository.count() + " was found");

        Post testPost2 = testService.retrievePost(1L);

        assertEquals(testPost.getId(), testPost2.getId(), "Id should stay the same after update.\n"
        + "Actual: " + testPost2.getId() + "\nExpected: " + testPost.getId());

        assertNotEquals(testPost.getMessage(), testPost2.getMessage(), "Message should be different after update.\n"
        + "Actual: " + testPost2.getMessage() + "\nExpected: " + testPost.getMessage());

        assertEquals("This is a message AFTER update", testPost2.getMessage());
    }

    @Test
    @DirtiesContext
    void testDeletePost(){
        //Checking if context is dirty
        assertEquals(0L,tRepository.count(), "Context could be dirty. NumberOfPosts: " + tRepository.count());
        
        //Create a new post to db, and check if it is there
        testService.createPost(new PostCreationDto("This is a message For deletion"));
        Long count = tRepository.count();
        assertEquals(1L,count, "There should be only one Post in db, but there is actually " + count);

        //Check if it allows to delete with a null id
        PostServiceException e = assertThrows(PostServiceException.class, () -> testService.deletePost(null));
        assertEquals("Id is null", e.getMessage());

        //Check if it throws a exception when post is not in db
        e = assertThrows(PostServiceException.class,() -> testService.deletePost(222L));
        assertEquals("Post not found for deletion", e.getMessage());

        //Check if the post was really deleted from db
        long postCount = tRepository.count();
        assertEquals(1, postCount);
        testService.deletePost(1L);
        postCount = tRepository.count();
        assertEquals(0, postCount);



    }

}
