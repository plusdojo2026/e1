// HTMLの読み込み完了後に処理を開始
document.addEventListener("DOMContentLoaded", function() {

	// 新規登録フォームを取得
	const form = document.getElementById("signupForm");

	// 入力を始めたら新規登録失敗メッセージを消す
	document.getElementById("mail_address").addEventListener("input", function() {
		document.getElementById("signupErrorMessage").textContent = "";
	});

	document.getElementById("name").addEventListener("input", function() {
		document.getElementById("signupErrorMessage").textContent = "";
	});

	document.getElementById("user_id").addEventListener("input", function() {
		document.getElementById("signupErrorMessage").textContent = "";
	});

	document.getElementById("password").addEventListener("input", function() {
		document.getElementById("signupErrorMessage").textContent = "";
	});

	// フォーム送信時の処理
	form.addEventListener("submit", function(event) {

		// 入力されたメールアドレス・氏名・ID・パスワードを取得
		const mailAddress =
			document.getElementById("mail_address").value;

		const name =
			document.getElementById("name").value;

		const userId =
			document.getElementById("user_id").value;

		const password =
			document.getElementById("password").value;

		// エラーフラグ
		let hasError = false;

		// 前回表示された入力エラーメッセージをクリア
		document.getElementById("mailError").textContent = "";
		document.getElementById("nameError").textContent = "";
		document.getElementById("idError").textContent = "";
		document.getElementById("passwordError").textContent = "";

		// メールアドレスが未入力の場合
		if (mailAddress.trim() === "") {
			document.getElementById("mailError").textContent =
				"メールアドレスを入力してください。";
			hasError = true;
		}

		// 氏名が未入力の場合
		if (name.trim() === "") {
			document.getElementById("nameError").textContent =
				"氏名を入力してください。";
			hasError = true;
		}

		// IDが未入力の場合
		if (userId.trim() === "") {
			document.getElementById("idError").textContent =
				"IDを入力してください。";
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
	});
});