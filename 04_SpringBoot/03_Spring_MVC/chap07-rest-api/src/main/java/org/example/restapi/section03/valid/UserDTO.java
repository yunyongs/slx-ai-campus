package org.example.restapi.section03.valid;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;

/*
* @validation
* */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {

    /*
    * Validation annotation은 위에서 아래대로 순서대로 체크된다.
    * */
    private int no;

    @NotNull(message = "아이디는 반드시 입력해야 합니다.")
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    @Size(min = 8, max=20, message="아이디는 8자 이상, 20자 이하로 입력해야 합니다.")
    // 정규표현식(regexp)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message="아이디는 영문자, 숫자, 밑줄(_)만 허용됩니다.")
    private String id;

    @Size(min=8, max=30)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()\\-_=+]).{8,30}$",
            message = "비밀번호는 8~30자이며 대문자, 소문자, 숫자, 특수문자를 각각 하나 이상 포함해야 합니다.")
    // 강사 코드 : @Size 에도 message, 특수문자는 @$!%*?& 만 허용하고 그 외 문자는 통째로 거부(허용 목록 방식)
//    @Size(min = 8, max = 30, message = "비밀번호는 8자 이상, 30자 이하로 입력해야 합니다.")
//    @Pattern(
//            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$",
//            message = "비밀번호에는 대문자, 소문자, 숫자, 특수문자가 각각 하나 이상 포함되어야 합니다."
//    )
    private String pwd;

    @NotNull
    @Size(min=2, max=5)
    // 강사 코드 : message 를 넣어야 기본 메시지("must not be null") 대신 한글 안내가 나간다
//    @NotNull(message = "이름은 반드시 입력해야 합니다.")
//    @Size(min = 2, max = 5, message = "이름은 2자 이상, 5자 이하로 입력해야 합니다.")
    private String name;

    @Past(message = "가입일은 과거 날짜만 입력할 수 있습니다.")
//    @Future
//    @PastOrPresent(message = "과거 또는 현재")
//    @FutureOrPresent
    private Date enrollAt;

    public UserDTO(String id, String pwd, String name) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
    }
}
