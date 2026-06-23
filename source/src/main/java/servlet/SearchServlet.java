package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LostItemsDao;
import dto.LostItems;

//検索ページを表示するサーブレット
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	//検索ページを表示する
@Override
protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    // セッション取得
    HttpSession session = request.getSession(false);

    // 未ログインならログイン画面へ
    if (session == null || session.getAttribute("user_id") == null) {
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
        return;
    }

    // search.jspへフォワード
    request.getRequestDispatcher("/WEB-INF/jsp/search.jsp")
           .forward(request, response);
}
//検索条件を受け取り、検索結果画面を表示する
@Override
protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

    // セッション取得
    HttpSession session = request.getSession(false);

    // 未ログインならログイン画面へ
    if (session == null || session.getAttribute("user_id") == null) {
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
        return;
    }
    System.out.println("SearchServlet doPost開始");
    request.setCharacterEncoding("UTF-8");
    // ログイン中のユーザーIDを取得
    String userId = (String) session.getAttribute("user_id");
 // 検索条件を格納するDTOを生成
    LostItems item = new LostItems();
 // 月別検索条件を取得
    String month = request.getParameter("month");
 // 名称の検索条件を取得（未入力なら空文字）
    item.setItem_name(
            request.getParameter("name") == null
            ? ""
            : request.getParameter("name"));
 // 発生場所の検索条件を取得（未入力なら空文字)
    item.setLocation(
            request.getParameter("location") == null
            ? ""
            : request.getParameter("location"));
 // 日付の検索条件を取得（未入力なら空文字）
    item.setLost_date(
            request.getParameter("date") == null
            ? ""
            : request.getParameter("date"));

    // 並び替え条件取得
    String sort = request.getParameter("sort");

    // 初回検索時は新しい順
    if (sort == null) {
        sort = "new";
    }
 // 開始日を取得（期間検索用）
    item.setStartDate(
    	    request.getParameter("startDate") == null ? "" : request.getParameter("startDate"));
 // 終了日を取得（期間検索用）
    item.setEndDate(
    	    request.getParameter("endDate") == null ? "" : request.getParameter("endDate"));
 // DAOを生成
    LostItemsDao dao = new LostItemsDao();
 // 検索条件、ユーザーID、並び替え条件をもとに検索を実行
    List<LostItems> result = dao.select(item, userId, sort, month);

    System.out.println("検索結果件数：" + result.size());
 // 検索結果をリクエストスコープに保存
    request.setAttribute("resultList", result);
 // 並び替え条件を保持
    request.setAttribute("sort", sort);

 // 入力した検索条件を保持
    request.setAttribute("item", item);
 // 月別検索条件を保持
    request.setAttribute("month", month);
 // 検索結果画面へ遷移
    RequestDispatcher dispatcher =
            request.getRequestDispatcher(
                    "/WEB-INF/jsp/search_result.jsp");

    dispatcher.forward(request, response);
}
}