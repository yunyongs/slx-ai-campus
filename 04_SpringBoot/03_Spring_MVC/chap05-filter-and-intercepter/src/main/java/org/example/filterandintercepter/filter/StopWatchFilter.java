package org.example.filterandintercepter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class StopWatchFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
        System.out.println("[1. Filter] init - 어플리케이션 시작 시 한번 호출");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        /*
         * ServletRequest와 ServletResponse: HTTP 전용 타입이 아님
         * -> URI, HEADER, 상태코드(HTTP)를 사용하기 위해 HttpServlet 타입으로 변환
         * */

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        System.out.println("httpServletRequest = " + httpServletRequest.getRequestURI());
        if(httpServletRequest.getRequestURI().equals("/filter/block")
                && httpServletRequest.getHeader("X-Test")==null) {
            System.out.println("[Filter]X-Test 헤더가 없어 요청 차단");

            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "X-Test 헤더가 필요합니다.");
            return; //chain.doFilter()를 호출하지 않으므로 이후 과정인 DispatcherServlet, Controller 동작하지 않음
        }

        System.out.println("[2. Filter] 전처리");
        long startTime = System.currentTimeMillis(); //호출 시작 시간 기록

        //DispatcherServlet으로 연결 -> preHandle()로
        chain.doFilter(request, response);
        System.out.println("[7. Filter] 후처리");
        long endTime = System.currentTimeMillis(); //로직 기록 후 종료시간 기록
        System.out.println("[7. Filter] - 전체 체인 수행 시간 " + (endTime-startTime) + "ms");
    }

    @Override
    public void destroy() {
//        Filter.super.destroy();
        System.out.println("[99.  Filter] destroy - 어플리케이션 종료 시 한번 호출");
    }
}
