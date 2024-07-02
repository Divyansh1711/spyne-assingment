package com.spyneAssesment.myProject.like_service.Controller;

import com.spyneAssesment.myProject.like_service.Model.LikeRequest;
import com.spyneAssesment.myProject.like_service.Model.LikeResponse;
import com.spyneAssesment.myProject.like_service.Service.LikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@Log4j2
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<LikeResponse> likeDiscussion(@RequestBody LikeRequest likeRequest) {
        LikeResponse likeResponse = likeService.likeDiscussion(likeRequest);
        return new ResponseEntity<>(likeResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{likeId}")
    public ResponseEntity<Void> unlikeDiscussion(@PathVariable("likeId") Long likeId) {
        likeService.unlikeDiscussion(likeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/discussion/{discussionId}")
    public ResponseEntity<List<LikeResponse>> getLikesByDiscussion(@PathVariable("discussionId") Long discussionId) {
        List<LikeResponse> likeResponses = likeService.getLikesByDiscussion(discussionId);
        return new ResponseEntity<>(likeResponses, HttpStatus.OK);
    }
}
