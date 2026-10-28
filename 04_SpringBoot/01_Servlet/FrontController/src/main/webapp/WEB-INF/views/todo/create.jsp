<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <h1>새 Todo 생성</h1>
    <form action="${pageContext.request.contextPath}/todo/create" method="POST">
        <input type="submit">
    </form>
  </body>
</html>
