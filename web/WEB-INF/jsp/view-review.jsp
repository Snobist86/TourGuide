<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="lib-content.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form>
<c:forEach items="${requestScope.reviews}" var="review">
    пользователь: ${review.user}<br>
    <a href="${pageContext.request.contextPath}/viewSight?id=${review.sightId}">${review.value}</a><br>
</c:forEach>
</form>
</body>
</html>
