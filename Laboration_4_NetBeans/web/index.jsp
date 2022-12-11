<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Guess a number</title>
    </head>
<body>
    <%  
        
        String messageToUser = (String) session.getAttribute("messageToUser");
        out.print(messageToUser);
        
           
       
      
%>  
    <form action="guess" method="get">
        <input type="text" name="guess"><br>
        <input type="submit">
    </form>
</body>
</html>