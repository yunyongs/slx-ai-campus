package org.example.restapi.section02.commonresponse;

import lombok.*;

/*
*
* @param <T> 응답 본문에 담을 데이터 타입
* */
// 강사 코드 : @Getter 가 없으면 Jackson 이 private 필드를 읽지 못해 응답이 {} 로 나간다 (2026-11-04 확인)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    /*
     * 팩토리 메서드
     * - 객체 생성과정을 캡슐화하여 객체를 생성,반환하는 메서드
     * - 생성 목적을 이름으로 명확하게 표현
     * */
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status,message,data);
    }

    public static <T> ApiResponse<T> failure(int status, String message, T data) {
        return new ApiResponse<>(status,message,null);
    }

    // 강사 코드 : 실패 응답은 data 가 항상 null 이므로 매개변수를 두 개만 받는다
//    public static <T> ApiResponse<T> failure(int status, String message) {
//        return new ApiResponse<>(status, message, null);
//    }

}
