package com.application.socialMedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.model.PageReport;
import com.application.socialMedia.repository.PageReportRepository;
import com.application.socialMedia.service.PageReportServiceInterface;

@Service
public class PageReportService implements PageReportServiceInterface{

    @Autowired
    PageReportRepository repo;
    
    @Override
    public List<PageReport> viewReport() {
        return repo.findAll();
    }

    @Override
    public PageReport report(PageReport page) {
        return repo.save(page);
    }
    
}
