<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="get">
<h2>Что нового вы хотите внести в базу данных?</h2>
    <a href="${pageContext.request.contextPath}/createCountry">${'Страну'}</a><br>
    <a href="${pageContext.request.contextPath}/createCity">${'Город'}</a><br>
    <a href="${pageContext.request.contextPath}/createAirport">${'Аэропорт'}</a><br>
    <a href="${pageContext.request.contextPath}/createSight">${'Достопримечательность'}</a><br>
    <a href="${pageContext.request.contextPath}/createCategory">${'Категорию'}</a><br>
    <a href="${pageContext.request.contextPath}/createStyle">${'Стиль'}</a><br>
    <a href="${pageContext.request.contextPath}/changeRole">${'Изменить статус пользователя'}</a><br>
</form>
</body>
</html>
