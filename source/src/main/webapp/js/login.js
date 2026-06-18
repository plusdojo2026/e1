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
	
});