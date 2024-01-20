package com.application.socialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.socialMedia.model.Comment;
import com.application.socialMedia.service.impl.CommentService;

@Controller
@RequestMapping(path = "comment")
public class CommentController {
    
    @Autowired
    CommentService service;


    @PostMapping("add")
    public ResponseEntity<Comment> addLike(@RequestBody Comment Comment) {
        return new ResponseEntity<>(service.save(Comment),HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public ResponseEntity<?> removeComment(@RequestParam String id) {
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
    
    @GetMapping("get_comments")
    public ResponseEntity<?> getMethodName(@RequestParam String id) {
        return new ResponseEntity<>(service.getComments(id),HttpStatus.OK);
    }


}
