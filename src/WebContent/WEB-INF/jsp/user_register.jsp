<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta  name=”viewport” content=”width=device-width,initial-scale=1.0″>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/common.css">
<link rel="stylesheet" type="text/css" href="/syuudeen/css/user_resister.css">
<script type="text/javascript" src="/syuudeen/js/user_register.js"></script>
<link href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

</head>
<body>
<div id = "logo_register"><img src="/syuudeen/img/syuudeen-logo.png"></div>

<form id="user_register" method="POST" action="/syuudeen/UserRegisterServlet">

 <div id="user_register_area">
 
  <div class="form_group">
  <input type="text" placeholder="１～１２文字の半角英数字で入力してください" id="user_id" name="user_id"value="${param.user_id }">
  <label for="id" class="form_label">ID</label>
  <span class="focus_line"></span>
  <div id="anim">
    <span class="tooltip" data-tooltip="１～１２文字の半角英数字で入力してください">?</span>
    </div>
  </div>
  
  <div class="form_group">
  <input type="password" placeholder="８～２０文字の半角英数字で入力してください" id="user_pw" name="user_pw">
  <div id="anim">
    <span class="tooltip" data-tooltip="８～２０文字の半角英数字で入力してください">?</span>
    </div>
  <label for="id" class="form_label">パスワード</label>
  </div>
  
  <div class="form_group">
  <input type="password"  placeholder="パスワードをもう一度入力してください" id="pw_check" name="pw_check">
  <span id="pw_check_button" class="fa fa-eye" onclick="pushHideButton()"></span>
  <label for="id" class="form_label">パスワードチェック</label>
  </div>
  </div>
 <div id="station_display_area">
  <div id="station"> 
 </div>
<input type="hidden" id="station_id" name="station_id" value="">
  <!-- onclickはjsです -->
   <input type="button" value="最寄り駅検索" onclick="search_position()" id = "search_button" >
   
   <div id="register_result_false">${errMsg}</div>
   
   <div><label id="check_message">※自宅で登録してください</label></div>

 </div>

 <input type="submit" value="登録" id="register" name="SUBMIT">

</form>

</body>
</html>