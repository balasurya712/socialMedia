package com.application.socialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.socialMedia.model.Like;
import com.application.socialMedia.service.impl.LikeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(path="like")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
public class LikeController {
    
    @Autowired
    LikeService service;


    @PostMapping("add")
    public ResponseEntity<Like> addLike(@RequestBody Like like) {
        return new ResponseEntity<>(service.save(like),HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> removeLike(@RequestBody Like like) {
        return new ResponseEntity<>(service.delete(like),HttpStatus.OK);
    }
    
    @GetMapping("get_likes")
    public ResponseEntity<?> getMethodName(@RequestParam String id) {
        return new ResponseEntity<>(service.getLikes(id),HttpStatus.OK);
    }


}
