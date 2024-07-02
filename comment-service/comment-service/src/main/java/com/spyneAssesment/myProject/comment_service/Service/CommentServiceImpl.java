package com.spyneAssesment.myProject.comment_service.Service;

import com.spyneAssesment.myProject.comment_service.Entity.Comment;
import com.spyneAssesment.myProject.comment_service.Exception.CommentServiceCustomException;
import com.spyneAssesment.myProject.comment_service.FeignClient.DiscussionFeignClient;
import com.spyneAssesment.myProject.comment_service.FeignClient.Model.DiscussionResponse;
import com.spyneAssesment.myProject.comment_service.Model.CommentRequest;
import com.spyneAssesment.myProject.comment_service.Model.CommentResponse;
import com.spyneAssesment.myProject.comment_service.Respository.CommentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DiscussionFeignClient discussionFeignClient;

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        log.info("Creating a new comment");

        Long discussionId = commentRequest.getDiscussionId();
        log.info("checking for comment id");
        DiscussionResponse discussionResponse = discussionFeignClient.getDiscussionById(discussionId).getBody();

        if (discussionResponse == null) {
            throw new CommentServiceCustomException("DISCUSSION_NOT_FOUND", String.valueOf(discussionId));
        }

        Comment comment = Comment.builder()
                .userId(commentRequest.getUserId())
                .discussionId(commentRequest.getDiscussionId())
                .text(commentRequest.getText())
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
        log.info("Comment created");

        return convertCommentToCommentResponse(comment);
    }

    @Override
    public CommentResponse updateComment(Long commentId, CommentRequest commentDetails) {
        log.info("Updating a comment");

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentServiceCustomException("COMMENT_NOT_FOUND", commentId.toString()));

        comment.setText(commentDetails.getText());

        commentRepository.save(comment);
        log.info("Comment updated");

        return convertCommentToCommentResponse(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        log.info("Deleting a comment");

        if (!commentRepository.existsById(commentId)) {
            throw new CommentServiceCustomException("COMMENT_NOT_FOUND", commentId.toString());
        }

        commentRepository.deleteById(commentId);
        log.info("Comment deleted");
    }

    @Override
    public List<CommentResponse> getAllCommentsByDiscussion(Long discussionId) {
        log.info("Fetching all comments for discussion {}", discussionId);

        List<Comment> comments = commentRepository.findAllByDiscussionId(discussionId);
        return comments.stream()
                .map(this::convertCommentToCommentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        log.info("Fetching comment by id {}", commentId);

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentServiceCustomException("COMMENT_NOT_FOUND", commentId.toString()));

        return convertCommentToCommentResponse(comment);
    }

    @Override
    public List<CommentResponse> getCommentsByDiscussionId(Long discussionId) {
        log.info("Fetching comments by discussion id {}", discussionId);

        List<Comment> comments = commentRepository.findAllByDiscussionId(discussionId);
        return comments.stream()
                .map(this::convertCommentToCommentResponse)
                .collect(Collectors.toList());
    }

    private CommentResponse convertCommentToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUserId())
                .discussionId(comment.getDiscussionId())
                .text(comment.getText())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
