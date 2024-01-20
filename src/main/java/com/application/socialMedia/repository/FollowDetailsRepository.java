package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.FollowDetail;

@Repository
public interface FollowDetailsRepository extends MongoRepository<FollowDetail,String>{
    
}
