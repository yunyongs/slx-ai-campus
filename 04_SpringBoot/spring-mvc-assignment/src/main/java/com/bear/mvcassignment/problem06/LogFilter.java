package com.bear.mvcassignment.problem06;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

public class LogFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 다운 캐스팅
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        System.out.println("[Log 전] " + httpServletRequest.getMethod() + " " + httpServletRequest.getRequestURI() + " "+ LocalTime.now());
        chain.doFilter(request, response);
        System.out.println("[Log 후] 응답 상태 " + httpServletResponse.getStatus() + " "+httpServletRequest.getHeader("Referer"));
    }
}
