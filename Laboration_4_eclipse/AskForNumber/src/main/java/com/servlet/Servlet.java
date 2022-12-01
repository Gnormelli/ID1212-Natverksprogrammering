package com.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		String guess = req.getParameter("guess");
		System.out.println(guess);
	
	}
}
