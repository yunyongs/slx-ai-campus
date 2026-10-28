<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>FrontController</title>
</head>
<body>
<h1>FrontController</h1>
<p>모든 요청은 FrontControllerServlet을 거쳐 처리됩니다.</p>
<div>
    <a href="${pageContext.request.contextPath}/todo/create">새 Todo</a>
</body>
</html>
