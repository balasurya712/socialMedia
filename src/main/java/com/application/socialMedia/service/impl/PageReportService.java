package com.application.socialMedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.dao.PageDeleteDao;
import com.application.socialMedia.dao.PageReportDao;
import com.application.socialMedia.dao.PostDao;
import com.application.socialMedia.model.PageReport;
import com.application.socialMedia.repository.PageReportRepository;
import com.application.socialMedia.repository.PageRepository;
import com.application.socialMedia.repository.UserRepository;
import com.application.socialMedia.service.PageReportServiceInterface;

@Service
public class PageReportService implements PageReportServiceInterface{

    @Autowired
    PageReportRepository repo;

    @Autowired
    PageReportDao pageReportDao;
    
    @Autowired
    PostDao postDao;

    @Autowired
    PageRepository pageRepo;

    @Autowired 
    UserRepository userRepo;

    @Autowired
    PageDeleteDao pageDeleteDao;
    
    @Override
    public List<PageReport> viewReport() {
        return repo.findAll();
    }

    @Override
    public PageReport report(PageReport page) {
        return repo.save(page);
    }

    @Override
    public Boolean deleteReport(String pageId) {
        pageReportDao.deleteByPageId(pageId);
        return true;
    }

    @Override
    public Boolean deleteReportAndPage(String pageId) {
        pageReportDao.deleteByPageId(pageId);
        userRepo.deleteByName(pageRepo.findById(pageId).get().getName());
        pageDeleteDao.deletePageAndItsRelevent(pageId);
        // pageRepo.deleteById(pageId);
        return true;
    }
    
}
