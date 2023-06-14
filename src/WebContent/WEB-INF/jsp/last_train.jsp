<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>終電表示画面</title>
<link rel="stylesheet" href="/syuudeen/css/common.css">
</head>
<body>
<%@ include file = "/header.jsp" %>
<div id = guide>本日の終電時刻は</div>
<div id = station></div>
<div id = start_time></div>
<form action = "/syuudeen/LastTrainServlet" method = "POST"><div id = "home_back"><input type="submit" value="ホームに戻る"></div></form>
</body>
</html>