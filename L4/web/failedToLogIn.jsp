<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Quiz</title>
    </head>
<body>

    <h4>Login</h4>
    <form method="GET" action="/L4/DBServlet">
        <p>Username <input type="text" name="username" size="16" maxlength="16">
        <p>Password <input type="text" name="password" size="16" maxlength="16">  
        <p><input type="submit" value="Skicka">
    </form>
</body>
</html>