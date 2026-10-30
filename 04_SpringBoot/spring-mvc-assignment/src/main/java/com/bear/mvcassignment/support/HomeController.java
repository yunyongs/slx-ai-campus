package com.bear.mvcassignment.support;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/** 제공 코드: 과제 시작 화면. 수정하지 않는다. */
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
