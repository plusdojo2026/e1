<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/e1/css/top.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<title>Moota?｜TOP</title>
</head>
<body>
	<!-- ヘッダー -->
	<header class="header">
		<a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
		<nav class="nav">
			<ul>
				<li><a class="active" href="#">TOP</a></li>
				<li><a href="#">登録</a></li>
				<li><a href="#">一覧</a></li>
				<li><a href="#">検索</a></li>
				<li><a href="ChecklistServlet">チェックリスト</a></li>
				<li><a href="LogoutServlet" onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
			</ul>
		</nav>
	</header>
	<main>
		<!--メッセージ-->
		<section class="message-box">
			<p>
				🔔おはようございます！<br> 今日のチェックリストを確認しましょう
			</p>
			<button class="alert-btn">
				<a href="AlertServlet">通知を設定</a>
			</button>
		</section>
		<section class="dashboard">

			<!--ランキング-->
			<article class="card">
				<h2>ランキング</h2>
				<div class="ranking-top3">
					<c:forEach var="r" items="${ranking}" begin="0" end="2" varStatus="status">
						<div class="rank-card">
							<div class="rank-number">${status.index + 1}位</div>
							<div class="item-name">${r.itemName}</div>
							<div class="item-count">${r.count}回</div>
						</div>
					</c:forEach>
				</div>
				<div class="ranking-list">
					<c:forEach var="r" items="${ranking}" begin="3" varStatus="status">
					    <div class="rank-row">
					        <span>${status.index + 1}位</span>
					        <span>${r.itemName}</span>
					        <span>${r.count}回</span>
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


const ctx = document.getElementById('monthChart').getContext('2d');

new Chart(ctx, {
    type: 'bar',
    data: {
        labels: [
            '1月','2月','3月','4月','5月','6月',
            '7月','8月','9月','10月','11月','12月'
        ],
        datasets: [{
            label: '月別忘れ物件数',
            data: monthlyData,
            backgroundColor: 'rgba(255, 179, 193, 0.5)',
            borderColor: 'rgba(255, 105, 135, 1)',
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true,
                ticks: {
                    stepSize: 1
                }
            }
        },
        animation: {
            duration: 2000,
            easing: 'easeInOutBounce'
        },
        plugins: {
            legend: {
                display: false
            }
        }
    }
});

</script>
</body>
</html>