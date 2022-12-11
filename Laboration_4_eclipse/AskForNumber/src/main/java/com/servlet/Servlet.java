package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Servlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		
		String guess = req.getParameter("guess");
		System.out.println(guess);
		
		Model model = new Model(Integer.parseInt(guess));
		String message = model.createTheMessage();
		
		HttpSession session = req.getSession();
		session.setAttribute("guess", model);
		
	
	}
}
