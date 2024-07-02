package com.spyneAssesment.myProject.comment_service.Exception;


public class CommentServiceCustomException extends RuntimeException {

    private final String errorCode;

    public CommentServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
