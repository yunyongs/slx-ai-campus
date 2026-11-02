package com.bear.mvcassignment.problem06;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

public class BadWordFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 다운 캐스팅
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String keyword = request.getParameter("keyword");

        if("광고".equals(keyword)) {
            System.out.println("[BadWord] 차단");
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "금지된 검색어입니다.");
            return;
        }

        System.out.println("[BadWord 통과] keyword" + keyword );
        chain.doFilter(request, response);

    }
}
