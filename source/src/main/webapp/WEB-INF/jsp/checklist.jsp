<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Motta?｜チェックリスト</title>
<link rel="stylesheet" href="/e1/css/checklist.css">
</head>

<body>
	<!-- ワッパー -->
	<div class="wrapper">

		<!-- ヘッダー -->
		<header class="header">

			<a href="TopServlet"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
			


			<nav class="nav">
				<ul>
					<li><a href="TopServlet">TOP</a></li>
					<li><a href="RegistServlet">登録</a></li>
					<li><a href="ListServlet">一覧</a></li>
					<li><a href="SearchServlet">検索</a></li>
					<li><a class="active" href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="LogoutServlet"
						onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
				</ul>
			</nav>
		</header>

		<!-- メイン -->
		<main>

			<!-- 背景丸 -->
			<div class="circle pink left-top"></div>
			<div class="circle pink right-top"></div>

			<div class="circle green center-left"></div>
			<div class="circle green right-bottom"></div>

			<div class="circle white left-bottom"></div>
			<div class="circle white right-center"></div>

			<form id="login_form" action="ChecklistServlet" method="post">

				<label> <br> <input type="text" name="item_name"
					placeholder="名称" required>
				</label> <input type="submit" name="action" value="登録" class="btn-register"> <input
					type="submit" name="action" value="削除" class="btn-reset"> <span
					id="error_message"></span>

			</form>

			<section class="dashboard">
			<!-- チェックリストカード -->
			<div class="check-card">

				<div class="check-container">
						
					<!-- データベース上でチェック前とチェック後を識別するために分割している -->	
												
					<div class="check-column">
						<h2 class="section-title">チェック前</h2>

						<ul id="beforeList">
							<c:forEach var="e" items="${checklist}">
								<c:if test="${!e.checked_flag}">
									<li><input type="checkbox" class="item-check"
										data-id="${e.id}"> ${e.item_name}</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					</div>
					</div>
			<div class="check-card">
			<div class="check-container">
					<div class="check-column">
						<h2 class="section-title">チェック後</h2>

						<ul id="afterList">
							<c:forEach var="e" items="${checklist}">
								<c:if test="${e.checked_flag}">
									<li><input type="checkbox" class="item-check"
										data-id="${e.id}" checked> ${e.item_name}</li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					</div>
				</div>
			
			
		</section>	
		</main>

	</div>




	<script>
		let formObj = document.getElementById('login_form');
		let errorMessageObj = document.getElementById('error_message');

		/* 削除ボタンをクリックしたときの処理 */
		formObj.onsubmit = function(event) {

		    // 削除ボタンが押された場合のみ確認
		    if (event.submitter.value === '削除') {

		        if (window.confirm('実行します。よろしいですか？') === false) {
		            event.preventDefault();
		        }

		    }

		};
		/* リセット */
		formObj.onreset = function() {
			errorMessageObj.textContent = '';
		};

		/* チェックリストでチェックをクリックされている状態で登録を押しても状態を維持する */
		document.addEventListener("change", function(e) {
			if (e.target.classList.contains("item-check")) {

				const li = e.target.closest("li");
				const id = e.target.dataset.id;
				const checked = e.target.checked;

				// ① クリックしたのを移動する
				if (checked) {
					document.getElementById("afterList").appendChild(li);
				} else {
					document.getElementById("beforeList").appendChild(li);
				}

				// ② チェック状態をサーバーに送ってDBを更新する処理
				fetch("ChecklistServlet", {
					method : "POST",
					headers : {
						"Content-Type" : "application/x-www-form-urlencoded"　//　普通のフォーム送信と同じ形式で送る処理
					},
					body : "action=toggle" + "&id=" + id + "&checked="
							+ checked
				});
			}
		});
	</script>

</body>
</html>