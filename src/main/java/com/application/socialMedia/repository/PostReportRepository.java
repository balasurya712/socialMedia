package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.PostReport;

@Repository
public interface PostReportRepository extends MongoRepository<PostReport,String>{
    
}