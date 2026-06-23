// HTMLの読み込み完了後に処理を開始
document.addEventListener("DOMContentLoaded", function() {

	// 新規登録フォームを取得
	const form = document.getElementById("signupForm");

	form.addEventListener("submit", function(e) {

		let hasError = false;

		// 入力を始めたら新規登録失敗メッセージを消す
		document.getElementById("mailError").textContent = "";
		document.getElementById("nameError").textContent = "";
		document.getElementById("userIdError").textContent = "";
		document.getElementById("passwordError").textContent = "";

		const mailAddress = document.getElementById("mail_address").value;
		const name = document.getElementById("name").value;
		const userId = document.getElementById("user_id").value;
		const password = document.getElementById("password").value;

		if (mailAddress.length > 100) {
			document.getElementById("mailError").textContent =
				"メールアドレスは100文字以内で入力してください";
			hasError = true;
		}

		if (name.length > 100) {
			document.getElementById("nameError").textContent =
				"氏名は100文字以内で入力してください";
			hasError = true;
		}

		if (userId.length > 50) {
			document.getElementById("userIdError").textContent =
				"IDは50文字以内で入力してください";
			hasError = true;
		}

		if (password.length > 255) {
			document.getElementById("passwordError").textContent =
				"パスワードは255文字以内で入力してください";
			hasError = true;
		}

		if (hasError) {
			e.preventDefault();
		}
	});

	// 入力したらその項目のエラーを消す
	document.getElementById("mail_address").addEventListener("input", function() {
		document.getElementById("mailError").textContent = "";
	});

	document.getElementById("name").addEventListener("input", function() {
		document.getElementById("nameError").textContent = "";
	});

	document.getElementById("user_id").addEventListener("input", function() {
		document.getElementById("userIdError").textContent = "";
	});

	document.getElementById("password").addEventListener("input", function() {
		document.getElementById("passwordError").textContent = "";
	});
});

// HTMLの読み込み完了後に処理を開始
document.addEventListener("DOMContentLoaded", function() {

	// 新規登録フォームを取得
	const form = document.getElementById("signupForm");

	form.addEventListener("submit", function(e) {

		let hasError = false;

		// 入力を始めたら新規登録失敗メッセージを消す
		document.getElementById("mailError").textContent = "";
		document.getElementById("nameError").textContent = "";
		document.getElementById("userIdError").textContent = "";
		document.getElementById("passwordError").textContent = "";

		const mailAddress = document.getElementById("mail_address").value;
		const name = document.getElementById("name").value;
		const userId = document.getElementById("user_id").value;
		const password = document.getElementById("password").value;

		// HTMLタグと入力文字数のチェック
		const tagPattern = /<[^>]*>/;

		if (mailAddress.length > 100) {
			document.getElementById("mailError").textContent =
				"メールアドレスは100文字以内で入力してください";
			hasError = true;
		}
		else if (tagPattern.test(mailAddress)) {
			document.getElementById("mailError").textContent =
				"メールアドレスにHTMLタグは使用できません";
			hasError = true;
		}

		if (name.length > 100) {
			document.getElementById("nameError").textContent =
				"氏名は100文字以内で入力してください";
			hasError = true;
		}
		else if (tagPattern.test(name)) {
			document.getElementById("nameError").textContent =
				"氏名にHTMLタグは使用できません";
			hasError = true;
		}

		if (userId.length > 50) {
			document.getElementById("userIdError").textContent =
				"IDは50文字以内で入力してください";
			hasError = true;
		}
		else if (tagPattern.test(userId)) {
			document.getElementById("userIdError").textContent =
				"IDにHTMLタグは使用できません";
			hasError = true;
		}

		if (password.length > 255) {
			document.getElementById("passwordError").textContent =
				"パスワードは255文字以内で入力してください";
			hasError = true;
		}
		else if (tagPattern.test(password)) {
			document.getElementById("passwordError").textContent =
				"パスワードにHTMLタグは使用できません";
			hasError = true;
		}

		if (hasError) {
			e.preventDefault();
		}
	});

	// 入力したらその項目のエラーを消す
	document.getElementById("mail_address").addEventListener("input", function() {
		document.getElementById("mailError").textContent = "";
	});

	document.getElementById("name").addEventListener("input", function() {
		document.getElementById("nameError").textContent = "";
	});

	document.getElementById("user_id").addEventListener("input", function() {
		document.getElementById("userIdError").textContent = "";
	});

	document.getElementById("password").addEventListener("input", function() {
		document.getElementById("passwordError").textContent = "";
	});
});