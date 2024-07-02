package com.spyneAssesment.myProject.follow_service.exceptions;

import lombok.Getter;

@Getter
public class FollowServiceCustomException extends RuntimeException {

    private final String errorCode;

    public FollowServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
