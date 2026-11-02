package org.example.chap04exceptionhandler.controller;

import org.example.chap04exceptionhandler.exception.MemberRegistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandlerController {

    @GetMapping("/controller-null")
    public String nullPointerException( ){
        String str = null;
        System.out.println("str.charAt(0) = " + str.charAt(0));
        return "/";
    }

    @GetMapping("/controller-user")
    public String userException() throws MemberRegistException {
        boolean check = true;
        if(check) {
            throw new MemberRegistException("당신은 우리 서비스의 회원이 될 수 없습니다.");

        }
        return "/";
    }

   /*
    * @ExceptionHandler
    * - 스프링 부트에서 컨트롤러 요청 처리 중 발생한 예외를 처리하는 메서드(Handler)에 사용하는 annotation
    * - 지정한 예외가 발생하면 해당 예외처리 메소드(Handler)가 실행된다.
    * */
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception){
        System.out.println("Controller 레벨의 exception 처리!!");
        return "error/nullPointer";
    }

    // 사용자가 만든 예외 처리!
    @ExceptionHandler(MemberRegistException.class)
    public String MemberRegistExceptionHandler(Model model, MemberRegistException exception){
        System.out.println("Controller 레벨의 exception 처리!!");
        model.addAttribute("exception", exception);
        return "error/memberRegist";
    }

    @ExceptionHandler(Exception.class)
    public String ExceptionHandler(Exception exception){
        System.out.println("Controller 레벨의 exception 처리!!");
        return "error/exception";
    }


}
