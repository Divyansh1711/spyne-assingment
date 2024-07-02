package com.spyneAssesment.myProject.follow_service.service;

import com.spyneAssesment.myProject.follow_service.model.FollowRequest;
import com.spyneAssesment.myProject.follow_service.model.FollowResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {

    // Simulating in-memory storage for demonstration purposes
    private Map<Long, List<Long>> followersMap = new HashMap<>();  // userId -> list of followers
    private Map<Long, List<Long>> followingMap = new HashMap<>();  // userId -> list of users being followed

    @Override
    public FollowResponse followUser(FollowRequest followRequest) {
        Long followerUserId = followRequest.getFollowerUserId();
        Long followedUserId = followRequest.getFollowedUserId();

        // Add followedUserId to followerUserId's following list
        followingMap.computeIfAbsent(followerUserId, k -> new ArrayList<>()).add(followedUserId);

        // Add followerUserId to followedUserId's followers list
        followersMap.computeIfAbsent(followedUserId, k -> new ArrayList<>()).add(followerUserId);

        // Return a response (this could include a success message or additional data if needed)
        return FollowResponse.builder()
                .followerUserId(followerUserId)
                .followedUserId(followedUserId)
                .build();
    }

    @Override
    public void unfollowUser(Long followerUserId, Long followedUserId) {
        // Remove followedUserId from followerUserId's following list
        List<Long> followingList = followingMap.getOrDefault(followerUserId, new ArrayList<>());
        followingList.remove(followedUserId);

        // Remove followerUserId from followedUserId's followers list
        List<Long> followersList = followersMap.getOrDefault(followedUserId, new ArrayList<>());
        followersList.remove(followerUserId);
    }

    @Override
    public List<Long> getFollowers(Long userId) {
        // Return the list of followers for the given userId
        return followersMap.getOrDefault(userId, new ArrayList<>());
    }

    @Override
    public List<Long> getFollowing(Long userId) {
        // Return the list of users being followed by the given userId
        return followingMap.getOrDefault(userId, new ArrayList<>());
    }
}
