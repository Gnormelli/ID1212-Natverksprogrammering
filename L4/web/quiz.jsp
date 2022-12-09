<%@page import="db.Model"%>
<%@page import="db.Question"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Play Quiz</title>
    </head>
<body>

    <h4>Quiz</h4>
     
    <form method="GET" action="/L4/DBServlet">
      
        
           <% 
              Model model = (Model) session.getAttribute("model"); 
               
              String text;
              for(Question item : model.quiz){
                out.print("<p>"+ item.getText()+" </p>");

                for (String option : item.getOptionsArray())
                    out.print("<p><input type='checkbox' name='AnswerToQuestion_"+ item.getId()  +  "' value='" + option + "'</p>" + option);
                }

              
              List<Question> quiz;
       
       %>   
        
       
        <p><input type="submit" name="quizAnswerd" value="true">
    </form>
</body>
</html>
      