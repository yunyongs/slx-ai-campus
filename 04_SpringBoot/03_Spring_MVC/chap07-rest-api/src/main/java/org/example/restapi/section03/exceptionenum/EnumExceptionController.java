package org.example.restapi.section03.exceptionenum;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/*
* 비즈니스 로직의 예외들을 처리해주는 예외처리 클래스
* */
@RestController
@RequestMapping("enum-error")
// 강사 코드 : @RequestMapping("/enum-errors")   // 복수형. 강사의 bussiness.http 는 이 경로를 호출한다
public class EnumExceptionController {
    private final Map<Integer, String> users = Map.of(
            1, "홍길동",
            2, "유관순"
    );

    @GetMapping("/users/{userNo}")
    public ResponseEntity<Map<String,Object>> findUser(@PathVariable int userNo) {
        if(userNo < 1) {
            throw new BusinessException(ErrorCode.INVALID_USER_NUMBER);
        }

        String userName = users.get(userNo);

        if (userName == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "회원번호 " + userNo + "에 해당하는 회원이 없습니다.");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/check-name")
    public ResponseEntity<Map<String,Object>> check(@RequestParam String userName) {

        if(users.containsValue(userName)) {
            throw new BusinessException(ErrorCode.DUPLICATE_USER_NAME, userName + "은(는) 이미 등록된 이름입니다.");
        }

        return ResponseEntity.ok().build();
    }

}
