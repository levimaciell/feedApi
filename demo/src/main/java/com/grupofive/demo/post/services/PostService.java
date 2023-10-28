package com.grupofive.demo.post.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.grupofive.demo.User.entities.User;
import com.grupofive.demo.User.repositories.UserRepository;
import com.grupofive.demo.post.dto.PostDto.PostCreationDto;
import com.grupofive.demo.post.dto.PostDto.PostUpdateDto;
import com.grupofive.demo.post.entities.Post;
import com.grupofive.demo.post.exceptions.PostServiceException;
import com.grupofive.demo.post.repositories.PostRepository;
import com.grupofive.demo.security_auth.TokenService;

import jakarta.transaction.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService service;
    
    @Transactional
    public void createPost(PostCreationDto post, String token){

        try{
            
            //Verificar se token é válido
            String subject = service.validateToken(token);

            //Verificar se usuário existe
            //TODO: É necessário isso?
            User user = userRepository.findByUsername(subject);
            if(user == null){
                throw new PostServiceException("Subject not found", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if(post.getPostMessage().isBlank()) {
                throw new PostServiceException("Post Message is empty!", HttpStatus.BAD_REQUEST);
            }

            //Create empty post. Let spring take care of the id
            Post postCreate = new Post();
            postCreate.setMessage(post.getPostMessage());
            postCreate.setUser(user);

            repository.save(postCreate);
        }
        catch(JWTCreationException e){
            throw new PostServiceException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        
    }

    public Post retrievePost(String id){
        if(id == null)
            throw new PostServiceException("Given id is null!", HttpStatus.BAD_REQUEST);

        try{
            
            Post post = repository.findById(id).get();
            return post;
        }
        catch(NoSuchElementException e){
            throw new PostServiceException("Post not found in database", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public Post updatePost(PostUpdateDto postUpdate, String token){

        try{
            //Verificar se token é válido
            String subject = service.validateToken(token);

            //Verificar se usuário existe
            User user = userRepository.findByUsername(subject);
            if(user == null){
                throw new PostServiceException("Subject not found", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(JWTCreationException e){
            throw new PostServiceException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }


        if(postUpdate.getChangeId() == null)
            throw new PostServiceException("Given id is null!", HttpStatus.BAD_REQUEST);
        
        if(!repository.existsById(postUpdate.getChangeId()))
            throw new PostServiceException("Post to update not found", HttpStatus.NOT_FOUND);

        if(postUpdate.getChangeMessage() == null || postUpdate.getChangeMessage().isBlank())
            throw new PostServiceException("New message to be changed is either null or empty", HttpStatus.BAD_REQUEST);

        Optional<Post> optionalPost = repository.findById(postUpdate.getChangeId());
        Post post = optionalPost.get();
        post.setMessage(postUpdate.getChangeMessage());
//        repository.save(post);
        return post;
    }
    
    @Transactional
    public void deletePost(String id, String token){

        try{
            //Verificar se token é válido
            String subject = service.validateToken(token);

            //Verificar se usuário existe
            User user = userRepository.findByUsername(subject);
            if(user == null){
                throw new PostServiceException("Subject not found", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch(JWTCreationException e){
            throw new PostServiceException(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        if(id == null)
            throw new PostServiceException("Id is null", HttpStatus.BAD_REQUEST);

        if(!repository.existsById(id))
            throw new PostServiceException("Post not found for deletion", HttpStatus.NOT_FOUND);

        repository.deleteById(id);
    }

    @Transactional
    public List<Post> getAllPosts() {
        var listPost = repository.findAll().stream().toList();
        return listPost;
    }
}
