<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Number Guess Game</title>
</head>
<script type = "text/javascript"> function inputfocus(form){document.getElementById("field").value}</script>
<body>
<h><%= "Message to user" %>
</h>
<form name="guessform">
    <input type=text name=guess>
    <input type=submit value="Guess">
</form>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
