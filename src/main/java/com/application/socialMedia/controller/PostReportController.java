package com.application.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.application.socialMedia.model.PostReport;
import com.application.socialMedia.service.impl.PostReportService;


@Controller
@RequestMapping(path = "report_post")
public class PostReportController {
    
    @Autowired
    PostReportService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("report")
    public ResponseEntity<PostReport> addReport(@RequestBody PostReport post) {
        return new ResponseEntity<PostReport>(service.report(post), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CONTENT_MODERATOR')")
    @GetMapping("view_report")
    public ResponseEntity<List<PostReport>> viewReport() {
        return new ResponseEntity<List<PostReport>>(service.viewReport(), HttpStatus.OK);  
    }

    @PreAuthorize("hasRole('CONTENT_MODERATOR')")
    @DeleteMapping("delete_report")
    public ResponseEntity<Boolean> deleteReport(@RequestParam String postId) {
        return new ResponseEntity<Boolean>(service.deleteReport(postId), HttpStatus.OK);  
    }

    @PreAuthorize("hasRole('CONTENT_MODERATOR')")
    @DeleteMapping("delete_report_post")
    public ResponseEntity<Boolean> deleteReportAndPost(@RequestParam String postId) {
        return new ResponseEntity<Boolean>(service.deleteReportAndPost(postId), HttpStatus.OK);  
    }

}
