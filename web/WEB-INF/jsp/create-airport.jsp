<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createAirport" method="post">
    Название аэропорта: <br>
    <input type="text" name="name"/> <br>
    Выберите город: <br>
    <select name="city">
        <c:forEach var="city" items="${requestScope.cities}">
            <option value="${city.id}">${city.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Внести в базу">
</form>
</body>
</html>
