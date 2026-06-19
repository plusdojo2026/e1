<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Motta?｜検索</title>
	<!-- ファビコン -->
	<link rel="icon" href="images/favicon.ico">
	<!-- 共通のCSS -->
	<link rel="stylesheet" href="/e1/css/style.css">
	<!-- 検索画面用CSS -->
	<link rel="stylesheet" href="/e1/css/search.css">
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
		  	<!-- ナビゲーション -->
			<nav class="nav">
				<ul>
					<li><a href="TopServlet">TOP</a></li>
					<li><a href="RegistServlet">登録</a></li>
					<li><a href="ListServlet">一覧</a></li>
					<li><a class="active" href="SearchServlet">検索</a></li>
					<li><a href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="LogoutServlet"
						onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<!-- メイン -->
	<main class="search-page">
		<!-- 検索フォーム -->
		<form class="search-form" action="SearchServlet" method="post">
			<input type="text" name="name" placeholder="名称">
			<input type="text" name="location" placeholder="発生場所">
			<div class="date-range">
			    <input type="date" name="startDate" class="date-input">
			    <span>～</span>
			    <input type="date" name="endDate" class="date-input">
			</div>
			<!-- ボタン -->	
			<div class="button-area">
				<button type="submit" class="search-btn">検索</button>

				<button type="reset" class="reset-btn">リセット</button>
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
	// 入力後の文字色変更
	document.querySelectorAll('.date-input').forEach(function(input) {

		function updateColor() {
			if (input.value) {
				input.classList.add("filled");
			} else {
				input.classList.remove("filled");
			}
		}

		updateColor();

		input.addEventListener("change", updateColor);
	});
	</script>
</body>
</html>