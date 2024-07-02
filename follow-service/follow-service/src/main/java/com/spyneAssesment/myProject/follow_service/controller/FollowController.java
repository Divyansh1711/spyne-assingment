package com.spyneAssesment.myProject.follow_service.controller;


import com.spyneAssesment.myProject.follow_service.model.FollowRequest;
import com.spyneAssesment.myProject.follow_service.model.FollowResponse;
import com.spyneAssesment.myProject.follow_service.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/follow")
    public ResponseEntity<FollowResponse> followUser(@RequestBody FollowRequest followRequest) {
        FollowResponse response = followService.followUser(followRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> unfollowUser(@RequestParam("followerUserId") Long followerUserId,
                                             @RequestParam("followedUserId") Long followedUserId) {
        followService.unfollowUser(followerUserId, followedUserId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<Long>> getFollowers(@PathVariable("userId") Long userId) {
        List<Long> followers = followService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<Long>> getFollowing(@PathVariable("userId") Long userId) {
        List<Long> following = followService.getFollowing(userId);
        return ResponseEntity.ok(following);
    }
}

