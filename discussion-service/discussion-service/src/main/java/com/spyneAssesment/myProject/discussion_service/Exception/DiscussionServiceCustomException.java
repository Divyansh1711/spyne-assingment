package com.spyneAssesment.myProject.discussion_service.Exception;

import lombok.Data;

@Data
public class DiscussionServiceCustomException extends  RuntimeException{
    private String errorCode;

    public DiscussionServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
