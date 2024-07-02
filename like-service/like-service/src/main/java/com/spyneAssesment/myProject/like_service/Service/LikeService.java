package com.spyneAssesment.myProject.like_service.Service;


import com.spyneAssesment.myProject.like_service.Model.LikeRequest;
import com.spyneAssesment.myProject.like_service.Model.LikeResponse;

import java.util.List;

public interface LikeService {
    LikeResponse likeDiscussion(LikeRequest likeRequest);
    void unlikeDiscussion(Long likeId);
    List<LikeResponse> getLikesByDiscussion(Long discussionId);

}
