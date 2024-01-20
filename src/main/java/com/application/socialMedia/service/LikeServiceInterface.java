package com.application.socialMedia.service;

import java.util.List;

import com.application.socialMedia.model.Like;

public interface LikeServiceInterface {
    public Like save(Like like);
    public boolean delete(Like like);
    public List<Like> getLikes(String postOrCommentId);
}
