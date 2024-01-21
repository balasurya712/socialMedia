package com.application.socialMedia.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.socialMedia.model.User;


@Repository
public interface UserRepository extends MongoRepository<User,String> {
    public Optional<User>  findByEmail(String email);
    public boolean existsByEmail(String email);
    Optional<User> findByName(String name);
    boolean existsByName(String name);
    void deleteByName(String name);
}
