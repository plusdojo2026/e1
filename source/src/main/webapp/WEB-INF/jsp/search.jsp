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
	<!-- ヘッダー -->
	<header class="header">
		<a href="TopServlet"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
		<nav class="nav">
			<ul>
					<li><a href="TopServlet">TOP</a></li>
					<li><a href="RegistSevlet">登録</a></li>
					<li><a href="ListServlet">一覧</a></li>
					<li><a href="SearchServlet" class="active">検索</a></li>
					<li><a href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="Logout">ログアウト</a></li>
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