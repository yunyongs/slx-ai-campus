package org.example.intro.chap02;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/chap02/attribute-result")
public class AttributeResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 클라이언트가 보낸 값
        String nameParameter = req.getParameter("name");

        // Attribute : 서블릿이 서버에 저장한 값
        String studentName = (String) req.getAttribute("studentName");
        Integer score = (Integer) req.getAttribute("score");
        Object temporary = req.getAttribute("temporary");

        // 응답 생성
        resp.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("=== Parameter와 Attribute 결과 ===");
        out.println("parameter name: " + nameParameter);
        out.println("attribute studentName: " + studentName);
        out.println("attribute score: " + score);
        out.println("삭제한 attribute temporary: " + temporary);
        out.println();
        out.println("temporary가 null이면 removeAttribute()로 정상 삭제된 것이다.");
    }
}
