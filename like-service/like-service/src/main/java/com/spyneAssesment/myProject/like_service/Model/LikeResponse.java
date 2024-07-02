package com.spyneAssesment.myProject.like_service.Model;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class LikeResponse {
    private Long likeId;
    private Long userId;
    private Long discussionId;
}
