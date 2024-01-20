package com.application.socialMedia.service;

import java.util.List;

import com.application.socialMedia.model.Post;
import com.application.socialMedia.model.response_model.PostResponse;

public interface PostServiceInterface {
    Post save(PostResponse postResponse);
    List<Post> recommendedPost();
}
