package com.application.socialMedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.dao.FollowDao;
import com.application.socialMedia.dao.PageDao;
import com.application.socialMedia.model.FollowDetail;
import com.application.socialMedia.repository.FollowDetailsRepository;
import com.application.socialMedia.service.FollowDetailsInterface;

@Service
public class FollowDetailsService implements FollowDetailsInterface {

    @Autowired
    FollowDetailsRepository repo;

    @Autowired
    PageDao pageDao;
    @Autowired
    FollowDao followDao;

    @Override
    public boolean addFollowing(FollowDetail followDetail) {
        pageDao.incFollowerCount(followDetail.getFollowingId().get_id());
        pageDao.incFollowingCount(followDetail.getFollowerId().get_id());
        repo.save(followDetail);
        return true;
    }

    @Override
    public boolean removeFollowing(FollowDetail followDetail) {
        System.out.println(followDetail.getFollowerId()+"   "+followDetail.getFollowingId());
        pageDao.decFollowerCount(followDetail.getFollowingId().get_id());
        pageDao.decFollowingCount(followDetail.getFollowerId().get_id());
        followDao.deleteFollowDetails(followDetail);
        return true;
    }
}
