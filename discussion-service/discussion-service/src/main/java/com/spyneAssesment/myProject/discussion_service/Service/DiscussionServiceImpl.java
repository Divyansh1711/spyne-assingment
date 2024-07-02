package com.spyneAssesment.myProject.discussion_service.Service;

import com.spyneAssesment.myProject.discussion_service.Entity.Discussion;
import com.spyneAssesment.myProject.discussion_service.Exception.DiscussionServiceCustomException;
import com.spyneAssesment.myProject.discussion_service.Model.DiscussionRequest;
import com.spyneAssesment.myProject.discussion_service.Model.DiscussionResponse;
import com.spyneAssesment.myProject.discussion_service.Repository.DiscussionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DiscussionServiceImpl implements DiscussionService {
    @Autowired
    private DiscussionRepository discussionRepository;

    @Override
    public DiscussionResponse createDiscussion(DiscussionRequest discussionRequest) {
        log.info("creating a new discussion");
        Set<String> hashtags = discussionRequest.getHashtags() != null ? new HashSet<>(discussionRequest.getHashtags()) : new HashSet<>();
        Discussion discussion
                =Discussion
                .builder()
                .userId(discussionRequest.getUserId())
                .imageUrl(discussionRequest.getImageUrl())
                .textField(discussionRequest.getTextField())
                .hashTags(hashtags) // Initialize with request hashtags
                .createdAt(Date.from(Instant.now()))
                .build();
        try{
        discussionRepository.save(discussion);}
        catch(Exception ex){
            throw  new DiscussionServiceCustomException("REQUIRED_FIELD_ARE_NULL","400");
        }
        log.info("created a new discussion");

        return convertDiscussionToDiscussionResponse(discussion);
    }

    @Override
    public DiscussionResponse updateDiscussion(Long discussionId, DiscussionRequest discussionDetails) {
        log.info("updating a discussion");
        Discussion discussion
                = discussionRepository.findById(discussionId)
                .orElseThrow(()-> new DiscussionServiceCustomException("DISCUSSION_NOT_FOUND",discussionId.toString()));
        Set<String> hashtags = discussionDetails.getHashtags() != null ? new HashSet<>(discussionDetails.getHashtags()) : new HashSet<>();
        discussion.setHashTags(discussionDetails.getHashtags());
        discussion.setImageUrl(discussionDetails.getImageUrl());
        discussion.setTextField(discussionDetails.getTextField());
        discussion.setHashTags(hashtags);

        discussionRepository.save(discussion);
        log.info("discussion updated");

        return convertDiscussionToDiscussionResponse(discussion);
    }

    @Override
    public void deleteDiscussion(Long discussionId) {
        try {
            discussionRepository.deleteById(discussionId);
        }catch (Exception ex){
            throw  new DiscussionServiceCustomException("DISCUSSION_NOT_FOUND",discussionId.toString());
        }
    }

    @Override
    public DiscussionResponse getDiscussionById(Long discussionId) {
        Discussion discussion= discussionRepository.findById(discussionId)
                .orElseThrow(()-> new DiscussionServiceCustomException("DISCUSSION_NOT_FOUND",discussionId.toString()));

        return convertDiscussionToDiscussionResponse(discussion);
    }

    @Override
    public List<DiscussionResponse> getAllDiscussions() {
        List<Discussion> discussionList= discussionRepository.findAll();
        return discussionListToResponseList(discussionList);
    }

    @Override
    public List<DiscussionResponse> getDiscussionsByHashtag(String hashtag) {
        List<Discussion> discussionList=discussionRepository.findByHashTagsContaining(hashtag);
        return discussionListToResponseList(discussionList);
    }
    private List<DiscussionResponse> discussionListToResponseList(List<Discussion> discussionList){
        return discussionList.stream().map(this::convertDiscussionToDiscussionResponse).collect(Collectors.toUnmodifiableList());
    }
    @Override
    public List<DiscussionResponse> getDiscussionsByText(String text) {
        List<Discussion> discussionList= discussionRepository.findByTextFieldContaining(text);
        return discussionListToResponseList(discussionList);
    }

    private DiscussionResponse convertDiscussionToDiscussionResponse(Discussion discussion) {
        DiscussionResponse discussionResponse
                = DiscussionResponse
                .builder()
                .discussionId(discussion.getDiscussionId())
                .userId(discussion.getUserId())
                .textField(discussion.getTextField())
                .createdAt(discussion.getCreatedAt())
                .hashtags(discussion.getHashTags() != null ? new HashSet<>(discussion.getHashTags()) : new HashSet<>())
                .imageUrl(discussion.getImageUrl())
                .build();
        return discussionResponse;
    }


}
