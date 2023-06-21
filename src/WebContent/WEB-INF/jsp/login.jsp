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
			<form id="user_login" method="POST" action="/syuudeen/LoginServlet">
				<div id="user_login_area">
					<input type="text" id="user_id" placeholder="ID" name="user_id" value="${param.user_id }">
					<input type="text" id="user_pw" placeholder="パスワード" name="user_pw">
					<input type="checkbox" id="pw_check_button">
					${result}
				</div>
				<div id="login_button">
					<input type="submit" value="ログイン" id="login">
				</div>
				<div id="login_result_false"></div>
				<div>
				<a href="/syuudeen/UserRegisterServlet" id="register_link">新規登録はこちらから</a>
				</div>
			</form>
	</body>
</html>