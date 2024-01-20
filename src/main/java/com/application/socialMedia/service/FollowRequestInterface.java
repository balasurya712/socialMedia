package com.application.socialMedia.service;

import com.application.socialMedia.model.FollowRequest;

public interface FollowRequestInterface {
    String addRequest(String followerId, String followingId);
    String removeRequest(String followerId, String followingId);
    FollowRequest viewRequest(String id);
}
