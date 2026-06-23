<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="images/favicon.ico">
<link rel="stylesheet" href="/e1/css/style.css">
<link rel="stylesheet" href="/e1/css/regist.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css"
	rel="stylesheet" />
<title>Motta?｜登録</title>
</head>
<body>
	<!-- ワッパー -->
	<div class="wrapper">

		<!-- ヘッダー -->
		<header class="header">
			<a href="TopServlet"><img src="images/header_logo.png"
				alt="Motta?" class="logo"></a>
			<!-- ハンバーガーボタン -->
			<div class="container">
				<div class="hamburger-menu">
					<div class="line"></div>
					<div class="line"></div>
					<div class="line"></div>
				</div>


				<nav class="nav">
					<ul>
						<li><a href="TopServlet">TOP</a></li>
						<li><a class="active" href="RegistServlet">登録</a></li>
						<li><a href="ListServlet">一覧</a></li>
						<li><a href="SearchServlet">検索</a></li>
						<li><a href="ChecklistServlet">チェックリスト</a></li>
						<li><a href="LogoutServlet"
							onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
					</ul>
				</nav>
			</div>
		</header>

		<!-- 背景丸 -->
		<div class="circle pink left-top"></div>
		<div class="circle pink right-top"></div>

		<div class="circle green center-left"></div>
		<div class="circle green right-bottom"></div>

		<div class="circle white left-bottom"></div>
		<div class="circle white right-center"></div>

		<!-- メイン -->
		<main class="regist-page">
			<form id="regist_form" action="RegistServlet" method="post">
			
				<!-- 名称入力 -->
				<input type="text" name="item_name" id="item_name" placeholder="名称"
					required>

				<!-- 日付入力 -->
				<input type="date" id="date" name="lost_date" required>

				<!-- 天気入力 -->
				<select id="weather" name="weather">
					<option value="" selected disabled>天気</option>
					<option value="晴れ">晴れ</option>
					<option value="曇り">曇り</option>
					<option value="雨">雨</option>
					<option value="雪">雪</option>
				</select>

				<!-- 発生場所-->
				<input type="text" id="location" name="location" placeholder="発生場所"
					required>

				<!-- 原因入力 -->
				<textarea name="reason" placeholder="原因"></textarea>
				<!-- ボタンエリア -->
				<div class="button-area">
					<!-- 登録ボタン -->
					<input type="submit" class="regist-btn" name="touroku" value="登録">

					<!-- リセットボタン -->
					<input type="reset" class="reset-btn" name="reset" value="リセット">
				</div>
				<span id="error_message"></span>
			</form>
		</main>
	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
	<script>
		'use strict';

		let formObj = document.getElementById('regist_form');
		let errorMessageObj = document.getElementById('error_message');
		/* ハンバーガーメニュー */
		var hamburger = document.querySelector('.hamburger-menu');
		var nav = document.querySelector('.nav');

		hamburger.addEventListener('click', function() {
			hamburger.classList.toggle('active');
			nav.classList.toggle('active');
		});
		/*入力フォーム*/
		formObj.onsubmit = function(event) {
			const itemName = formObj.item_name.value.trim();
			const location = formObj.location.value.trim();
			const reason = formObj.reason.value.trim();
		   
			// HTMLタグチェック
			if (/<[^>]*>/.test(itemName) || /<[^>]*>/.test(location)
					|| /<[^>]*>/.test(reason)) {
				alert("HTMLタグは使用できません");
				event.preventDefault();
				return;
			}
			if (!formObj.item_name.value) {
				errorMessageObj.textContent = '※名称を入力してください！';
				event.preventDefault();
			}
			if (!formObj.lost_date.value) {
				errorMessageObj.textContent = '※日付を入力してください！';
				errorMessageObj.textContent = '';
				event.preventDefault();
			}
			if (!formObj.location.value) {
				errorMessageObj.textContent = '※発生場所を入力してください！';
				errorMessageObj.textContent = '';
				event.preventDefault();
			}
			 // 文字数チェック
	        if (itemName.length > 50) {
	            alert("名称は50文字以内で入力してください");
	            event.preventDefault();
	            return;
	        }
	        // 文字数チェック
	        if (location.length > 50) {
	            alert("発生場所は50文字以内で入力してください");
	            event.preventDefault();
	            return;

	        }
	        // 文字数チェック
	        if (reason.length > 200) {
	            alert("原因は200文字以内で入力してください");
	            event.preventDefault();
	            return;

	        }
		}

		/* リセット */
		formObj.onreset = function() {
			window.confirm('リセットしますか?');
			/* エラーメッセージを消します */
			errorMessageObj.textContent = null;
		};
		//入力されたら文字色を変更
		document
				.querySelectorAll(
						'#regist_form input, #regist_form select, #regist_form textarea')
				.forEach(function(element) {

					function updateColor() {
						if (element.value.trim() !== "") {
							element.classList.add("filled");
						} else {
							element.classList.remove("filled");
						}
					}

					updateColor();

					element.addEventListener("input", updateColor);
					element.addEventListener("change", updateColor);
				});

		//天気アイコン表示
		const icons = {
			'晴れ' : '<i class="fa-solid fa-sun"></i>',
			'曇り' : '<i class="fa-solid fa-cloud"></i>',
			'雨' : '<i class="fa-solid fa-umbrella"></i>',
			'雪' : '<i class="fa-regular fa-snowflake"></i>'
		};

		function formatWeather(state) {
			if (!state.id) {
				return state.text;
			}

			return $('<span>' + state.text + ' ' + icons[state.text]
					+ '</span>');
		}

		$('#weather').select2({
			minimumResultsForSearch : Infinity,
			templateResult : formatWeather,
			templateSelection : formatWeather,
			escapeMarkup : function(markup) {
				return markup;
			}
		});
		$('#weather').on(
				'change',
				function() {
					$(this).next('.select2-container').toggleClass('filled',
							!!$(this).val());
				});

		// 初期状態も反映
		$('#weather').trigger('change');

		//リセット処理
		formObj.onreset = function() {

			errorMessageObj.textContent = null;

			setTimeout(
					function() {
						// 全入力項目の色を初期化
						document
								.querySelectorAll(
										'#regist_form input, #regist_form select, #regist_form textarea')
								.forEach(function(element) {
									element.classList.remove('filled');
								});

						// Select2も初期化
						$('#weather').val('').trigger('change');

					}, 0);
		};
	</script>

</body>
</html>