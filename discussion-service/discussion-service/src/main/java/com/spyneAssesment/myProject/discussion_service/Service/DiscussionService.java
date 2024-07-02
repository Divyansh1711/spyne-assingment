package com.spyneAssesment.myProject.discussion_service.Service;

import com.spyneAssesment.myProject.discussion_service.Model.DiscussionRequest;
import com.spyneAssesment.myProject.discussion_service.Model.DiscussionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscussionService {
    DiscussionResponse createDiscussion(DiscussionRequest discussionRequest);

    DiscussionResponse updateDiscussion(Long discussionId, DiscussionRequest discussionDetails);

    void deleteDiscussion(Long discussionId);

   DiscussionResponse getDiscussionById(Long discussionId);

    List<DiscussionResponse> getAllDiscussions();

    List<DiscussionResponse> getDiscussionsByHashtag(String hashtag);

    List<DiscussionResponse> getDiscussionsByText(String text);
}
