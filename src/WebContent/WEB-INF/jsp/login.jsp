<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="/syuudeen/css/common.css">
	</head>
	<body>
		<div id = "logo_login">logo</div>
			<form id="user_login" method="POST" action="/syuudeen/HomeServlet">
				<div id="user_login_area">
					<input type="text" id="user_id" placeholder="ID">
					<input type="text" id="user_pw" placeholder="パスワード">
					<input type="checkbox" id="pw_check_button">
				</div>
				<div id="login_button">
					<input type="submit" value="ログイン" id="login">
				</div>
				<div id="login_result_false"></div>
				<div>
				<a href="/simpleBC/UserRegisterServlet" id="register_link">新規登録はこちらから</a>
				</div>
			</form>
	</body>
</html>