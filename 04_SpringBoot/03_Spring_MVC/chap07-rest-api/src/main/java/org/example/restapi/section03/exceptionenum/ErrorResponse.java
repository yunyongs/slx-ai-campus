package org.example.restapi.section03.exceptionenum;

/*
 * 예외가 발생했을때 클라이언트에 전달할 응답 DTO
 * */
public class ErrorResponse {

    private int status;
    private String code;
    private String message;
    private String detail;

    public ErrorResponse(int status, String code, String message, String detail) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    // BusinessException 가진 Error코드와 상세메시지로 응답 객체 생성하는 팩토리 메서드
    public static ErrorResponse from(BusinessException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        return new ErrorResponse(
                errorCode.getHttpStatus().value(),
                errorCode.getCode(),
                errorCode.getMessage(),
                exception.getMessage()
        );
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
