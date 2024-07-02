package com.spyneAssesment.myProject.like_service.Service;

import com.spyneAssesment.myProject.like_service.Entity.UserLike;
import com.spyneAssesment.myProject.like_service.Model.LikeRequest;
import com.spyneAssesment.myProject.like_service.Model.LikeResponse;
import com.spyneAssesment.myProject.like_service.Repository.LikeRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public LikeResponse likeDiscussion(LikeRequest likeRequest) {
        log.info("Liking discussion with userId: {} and discussionId: {}", likeRequest.getUserId(), likeRequest.getDiscussionId());

        UserLike like = UserLike.builder()
                .userId(likeRequest.getUserId())
                .discussionId(likeRequest.getDiscussionId())
                .createdAt(LocalDateTime.now())
                .build();

        like = likeRepository.save(like);

        log.info("Liked discussion saved with likeId: {}", like.getLikeId());

        return convertLikeToLikeResponse(like);
    }

    @Override
    public void unlikeDiscussion(Long likeId) {
        log.info("Unliking discussion with likeId: {}", likeId);

        likeRepository.deleteById(likeId);

        log.info("Discussion unliked successfully");
    }

    @Override
    public List<LikeResponse> getLikesByDiscussion(Long discussionId) {
        log.info("Fetching likes for discussionId: {}", discussionId);

        List<UserLike> likeList = likeRepository.findAllByDiscussionId(discussionId);

        log.info("Fetched {} likes for discussionId: {}", likeList.size(), discussionId);

        return likeList.stream()
                .map(this::convertLikeToLikeResponse)
                .collect(Collectors.toList());
    }

    private LikeResponse convertLikeToLikeResponse(UserLike like) {
        return LikeResponse.builder()
                .likeId(like.getLikeId())
                .userId(like.getUserId())
                .discussionId(like.getDiscussionId())
                .build();
    }
}
