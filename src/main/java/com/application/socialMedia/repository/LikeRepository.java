package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Like;


@Repository
public interface LikeRepository extends MongoRepository<Like,String>{
}
