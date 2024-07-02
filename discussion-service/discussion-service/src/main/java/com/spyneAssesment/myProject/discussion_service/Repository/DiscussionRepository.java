package com.spyneAssesment.myProject.discussion_service.Repository;

import com.spyneAssesment.myProject.discussion_service.Entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion,Long> {
    void deleteById(Long id);

    @Query(value = "SELECT * FROM Discussion d WHERE :hashtag IN ELEMENTS(d.hashTags)", nativeQuery = true)
    List<Discussion> findByHashTagsContaining(@Param("hashtag") String hashtag);

    List<Discussion> findByTextFieldContaining(String text);
}
