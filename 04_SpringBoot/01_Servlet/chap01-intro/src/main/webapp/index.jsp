<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<br/>
<a href="life-cycle">Life Cycle</a>
<h4>입력값을 GET 또는 POST 방식으로 전송</h4>
<form action="service" method="get">
    <label for="message">전송할 값:</label>
    <input type="text" id="message" name="message" required>

    <button type="submit" formmethod="get">GET 방식으로 전송</button>
    <button type="submit" formmethod="post">POST 방식으로 전송</button>
</form>
</body>
</html>
