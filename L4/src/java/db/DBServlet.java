package db;
import java.util.*;  
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "DBServlet", urlPatterns = {"/DBServlet"})
public class DBServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession(true);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try{
          
            if(session.isNew()){
                
                session.setAttribute("model", new Model());
                boolean logdIn = checkLogIn(request, response);
                 session.setAttribute("loggedIn", logdIn);
                
              
                
                
                
                
                //log in
                
                
                //get info from user
               
               
                
                if(logdIn){
                    chooseSubject(request, response);
                }else{
                    out.println("Wrong log in information");
                    response.setContentType("text/html");
                    RequestDispatcher rd = request.getRequestDispatcher("failedToLogIn.jsp");
                    rd.include(request, response);
                }    
            }else{
             
               Model model = (Model)session.getAttribute("model");
               
               if((boolean) session.getAttribute("loggedIn") == true){
                          
                   String quizButton =(String) request.getParameter("quizAnswerd");
                   if(quizButton == null){
                       
                        String subject =(String) request.getParameter("subject1");
                        if(subject == null){
                           chooseSubject(request, response);
                        }else{ //String username =(String) request.getParameter("username");
                          
                           model.setChoosenSubject(subject);
                           session.setAttribute("model", model);
                           //start quiz for the subject
                           playQuiz(request, response);
                       } 
                        
                       //modelSaveQuiz
                       
                    }else{
                    
                        String answers = request.getQueryString();
                        model.getResult(answers);
                    }
                         
                }else{
                    boolean logdIn = checkLogIn(request, response);
                    session.setAttribute("loggedIn", logdIn);
                    if(logdIn){
                        chooseSubject(request, response);
                    }else{
                     out.println("Wrong log in information");
                     response.setContentType("text/html");
                     RequestDispatcher rd = request.getRequestDispatcher("failedToLogIn.jsp");
                     rd.include(request, response);
                    }
                }
            }
        }
        catch(Exception e){
            out.println(e.getMessage());
        }
	
    }
    private boolean checkLogIn(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession(true);
            response.setContentType("text/html");
            String username =(String) request.getParameter("username");
            String password =(String) request.getParameter("password");
           
            
           
            
            
            
            Model model = (Model)session.getAttribute("model");
            boolean areWeLoggedIn = model.logIn(username, password);
            return areWeLoggedIn;
        }catch(Exception e){
            
        }
       return false;
    }
    private void chooseSubject(HttpServletRequest request, HttpServletResponse response){
            try{
                HttpSession session = request.getSession(true);
                response.setContentType("text/html");
                Model model = (Model)session.getAttribute("model");
                List<Pair> allSubjects = model.getSubjects();
                  
                session.setAttribute("allSubjects", allSubjects);
                RequestDispatcher rd = request.getRequestDispatcher("subjectChoose.jsp");
                rd.include(request, response);
        }catch(Exception e){
                System.out.println("choosenSubject" + e);
        }
    }
    private void playQuiz(HttpServletRequest request, HttpServletResponse response){
        try{
                HttpSession session = request.getSession(true);
                response.setContentType("text/html");
                
                Model model = (Model)session.getAttribute("model");
                model.setQuestions();
                session.setAttribute("model", model);
                RequestDispatcher rd = request.getRequestDispatcher("quiz.jsp");
                rd.include(request, response);
        }catch(Exception e){
            System.out.println("playQuiz : \n" + e);
        }
    }
}