<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>チェックリスト | Motta?</title>
<link rel="stylesheet" href="/e1/css/checklist.css">
</head>

<body>

<div class="wrapper">

  <!-- ヘッダー -->
  <header class="header">
    
<a href="#"><img src="images/header_logo.png" alt="Motta?" class="logo"></a>
  

    <nav class="nav">
    <ul>
				<li><a href="TopServlet">TOP</a></li>
				<li><a href="RegistServlet">登録</a></li>
				<li><a href="ListServlet">一覧</a></li>
				<li><a href="SearchServlet">検索</a></li>
				<li><a class="active" href="ChecklistServlet">チェックリスト</a></li>
				<li><a href="LogoutServlet" onclick="return confirm('ログアウトしますか？');">ログアウト</a></li>
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

      <label>
        <br>
        <input type="text" name="item_name" placeholder="名称">
      </label>

   		<input type="submit" name="action" value="登録">
		<input type="submit" name="action" value="削除">

      <span id="error_message"></span>

    </form>

    <!-- チェックリストカード -->
    <div class="check-card">

      

      <ul id="beforeList">
  <c:forEach var="e" items="${checklist}">
    <li>
      <input type="checkbox" class="item-check">
      ${e.item_name}
    </li>
  </c:forEach>
        <li>
          <input type="checkbox" class="item-check">
          スマートフォン
        </li>
        <li>
          <input type="checkbox" class="item-check">
          財布
        </li>
        <li>
          <input type="checkbox" class="item-check">
          ワイヤレスイヤフォン
        </li>
        <li>
          <input type="checkbox" class="item-check">
          充電コード
        </li>
        <li>
          <input type="checkbox" class="item-check">
          モバイルバッテリー
        </li>
        <li>
          <input type="checkbox" class="item-check">
          折りたたみ傘
        </li>
      </ul>

      <h2 class="section-title">チェック後</h2>

      <ul id="afterList"></ul>

    </div>

  </main>

</div>

<script>
let formObj = document.getElementById('login_form');
let errorMessageObj = document.getElementById('error_message');

/* 登録ボタン */
formObj.onsubmit = function(event) {

  

    if (!confirm('実行します。よろしいですか？')) {
      event.preventDefault();
    }
};

/* チェック移動 */
document.addEventListener("change", function (e) {
  if (e.target.classList.contains("item-check")) {

    const li = e.target.closest("li");

    if (e.target.checked) {
      document.getElementById("afterList").appendChild(li);
    } else {
      document.getElementById("beforeList").appendChild(li);
    }
  }
});

/* リセット */
formObj.onreset = function() {
  errorMessageObj.textContent = '';
};
</script>

</body>
</html>