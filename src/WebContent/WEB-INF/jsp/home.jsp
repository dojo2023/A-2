<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/home.css">
<script type="text/javascript" src="/syuudeen/js/home.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
<%@ include file = "/WEB-INF/jsp/header.jsp" %>
<div id = "alert">通知<input type="checkbox" name="alert_check" id="alert_check" onchange="notice()"></div>
<div id = "timer"><div id = "timer_img"></div><div id = "timer_text"></div></div>
<div id = "search_position_button">
<form action="/syuudeen/HomeServlet" method="POST">
<input type="submit" value="位置情報検索" id="search_position" name="search_position" onclick="position()">
<input type="hidden" id="hidden_position" name="hidden_position" value="">
</form>
</div>
<div id = "plan"><table></table></div>
</body>
</html>