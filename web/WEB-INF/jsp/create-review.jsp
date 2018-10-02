<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createReview?=${param.id}" method="post">
    Ваш комментарий: <br>
    <input type="text" name="value"/> <br>
    <input type="submit" value="Отправить">
</form>
</body>
</html>
