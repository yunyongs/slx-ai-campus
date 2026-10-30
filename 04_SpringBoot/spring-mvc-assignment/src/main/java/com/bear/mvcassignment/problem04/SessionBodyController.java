package com.bear.mvcassignment.problem04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

/**
 * 문제 4. 세션과 요청 본문·헤더·쿠키
 *
 * 1. 이 클래스를 @Controller로 등록하고 클래스 공통 경로를 /session으로 설정한다.
 * 2. 아래 메서드의 요청 매핑과 매개변수 바인딩 어노테이션을 작성한다.
 * 3. 닉네임은 HttpSession, 요청 원문은 @RequestBody로 받는다.
 *
 * 이 Java 파일의 어노테이션과 TODO만 완성한다. 메서드 이름과 반환형은 유지한다.
 * 홈 화면의 문제 4 링크를 열어 같은 브라우저에서 로그인·로그아웃·본문 전송을 확인한다.
 * 로그인은 닉네임을 저장하는 연습이다. 비밀번호나 실제 인증 기능은 구현하지 않는다.
 */
public class SessionBodyController {

    /**
     * 문제 4-1. 세션 입력 화면
     *
     * 요청: GET /session/home
     * 1. GET 매핑을 작성하고 논리 뷰 이름 session/home을 반환한다.
     * 2. 세션의 nickname을 임의로 만들거나 덮어쓰지 않는다.
     *
     * 확인: 로그인 폼과 본문 전송 폼이 표시된다.
     *       로그인 전에는 로그인 정보가 없습니다.가, 로그인 후에는 저장한 닉네임이 표시된다.
     */
    public String home() {
        throw new UnsupportedOperationException("TODO 4-1");
    }

    /**
     * 문제 4-2. 세션에 닉네임 저장
     *
     * 요청: POST /session/login
     * 입력: nickname (필수 String, 예: 김학생)
     * 1. @RequestParam("nickname")으로 값을 받고 HttpSession에 nickname이라는 이름으로 저장한다.
     * 2. 문자열 redirect:/session/home을 반환한다.
     *
     * 확인: 김학생으로 로그인하면 이동한 화면에 김학생이 표시된다.
     *       같은 브라우저에서 새로고침해도 유지되고, 다른 브라우저의 세션과 섞이지 않는다.
     */
    public String login(String nickname, HttpSession session) {
        throw new UnsupportedOperationException("TODO 4-2");
    }

    /**
     * 문제 4-3. 세션 전체 만료
     *
     * 요청: POST /session/logout
     * 1. HttpSession.invalidate()로 세션 전체를 무효화한다.
     *    nickname만 removeAttribute()로 지우는 방식으로 대체하지 않는다.
     * 2. 문자열 redirect:/session/home을 반환한다.
     *
     * 확인: 로그아웃 후 화면에 로그인 정보가 없습니다.가 표시되고 이전 닉네임이 남지 않는다.
     */
    public String logout(HttpSession session) {
        throw new UnsupportedOperationException("TODO 4-3");
    }

    /**
     * 문제 4-4. 요청 본문·헤더·선택 쿠키 확인
     *
     * 요청: POST /session/inspect
     * 1. rawBody에는 @RequestBody로 본문 원문을 String으로 받는다. 변형하거나 디코딩하지 않는다.
     * 2. contentType에는 @RequestHeader("Content-Type")으로 헤더를 받는다.
     * 3. sessionId에는 @CookieValue(value="JSESSIONID", required=false)로 쿠키를 받는다.
     * 4. Model에 rawBody, contentType을 그대로 넣는다. sessionId는 쿠키 값, 없으면 "없음"을 넣는다.
     * 5. 논리 뷰 이름 session/inspect를 반환한다.
     *
     * 화면의 본문 전송 버튼이 text/plain;charset=UTF-8로 전송한다. JavaScript는 수정하지 않는다.
     * 확인: 본문=스터디룸 이용 문의 -> rawBody=스터디룸 이용 문의,
     *       contentType=text/plain;charset=UTF-8
     *       JSESSIONID=sample-session 쿠키가 있으면 sessionId=sample-session, 없으면 sessionId=없음
     */
    public String inspect(String rawBody, String contentType, String sessionId, Model model) {
        throw new UnsupportedOperationException("TODO 4-4");
    }
}
