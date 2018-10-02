<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Добро пожаловать, ${sessionScope.currentUser.login}!<br>
Здесь ты можешь найти интересные места<br>
и узнать что-то новое о них.<br></h1>
<form action="${pageContext.request.contextPath}/searchSight" method="get">
    <button type="submit">Поиск достопримечательностей</button>
</form>
<form action="${pageContext.request.contextPath}/searchAirport" method="get">
    <button type="submit">Поиск аэропортов</button>
</form>
<form action="${pageContext.request.contextPath}/listUnesco" method="get">
    <button type="submit">Список ЮНЕСКО</button>
</form>
<c:if test="${not empty sessionScope.currentUser and sessionScope.currentUser.getRoleId() == 2}">
<form action="${pageContext.request.contextPath}/create" method="get">
    <button type="submit">Это только для админа</button>
</form>
</c:if>
<form action="${pageContext.request.contextPath}/logout" method="get">
    <button type="submit">Выйти</button>
</form>
</body>
</html>
