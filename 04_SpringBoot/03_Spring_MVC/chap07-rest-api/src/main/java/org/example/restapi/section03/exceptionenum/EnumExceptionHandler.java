package org.example.restapi.section03.exceptionenum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "org.example.restapi.section03.exceptionenum")
public class EnumExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException businessException) {

        // ErrorCode 꺼내기
        ErrorCode errorCode = businessException.getErrorCode();
        // ErrorResponse 만들기
        ErrorResponse response = ErrorResponse.from(businessException);

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(response);
    }
}
