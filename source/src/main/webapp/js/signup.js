document.addEventListener("DOMContentLoaded", function() {

	const form = document.getElementById("signupForm");

	form.addEventListener("submit", function(event) {

		const mailAddress =
			document.getElementById("mail_address").value;

		const name =
			document.getElementById("name").value;

		const userId =
			document.getElementById("user_id").value;

		const password =
			document.getElementById("password").value;

		let hasError = false;

		document.getElementById("mailError").textContent = "";
		document.getElementById("nameError").textContent = "";
		document.getElementById("idError").textContent = "";
		document.getElementById("passwordError").textContent = "";

		// メールアドレス未入力
		if (mailAddress.trim() === "") {
			document.getElementById("mailError").textContent =
				"メールアドレスを入力してください。";
			hasError = true;
		}

		// 氏名未入力
		if (name.trim() === "") {
			document.getElementById("nameError").textContent =
				"氏名を入力してください。";
			hasError = true;
		}

		// ID未入力
		if (userId.trim() === "") {
			document.getElementById("idError").textContent =
				"IDを入力してください。";
			hasError = true;
		}

		// パスワード未入力
		if (password.trim() === "") {
			document.getElementById("passwordError").textContent =
				"パスワードを入力してください。";
			hasError = true;
		}

		if (hasError) {
			event.preventDefault();
		}
	});
});