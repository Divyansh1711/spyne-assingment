package com.spyneAssesment.myProject.user_service.Exception;

import com.spyneAssesment.myProject.user_service.Model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserServiceCustomException.class)
    public ResponseEntity<ErrorResponse> userError(UserServiceCustomException userServiceCustomException){
      ErrorResponse errorResponse
              = new ErrorResponse()
              .builder()
              .errorCode(userServiceCustomException.getErrorCode())
              .errorMessage(userServiceCustomException.getMessage())
              .build();
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
