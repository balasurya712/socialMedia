package com.application.socialMedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.socialMedia.dao.CommentDao;
import com.application.socialMedia.dao.PostDao;
import com.application.socialMedia.model.Comment;
import com.application.socialMedia.repository.CommentRepository;
import com.application.socialMedia.service.CommentServiceInterface;

@Service
public class CommentService implements CommentServiceInterface {

    @Autowired
    CommentRepository repo;

    @Autowired
    CommentDao commentDao;

    @Autowired
    PostDao postDao;

    @Override
    public Comment save(Comment comment) {
        if (comment.getType().equals("post")) {
            System.out.println("hi    " + comment.getType());
            postDao.incCommentCount(comment.getPostOrCommentId());
        }
        if (comment.getType().equals("comment")) {
            commentDao.incCommentCount(comment.getPostOrCommentId());
        }
        return repo.save(comment);
    }

    @Override
    public boolean delete(String id) {
        Comment comment = repo.findById(id).get();
        if (comment.getType().equals("post"))
            postDao.decCommentCount(comment.getPostOrCommentId());
        if (comment.getType().equals("comment"))
            commentDao.decCommentCount(comment.getPostOrCommentId());
        repo.deleteById(id);
        return true;
    }

    @Override
    public List<Comment> getComments(String postOrcommentId) {
        return commentDao.getComment(postOrcommentId);
    }

}
