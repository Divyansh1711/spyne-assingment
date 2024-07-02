package com.spyneAssesment.myProject.discussion_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DiscussionResponse {
    private Long discussionId;
    private Long userId;
    private String textField;
    private String imageUrl;
    private Set<String> hashtags;
    private Date createdAt;
}
