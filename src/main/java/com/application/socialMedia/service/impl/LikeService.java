package com.application.socialMedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.dao.CommentDao;
import com.application.socialMedia.dao.LikeDao;
import com.application.socialMedia.dao.PostDao;
import com.application.socialMedia.model.Like;
import com.application.socialMedia.repository.LikeRepository;
import com.application.socialMedia.service.LikeServiceInterface;

@Service
public class LikeService implements LikeServiceInterface {

    @Autowired
    LikeRepository repo;

    @Autowired
    PostDao postDao;

    @Autowired
    LikeDao likeDao;

    @Autowired
    CommentDao commentDao;

    @Override
    public Like save(Like like) {
        if(like.getType().equals("post"))
        postDao.incLikeCount(like.getPostOrCommentId());
        if(like.getType().equals("comment"))
        commentDao.incLikeCount(like.getPostOrCommentId());
        return repo.save(like);
    }

    @Override
    public boolean delete(Like like) {
        if(like.getType().equals("post"))
        postDao.decLikeCount(like.getPostOrCommentId());
        if(like.getType().equals("comment"))
        commentDao.decLikeCount(like.getPostOrCommentId());
        likeDao.deleteLike(like);
        return true;
    }

    @Override
    public List<Like> getLikes(String postOrCommentId) {
        return likeDao.getLike(postOrCommentId);
    }
    
}
