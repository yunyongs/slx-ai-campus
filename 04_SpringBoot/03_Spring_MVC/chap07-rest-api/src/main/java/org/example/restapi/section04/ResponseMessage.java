package org.example.restapi.section04;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseMessage {

    private int httpStatus;     // HTTP 응답코드
    private String message;     // HTTP 응답 메시지
    private Map<String, Object> result; // HTTP 응답 데이터
}
