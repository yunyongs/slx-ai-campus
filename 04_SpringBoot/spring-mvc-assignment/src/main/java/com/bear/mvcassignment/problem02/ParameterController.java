package com.bear.mvcassignment.problem02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

/**
 * 문제 2. 요청 파라미터와 암시적 뷰
 *
 * 1. 이 클래스를 @Controller로 등록하고 클래스 공통 경로를 /parameters로 설정한다.
 * 2. form은 void, calculate는 WebRequest, search는 @RequestParam을 사용한다.
 * 3. 아래 각 메서드의 요청값, 모델 속성, 논리 뷰 이름을 그대로 맞춘다.
 *
 * 이 Java 파일의 어노테이션과 TODO만 완성한다. 제공 HTML과 설정은 수정하지 않는다.
 * 홈 화면에서 문제 2의 파라미터 입력 링크를 열어 확인한다.
 * 별도의 DB나 사용자 정의 유효성 검사는 구현하지 않는다.
 */
@Controller
@RequestMapping("/parameters")
public class ParameterController {

    /**
     * 문제 2-1. void 메서드로 입력 화면 열기
     *
     * 요청: GET /parameters/form
     * 1. GET 매핑을 작성하고 반환형 void를 유지한다.
     * 2. 명시적으로 뷰 이름을 반환하거나 redirect하지 않는다. 본문을 비워 두어도 된다.
     *
     * 확인: 요청 경로에서 논리 뷰 이름 parameters/form이 결정되어
     *       templates/parameters/form.html의 요금 계산·검색 폼이 표시된다.
     */
    @GetMapping("form")
    public void form() {
    }

    /**
     * 문제 2-2. WebRequest로 요금 계산
     *
     * 요청: POST /parameters/calculate
     * 입력: roomName=방 이름, hours=이용 시간, price=시간당 요금
     * 1. WebRequest.getParameter()로 세 값을 읽는다. hours와 price는 int로 변환한다.
     * 2. totalPrice = hours * price를 계산한다. 결과를 하드코딩하지 않는다.
     * 3. Model에 roomName(String), hours(int), price(int), totalPrice(int)를 같은 이름으로 추가한다.
     * 4. 논리 뷰 이름 parameters/calculation을 반환한다.
     *
     * 정상 입력만 처리한다: roomName은 비어 있지 않고 hours는 1~8, price는 0~10000의 정수.
     * 확인: A룸, 3, 5000 -> roomName=A룸, hours=3, price=5000, totalPrice=15000
     *       B룸, 2, 7000 -> totalPrice=14000
     */
    @PostMapping("calculate")
    public String calculate(WebRequest request, Model model) {
//        throw new UnsupportedOperationException("TODO 2-2");
        String roomName = request.getParameter("roomName");
        int hours = Integer.parseInt(request.getParameter("hours"));
        int price = Integer.parseInt( request.getParameter("price"));
        int totalPrice = hours * price;

        model.addAttribute("roomName", roomName);
        model.addAttribute("hours", hours);
        model.addAttribute("price", price);
        model.addAttribute("totalPrice", totalPrice);

        return "parameters/calculation";
    }

    /**
     * 문제 2-3. 필수값·기본값·선택값 받기
     *
     * 요청: GET /parameters/search
     * 1. 세 매개변수에 이름을 명시한 @RequestParam을 작성한다.
     *    keyword: 필수 String / page: int, defaultValue="1" / tag: String, required=false
     * 2. Model에 keyword, page, tag를 같은 이름으로 추가한다.
     * 3. 논리 뷰 이름 parameters/search를 반환한다.
     *
     * 확인: ?keyword=자바 -> keyword=자바, page=1, tag=null (화면에서는 없음)
     *       ?keyword=스프링&page=2&tag=오전 -> keyword=스프링, page=2, tag=오전
     *       ?keyword=자바&page= -> page=1 (누락뿐 아니라 빈 값도 기본값 사용)
     *       keyword 누락 또는 숫자가 아닌 page=abc -> HTTP 400
     */
    @GetMapping("search")
    public String search(@RequestParam(value = "keyword", required = true) String keyword,
                         @RequestParam(value = "page", defaultValue = "1") int page,
                         @RequestParam(value = "tag", required = false) String tag,
                         Model model) {
//        throw new UnsupportedOperationException("TODO 2-3");
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("tag", tag);

        return "parameters/search";
    }
}
