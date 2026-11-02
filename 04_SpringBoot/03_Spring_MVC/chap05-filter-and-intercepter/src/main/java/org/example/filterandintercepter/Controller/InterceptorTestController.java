package org.example.filterandintercepter.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interceptor")

public class InterceptorTestController {

    @GetMapping("/block")
    public String block() {
        return "Interceptor를 통과하여 Controller가 실행됨";
    }
}
