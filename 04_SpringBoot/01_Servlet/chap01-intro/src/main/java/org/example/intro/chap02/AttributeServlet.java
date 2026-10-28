package org.example.intro.chap02;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chap02/attribute")
public class AttributeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 클라이언트가 보낸 값
        String nameParameter = req.getParameter("name");

        // Attribute : 서블릿이 서버에 저장한 값
        req.setAttribute("studentName", nameParameter);
        req.setAttribute("score", 100);
        req.setAttribute("temporary", "삭제될 값");

        // Attribute 조회
        Object score = req.getAttribute("score");
        System.out.println("저장된 score : " + score);

        // Attribute  삭제
        req.removeAttribute("temporary");

        // RequestDispatcher를 이용해 요청을 forward로 다른 서블릿으로 전달
        req.getRequestDispatcher("/chap02/attribute-result").forward(req, resp);
    }
}
