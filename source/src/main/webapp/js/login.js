// HTMLの読み込み完了後に処理を開始
document.addEventListener("DOMContentLoaded", function() {

	// ログインフォームを取得
	const form = document.getElementById("loginForm");

	// 入力を始めたらログイン失敗メッセージを消す
	document.getElementById("user_id").addEventListener("input", function() {
		document.getElementById("loginErrorMessage").textContent = "";
	});

	document.getElementById("password").addEventListener("input", function() {
		document.getElementById("loginErrorMessage").textContent = "";
	});

	// フォーム送信時の処理
	form.addEventListener("submit", function(event) {

		// 入力されたIDとパスワードを取得
		const userId = document.getElementById("user_id").value;
		const password = document.getElementById("password").value;

		// エラーフラグ
		let hasError = false;

		// 前回表示されたエラーメッセージをクリア
		document.getElementById("idError").textContent = "";
		document.getElementById("passwordError").textContent = "";

		// ログインIDが未入力の場合
		if (userId.trim() === "") {
			document.getElementById("idError").textContent =
				"ログインIDを入力してください。";
			hasError = true;
		}

		// パスワードが未入力の場合
		if (password.trim() === "") {
			document.getElementById("passwordError").textContent =
				"パスワードを入力してください。";
			hasError = true;
		}

		// 入力エラーがある場合はフォーム送信を中止
		if (hasError) {
			event.preventDefault();
		}

		// ログイン失敗時のメッセージ表示
		const loginError = document.getElementById("loginError");

		// サーバー側からログイン失敗フラグが渡された場合
		if (loginError && loginError.value === "true") {
			document.getElementById("loginErrorMessage").textContent =
				"ログインIDまたはパスワードに誤りがあります。";
		}
	});

});