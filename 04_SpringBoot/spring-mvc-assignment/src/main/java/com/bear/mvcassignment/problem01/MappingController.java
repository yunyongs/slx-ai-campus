package com.bear.mvcassignment.problem01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 문제 1. 요청 경로와 HTTP 메서드 매핑
 *
 * 1. 이 클래스를 @Controller로 등록하고 클래스 공통 경로를 /mapping으로 설정한다.
 * 2. 아래 메서드마다 적힌 요청 방식과 경로에 맞는 매핑 어노테이션을 작성한다.
 * 3. 메서드별 주석에 있는 모델 속성과 문구를 전달한다. 화면은 이미 준비되어 있다.
 *
 * 이 Java 파일의 어노테이션과 TODO만 완성한다. 메서드 이름과 반환형은 유지한다.
 * 홈 화면의 문제 1 링크와 버튼으로 확인한다. 구현 전 요청의 404는 정상이다.
 */
@Controller
@RequestMapping("/mapping")
public class MappingController {

    /**
     * 문제 1-1. 요청 방식에 제한이 없는 환영 화면
     *
     * 요청: /mapping/welcome (GET과 POST 모두 같은 메서드로 처리)
     * 1. @RequestMapping을 사용하고 method 속성을 지정하지 않는다.
     * 2. Model에 message="스터디룸에 오신 것을 환영합니다."를 추가한다.
     * 3. 논리 뷰 이름 result를 문자열로 반환한다.
     *
     * 확인: GET 링크와 POST 버튼 모두 HTTP 200으로 같은 환영 문구가 표시된다.
     */
    @RequestMapping("/welcome")
    public String welcome(Model model) {
//        throw new UnsupportedOperationException("TODO 1-1");
        model.addAttribute("message", "스터디룸에 오신 것을 환영합니다.");
        return  "result";
    }


    /**
     * 문제 1-2. GET 확인 화면
     *
     * 요청: GET /mapping/confirm
     * 1. GET 전용 매핑을 작성한다.
     * 2. Model에 message="예약 확인 화면입니다."를 추가한다.
     * 3. 논리 뷰 이름 result를 반환한다.
     *
     * 확인: GET 확인 링크를 누르면 예약 확인 화면입니다.가 표시된다.
     */
    @GetMapping("/confirm")
    public String confirmForm(Model model) {
        // throw new UnsupportedOperationException("TODO 1-2");
        model.addAttribute("message", "예약 확인 화면입니다.");
        return "result";
    }

    /**
     * 문제 1-3. POST 확인 처리
     *
     * 요청: POST /mapping/confirm
     * 1. POST 전용 매핑을 작성한다. confirmForm과 URL은 같고 요청 방식은 다르다.
     * 2. Model에 message="예약 확인 요청을 처리했습니다."를 추가한다.
     * 3. 논리 뷰 이름 result를 반환한다.
     *
     * 확인: POST 확인 버튼을 누르면 예약 확인 요청을 처리했습니다.가 표시된다.
     */
    @PostMapping("confirm")
    public String confirmSubmit(Model model) {
//        throw new UnsupportedOperationException("TODO 1-3");
        model.addAttribute("message", "예약 확인 요청을 처리했습니다.");
        return "result";

    }

    /**
     * 문제 1-4. 하나의 메서드에 두 경로 매핑
     *
     * 요청: GET /mapping/notice 또는 GET /mapping/help
     * 1. 하나의 GET 매핑에 두 경로를 배열로 지정한다. 메서드를 둘로 나누지 않는다.
     * 2. Model에 message="이용 안내입니다."를 추가한다.
     * 3. 논리 뷰 이름 result를 반환한다.
     *
     * 확인: 이용 공지와 이용 도움말 링크 모두 같은 안내 문구를 표시한다.
     * POST /mapping/notice는 허용하지 않아야 한다(HTTP 405).
     */
//    @RequestMapping(value = {"notice", "help"}, method = RequestMethod.GET)
    @GetMapping({"notice", "help"})
    public String notice(Model model) {

//        throw new UnsupportedOperationException("TODO 1-4");
        model.addAttribute("message", "이용 안내입니다.");
        return "result";
    }

    /**
     * 문제 1-5. 경로 변수로 방 번호 받기
     *
     * 요청: GET /mapping/rooms/{roomNo}
     * 1. @PathVariable("roomNo")로 경로의 방 번호를 int roomNo에 바인딩한다.
     * 2. Model에 roomNo를 int로 추가하고 message에 방 번호 + "번 스터디룸입니다."를 넣는다.
     * 3. 논리 뷰 이름 result를 반환한다. 입력값이 달라지면 문구도 바뀌어야 한다.
     *
     * 확인: /mapping/rooms/7 -> roomNo=7, message=7번 스터디룸입니다.
     *       /mapping/rooms/12 -> roomNo=12, message=12번 스터디룸입니다.
     *       /mapping/rooms/abc -> HTTP 400 (Spring의 int 변환 실패)
     */
    @GetMapping("rooms/{roomNo}")
    public String room(Model model, @PathVariable("roomNo") int roomNo) {
        model.addAttribute("roomNo", roomNo);                       // → ${roomNo} (int)
        model.addAttribute("message", roomNo + "번 스터디룸입니다.");  // 7 + "번…" → "7번 스터디룸입니다."
        return "result";
    }

}
