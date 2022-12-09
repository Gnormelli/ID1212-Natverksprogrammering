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
                //log in
                boolean logdIn = checkLogIn(request, response);
                //get info from user
               
                session.setAttribute("loggedIn", logdIn);
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
                   if(model.subject.equals("nothing")){
                       chooseSubject(request, response);
                   }else{ //String username =(String) request.getParameter("username");
                       //start quiz for the subject
                   }
                   
                   
               }else{
                   boolean logdIn = checkLogIn(request, response);
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
    
    private void chooseSubject(HttpServletRequest request, HttpServletResponse response){
            
            try{
                System.out.println("begins choosenSubject+");
                HttpSession session = request.getSession(true);
                response.setContentType("text/html");
                Model model = (Model)session.getAttribute("model");
                List<Pair> allSubjects = model.getSubjects();
                PrintWriter out = response.getWriter();
                
                
                session.setAttribute("allSubjects", allSubjects);
                RequestDispatcher rd = request.getRequestDispatcher("subjectChoose.jsp");
                rd.include(request, response);
                
                String subject = request.getParameter("subject1");
                System.out.print("Valt subject: "+ subject);
                model.setChoosenSubject(subject);
                session.setAttribute("model", model);
                
                //session.setAttribute("model", model);
                
                  
        }catch(Exception e){
                System.out.println("choosenSubject" + e);
        }
    }
    
    private boolean checkLogIn(HttpServletRequest request, HttpServletResponse response){
        try{
            HttpSession session = request.getSession(true);
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String username =(String) request.getParameter("username");
            String password =(String) request.getParameter("password");
            Model model = (Model)session.getAttribute("model");
            boolean areWeLoggedIn = model.logIn(username, password);
            out.println("We are logged in (from check log in) = " + areWeLoggedIn);
            return areWeLoggedIn;
        }catch(Exception e){
            
        }
       return false;
    }
}