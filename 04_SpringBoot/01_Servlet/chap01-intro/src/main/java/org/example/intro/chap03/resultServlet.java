package org.example.intro.chap03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chap03/result")
public class resultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String moveType = (String) req.getAttribute("moveType");

        // Attribute에 담긴 메시지
        Object attributeMessage = req.getAttribute("message");

        // 사용자 쿼리스트링에 담긴 메시지
        String parameterMessage = req.getParameter("message");

        // 응답 만들기
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("=== forward / redirect 실행 결과 ===");
        out.println("이동 방식: " + moveType);
        out.println("request 속성(message): " + attributeMessage);
        out.println("request 파라미터(message): " + parameterMessage);




    }
}
