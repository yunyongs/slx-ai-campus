package com.bear.mvcassignment.support;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

/** 제공 코드: 화면에 문제 번호와 처리 컨트롤러·요청 정보를 표시한다. 수정하지 않는다. */
@ControllerAdvice
public class ViewLabelAdvice {

    @ModelAttribute("problemNumber")
    public Integer problemNumber(HttpServletRequest request) {
        String path = request.getRequestURI().substring(request.getContextPath().length());
        if (path.startsWith("/mapping/")) {
            return 1;
        }
        if (path.startsWith("/views/")) {
            return 5;
        }
        return null;
    }

    @ModelAttribute("handlerController")
    public String handlerController(HttpServletRequest request) {
        Object handler = request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);
        if (handler instanceof HandlerMethod handlerMethod) {
            return handlerMethod.getBeanType().getSimpleName();
        }
        return "Controller";
    }

    @ModelAttribute("requestInfo")
    public String requestInfo(HttpServletRequest request) {
        return request.getMethod() + " " + request.getRequestURI();
    }
}
