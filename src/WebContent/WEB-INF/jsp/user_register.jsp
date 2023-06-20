<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/common.css">
<script type="text/javascript" src="/syuudeen/js/user_register.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
<div id = "logo_register">logo</div>

<form id="user_register" method="POST" action="/syuudeen/UserRegisterServlet">

 <div id="user_register_area">
  <input type="text" placeholder="１文字以上１２文字以下の半角英数字で入力してください" id="user_id" name="user_id">
  <input type="text" placeholder="８文字以上２０文字以下の半角英数字で入力してください" id="user_pw" name="user_pw">
  <input type="checkbox" id="pw_check_button">
  <input type="text"  placeholder="パスワードをもう一度入力してください" id="pw_check" name="pw_check">
 </div>

 <div id="station_display_area">
  <div id="station">
  駅名を表示
 </div>
<input type="hidden" id="station_id" name="station_id" value="">
  <!-- onclickはjsです -->
   <input type="button" value="最寄り駅検索" onclick="search_position()">
   <label id="check_message">※自宅で登録してください</label>

 </div>

 <div id="register_result_false">${errMsg}</div>

 <input type="submit" value="登録" id="register" name="SUBMIT">

</form>

</body>
</html>