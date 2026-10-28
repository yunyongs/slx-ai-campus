package com.bear.frontcontroller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController {

    public String getIndex(HttpServletRequest req, HttpServletResponse resp) {
        return "index";
    }
}
