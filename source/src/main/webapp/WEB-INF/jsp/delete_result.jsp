<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Motta? | 削除完了</title>
<!-- ファビコン -->
	<link rel="icon" href="images/favicon.ico">
	<!-- 共通のCSS -->
	<link rel="stylesheet" href="/e1/css/style.css">
	<!-- 削除完了画面用CSS -->
<link rel="stylesheet" href="/e1/css/delete_result.css">
</head>
<body>

<header class="header">
    <a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>

    <nav class="nav">
        <ul>
            <li><a href="TopServlet">TOP</a></li>
            <li><a href="RegistServlet">登録</a></li>
            <li><a href="ListServlet">一覧</a></li>
			<li><a href="SearchServlet">検索</a></li>
			<li><a href="ChecklistServlet">チェックリスト</a></li>
			<li><a href="LogoutServlet" onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
        </ul>
    </nav>
</header>

<main class="complete-page">

    <!-- 背景丸 -->
    <div class="circle pink left-top"></div>
    <div class="circle pink right-top"></div>

    <div class="circle green center-left"></div>
    <div class="circle green right-bottom"></div>

    <div class="circle white left-bottom"></div>
    <div class="circle white right-center"></div>

    <div class="complete-box">
        <h2>削除完了しました！</h2>

        <a href="SearchServlet" class="back-btn">
            検索画面へ戻る
        </a>
    </div>

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