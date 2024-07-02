package com.spyneAssesment.myProject.comment_service.Service;



import com.spyneAssesment.myProject.comment_service.Model.CommentRequest;
import com.spyneAssesment.myProject.comment_service.Model.CommentResponse;

import java.util.List;

public interface CommentService {
    CommentResponse createComment(CommentRequest commentRequest);
    CommentResponse updateComment(Long commentId, CommentRequest commentDetails);
    void deleteComment(Long commentId);
    List<CommentResponse> getAllCommentsByDiscussion(Long discussionId);

    CommentResponse getCommentById(Long commentId);

    List<CommentResponse> getCommentsByDiscussionId(Long discussionId);
}
