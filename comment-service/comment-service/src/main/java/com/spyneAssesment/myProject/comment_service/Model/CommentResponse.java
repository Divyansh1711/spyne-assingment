package com.spyneAssesment.myProject.comment_service.Model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Long commentId;
    private Long userId;
    private Long discussionId;
    private String text;
    private LocalDateTime createdAt;
}
