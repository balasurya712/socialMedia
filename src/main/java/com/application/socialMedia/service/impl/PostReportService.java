package com.application.socialMedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.dao.PostReportDao;
import com.application.socialMedia.model.PostReport;
import com.application.socialMedia.repository.PostReportRepository;
import com.application.socialMedia.repository.PostRepository;
import com.application.socialMedia.service.PostReportServiceInterface;

@Service
public class PostReportService implements PostReportServiceInterface{

    @Autowired
    PostReportRepository repo;

    @Autowired
    PostRepository postRepo;

    @Autowired
    PostReportDao postReportDao;
    
    @Override
    public PostReport report(PostReport post) {
        return repo.save(post);
    }

    @Override
    public List<PostReport> viewReport() {
        return repo.findAll();
    }
    
    public Boolean deleteReport(String postId){
        postReportDao.deleteByPostId(postId);
        return true;
    }

    public Boolean deleteReportAndPost(String postId){
        postReportDao.deleteByPostId(postId);
        postRepo.deleteById(postId);
        return true;
    }
}
