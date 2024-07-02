package com.spyneAssesment.myProject.comment_service.Entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @Column(nullable = false, name = "USER_ID")
    private Long userId;

    @Column(nullable = false, name = "DISCUSSION_ID")
    private Long discussionId;

    @Column(nullable = false, name = "TEXT")
    private String text;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;
}
