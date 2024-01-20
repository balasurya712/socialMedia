package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String>{
    
}
