package com.spyneAssesment.myProject.like_service.Model;

import lombok.Data;


@Data
public class LikeRequest {
    private Long userId;
    private Long discussionId;

}