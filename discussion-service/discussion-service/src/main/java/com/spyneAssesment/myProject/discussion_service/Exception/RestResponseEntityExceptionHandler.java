package com.spyneAssesment.myProject.discussion_service.Exception;

import com.spyneAssesment.myProject.discussion_service.Model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler({DiscussionServiceCustomException.class})
    public ResponseEntity<com.spyneAssesment.myProject.discussion_service.Model.ErrorResponse> discussionError(DiscussionServiceCustomException exception){
        com.spyneAssesment.myProject.discussion_service.Model.ErrorResponse errorResponse=
                new com.spyneAssesment.myProject.discussion_service.Model.ErrorResponse(exception.getErrorCode(), exception.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
    }
}
