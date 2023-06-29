<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>最寄り駅変更 | Syuudeen</title>
<link rel="stylesheet" type="text/css"
	href="/syuudeen/css/change_nearest.css">
<script type="text/javascript" src="/syuudeen/js/change_nearest.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div id="user_id" name="user_id">
		<span id="user">ID：${userId}</span>
	</div>

	<div id="near_station">

		<form method="POST" action="/syuudeen/ChangeNearestServlet">
			<div id="change_check">
				変更<%@ include file="/WEB-INF/jsp/toggle.jsp"%></div>

			<div id="staiton_search_position">
				<div id="station">${stationHomeName}</div>
				<input type="hidden" id="station_id" name="station_id"
					value="${stationHome}">

				<div id="search_position">
					<input type="button" id="search_button" name="SUBMIT"
						value="最寄り駅検索" onclick="search_position()" disabled>
				</div>
			</div>

			<div id="check_message">※自宅で登録してください</div>

			<div id="update">
				<input type="submit" id="SUBMIT" name="SUBMIT" value="更新">
			</div>

		</form>
	</div>


</body>
</html>