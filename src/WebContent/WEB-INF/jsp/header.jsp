<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/syuudeen/css/common.css">
<script type="text/javascript" defer src="/syuudeen/js/common.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<div class="content">
		<div class="inner">
			<span id="home"><a href="/syuudeen/HomeServlet"><svg
						xmlns="http://www.w3.org/2000/svg" fill="none"
						stroke="currentColor" viewBox="0 0 24 24"
						class="humbleicons hi-home">
						<path xmlns="http://www.w3.org/2000/svg" stroke="currentColor"
							stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
							d="M6 10v9a1 1 0 001 1h10a1 1 0 001-1v-9M6 10l6-6 6 6M6 10l-2 2m14-2l2 2m-10 1h4v4h-4v-4z" /></svg></a></span>
			<span id="logo"><img src="/syuudeen/img/syuudeen-logo.png"></span>
			<span class="menu_2"> <span class="menu"><img
					class="menu_open" src="/syuudeen/img/ham-menu-open.png"> <img
					class="menu_close" src="/syuudeen/img/ham-menu-close.png"> </span>
			</span>
		</div>
	</div>

	<nav class="menu_list">
		<ul>
			<li><a href="/syuudeen/ChangeNearestServlet">最寄り駅変更</a></li>
			<li><a href="/syuudeen/CalenderServlet">カレンダー</a></li>
		</ul>
	</nav>

</body>
</html>