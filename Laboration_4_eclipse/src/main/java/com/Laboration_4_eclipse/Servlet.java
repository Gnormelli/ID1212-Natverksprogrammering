package com.Laboration_4_eclipse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
	private String message;

    public void init() {
        message = "HHEJ!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int guess = Integer.parseInt(request.getParameter("guess"));

        PrintWriter out = response.getWriter();
        System.out.println(guess);
        out.println(guess);

    }

    public void destroy() {
    }
}
