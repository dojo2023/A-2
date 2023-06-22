<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/home.css">
<script type="text/javascript" defer src="/syuudeen/js/home.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/push.js/1.0.12/push.min.js"></script>
</head>
<body>
<%@ include file = "/WEB-INF/jsp/header.jsp" %>

<div id = "alert">通知
<%@ include file = "/WEB-INF/jsp/toggle.jsp" %>
</div>

<div id = "timer"><div id = "timer_img"></div><div id = "timer_text"></div></div>
<input type="hidden" id="hidden_time" value="${startTime}">
<div id = "search_position_button">
<form name="search_form" action="/syuudeen/HomeServlet" method="POST">
<input type="button" value="位置情報検索" id="search_position" name="search_position" onclick="position()">
<input type="hidden" id="hidden_position" name="hidden_position" value="">
</form>

 <input type="button" id="push" onclick="return push()" value="クリックするとプッシュ通知が送られます">

</div>
<div id = "plan"><table></table></div>
</body>
</html>