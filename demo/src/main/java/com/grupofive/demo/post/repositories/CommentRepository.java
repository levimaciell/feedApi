package com.grupofive.demo.post.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupofive.demo.post.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String>{
    
}
