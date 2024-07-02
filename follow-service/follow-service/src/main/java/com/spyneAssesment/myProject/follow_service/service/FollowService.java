package com.spyneAssesment.myProject.follow_service.service;

import com.spyneAssesment.myProject.follow_service.model.FollowRequest;
import com.spyneAssesment.myProject.follow_service.model.FollowResponse;

import java.util.List;

public interface FollowService {
    FollowResponse followUser(FollowRequest followRequest);

    void unfollowUser(Long followerUserId, Long followedUserId);

    List<Long> getFollowers(Long userId);

    List<Long> getFollowing(Long userId);
}
