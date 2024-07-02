package com.spyneAssesment.myProject.comment_service.Controller;

import com.spyneAssesment.myProject.comment_service.Model.CommentRequest;
import com.spyneAssesment.myProject.comment_service.Model.CommentResponse;
import com.spyneAssesment.myProject.comment_service.Service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@Log4j2
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest) {
        log.info("Creating a new comment");
        CommentResponse commentResponse = commentService.createComment(commentRequest);
        return new ResponseEntity<>(commentResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable("commentId") Long commentId,
            @RequestBody CommentRequest commentRequest) {
        log.info("Updating comment with ID: " + commentId);
        CommentResponse updatedComment = commentService.updateComment(commentId, commentRequest);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        log.info("Deleting comment with ID: " + commentId);
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("commentId") Long commentId) {
        log.info("Fetching comment with ID: " + commentId);
        CommentResponse commentResponse = commentService.getCommentById(commentId);
        return new ResponseEntity<>(commentResponse, HttpStatus.OK);
    }

    @GetMapping("/discussion/{discussionId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByDiscussionId(
            @PathVariable("discussionId") Long discussionId) {
        log.info("Fetching comments for discussion with ID: " + discussionId);
        List<CommentResponse> comments = commentService.getCommentsByDiscussionId(discussionId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
