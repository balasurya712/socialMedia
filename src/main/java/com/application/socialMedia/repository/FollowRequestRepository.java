package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.FollowRequest;
import java.util.Optional;


@Repository
public interface FollowRequestRepository extends MongoRepository<FollowRequest,String>{
    Optional<FollowRequest> findByPageId(String pageId);
}
