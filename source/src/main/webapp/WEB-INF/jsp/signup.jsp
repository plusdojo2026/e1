<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">

<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Motta?｜新規登録</title>
<!-- 新規登録画面用CSS -->
<link rel="stylesheet" href="css/signup.css">
<link rel="icon" href="images/favicon.ico">
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

			<!-- スマホ用のMottaのロゴ -->
			<div class="mobile-logo">
				<img src="images/header_logo.png" alt="Motta" class="logo-image">
			</div>

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
							name="mail_address" required>
						<p class="field-error" id="mailError"></p>
						<%=request.getAttribute("mailError") != null ? request.getAttribute("mailError") : ""%>
					</div>

					<!-- 氏名入力欄 -->
					<div class="form-group3">
						<label>氏名</label> <input type="text" id="name" name="name"
							required>
						<p class="field-error" id="nameError"></p>
						<%= request.getAttribute("nameError") != null ? request.getAttribute("nameError") : "" %>
					</div>

					<!-- ID入力欄 -->
					<div class="form-group3">
						<label>ID</label> <input type="text" id="user_id" name="user_id"
							required>
						<p class="field-error" id="userIdError"></p>
						<%=request.getAttribute("userIdError") != null ? request.getAttribute("userIdError") : ""%>
					</div>

					<!-- パスワード入力欄 -->
					<div class="form-group2">
						<label>パスワード</label> <input type="password" id="password"
							name="password" required>
						<p class="field-error" id="passwordError"></p>
						<%=request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : ""%>
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