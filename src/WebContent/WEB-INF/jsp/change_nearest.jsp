<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/change_nearest.css">
</head>
<body>
<%@ include file = "/header.jsp" %>

<div id = "user_id">ID：</div>
<form method = "POST" action = "/syuudeen/ChangeNearestServlet">
<div id = "change_check">変更する<input type = "checkbox"></div>

<div id = "station">
  <label>ここに駅名表示</label>
</div>

<div id = "search_position">
  <input type = "button" value = "最寄り駅検索" onclick="">
</div>

<div id = "check_message">※自宅で登録してください</div>

<div id = "update">
  <input type = "submit" value = "更新">
</div>

</form>
</body>
</html>