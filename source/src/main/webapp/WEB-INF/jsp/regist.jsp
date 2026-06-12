<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head>
<meta charset="UTF-8">
<title>忘れ物登録 | Motta?</title>
<link rel="stylesheet" href="css/regist.css">
</head>

<body>


<!-- ヘッダー -->
<header class="header">
<h1 class="logo">Motta?</h1>




<nav class="nav">
<ul>
<li><a href="#">TOP</a></li>
<li class="active"><a href="#">登録</a></li>
<li><a href="#">一覧</a></li>
<li><a href="#">検索</a></li>
<li><a href="#">チェックリスト</a></li>
<li><a href="#">ログアウト</a></li>
</ul>
</nav>
</header>

<!-- メイン -->
<main>

<form id="regist_form" action="Motta_list.html">
<br>
<!-- 名称入力 -->
<input type="text" name="name" placeholder="名称">
<label>
<br>
<!-- 発生場所-->
<input type="text" name="location" placeholder="発生場所">
</label>
<label>
<br>
<!-- 日付入力 -->
<input type="date" name="date">
</label>
<!-- 天気入力 -->
<label>
<br>
 <select name="weather" form="weather">
    <option value="1">晴れ</option>
    <option value="2">曇り</option>
    <option value="3">雨</option>
    <option value="4">雪</option>
 </select>
</label>

<!-- 原因入力 -->
<label>
<br>
<input type="text" name="reason" class="cause" placeholder="原因">
</label>

<h3>
<!-- 登録ボタン -->
<input type="submit" value="登録">
<!-- リセットボタン -->
<input type="submit" value="リセット">
</h3>
<span id="error_message"></span>
</form>
</main>


<script>
'use strict';
let formObj = document.getElementById('login_form');
let errorMessageObj = document.getElementById('error_message');

/* 登録ボタン */
formObj.onsubmit = function(event) {

	if (!formObj.name.value) {
		errorMessageObj.textContent = '※名称を入力してください！';
		event.preventDefault();
		} 
		if (!formObj.location.value){
		errorMessageObj.textContent = '※発生場所を入力してください！';
		errorMessageObj.textContent = '';
		event.preventDefault();
		}  
		if (!formObj.date.value){
		   errorMessageObj.textContent = '※日付を入力してください！';
		   errorMessageObj.textContent = '';
		   event.preventDefault();
		}
		};


/* リセット */
formObj.onreset = function() {
  window.confirm('リセットしますか?');
  /* エラーメッセージを消します */
  errorMessageObj.textContent = null;
};
</script>

</body>
</html>