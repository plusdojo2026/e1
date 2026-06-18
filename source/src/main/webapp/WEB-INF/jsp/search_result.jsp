<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="dto.LostItems" %>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>Motta?｜検索結果</title>
<link rel="stylesheet" href="/e1/css/style.css">
<link rel="stylesheet" href="/e1/css/search_result.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
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
					<li><a href="ListServlet">一覧</a></li>
					<li><a href="SearchServlet" class="active">検索</a></li>
					<li><a href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="Logout">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	
		<main>
	
			<!-- 上部 -->
			<div class="top-area">
	
				<%List<LostItems> list =(List<LostItems>)request.getAttribute("resultList");
				
				if(list == null){list = new java.util.ArrayList<LostItems>();}%>
	
				<div class="count"><%= list.size() %>件</div>
	
<%LostItems searchItem = (LostItems)request.getAttribute("item");
String sort = (String)request.getAttribute("sort");
if(sort == null){sort = "new";}%>

<div class="sort-area">

<form action="SearchServlet" method="post">
	<!-- 月を保持 -->
	<input type="hidden" name="month"
       value="<%= request.getAttribute("month") != null ? request.getAttribute("month") : "" %>">

    <input type="hidden" name="startDate"
       value="<%= searchItem != null ? searchItem.getStartDate() : "" %>">

	<input type="hidden" name="endDate"
       value="<%= searchItem != null ? searchItem.getEndDate() : "" %>">

    <input type="hidden" name="location"
           value="<%= searchItem != null ? searchItem.getLocation() : "" %>">

    <input type="hidden" name="date"
           value="<%= searchItem != null ? searchItem.getLost_date() : "" %>">

    <label for="sort">並び替え：</label>

    <select id="sort" name="sort" onchange="this.form.submit()">

        <option value="new"
            <%= "new".equals(sort) ? "selected" : "" %>>
            日付（新しい順）
        </option>

        <option value="old"
            <%= "old".equals(sort) ? "selected" : "" %>>
            日付（古い順）
        </option>

    </select>

</form>

</div>
</div>  <!-- top-area -->
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
					<% if("晴れ".equals(item.getWeather())) { %>
					    <i class="fa-solid fa-sun"></i>
					
					<% } else if("曇り".equals(item.getWeather())) { %>
					    <i class="fa-solid fa-cloud"></i>
					
					<% } else if("雨".equals(item.getWeather())) { %>
					    <i class="fa-solid fa-umbrella"></i>
					
					<% } else if("雪".equals(item.getWeather())) { %>
					    <i class="fa-regular fa-snowflake"></i>
					
					<% } %>		
				</div>
		
				<div class="item-place">
					📍 <%= item.getLocation() %>
				</div>
		
				<div class="item-reason">
					原因：<%= item.getReason() %>
				</div>
		
				<div class="item-buttons">
		
					<!--<button class="edit-btn">編集</button>  -->
		
					<form action="DeleteServlet" method="post" 
					onsubmit="return confirm('本当に削除してよろしいですか？');">
					
					    <input type="hidden" name="id" value="<%= item.getId() %>">
					    
					    <button type="submit" class="delete-btn">削除</button>
				    </form>
		
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
		/* ハンバーガーメニュー */
		var hamburger = document.querySelector('.hamburger-menu');
		var nav = document.querySelector('.nav');
		
		hamburger.addEventListener('click', function () {
		  hamburger.classList.toggle('active');
		  nav.classList.toggle('active');
		});
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
			
			function createPageButtons() {

			    const pageNumbers = document.getElementById("pageNumbers");

			    pageNumbers.innerHTML = "";

			    function addButton(page) {

			        const btn = document.createElement("button");

			        btn.textContent = page;
			        btn.classList.add("page-btn");
			        btn.dataset.page = page;

			        if (page === currentPage) {
			            btn.classList.add("active");
			        }

			        btn.addEventListener("click", () => {
			            showPage(page);
			            createPageButtons();
			        });

			        pageNumbers.appendChild(btn);
			    }

			    function addDots() {
			        const span = document.createElement("span");
			        span.textContent = "...";
			        span.classList.add("dots");
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
		</script>
	</body>
</html>