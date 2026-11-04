package org.example.restapi.section03.exceptionenum;

// 비즈니스 규칙 위반을 표현하는 공통 사용자 정의 예외
public class BusinessException extends RuntimeException{

    private final ErrorCode errorCode;

    // ErrorCode의 기본 메시지를 활용하는 예외 생성자
    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    // 같은 오류 코드에 상세 내용을 적을 때
    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
