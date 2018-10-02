<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createSight" method="post">
    Название: <br>
    <input type="text" name="name"/> <br>
    Город: <br>
    <select name="city">
        <c:forEach var="city" items="${requestScope.cities}">
            <option value="${city.id}">${city.name}</option>
        </c:forEach>
    </select><br>
    Год постройки: <br>
    <input type="number" name="yearOfBuilding"/> <br>
    Категория: <br>
    <select name="category">
        <c:forEach var="category" items="${requestScope.categories}">
            <option value="${category.id}">${category.name}</option>
        </c:forEach>
    </select><br>
    Архитектурный стиль: <br>
    <select name="style">
        <c:forEach var="style" items="${requestScope.styles}">
            <option value="${style.id}">${style.name}</option>
        </c:forEach>
    </select><br>
    В списке ЮНЕСКО? <br>
    <input type="radio" name="isListUNESCO" value="${true}"> Да
    <input type="radio" name="isListUNESCO" value="${false}"> Нет <br>
    Рейтинг TripAdvisor: <br>
    <input type="number" name="ratingOfTripAdvisor" step="0.1"> <br>
    <input type="submit" value="Внести в базу">
</form>
</body>
</html>
