package org.example.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // @Controller가 붙은 메소드에서 반환하는 문자열은
    @RequestMapping(value = {"/", "/main"})
    public String main() {
        return "main";
    }
}
