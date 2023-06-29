<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name=”viewport” content=”width=device-width,initial-scale=1.0″>
<title>終電表示 | Syuudeen</title>
<link rel="stylesheet" href="/syuudeen/css/last_train.css">
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div id=guide>本日の終電時刻は</div>
	<div id=station>${lineName}${stationName}</div>
	<div id=start_time>${startTime}</div>
	<%
		request.setAttribute("startTime", request.getAttribute("startTime"));
	%>
	<div id="back_form">
		<form action="/syuudeen/LastTrainServlet" method="POST">
			<div id="home_back">
				<input type="submit" class="btn_back" value="ホームに戻る">
			</div>
		</form>
	</div>
</body>
</html>