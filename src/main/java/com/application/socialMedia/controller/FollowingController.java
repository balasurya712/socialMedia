package com.application.socialMedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.socialMedia.model.FollowDetail;
import com.application.socialMedia.model.FollowRequest;
import com.application.socialMedia.service.impl.FollowDetailsService;
import com.application.socialMedia.service.impl.FollowRequestService;
import com.application.socialMedia.service.impl.PageService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping(path = "follow")
@PreAuthorize("hasRole('USER')")
public class FollowingController {

    @Autowired
    FollowDetailsService followService;
    @Autowired
    FollowRequestService requestService;
    @Autowired
    PageService pageService;
    
    @PostMapping("/request")
    public ResponseEntity<String> followRequs(@RequestBody FollowDetail follow) {
        if(pageService.isAccountPrivate(follow.getFollowingId().get_id())){
            requestService.addRequest(follow.getFollowerId().get_id(), follow.getFollowingId().get_id());
            return new ResponseEntity<String>("Requested To Follow ", HttpStatus.OK);
        }
        else{
            followService.addFollowing(follow);
        }
        return new ResponseEntity<String>("followed", HttpStatus.OK);
    }
    
    @GetMapping("/view_request")
    public ResponseEntity<FollowRequest> viewRequs(@RequestParam String id) {
        return new ResponseEntity<FollowRequest>(requestService.viewRequest(id), HttpStatus.OK);
    }
    
    @PostMapping("/accept_request")
    public ResponseEntity<String> acceptRequs(@RequestBody FollowDetail followDetail) {
        followService.addFollowing(followDetail);
        requestService.removeRequest(followDetail.getFollowerId().get_id(), followDetail.getFollowingId().get_id());
        return new ResponseEntity<String>("request accepted", HttpStatus.OK);
    }

    @DeleteMapping("/delete_following")
    public ResponseEntity<String> deleteFollowing(@RequestBody FollowDetail followDetail) {
        followService.removeFollowing(followDetail);
        return new ResponseEntity<String>("Unfollowed successful", HttpStatus.OK);
    }

    @DeleteMapping("/delete_request")
    public ResponseEntity<String> deleteRequest(@RequestBody FollowDetail followDetail) {
        requestService.removeRequest(followDetail.getFollowerId().get_id(),followDetail.getFollowingId().get_id());
        return new ResponseEntity<String>("request canceled", HttpStatus.OK);
    }
    
}
