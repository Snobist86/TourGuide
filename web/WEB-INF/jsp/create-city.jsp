<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createCity" method="post">
    Название города: <br>
    <input type="text" name="name"/> <br>
    Выберите страну: <br>
    <select name="country">
        <c:forEach var="country" items="${requestScope.countries}">
            <option value="${country.id}">${country.name}</option>
        </c:forEach>
    </select><br>
    <input type="submit" value="Внести в базу">
</form>
</body>
</html>
