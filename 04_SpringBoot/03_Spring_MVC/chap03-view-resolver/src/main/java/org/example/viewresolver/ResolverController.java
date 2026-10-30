package org.example.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {


    @GetMapping("string")
    public String stringReturning(Model model) {


        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함...");


        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect()  {

        // redirect: 리다이렉트를 지시하는 특별한 접두사
        return "redirect:/";
    }


    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rAttr) {

        rAttr.addFlashAttribute("flashMessage1", "RedirectAttribute를 사용해 redirect..");

        return "redirect:/";
    }

    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {


        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");
        mv.setViewName("result");

        return mv;
    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv)  {

        mv.setViewName("redirect:/");

        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirect(ModelAndView mv, RedirectAttributes rttr) {

        rttr.addFlashAttribute("flashMessage2", "ModelAndView를 이용한 RedirectAttribute");
        mv.setViewName("redirect:/");

        return mv;
    }
}
