<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    Пользователь: <input type="text" name="login"><br>
    Пароль: <input type="password" name="password"><br>
    Электронная почта: <input type="text" name="eMail"><br>
    <input type="submit" value="Login"> <br>
</form>
<form action="${pageContext.request.contextPath}/login" method="get">
    <button type="submit">Lod in</button>
</form>
</body>
</html>
