package org.example.chap04exceptionhandler.controller;

import org.example.chap04exceptionhandler.exception.MemberRegistException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OtherController {

    @GetMapping("other-controller-null")
    public String main(String[] args) {

        String str = null;
        System.out.println("str.charAt(0) = " + str.charAt(0)); //NullPointerException
        return "/";

    }

    @GetMapping("other-controller-user")
    public String userException() throws MemberRegistException {
        boolean check = true;
        if(check) {
            throw new MemberRegistException("당신은 우리 서비스의 회원이 될 수 없습니다.");

        }
        return "/";
    }





}
