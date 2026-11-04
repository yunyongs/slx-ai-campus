package org.example.restapi.section04;

import org.springframework.hateoas.EntityModel;
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

/*
* Hateoas (Hypermedia As The Engine Of Application State)
* - REST API 응답이 현재 데이터 뿐 아니라, 해당 데이터와 관련된 다음 요청의 링크도 함께 제공하자
* */
@RestController
@RequestMapping("/hateoas")
public class HateoasTestController {

    private List<UserDTO> users;

    // UserDTO를 EntityModel<UserDTO>(데이터 + 링크)로 바꿔 주는 조립기. 스프링이 주입한다.
    private final DtoModelAssembler assembler;

    public HateoasTestController(DtoModelAssembler assembler) {
        this.assembler = assembler;

        this.users = new ArrayList<>();
        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new Date()));
    }

    // 유저 목록 전체 조회 : 각 유저에 self 링크가 붙어서 나간다
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        // 사용자 목록을 EntityModel로 변환하기위해 Assembler의 toModel 메소드 사용
        List<EntityModel<UserDTO>> usersWithRel = users.stream()
                .map(user -> assembler.toModel(user))
//                .map(assembler::toModel) // 같은내용
                .toList();

        // for 문으로 쓰면 같은 내용
//        List<EntityModel<UserDTO>> usersWithRel = new ArrayList<>();
//        for (UserDTO user : users) {
//            usersWithRel.add(assembler.toModel(user));
//        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", usersWithRel);

        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공", responseMap);

        /*
         * application/json : HTTP의 응답이 JSON 형식의 데이터임을 의미
         * */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(
                new MediaType("application", "json", StandardCharsets.UTF_8)
        );

        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }

    // 특정 유저 조회 : assembler 가 self 링크를 만들 때 이 메서드를 가리킨다
    @GetMapping("/users/{userNo}")
    // 강사 코드 : @GetMapping("/user/{userNo}")   // 단수형. self 링크도 /hateoas/user/1 로 만들어진다
    public ResponseEntity<ResponseMessage> findUserByUserNo(@PathVariable int userNo) {
        /*
         * application/json : HTTP의 응답이 JSON 형식의 데이터임을 의미
         * */
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserDTO foundUser = new UserDTO();

        // 일치하는 회원 찾기
        for (UserDTO user : users) {
            if (user.getNo() == userNo) {
                foundUser = user;
                break;
            }
        }

        // 응답 데이터 : 찾은 회원을 EntityModel 로 감싸서 self 링크를 포함시킨다
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("user", assembler.toModel(foundUser));
        // 강사 코드 : 단건 조회는 링크 없이 DTO 를 그대로 담는다
//        responseMap.put("user", foundUser);

        // 메서드 체이닝 + 빌더 패턴
        return ResponseEntity
                .ok() // 200 상태코드
                .headers(headers)
                .body(new ResponseMessage(200, "조회성공", responseMap));
    }
}
