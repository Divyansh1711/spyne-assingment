package com.spyneAssesment.myProject.comment_service.Model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentRequest {
    private Long userId;
    private Long discussionId;
    private String text;
}
