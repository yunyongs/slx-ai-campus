package com.bear.mvcassignment.problem03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 문제 3. 예약 DTO 바인딩과 예약 완료
 *
 * 1. ReservationForm은 예약 입력값을 담는 완성된 제공 DTO이다. 수정하지 않는다.
 * 2. 이 클래스를 @Controller로 등록하고 공통 경로를 /reservations로 설정한다.
 * 3. 입력값은 @ModelAttribute("reservation")로 ReservationForm에 자동 바인딩한다.
 *    폼 필드는 applicant(String), roomName(String), hours(int), price(int)이다.
 * 4. 미리보기 -> 예약 완료 POST -> 리다이렉트된 GET 순서로 동작하게 만든다.
 *
 * 아래 메서드 주석에 적힌 어노테이션과 TODO만 작성한다. HTML은 수정하지 않는다.
 * 홈 화면의 문제 3 폼을 사용한다. DB 저장, 중복 예약 검사, 실제 회원 인증은 구현하지 않는다.
 * hours는 1~8, price는 0~10000의 정수이며 문자열은 비어 있지 않은 값만 입력한다.
 */
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    /**
     * 문제 3-1. DTO로 예약 미리보기
     *
     * 요청: POST /reservations/preview
     * 입력: applicant, roomName, hours, price (ReservationForm의 네 프로퍼티와 같은 이름)
     * 1. reservation 매개변수에 @ModelAttribute("reservation")를 작성해 자동 바인딩한다.
     *    폼 값을 개별 @RequestParam으로 받거나 DTO를 수동으로 생성하지 않는다.
     * 2. DTO의 hours * price로 totalPrice를 계산해 Model에 int 값으로 추가한다.
     * 3. 논리 뷰 이름 reservations/preview를 반환한다.
     *    reservation 모델 속성은 @ModelAttribute가 자동 등록하므로 다시 만들지 않는다.
     *
     * 확인: 김학생, A룸, 3, 5000 -> reservation의 네 값이 그대로 표시되고 totalPrice=15000
     * GET /reservations/preview는 허용하지 않아야 한다(HTTP 405).
     */
    @PostMapping("preview")
    public String preview(@ModelAttribute("reservation") ReservationForm reservation, Model model) {
//        throw new UnsupportedOperationException("TODO 3-1");
        int totalPrice = reservation.getPrice() * reservation.getHours();
        model.addAttribute("totalPrice", totalPrice);
        return "reservations/preview";
    }

    /**
     * 문제 3-2. 예약 완료 후 리다이렉트
     *
     * 요청: POST /reservations/complete (미리보기 화면의 예약 완료 버튼)
     * 입력: applicant, roomName, hours, price
     * 1. @ModelAttribute("reservation")로 ReservationForm에 자동 바인딩한다.
     * 2. 완료 문구를 applicant + "님의 " + roomName + " 예약이 완료되었습니다."로 만든다.
     * 3. RedirectAttributes.addFlashAttribute()로 flashMessage라는 이름에 문구를 저장한다.
     * 4. 문자열 redirect:/reservations/complete를 반환한다.
     *    일반 Model·세션에 문구를 저장하거나 URL의 쿼리 문자열로 붙이지 않는다.
     *
     * 확인: 김학생, A룸 입력 -> HTTP 302, Location=/reservations/complete
     *       이동한 첫 GET에 김학생님의 A룸 예약이 완료되었습니다. 표시
     *       이동한 GET 화면을 다시 새로고침하면 문구가 사라져야 한다.
     */
    @PostMapping("complete")
    public String complete(@ModelAttribute("reservation") ReservationForm reservation, RedirectAttributes redirectAttributes) {
//        throw new UnsupportedOperationException("TODO 3-2");
        String text = reservation.getApplicant() + "님의 " + reservation.getRoomName() + " 예약이 완료되었습니다.";
        redirectAttributes.addFlashAttribute("flashMessage", text);

        return "redirect:/reservations/complete";
    }

    /**
     * 문제 3-3. 예약 완료 화면
     *
     * 요청: GET /reservations/complete
     * 1. 논리 뷰 이름 reservations/complete만 반환한다.
     * 2. flashMessage를 새로 추가하거나 덮어쓰지 않는다.
     *
     * 확인: 예약 완료 POST 직후 첫 GET에는 전달된 완료 문구가 표시된다.
     *       직접 접근하거나 다시 GET하면 표시할 완료 메시지가 없습니다.가 표시된다.
     *       메시지가 없는 경우의 화면 처리는 제공 템플릿이 담당한다.
     */
    @GetMapping("complete")
    public String completePage() {

//        throw new UnsupportedOperationException("TODO 3-3");
        return "reservations/complete";
    }
}
