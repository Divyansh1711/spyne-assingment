package com.spyneAssesment.myProject.comment_service.FeignClient;

import com.spyneAssesment.myProject.comment_service.FeignClient.Model.DiscussionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "discussion-service", url = "http://localhost:8082")
public interface DiscussionFeignClient {

    @GetMapping("/discussions/{discussionId}")
    ResponseEntity<DiscussionResponse> getDiscussionById(@PathVariable("discussionId") Long discussionId);


}

