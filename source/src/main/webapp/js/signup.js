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

});