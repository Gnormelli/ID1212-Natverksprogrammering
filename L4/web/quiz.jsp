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
     <h4>Choose subject: </h4>
    <form method="GET" action="/L4/DBServlet">
      
       <% List<Question> quizList = (List) session.getAttribute("allSubjects"); 
            String text;
            for(Question item : quizList){
                text = item.getText();
                out.print("<p><input type='checkbox' name='subject1' value='" + subject + "'</p>" + subject);
           }
       %>
        <p><input type="submit" value="Skicka">
    </form>
</body>
</html>