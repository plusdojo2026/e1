<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/e1/css/style.css">
<link rel="stylesheet" href="/e1/css/top.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<title>Motta?｜TOP</title>
</head>
<body>
	<!-- ヘッダー -->
	<header class="header">
		<a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
		<!-- ハンバーガーボタン -->
		<div class="container">
		    <div class="hamburger-menu">
			    <div class="line"></div>
			    <div class="line"></div>
			    <div class="line"></div>
		  	</div>
			<nav class="nav">
				<ul>
					<li><a class="active" href="#">TOP</a></li>
					<li><a href="RegistServlet">登録</a></li>
					<li><a href="ListServlet">一覧</a></li>
					<li><a href="SearchServlet">検索</a></li>
					<li><a href="ChecklistServlet">チェックリスト</a></li>
					<li><a href="LogoutServlet"
						onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<!-- メイン -->
	<main>
		<!--メッセージ-->
		<section class="message-box">
			<p>
				🔔おはようございます！<br> 今日のチェックリストを確認しましょう
			</p>
			<a href="AlertServlet" class="alert-btn">通知を設定</a>
		</section>
		<section class="dashboard">

			<!--ランキング-->
			<article class="card">			
				<h2>ランキング</h2>
				<div class="ranking-top3">
					<c:forEach var="r" items="${ranking}" begin="0" end="2"	varStatus="status">

						<form action="SearchServlet" method="post" class="rank-form">
							<input type="hidden" name="name" value="${r.itemName}">

							<button type="submit" class="rank-card">
								<div class="rank-number">${status.index + 1}位</div>
								<div class="item-name">${r.itemName}</div>
								<div class="item-count">${r.count}回</div>
							</button>
						</form>

					</c:forEach>
				</div>
				
				<div class="ranking-list">
					<c:forEach var="r" items="${ranking}" begin="3" varStatus="status">
						<div class="rank-row">
							<span>${status.index + 1}位</span> <span>${r.itemName}</span> <span>${r.count}回</span>
						</div>
					</c:forEach>
				</div>
			</article>
			<!--グラフ-->
			<article class="card">
				<h2>月別累計忘れもの数</h2>
				<canvas id="monthChart" width="400" height="200"></canvas>
			</article>
		</section>
	</main>

	<script>
	/*　12カ月文のデータ　*/
	const monthlyData = [
	    ${monthlyCount[0]},
	    ${monthlyCount[1]},
	    ${monthlyCount[2]},
	    ${monthlyCount[3]},
	    ${monthlyCount[4]},
	    ${monthlyCount[5]},
	    ${monthlyCount[6]},
	    ${monthlyCount[7]},
	    ${monthlyCount[8]},
	    ${monthlyCount[9]},
	    ${monthlyCount[10]},
	    ${monthlyCount[11]}
	];

	/*　y軸の最大値　*/
	const yMax = Math.max(Math.ceil(Math.max(...monthlyData) * 1.1), 20);
	/*　キャンバス要素を取得し、2D描画コンテキストを取得　*/
	const ctx = document.getElementById('monthChart').getContext('2d');
	/*　Chart.jsを使って棒グラフを作成　*/
	new Chart(ctx, {
	    type: 'bar', // 棒グラフ
	    data: {
	        labels: [
	            '1月','2月','3月','4月','5月','6月',
	            '7月','8月','9月','10月','11月','12月'
	        ],
	        datasets: [{
	            label: '月別忘れ物件数', // 凡例
	            data: monthlyData,
	            backgroundColor: 'rgba(255, 179, 193, 0.5)',
	            borderColor: 'rgba(255, 105, 135, 1)',
	            borderWidth: 1
	        }]
	    },
	    options: {
	    	/* クリックイベント */
	    	onClick: function(evt, elements) {
	            if (elements.length > 0) {
	                const month = elements[0].index + 1;
					/* POST送信用フォーム */
	                const form = document.createElement("form");
	                form.method = "post";
	                form.action = "SearchServlet";
					/* 送信するパラメータ */
	                const input = document.createElement("input");
	                input.type = "hidden";
	                input.name = "month";
	                input.value = month;
	                form.appendChild(input);
	                document.body.appendChild(form);
	                form.submit();
	            }
	        },
	        scales: {
	            y: {
	                beginAtZero: true,	// Y軸を0から開始
	                max: yMax,			// 計算した最大値を設定
	                ticks: {
	                    stepSize: 1		// 目盛りの間隔を1に設定
	                }
	            }
	        },
	        animation: {
	            duration: 2000,				// アニメーション時間（2秒）
	            easing: 'easeInOutBounce'	// バウンドするようなアニメーション
	        },
	        plugins: {
	            legend: {
	                display: false		// 凡例を非表示
	            }
	        }
	    }
	});
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