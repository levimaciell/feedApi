package com.grupofive.demo.post.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupofive.demo.post.entities.Post;
import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, UUID>{
    List<Post> findByMessage(String message);
}
