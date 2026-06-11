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
	<header class="header">
		<a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
        <nav class="nav">
        	<ul>
				<li><a href="#">TOP</a></li>
				<li><a href="#">登録</a></li>
				<li><a href="#">一覧</a></li>
				<li><a href="#">検索</a></li>
				<li><a href="#">チェックリスト</a></li>
				<li><a href="#">ログアウト</a></li>
			</ul>
		</nav>
	</header>
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
	</body> 
</html>