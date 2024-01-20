package com.application.socialMedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.dao.FollowDao;
import com.application.socialMedia.model.FollowRequest;
import com.application.socialMedia.repository.FollowRequestRepository;
import com.application.socialMedia.service.FollowRequestInterface;

@Service
public class FollowRequestService implements FollowRequestInterface {

    @Autowired
    FollowRequestRepository repo;

    @Autowired
    FollowDao followDao;
    @Override
    public String addRequest(String followerId, String followingId) {
        followDao.addFollowerToFollowerList(followerId, followingId);
        return "Requested";
    }

    @Override
    public String removeRequest(String followerId, String followingId) {
        followDao.removeFollowerToFollowerList(followerId, followingId);
        return "Removed";
    }

    @Override
    public FollowRequest viewRequest(String id) {
        return repo.findByPageId(id).get();
    }

    
    
}
