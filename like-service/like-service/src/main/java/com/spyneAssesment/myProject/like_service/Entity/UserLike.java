package com.spyneAssesment.myProject.like_service.Entity;

import jakarta.persistence.*;
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
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long discussionId;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}

