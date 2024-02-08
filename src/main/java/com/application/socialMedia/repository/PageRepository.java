package com.application.socialMedia.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.Page;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;


@Repository
public interface PageRepository extends MongoRepository<Page,String> {
    
    public boolean existsByName(String name);
    public Optional<Page> findByName(String name);
}