<%@page import="db.Model"%>
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

    <h4>Choose subject: </h4>
    <form method="GET" action="/L4/DBServlet">
      
       <%  
            Model model = (Model)session.getAttribute("model");
            List<Pair> allSubjects = model.getSubjects(); 
            String subject;
            for(int i = 0; i < allSubjects.size(); i++){
                subject = (String) allSubjects.get(i).getValue();
                out.print("<p><input type='checkbox' name='subject1' value='" + subject + "'</p>" + subject);
           }
           


        
        List<Pair> scoresList = model.listOfScores;
        out.print("<p> Your previus scores </p>");
        for(Pair scoreSet : scoresList){

           out.print("<p> Subject: " + scoreSet.getKey() + "-> Score: "+scoreSet.getValue() +"</p>");
        
        }
        out.print("<p> ^- this is you latest score </p>");
       %>
       
        <p><input type="submit" value="Skicka">
    </form>
</body>
</html>