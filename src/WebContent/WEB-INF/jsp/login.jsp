<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン | syuudeen</title>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/syuudeen/css/common.css">
<link rel="stylesheet" type="text/css" href="/syuudeen/css/login.css">
<script type="text/javascript" src="/syuudeen/js/login.js"></script>
</head>
<body>
	<div id="logo_login">
		<img src="/syuudeen/img/syuudeen-logo.png">
	</div>
	<form id="user_login" method="POST" action="/syuudeen/LoginServlet">
		<div id="user_login_area">

			<div class="form_group">
				<input type="text" placeholder="ID" id="user_id" name="user_id"
					value="${param.user_id }"> <label for="id"
					class="form_label">ID</label>
			</div>

			<div class="form_group">

				<input type="password" id="user_pw" placeholder="パスワード"
					name="user_pw"> <span id="pw_check_button"
					class="fa fa-eye-slash" onclick="pushHideButton()"></span> <label
					for="pw" class="form_label">パスワード</label>

			</div>
		</div>
		<div id="emg">${result}</div>

		<div id="login_button">
			<input type="submit" value="ログイン" id="login">
		</div>
		<div id="login_result_false"></div>
		<div id="register_link">
			<a href="/syuudeen/UserRegisterServlet" id="register_link">新規登録はこちらから</a>
		</div>
	</form>
</body>
</html>