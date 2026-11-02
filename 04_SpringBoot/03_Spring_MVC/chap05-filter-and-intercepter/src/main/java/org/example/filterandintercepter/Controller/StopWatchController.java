package org.example.filterandintercepter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StopWatchController {

    @GetMapping("/stopwatch")
    public String stopwatch() throws InterruptedException {
        System.out.println("[4. Controller] handler Method 호출");
        Thread.sleep(1000);
        System.out.println("[4. Controller] 1 sec passed");
        Thread.sleep(1000);
        System.out.println("[4. Controller] 2 sec passed");

        return "result";
    }
}
