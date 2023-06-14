<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/home.css">
</head>
<body>
<%@ include file = "/WEB-INF/jsp/header.jsp" %>
<div id = "alert">通知<input type="checkbox"></div>
<div id = "timer"><div id = "timer_img"></div><div id = "timer_text"></div></div>
<div id = "search_position">
<form action="/syuudeen/HomeServlet" method="POST">
<input type="submit" value="位置情報検索">
</form>
</div>
<div id = "plan"><table></table></div>
</body>
</html>