<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Motta Search_Result</title>
<link rel="stylesheet" href="css/search_result.css">
</head>
<body>

	<!-- ヘッダー -->
	<header class="header">
		<h1>Motta?</h1>

		<nav class="nav">
			<ul>
				<li><a href="#">TOP</a></li>
				<li><a href="#">登録</a></li>
				<li><a href="#">一覧</a></li>
				<li><a href="#" class="active">検索</a></li>
				<li><a href="#">チェックリスト</a></li>
				<li><a href="#">ログアウト</a></li>
			</ul>
		</nav>
	</header>

	<main>

		<!-- 上部 -->
		<div class="top-area">

			<div class="count">4件／全4件</div>

			<div class="sort-area">
				<label for="sort">並び替え：</label> <select id="sort">
					<option>日付（新しい順）</option>
					<option>日付（古い順）</option>
				</select>
			</div>

		</div>

		<!-- 一覧 -->
		<div class="list-area">

			<div class="item-card">

				<div class="item-name">財布</div>

				<div class="item-date">📅 2024/5/14(火)</div>

				<div class="weather">☀️</div>

				<div class="item-place">📍 自宅</div>

				<div class="item-reason">原因：カバンに入れ忘れた</div>

				<div class="item-buttons">
					<button class="edit-btn">編集</button>
					<button class="delete-btn">削除</button>
				</div>

			</div>

			<div class="item-card">

				<div class="item-name">財布</div>

				<div class="item-date">📅 2024/5/10(金)</div>

				<div class="weather">☀️</div>

				<div class="item-place">📍 自宅</div>

				<div class="item-reason">原因：別のカバンに入れていた</div>

				<div class="item-buttons">
					<button class="edit-btn">編集</button>
					<button class="delete-btn">削除</button>
				</div>


			</div>

			<div class="item-card">

				<div class="item-name">財布</div>

				<div class="item-date">📅 2024/5/8(水)</div>

				<div class="weather">☀️</div>

				<div class="item-place">📍 コンビニ</div>

				<div class="item-reason">原因：置き忘れて帰った</div>

				<div class="item-buttons">
					<button class="edit-btn">編集</button>
					<button class="delete-btn">削除</button>
				</div>


			</div>

			<div class="item-card">

				<div class="item-name">財布</div>

				<div class="item-date">📅 2024/5/2(木)</div>

				<div class="weather">☀️</div>

				<div class="item-place">📍 自宅</div>

				<div class="item-reason">原因：カバンに入れ忘れた</div>

				<div class="item-buttons">
					<button class="edit-btn">編集</button>
					<button class="delete-btn">削除</button>
				</div>


			</div>

		</div>

		<!-- ページング -->
		<div class="paging">

			<button>&lt;</button>

			<span>1</span>

			<button>&gt;</button>

		</div>

	</main>

</body>
</html>