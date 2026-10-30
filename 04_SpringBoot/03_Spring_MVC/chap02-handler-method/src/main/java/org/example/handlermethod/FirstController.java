package org.example.handlermethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/first/*")
public class FirstController {

    @GetMapping("regist")
    public String regist() {

        return "first/regist";
    }
    
    
    //WebRequest 는 http요청을 받아온다
    @PostMapping("regist")
    //public String registMenu(Model model, WebRequest request) {
    public String registMenu(Model model, HttpServletRequest request) {
        System.out.println(request.getMethod());
        System.out.println(request.getRequestURL());

        
        String name = request.getParameter("name");
        System.out.println("name = " + name);
        int price = Integer.parseInt( request.getParameter("price"));
        System.out.println("price = " + price);
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
        System.out.println("categoryCode = " + categoryCode);

        model.addAttribute("message", name + "을 신규메뉴목록의 " + categoryCode + " 번, 가격은 " + price + "원으로 추가했습니다.");
        
        return "first/messagePrinter";


    }

    @GetMapping("modify")
    public void modify() {

    }


    /*
    * RequestParam
    * - 요청 파라미터를 핸들러 메소드의 매개변수에 바인딩
    * - RequestParam은 생략가능
    * required: 기본값은 true 필수파라미터가 없으면 400 예외 발생
    * defaultValue: 기본값 지정
    * */
    @PostMapping("modify")
    public String modifyMenu(
            Model model,
            //@RequestParam(required = false) String modifyName,
            @RequestParam String modifyName,
//            @RequestParam(defaultValue = "0") String modifyPrice
            String modifyPrice
    ) {
        String message = modifyName + "을 " + modifyPrice +"원으로 수정했습니다.";
        model.addAttribute("message", message);
        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search() {

    }

    /*
    * @ModelAttribute: 여러 요청 파라미터를 DTO 같은 객체에 바인딩
    * - 핸들러 메소드의 매개변수로 전달되는 동시에 Model에도 자동으로 추가된다
    * - Model에 담길 변수는 이름을 작성하지 않으면 클래스명의 소문자 캐멀케이스로 저장된다. 
    * */
    @PostMapping("search")
    public String searchMenu(Model model, @ModelAttribute("menu") MenuDTO menuDTO) {
//        model.addAttribute("message", menuDTO)
        return "first/searchResult";
    }

    @GetMapping("login")
    public void login() {

    }


    /*
    * Session: 사용자별 정보를 일정시간 동안 보관하는 공간(서버측)
    * - key-value 쌍으로 보관
    * - 로그인 상태, 장바구니 같은 임시정보를 유지할 때 사용
    *
    * 동작과정
    * 1. 사용자가 처음, 서버에 요청
    * 2. 서버가 사용자 전용 세션을 만듦(JSESSIONID)
    * 3. 서버가 사용자에게 응답할 때 JSESSIONID 쿠키 전달
    * 4. 브라우저가 이후 요청마다 JSESSIONID로 사용자의 세션을 조회
    * */
    @PostMapping("login")
    public String loginSession(HttpSession session, @RequestParam String id, String pwd) { //@RequestParam 생략 가능

        //세션에 값을 담아 주기
        session.setAttribute("id", id); // 세션에 값 저장

        return "first/loginResult";
    }

    @GetMapping("logout")
    public String logoutTest(HttpSession session) {
        session.invalidate();
        return "first/loginResult";
    }

    @GetMapping("body")
    public void body() {

    }

    /*
    * @RequestBody : Http 요청 본문을 읽어서 핸들러 메소드의 매개변수로 전달
    * */
    @PostMapping("body")
    public void bodyTest(
            @RequestBody String body,
            @RequestHeader("content-type") String contentType


    ){
        System.out.println("body = " + URLDecoder.decode(body, StandardCharsets.UTF_8));
    }
}
