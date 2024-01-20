package com.application.socialMedia.service;

import java.util.List;

import com.application.socialMedia.model.Comment;

public interface CommentServiceInterface {
    public Comment save(Comment comment);
    public boolean delete(String id);
    public List<Comment> getComments(String id);
}
