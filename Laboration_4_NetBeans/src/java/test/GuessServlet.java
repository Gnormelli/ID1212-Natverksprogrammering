/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gustav Normelli
 */
@WebServlet(name = "GuessServlet", urlPatterns = {"/GuessServlet"})
public class GuessServlet extends HttpServlet {


//    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
//        HttpSession session = req.getSession(true);
//        Model model;
//        int guessInt = Integer.parseInt(req.getParameter("guess"));
//        if(session.isNew()) {
//                System.out.print("I am a new session");
//                model = new Model();
//                session.setAttribute("model", model);
//
//        }
//    }


    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       
        HttpSession session = req.getSession(true);
        PrintWriter out = res.getWriter();
        
        Model model;
        if(session.isNew()) {
            //out.println("im new");
            int guessInt = Integer.parseInt(req.getParameter("guess"));
            model = new Model();
            model.incrementNumberOfGuesses();
            model.setGuess(guessInt);  
            
            session.setAttribute("model", model);            
        }else if((req.getParameter("guess").equals(""))){
            //out.print("i stopped a crash");
            model = (Model) session.getAttribute("model");
        }
        
        else { 
            //out.println("hi there im old");
            model = (Model) session.getAttribute("model");
            int guessInt = Integer.parseInt(req.getParameter("guess"));
            model.setGuess(guessInt);
            model.incrementNumberOfGuesses();
        }
       
        //Set the content type 
        res.setContentType("text/html");
        session.setAttribute("messageToUser", model.createTheMessage());
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.include(req, res);
   
    }
}
