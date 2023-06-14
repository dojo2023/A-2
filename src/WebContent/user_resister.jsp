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
<div id = "logo_register">logo</div>

<form id="user_register" method="POST" action="/syuudeen/HomeServlet">

 <div id="user_register_area">
  <input type="text" placeholder="１文字以上１２文字以下の半角英数字で入力してください" id="user_id">
  <input type="text" placeholder="８文字以上２０文字以下の半角英数字で入力してください" id="user_pw">
  <input type="checkbox" id="pw_check_button">
  <input type="text"  placeholder="パスワードをもう一度入力してください" id="pw_check">
 </div>

 <div id="station_display_area">
  <div id="station_display">
  <label>駅名を表示</label>
 </div>

  <!-- onclickはjsです -->
   <input type="button" value="最寄り駅検索" onclick="goAjax()">
   <label id="check_message">※自宅で登録してください</label>

 </div>

 <div id="register_result_false"></div>

 <input type="submit" value="登録" id="register">

</form>

</body>
</html>