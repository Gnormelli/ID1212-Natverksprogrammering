package com.example.laboration_4;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//@WebServlet(name = "Laboration_4_war_exploded", value = "/Laboration_4_war_exploded")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "HHEJ!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String guess = request.getParameter("guess");

        // Hello
        PrintWriter out = response.getWriter();
        System.out.println(guess);
        out.println(guess);

    }

    public void destroy() {
    }
}