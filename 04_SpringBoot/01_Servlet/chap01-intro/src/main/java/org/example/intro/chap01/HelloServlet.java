package org.example.intro.chap01;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "메시지 변경 servlet";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // setContentType: 응답의 데이터 형식을 지정
        // MIME(Multipurpose Internet Mail Extensions)
        // - 전송하는 데이터의 종류와 형식을 알려주는 표준
        response.setContentType("application/json");
        //response.setContentType("text/html");

        // getWriter: 본문에 데이터를 담을 때 사용
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
