package com.application.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.socialMedia.model.Post;
import com.application.socialMedia.model.response_model.PostResponse;
import com.application.socialMedia.service.impl.PageService;
import com.application.socialMedia.service.impl.PostService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path = "post")
public class PostController {

    @Autowired
    PostService service;

    @Autowired
    PageService pageService;
    @PostMapping("save")
    public ResponseEntity<Post> postMethodName(@ModelAttribute PostResponse postResponse) {

        return new ResponseEntity<>(service.save(postResponse),HttpStatus.OK);
    }
    
    @GetMapping("recommended_post")
    public ResponseEntity<List<Post>> getMethodName() {
        return new ResponseEntity<List<Post>>(service.recommendedPost(),HttpStatus.OK);
    }

    @GetMapping("f_post")
    public ResponseEntity<List<Post>> getFollowingPost(@RequestParam String id) {
        return new ResponseEntity<List<Post>>(service.getFollowingPost(id),HttpStatus.OK);
    }
    
    @DeleteMapping("delete_post")
    public ResponseEntity<?> deletePost(@RequestParam String id) {
        return new ResponseEntity<>(service.delete(id),HttpStatus.OK);
    }
}
