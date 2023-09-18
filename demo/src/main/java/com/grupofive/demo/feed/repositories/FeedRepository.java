package com.grupofive.demo.feed.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupofive.demo.feed.entities.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long>{
    
}
