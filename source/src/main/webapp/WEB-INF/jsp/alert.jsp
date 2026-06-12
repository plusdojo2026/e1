<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
	<title>通知設定</title>
	<link rel="stylesheet" href="/e1/css/alert.css">
</head>
<body>
	<!-- ヘッダー -->
	<header class="header">
		<a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
		<nav class="nav">
			<ul>
				<li><a href="TopServlet">TOP</a></li>
				<li><a href="#">登録</a></li>
				<li><a href="ListServlet">一覧</a></li>
				<li><a href="SearchServlet">検索</a></li>
				<li><a href="ChecklistServlet">チェックリスト</a></li>
				<li><a href="LogoutServlet" onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
			</ul>
		</nav>
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
	</body> 
</html>