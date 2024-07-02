package com.spyneAssesment.myProject.follow_service.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowRequest {
    private Long followerUserId;    // User who is following
    private Long followedUserId;    // User who is being followed
}

