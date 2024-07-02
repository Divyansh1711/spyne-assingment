package com.spyneAssesment.myProject.discussion_service.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discussion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long discussionId;
    @Column(nullable = false,name="USER_ID")
    private Long userId;
    @Column(nullable = false,name="TEXT_FIELD")
    private String textField;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @ElementCollection
    @CollectionTable(name = "discussion_hashtags", joinColumns = @JoinColumn(name = "discussion_id"))
    @Column(name = "hashtag")
    private Set<String> hashTags;
    @Column(name="CREATED_AT")
    private Date createdAt;
}
