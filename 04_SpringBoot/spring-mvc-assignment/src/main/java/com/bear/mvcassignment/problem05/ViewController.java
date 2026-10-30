package com.bear.mvcassignment.problem05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 문제 5. String·ModelAndView와 리다이렉트
 *
 * 1. 이 클래스를 @Controller로 등록하고 클래스 공통 경로를 /views로 설정한다.
 * 2. 메서드 이름과 반환형을 유지하고 아래 주석에 적힌 방식으로 구현한다.
 * 3. 일반 응답에는 result 뷰, 리다이렉트 도착 화면에는 views/landing 뷰를 사용한다.
 *
 * @Controller를 사용하며 @RestController와 @ResponseBody는 사용하지 않는다.
 * 이 Java 파일의 어노테이션과 TODO만 작성하고 제공 HTML과 설정은 수정하지 않는다.
 * 홈 화면의 문제 5 링크·버튼을 사용한다. 리다이렉트 후 주소창과 새로고침 결과도 확인한다.
 */
public class ViewController {

    /**
     * 문제 5-1. Model과 String으로 뷰 반환
     *
     * 요청: GET /views/string
     * 1. Model에 message="문자열 뷰 응답입니다."를 추가한다.
     * 2. 논리 뷰 이름 result를 String으로 반환한다.
     *
     * 확인: 현재 요청에서 result 화면이 렌더링되고 문자열 뷰 응답입니다.가 표시된다.
     */
    public String stringView(Model model) {
        throw new UnsupportedOperationException("TODO 5-1");
    }

    /**
     * 문제 5-2. ModelAndView로 뷰 반환
     *
     * 요청: GET /views/model-and-view
     * 1. ModelAndView 객체를 만들고 addObject()로 message="ModelAndView 뷰 응답입니다."를 추가한다.
     * 2. setViewName()으로 논리 뷰 이름 result를 설정하고 해당 객체를 반환한다.
     *
     * 확인: 현재 요청에서 result 화면이 렌더링되고 ModelAndView 뷰 응답입니다.가 표시된다.
     */
    public ModelAndView modelAndView() {
        throw new UnsupportedOperationException("TODO 5-2");
    }

    /**
     * 문제 5-3. String으로 리다이렉트
     *
     * 요청: POST /views/redirect
     * 1. RedirectAttributes.addFlashAttribute()로 flashMessage="문자열 리다이렉트가 완료되었습니다."를 넣는다.
     * 2. String 반환형을 유지하고 redirect:/views/landing을 반환한다.
     *
     * 확인: HTTP 302 후 브라우저가 GET /views/landing을 새로 요청한다.
     *       첫 화면에는 문자열 리다이렉트가 완료되었습니다.가 표시되고 GET 새로고침 시 사라진다.
     */
    public String redirect(RedirectAttributes redirectAttributes) {
        throw new UnsupportedOperationException("TODO 5-3");
    }

    /**
     * 문제 5-4. ModelAndView로 리다이렉트
     *
     * 요청: POST /views/model-and-view-redirect
     * 1. RedirectAttributes.addFlashAttribute()로 flashMessage="ModelAndView 리다이렉트가 완료되었습니다."를 넣는다.
     * 2. ModelAndView의 뷰 이름을 redirect:/views/landing으로 설정하고 해당 객체를 반환한다.
     *    flashMessage를 일반 모델용 addObject()에만 넣는 방식으로 대체하지 않는다.
     *
     * 확인: HTTP 302 후 GET /views/landing으로 이동한다.
     *       첫 화면에는 ModelAndView 리다이렉트가 완료되었습니다.가 표시되고 GET 새로고침 시 사라진다.
     */
    public ModelAndView modelAndViewRedirect(RedirectAttributes redirectAttributes) {
        throw new UnsupportedOperationException("TODO 5-4");
    }

    /**
     * 문제 5-5. 리다이렉트 도착 화면
     *
     * 요청: GET /views/landing
     * 1. 논리 뷰 이름 views/landing만 반환한다.
     * 2. 전달받은 flashMessage를 새 값으로 덮어쓰지 않는다.
     *
     * 확인: 문제 5-3 또는 5-4 직후 첫 GET에는 해당 문구가 표시된다.
     *       직접 접근하거나 다시 GET하면 표시할 이동 메시지가 없습니다.가 표시된다.
     */
    public String landing() {
        throw new UnsupportedOperationException("TODO 5-5");
    }
}
