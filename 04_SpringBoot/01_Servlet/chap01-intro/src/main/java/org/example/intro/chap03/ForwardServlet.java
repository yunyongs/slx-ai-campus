package org.example.intro.chap03;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/chap03/forward")
public class ForwardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");

        req.setAttribute("moveType", "FORWARD");
        req.setAttribute("message", message);

        //forward 실제 보여주는 것은 result에 있는 것이지만 주소는 바뀌지 않음
        req.getRequestDispatcher("/chap03/result").forward(req, resp);

        //redirect: chap03/result가 호출됨(주소가 바뀜) // result가 결과를 만들어서 result자체가 보여주는 것임

    }
}
