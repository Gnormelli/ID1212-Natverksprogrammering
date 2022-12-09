<%@page import="javafx.util.Pair"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quiz</title>
    </head>
<body>

    <h4>Quiz</h4>
    <form method="GET" action="form">
       
        
       <% List<Pair> allSubjects = (List) session.getAttribute("allSubjects"); 
            String subject;
            for(int i = 0; i < allSubjects.size(); i++){
                subject = (String) allSubjects.get(i).getValue();
                out.print("<p><input type='checkbox' value='" + subject + "'</p>" + subject);
           }
       %>
        
        
        
        
        
        
        <p><input type="submit" value="Skicka">
    </form>
</body>
</html>