package com.application.socialMedia.service;

import com.application.socialMedia.model.FollowDetail;

public interface FollowDetailsInterface {
    boolean addFollowing(FollowDetail followDetail);
    boolean removeFollowing(FollowDetail followDetail);
}
