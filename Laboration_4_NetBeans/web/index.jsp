<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Guess a number</title>
    </head>
<body>
    <%  
        String name=request.getParameter("guess");  
        if(name == null){
            out.print("Welcome to the Number Guess Game. Guess a number between 1 and 100.");
        }
           
       
      
%>  
    <form action="guess" method="get">
        <input type="text" name="guess"><br>
        <input type="submit">
    </form>
</body>
</html>