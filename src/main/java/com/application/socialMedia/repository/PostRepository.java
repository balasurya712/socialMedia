package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
    
}