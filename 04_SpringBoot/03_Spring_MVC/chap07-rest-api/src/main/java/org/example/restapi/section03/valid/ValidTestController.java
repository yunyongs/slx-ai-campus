package org.example.restapi.section03.valid;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Validation (검증)
 * - 클라이언트의 요청은 조작이 쉽고, 데이터가 비정상적으로 들어올 가능성이 있기에
 * - 데이터 검증을 할 필요가 있다!
 * */
@RestController
@RequestMapping("/valid")
public class ValidTestController {

    @PostMapping("/users")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("userDTO = " + userDTO);
        return ResponseEntity.ok().build();
    }
}
