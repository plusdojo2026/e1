<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="images/favicon.ico">
<link rel="stylesheet" href="/e1/css/style.css">
<link rel="stylesheet" href="/e1/css/alert.css">
<title>Motta?｜通知設定</title>
</head>
<body>
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
					<li><a href="RegistServlet">登録</a></li>
					<li><a href="ListServlet">一覧</a></li>
					<li><a href="SearchServlet">検索</a></li>
					<li><a href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="LogoutServlet"
						onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<!-- メイン -->
	<main>
	<form class="date-area" action="AlertServlet" method="post">

    	<label>通知日</label>
        <input type="date" name="date" required>

        <label class="time-label">通知時刻</label>
        <input type="time" name="time" required>

        <div class="button-area">
        	<button type="submit" class="btn-register">登録</button>
            <button type="reset" class="btn-reset">リセット</button>
        </div>
	</form>  
	</main>   
	<script>
	/* ハンバーガーメニュー */
	var hamburger = document.querySelector('.hamburger-menu');
	var nav = document.querySelector('.nav');
	
	hamburger.addEventListener('click', function () {
	  hamburger.classList.toggle('active');
	  nav.classList.toggle('active');
	});
	</script>
	</body> 
</html>