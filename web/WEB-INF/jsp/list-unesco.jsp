<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>Достопримечательности вошедшие<br>
    список Всемирного наследия ЮНЕСКО:<br></h4>
<c:forEach items="${requestScope.sights}" var="sight">
    <a href="${pageContext.request.contextPath}/viewSight?id=${sight.id}">${sight.name}</a><br>
</c:forEach>
</body>
</html>
