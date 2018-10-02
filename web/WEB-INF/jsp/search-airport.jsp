<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/searchAirport" method="post">
Страна: <br>
<select name="country">
    <option value="">${"любая страна"}</option>
    <c:forEach var="country" items="${requestScope.countries}">
        <option value="${country.id}">${country.name}</option>
    </c:forEach>
</select><br>
Город: <br>
<select name="city">
    <option value="">${"любой город"}</option>
    <c:forEach var="city" items="${requestScope.cities}">
        <option value="${city.id}">${city.name}</option>
    </c:forEach>
</select><br>
<input type="submit" value="Найти"><br>
<c:forEach items="${requestScope.airports}" var="airport">
    ${airport.name}<br>
    ${airport.city}<br>
    <br>
</c:forEach>
</form>
<form action="${pageContext.request.contextPath}/main" method="get">
    <button type="submit">На главную страницу</button>
</form>
</body>
</html>
