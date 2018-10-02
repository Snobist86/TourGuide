<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/searchUser" method="get">
    Пользователь: <br>
    <input type="text" name="name"/> <br>
    <input type="submit" value="Найти"><br>
    <c:forEach items="${requestScope.sights}" var="sight">
        <a>${sight.name}</a><br>
    </c:forEach>
</form>
</body>
</html>
