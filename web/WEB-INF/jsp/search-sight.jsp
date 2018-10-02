<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/searchSight" method="post">
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
    Категория: <br>
    <select name="category">
        <option value="">${"любая категорию"}</option>
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>
    Архитектурный стиль: <br>
    <select name="style">
        <option value="">${"любой стиль"}</option>
        <c:forEach var="style" items="${requestScope.styles}">
            <option value="${style.id}">${style.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Найти"><br>
    <c:forEach items="${requestScope.sights}" var="sight">
        <a href="${pageContext.request.contextPath}/viewSight?id=${sight.id}">${sight.name}</a><br>
    </c:forEach>
</form>
<form action="${pageContext.request.contextPath}/main" method="get">
    <button type="submit">На главную страницу</button>
</form>
</body>
</html>
