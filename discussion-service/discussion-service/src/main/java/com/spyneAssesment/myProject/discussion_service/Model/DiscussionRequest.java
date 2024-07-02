package com.spyneAssesment.myProject.discussion_service.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DiscussionRequest {
    private Long userId;
    private String textField;
    private String imageUrl;
    private Set<String> hashtags;
}
