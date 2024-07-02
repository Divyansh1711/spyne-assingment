package com.spyneAssesment.myProject.discussion_service.Controller;

import com.spyneAssesment.myProject.discussion_service.Entity.Discussion;
import com.spyneAssesment.myProject.discussion_service.Model.DiscussionRequest;
import com.spyneAssesment.myProject.discussion_service.Model.DiscussionResponse;
import com.spyneAssesment.myProject.discussion_service.Service.DiscussionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discussions")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;

    @PostMapping("")
    public ResponseEntity<DiscussionResponse> createDiscussion(@RequestBody DiscussionRequest discussionRequest){

        DiscussionResponse discussionResponse
                = discussionService.createDiscussion(discussionRequest);
        return new ResponseEntity<>(discussionResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{discussionId}")
    public ResponseEntity<DiscussionResponse> updateDiscussion(@PathVariable("discussionId") Long discussionId, @RequestBody DiscussionRequest discussionDetails) {
        DiscussionResponse updatedDiscussion = discussionService.updateDiscussion(discussionId, discussionDetails);

        return new ResponseEntity<>(updatedDiscussion, HttpStatus.OK);

    }

    @DeleteMapping("/{discussionId}")
    public ResponseEntity<Void> deleteDiscussion(@PathVariable("discussionId") Long discussionId) {
        discussionService.deleteDiscussion(discussionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{discussionId}")
    public ResponseEntity<DiscussionResponse> getDiscussionById(@PathVariable("discussionId") Long discussionId) {
        DiscussionResponse discussionResponse= discussionService.getDiscussionById(discussionId);
        return  new ResponseEntity<>(discussionResponse,HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DiscussionResponse>> getAllDiscussions() {
        List<DiscussionResponse> discussions = discussionService.getAllDiscussions();
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @GetMapping("/searchByHashtag")
    public ResponseEntity<List<DiscussionResponse>> getDiscussionsByHashtag(@RequestParam("hashtag") String hashtag) {
        List<DiscussionResponse> discussions = discussionService.getDiscussionsByHashtag(hashtag);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }

    @GetMapping("/searchByText")
    public ResponseEntity<List<DiscussionResponse>> getDiscussionsByText(@RequestParam("text") String text) {
        List<DiscussionResponse> discussions = discussionService.getDiscussionsByText(text);
        return new ResponseEntity<>(discussions, HttpStatus.OK);
    }
}

