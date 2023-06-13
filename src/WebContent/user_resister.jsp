<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id = "logo_register">logo</div>

<div id="user_register_area">
<input type="text" id="user_id">
<input type="text" id="user_pw">
<input type="checkbox" id="pw_check_button">
<input type="text" id="pw_check">
</div>

<div id="station_display_area">
 <div id="station_display">
  <label>駅名を表示</label>
 </div>

 <div id="station_home">
  <input type="submit" value="最寄り駅検索">
  <label id="check_message">※自宅で登録してください</label>
 </div>
</div>

<div id="register_result_false">
</div>

<input type="submit" value="登録" id="register">

</body>
</html>