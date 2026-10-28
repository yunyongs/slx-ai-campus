package com.bear.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
* 어플리케이션의 모든 요청을 한 곳에서 처리하는 Servlet
* value가 "/"<< "/"로 오는 모든 요청을 받겠다.
*
*
* */
@WebServlet(name = "frontControllerServlet", value = "/")
public class FrontControllerServlet extends HttpServlet {

    private final HomeController homeController = new HomeController();
    private final TodoController todoController = new TodoController();

    private static final String VIEW_PREFIX = "/WEB-INF/views/";
    private static final String VIEW_SUFFIX = ".jsp";

    // 전체 URI에서 어플리케이션의 Context Path를 제거
    private String getRequestPath(HttpServletRequest req) {

        String requestURI = req.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        String contextPath = req.getContextPath();

        // 해당 문자열에서 컨텍스트 패스 길이만큼 자른다
        return requestURI.substring(contextPath.length());
    }

    // GET 요청 경로에 맞는 Controller 메소드를 직접 호출한다.
    private String dispatchGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        // "/"
        /*
        * /member/id
        * /home/path
        * */
        String requestPath = getRequestPath(req);

        switch (requestPath) {
            case "/":
                return homeController.getIndex(req, resp);
            case "/todo/list":
                return todoController.getList(req, resp);
            case "/todo/create":
                return todoController.getCreate(req, resp);
            default:
                return null;
        }
    }

    // Post 요청에 대한 처리
    private String dispatchPost(HttpServletRequest req, HttpServletResponse resp) {

        String requestPath = getRequestPath(req);

        switch (requestPath) {
            case "/todo/create":
                return todoController.postCreate(req, resp);
            default:
                return null;
        }
    }

    private void render(String viewName, HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        if (viewName == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (viewName.startsWith("redirect:")) {
            String redirectPath = viewName.substring("redirect:".length());
            resp.sendRedirect(req.getContextPath() + redirectPath);
            return;
        }

        // view 이름 앞뒤에 prefix, suffix를 붙여 JSP 경로를 만든다 (todo/list → /WEB-INF/views/todo/list.jsp)
        String viewPath = VIEW_PREFIX + viewName + VIEW_SUFFIX;

        // jsp 경로를 추가한  RequestDispatcher 객체 생성
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);

        // RequestDispatcher를 사용해 해당 경로로 포워딩
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        String viewName = dispatchGet(req, resp);
        render(viewName, req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String viewName = dispatchPost(req, resp);
        render(viewName, req, resp);

    }
}
