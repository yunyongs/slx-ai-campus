package org.example.restapi.section02.commonresponse;


import org.apache.catalina.User;
import org.example.restapi.section02.responseentity.ResponseMessage;
import org.example.restapi.section02.responseentity.UserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("common-response")
public class CommonResponseController {

    private List<UserDTO> users;

    public CommonResponseController() {
        this.users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }
    // 유저 목록 전체 조회
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserDTO>>> findAllUsers() {

        ApiResponse<List<UserDTO>> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "사용자 목록 조회에 성공했습니다.",
                users
        );

        return ResponseEntity.ok(response);
    }

    // 특정 유저 상세 조회
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ApiResponse<UserDTO>> findUserByNo(@PathVariable int userNo) {

        UserDTO foundUser = new UserDTO();

        // 일치하는 회원 찾기
        for (UserDTO user : users) {
            if (user.getNo() == userNo) {
                foundUser = user;
                break;
            }
        }

        ApiResponse<UserDTO> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "사용자 조회에 성공했습니다.",
                foundUser
        );

        return ResponseEntity
                .ok() // 200 상태코드
                .body(response);
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Void>> healthCheck() {

        // 반환할 데이터가 없는 경우에는 제네릭으로 Void를 사용하고 데이터에 null 담기 가능!
        ApiResponse<Void> response = ApiResponse.success(
                HttpStatus.OK.value(),
                "서버가 정상적으로 동작합니다",
                null
        );

        return ResponseEntity.ok(response);
    }
    }

