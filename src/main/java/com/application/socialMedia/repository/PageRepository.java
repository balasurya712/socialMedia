package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Page;

@Repository
public interface PageRepository extends MongoRepository<Page,String> {
    
    public boolean existsByName(String name);
}