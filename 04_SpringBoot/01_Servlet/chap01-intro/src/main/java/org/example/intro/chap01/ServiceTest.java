package org.example.intro.chap01;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/service")
public class ServiceTest extends HttpServlet {

    /*
     * GET 요청을 처리하는 메소드 ( Get : /service )
     * - 데이터를 조회할때 사용
     * - 전달할 데이터를 Body(본문)에 담지 않음
     * - URL의 쿼리문자열에 포함 (예: /service?message=bear)
     * */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");

        System.out.println("GET-message : " + message);
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().println("GET으로 받은 값: " + message);

    }

    /*
     * POST 요청을 처리하는 메소드 ( Post : /service )
     * - 전달할 데이터가 HTTP요청 본문(body)에 담기므로 URL에 노출되지 않는다.
     * */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");

        String message = req.getParameter("message");
        System.out.println("message = " + message);
        resp.setContentType("text/plain; charset=UTF-8");
        resp.getWriter().println("POST으로 받은값 : " + message);
    }

}
