package com.application.socialMedia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.socialMedia.model.PageReport;
import com.application.socialMedia.service.impl.PageReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path = "report_page")
public class PageReportController {
    
    @Autowired
    PageReportService service;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("report")
    public ResponseEntity<PageReport> addReport(@RequestBody PageReport page) {
        return new ResponseEntity<PageReport>(service.report(page), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CONTENT_MODERATOR')")
    @GetMapping("view_report")
    public ResponseEntity<List<PageReport>> viewReport() {
        return new ResponseEntity<List<PageReport>>(service.viewReport(), HttpStatus.OK);  
    }
    
    
}
