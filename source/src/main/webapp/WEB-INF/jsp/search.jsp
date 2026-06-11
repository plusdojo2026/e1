<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
	<title>検索</title>
	<link rel="stylesheet" href="/e1/css/search.css">
</head>

<body>
	<header class="header">
		<a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
		<nav class="nav">
			<ul>
				<li><a href="#">TOP</a></li>
				<li><a href="#">登録</a></li>
				<li><a href="#">一覧</a></li>
				<li><a href="#" class="active">検索</a></li>
				<li><a href="#">チェックリスト</a></li>
				<li><a href="#">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<main class="search-page">
		<!-- 検索フォーム -->
		<form class="search-form" action="SearchServlet" method="post">

			<input type="text" name="name" placeholder="名称">
			<input type="text" name="location" placeholder="発生場所">
			<input type="date" name="date" class="date-input">

			<div class="button-area">
				<button type="submit" class="search-btn">検索</button>

				<button type="reset" class="reset-btn">リセット</button>
			</div>
		</form>
	</main>
</body>
</html>