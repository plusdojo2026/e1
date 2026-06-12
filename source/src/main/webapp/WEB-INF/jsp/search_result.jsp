<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="dto.LostItems" %>

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
	
				<%List<LostItems> list =(List<LostItems>)request.getAttribute("resultList");
				
				if(list == null){list = new java.util.ArrayList<LostItems>();}%>
	
				<div class="count"><%= list.size() %>件</div>
	
				<div class="sort-area">
					<label for="sort">並び替え：</label> <select id="sort">
						<option>日付（新しい順）</option>
						<option>日付（古い順）</option>
					</select>
				</div>
	
			</div>
	
			<!-- 一覧 -->
			<div class="list-area">
	
		<%for(LostItems item : list){%>
		
			<div class="item-card">
		
				<div class="item-name">
					<%= item.getItem_name() %>
				</div>
		
				<div class="item-date">
					📅 <%= item.getLost_date() %>
				</div>
		
				<div class="weather">
					<%= item.getWeather() %>
				</div>
		
				<div class="item-place">
					📍 <%= item.getLocation() %>
				</div>
		
				<div class="item-reason">
					原因：<%= item.getReason() %>
				</div>
		
				<div class="item-buttons">
		
					<button class="edit-btn">
						編集
					</button>
		
					<button class="delete-btn">
						削除
					</button>
		
				</div>
		
			</div>
		
		<%}%>
	
	
				</div>
	<!--ページング-->
            <section class="paging">
                <button id="prevBtn">&lt;</button>

                <div id="pageNumbers"></div>

                <button id="nextBtn">&gt;</button>
            </section>
		</main>
	
		<script>
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
			
			//ページ数を自動取得
			function createPageButtons() {
		
		    const pageNumbers = document.getElementById("pageNumbers");
		
		    pageNumbers.innerHTML = "";
		
		    for (let i = 1; i <= totalPages; i++) {
		
		        const btn = document.createElement("button");
		
		        btn.textContent = i;
		        btn.classList.add("page-btn");
		        btn.dataset.page = i;
		
		        btn.addEventListener("click", () => {
		            showPage(i);
		        });
		
		        pageNumbers.appendChild(btn);
		    }
			}
			
			// 初期表示
			createPageButtons();
			showPage(1);
		</script>
	</body>
</html>