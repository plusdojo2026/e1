<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="images/favicon.ico">
<link rel="stylesheet" href="/e1/css/style.css">
<link rel="stylesheet" href="/e1/css/regist.css">
</head>
<body>
<!-- ワッパー -->
	<div class="wrapper">

	<!-- ヘッダー -->
	<header class="header">
		<a href="TopServlet"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
			<!-- ハンバーガーボタン -->
		<div class="container">
		    <div class="hamburger-menu">
			    <div class="line"></div>
			    <div class="line"></div>
			    <div class="line"></div>
		  	</div>


			<nav class="nav">
				<ul>
					<li><a href="TopServlet">TOP</a></li>
					<li><a class="active" href="RegistServlet">登録</a></li>
					<li><a href="ListServlet">一覧</a></li>
					<li><a href="SearchServlet">検索</a></li>
					<li><a  href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="LogoutServlet"
						onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	
  <!-- 背景丸 -->
    <div class="circle pink left-top"></div>
    <div class="circle pink right-top"></div>

    <div class="circle green center-left"></div>
    <div class="circle green right-bottom"></div>

    <div class="circle white left-bottom"></div>
    <div class="circle white right-center"></div>

<!-- メイン -->
<main class="regist-page">
<form id="regist_form" action="RegistServlet" method="post">
<!-- 名称入力 -->
<input type="text" name="item_name" id="item_name" placeholder="名称" required>

<!-- 日付入力 -->
<input type="date" id="date" name="lost_date" required>

<!-- 天気入力 -->
 <select name="weather">
    <option value="${item.weather == '晴れ'}">晴れ</option>
    <option value="曇り">曇り</option>
    <option value="雨">雨</option>
    <option value="雪">雪</option>
</select>

 <!-- 発生場所-->
<input type="text" id="location" name="location" placeholder="発生場所" required>

<!-- 原因入力 -->
<textarea name="reason" placeholder="原因"></textarea>

<div class="button-area">
				<input type="submit" class="regist-btn" name="regist"	value="登録">

				<input type="reset" class="reset-btn" name="reset" value="リセット">
			</div>
<span id="error_message"></span>
</form>
</main>
</div>

<script>

'use strict';
let formObj = document.getElementById('regist_form');
let errorMessageObj = document.getElementById('error_message');
/* ハンバーガーメニュー */
var hamburger = document.querySelector('.hamburger-menu');
var nav = document.querySelector('.nav');

hamburger.addEventListener('click', function () {
  hamburger.classList.toggle('active');
  nav.classList.toggle('active');
});
/*入力フォーム*/
formObj.onsubmit = function(event) {

	if (!formObj.item_name.value) {
		errorMessageObj.textContent = '※名称を入力してください！';
		event.preventDefault();
		} 
	 if (!formObj.lost_date.value){
		   errorMessageObj.textContent = '※日付を入力してください！';
		   errorMessageObj.textContent = '';
		   event.preventDefault();
		}
		if (!formObj.location.value){
		errorMessageObj.textContent = '※発生場所を入力してください！';
		errorMessageObj.textContent = '';
		event.preventDefault();
		}  
		
		}


/* リセット */
formObj.onreset = function() {
  window.confirm('リセットしますか?');
  /* エラーメッセージを消します */
  errorMessageObj.textContent = null;
};
</script>

</body>
</html>