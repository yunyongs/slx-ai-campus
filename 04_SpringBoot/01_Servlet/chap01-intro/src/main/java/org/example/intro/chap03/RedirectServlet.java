package org.example.intro.chap03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chap03/redirect")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");

        //새 요청이 만들어지므로 저장한 값이 사라질 것!
        req.setAttribute("moveType", "FORWARD");
        req.setAttribute("message", message);

        // 리다이렉트할 URL생성
        String redirectUrl = req.getContextPath() + "chat03/result?message" + message;

        resp.sendRedirect(redirectUrl);
        //redirect: chap03/result가 호출됨(주소가 바뀜) // result가 결과를 만들어서 result자체가 보여주는 것임

    }
}
