package org.example.filterandintercepter.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * Interceptor
 * - DispatcherServlet 내부에서 선택된 Handler(Controller)를 기준으로 동작
 * - Controller에 가까움(스프링컨텍스트 내부) Handler 정보와 ModelAndView를 사용 가능
 * */
@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    /*
     * preHandle()
     * - Controller 호출전에 실행
     * - 반환값이 true이면 다음 단계로 진행, false이면 요청 처리를 중단
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("[3. Interceptor] preHandle - 컨트롤러 호출 전 !");

        // /interceptor/block 요청만 access=granted 파라미터를 검사하고, 나머지(/stopwatch 등)는 통과
        if (
                request.getRequestURI().equals("/interceptor/block")
                && !"granted".equals(request.getParameter("access"))
        ) {
            System.out.println("[Interceptor] access 값이 없어 요청 차단");
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "access=granted 필요");
            return false; // Controller, postHandle, afterCompletion 모두 실행되지 않음
        }

        request.setAttribute("interceptorStartTime", System.currentTimeMillis());
        return true;
    }

    /*
     * postHandle()
     * - Controller 호출 이후 실행, View가 렌더링 되기 전에 실행
     * - ModelAndView가 존재하면 전달할 데이터를 추가하거나 수정 가능
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {


        long startTime = (long) request.getAttribute("interceptorStartTime");
        long interval = System.currentTimeMillis() - startTime;
        System.out.println("[5. Interceptor] postHandle - 컨트롤러 호출 후, 뷰 렌더링 전 !");

        // @RestController(@ResponseBody)는 뷰가 없으므로 modelAndView가 null
        if (modelAndView != null) {
            modelAndView.addObject("interceptorInterval", interval);
        }
    }

    /*
     * afterCompletion()
     * - View 렌더링을 포함한 요청 처리가 모두 끝난 뒤 실행
     * - 예외에 대한 정보도 받을 수 있음
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("[6. Interceptor] afterCompletion - 뷰 렌더링 후!");
    }
}
