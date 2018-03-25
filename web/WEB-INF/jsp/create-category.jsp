<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/createCategory" method="post">
    Название категории: <br>
    <input type="text" name="name"/> <br>
    <input type="submit" value="Внести в базу">
</form>
</body>
</html>