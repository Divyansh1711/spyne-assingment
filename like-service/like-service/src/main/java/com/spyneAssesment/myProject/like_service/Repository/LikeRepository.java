package com.spyneAssesment.myProject.like_service.Repository;

import com.spyneAssesment.myProject.like_service.Entity.UserLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<UserLike, Long> {
    List<UserLike> findAllByDiscussionId(Long discussionId);
}
