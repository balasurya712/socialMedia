package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.PageReport;

@Repository
public interface PageReportRepository extends MongoRepository<PageReport,String>{
    
}