<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/e1/css/style.css">
<link rel="stylesheet" href="/e1/css/list.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<title>Motta?｜一覧</title>
</head>
<body>
	<!-- ヘッダー -->
	<header class="header">
		<a href="TopServlet"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
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
					<li><a href="RegistServlet">登録</a></li>
					<li><a href="ListServlet" class="active">一覧</a></li>
					<li><a href="SearchServlet">検索</a></li>
					<li><a href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="LogoutServlet" onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
			</ul>
		</nav>
		</div>
	</header>

	<!-- メイン -->
	<main>
		<!--件数表示-->
		<section class="top-area">
			<p class="count">${itemList.size()}件</p>

			<!--並び替え-->
			<div class="sort-area">
    <label for="sort">並び替え：</label>

    <form action="/e1/ListServlet" method="get">
       <select id="sort" name="sort" onchange="this.form.submit()">

    <option value="new"
        ${sort eq 'new' ? 'selected' : ''}>
        日付(新しい順)
    </option>

    <option value="old"
        ${sort eq 'old' ? 'selected' : ''}>
        日付(古い順)
    </option>

</select>
    </form>
</div>
</section>

		<!--一覧-->
		<section class="list-area">
			<c:forEach var="item" items="${itemList}">
				<article class="item-card">
					<div class="item-name">${item.item_name}</div>
					<div class="date-weather-place">
					<div class="item-date" data-date="${item.lost_date}"><i class="fa-solid fa-calendar-days"></i>  ${item.lost_date}</div>
					<div class="weather">
						<c:choose>
							<c:when test="${item.weather == '晴れ'}">
							<i class="fa-solid fa-sun"></i>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${item.weather == '曇り'}">
							<i class="fa-solid fa-cloud"></i>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${item.weather == '雨'}">
							<i class="fa-solid fa-umbrella"></i>
							</c:when>
						</c:choose>
						<c:choose>
							<c:when test="${item.weather == '雪'}">
							<i class="fa-regular fa-snowflake"></i>
							</c:when>
						</c:choose>
					</div>
					<div class="item-place"><i class="fa-solid fa-location-dot"></i>  ${item.location}</div>
					</div>
					<div class="item-reason"><i class="fa-solid fa-magnifying-glass"></i>  ${item.reason}</div>
				</article>
			</c:forEach>
		</section>

		<!--ページング-->
		<section class="paging">
			<button id="prevBtn">&lt;</button>

			<div id="pageNumbers"></div>

			<button id="nextBtn">&gt;</button>
		</section>

	</main>

	<script>
	// ページング
	const itemsPerPage = 5;
	const items = document.querySelectorAll(".item-card");
	
	let currentPage = 1;
	const totalPages = Math.ceil(items.length / itemsPerPage);
	
	// ページ表示
	function showPage(page) {

    // 一覧を非表示
    items.forEach(item => {
        item.style.display = "none";
    });

    const start = (page - 1) * itemsPerPage;
    const end = start + itemsPerPage;

    //件数表示
   document.querySelector(".count").textContent = (start + 1) + "～" + Math.min(end, items.length) + "件 / 全" + items.length + "件";

    // 対象ページだけ表示
    for (let i = start; i < end && i < items.length; i++) {
        items[i].style.display = "flex";
    }

    // ページ番号の色変更
    document.querySelectorAll(".page-btn").forEach(btn => {
        btn.classList.remove("active");
    });

    const activeBtn =
    	document.querySelector(
    	".page-btn[data-page='" + page + "']"
    	);

    if (activeBtn) {
        activeBtn.classList.add("active");
    }

    // 前へ・次へボタン制御
    document.getElementById("prevBtn").disabled = page === 1;
    document.getElementById("nextBtn").disabled = page === totalPages;

    currentPage = page;
    createPageButtons();
	}
	
	// 前へ
	document.getElementById("prevBtn").addEventListener("click", () => {
	    if (currentPage > 1) {
	        showPage(currentPage - 1);
	    }
	});
	
	// 次へ
	document.getElementById("nextBtn").addEventListener("click", () => {
	    if (currentPage < totalPages) {
	        showPage(currentPage + 1);
	    }
	});
	

	//ページ番号ボタンを生成する関数
	function createPageButtons() {
		// ページ番号を表示する要素を取得
		const pageNumbers = document.getElementById("pageNumbers");
		// 既存のページ番号を一旦削除
		pageNumbers.innerHTML = "";
		//ページ番号ボタンを作成する関数
	    function addButton(page) {
	    	// button要素を生成
	        const btn = document.createElement("button");
	    	 // ボタンにページ番号を表示
	        btn.textContent = page;
	     	// CSS用クラスを追加
	        btn.classList.add("page-btn");
	     	// ページ番号をデータ属性として保持
	        btn.dataset.page = page;
	    	 // 現在表示中のページならactiveクラスを付与
	        if (page === currentPage) {
	            btn.classList.add("active");
	        }
	     	// ボタンクリック時の処理
	        btn.addEventListener("click", () => {
	        	// 指定ページを表示
	        	showPage(page);
	        	// ページボタンを再生成
	            createPageButtons();
	        });
	     	// 作成したボタンを画面へ追加
	        pageNumbers.appendChild(btn);
	    }
		//「...」を表示する関数
	    function addDots() {
	    	// span要素を生成
	        const span = document.createElement("span");
	    	//「・・・」を表示
	        span.textContent = "...";
	    	// CSS用クラスを追加
	        span.classList.add("dots");
	     	// 画面へ追加
	        pageNumbers.appendChild(span);
	    }

	    // 最初のページ
	    addButton(1);

	    // 左側省略
	    if (currentPage > 3) {
	        addDots();
	    }

	    // 現在ページ周辺
	    for (
	        let i = Math.max(2, currentPage - 1);
	        i <= Math.min(totalPages - 1, currentPage + 1);
	        i++
	    ) {
	        addButton(i);
	    }

	    // 右側省略
	    if (currentPage < totalPages - 2) {
	        addDots();
	    }

	    // 最後のページ
	    if (totalPages > 1) {
	        addButton(totalPages);
	    }
	}

	// 初期表示
	createPageButtons();
	showPage(1);
	
	/* ハンバーガーメニュー */
	var hamburger = document.querySelector('.hamburger-menu');
	var nav = document.querySelector('.nav');
	
	hamburger.addEventListener('click', function () {
	  hamburger.classList.toggle('active');
	  nav.classList.toggle('active');
	});
</script>

</body>
</html>