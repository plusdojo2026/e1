<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Motta Login</title>

<!-- ログイン画面用CSS -->
<link rel="stylesheet" href="css/login.css">
</head>
<body>

	<!-- 画面全体 -->
	<div class="container">

		<!-- 左側エリア -->
		<div class="left-panel">
			<div class="logo-area">

				<!-- Mottaのロゴ -->
				<img src="images/motta-logo.png" alt="Motta" class="logo-image">

			</div>
		</div>

		<!-- 右側：ログインフォームエリア -->
		<div class="right-panel">
			<div class="login-box">

				<!-- 画面タイトル -->
				<h2>ログイン</h2>

				<!-- サーブレットから渡されたエラーメッセージ -->
				<p class="error-message">
					<%=request.getAttribute("errorMsg") != null ? request.getAttribute("errorMsg") : ""%>
				</p>

				<!-- ログインフォーム -->
				<form id="loginForm" action="/e1/LoginServlet" method="post">

					<!-- ID入力欄 -->
					<div class="form-group1">
						<label>ID</label> <input type="text" id="user_id" name="user_id">

						<!-- JavaScript入力チェック用 -->
						<span id="idError" class="error"></span>
					</div>

					<!-- パスワード入力欄 -->
					<div class="form-group2">
						<label>パスワード</label> <input type="password" id="password"
							name="password">

						<!-- JavaScript入力チェック用 -->
						<span id="passwordError" class="error"></span>
					</div>

					<!-- ログインボタン -->
					<button class="login-btn">ログイン</button>

				</form>

				<!-- 区切り線 -->
				<div class="divider">
					<span>または</span>
				</div>

				<!-- 新規登録画面へ戻るボタン -->
				<button type="button" class="register-btn"
					onclick="location.href='/e1/SignupServlet'">

					新規登録はこちら <span>&gt;</span>

				</button>

			</div>
		</div>

	</div>

	<!-- 入力チェック用JavaScript -->
	<script src="js/login.js"></script>

</body>
</html>