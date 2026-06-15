<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Motta Signup</title>
<!-- 新規登録画面用CSS -->
<link rel="stylesheet" href="css/signup.css">
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

		<!-- 右側：新規登録フォームエリア -->
		<div class="right-panel">
			<div class="signup-box">

				<!-- 画面タイトル -->
				<h2>新規登録</h2>

				<!-- サーブレットから渡されたエラーメッセージ -->
				<p class="error-message" id="signupErrorMessage">
					<%=request.getAttribute("errorMsg") != null ? request.getAttribute("errorMsg") : ""%>
				</p>

				<!-- 新規登録フォーム -->
				<form id="signupForm" action="/e1/SignupServlet" method="post">

					<!-- メールアドレス入力欄 -->
					<div class="form-group3">
						<label>メールアドレス</label> <input type="email" id="mail_address"
							name="mail_address">

						<!-- JavaScript用エラー表示 -->
						<span id="mailError" class="error"></span>
					</div>

					<!-- 氏名入力欄 -->
					<div class="form-group3">
						<label>氏名</label> <input type="text" id="name" name="name">

						<!-- JavaScript用エラー表示 -->
						<span id="nameError" class="error"></span>
					</div>

					<!-- ID入力欄 -->
					<div class="form-group3">
						<label>ID</label> <input type="text" id="user_id" name="user_id">

						<!-- JavaScript用エラー表示 -->
						<span id="idError" class="error"></span>
					</div>

					<!-- パスワード入力欄 -->
					<div class="form-group2">
						<label>パスワード</label> <input type="password" id="password"
							name="password">

						<!-- JavaScript用エラー表示 -->
						<span id="passwordError" class="error"></span>
					</div>

					<!-- 登録ボタン -->
					<button class="signup-btn">登録</button>

					<!-- 区切り線 -->
					<div class="divider">
						<span>すでにアカウントをお持ちの方は</span>
					</div>

				</form>

				<!-- ログイン画面へ戻るボタン -->
				<button type="button" class="register-btn"
					onclick="location.href='/e1/LoginServlet'">

					ログイン画面に戻る <span>&gt;</span>

				</button>

			</div>
		</div>

	</div>

	<!-- 入力チェック用JavaScript -->
	<script src="js/signup.js"></script>

</body>
</html>