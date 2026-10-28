package com.bear.frontcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;

// Todo와 관련된 요청을 처리
public class TodoController {

    public String getList(HttpServletRequest req, HttpServletResponse resp) {
        List<String> todoList = Arrays.asList("Todo 1", "Todo 2", "Todo 3");
        req.setAttribute("todoList", todoList);

        return "todo/list";
    }

    // Todo 생성 화면(form)으로 이동
    public String getCreate(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("Get /todo/create ");
        return "todo/create";
    }

    // todo생성이 반복되지 않게 redirect 시키기 (목록 주소로)
    public String postCreate(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("Post /todo/create ");

        return "redirect:/todo/list";
    }
}

