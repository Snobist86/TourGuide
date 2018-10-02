<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

 <label>Название: ${requestScope.sight.name}</label><br>
 <label>Город: ${requestScope.sight.city}</label><br>
 <label>Год постройки: ${requestScope.sight.yearOfBuilding}</label><br>
 <label>Категория: ${requestScope.sight.category}</label><br>
 <label>Стиль: ${requestScope.sight.style}</label><br>
 <label>В списке ЮНЕСКО: ${requestScope.sight.isListUNESCO eq true ? 'Да' : 'Нет'}</label><br>
 <label>Рейтинг TripAdvisor: ${requestScope.sight.ratingOfTripAdvisor}</label><br>

 <form action="${pageContext.request.contextPath}/viewReview?id=${param.id}" method="post">
  <input type="submit" value="Все комментарии">
 </form>
 
 <form action="${pageContext.request.contextPath}/createReview?id=${param.id}" method="post">
  Ваш комментарий: <br>
  <label>
   <input type="text" name="value"/>
  </label> <br>
  <input type="submit" value="Отправить">
 </form>
</body>
</html>
