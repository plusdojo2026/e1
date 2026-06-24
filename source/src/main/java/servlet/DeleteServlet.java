package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LostItemsDao;

// 忘れ物情報の削除を行うサーブレット

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

    	// キャッシュを禁止
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
	    HttpSession session = request.getSession(false);

	    // 未ログインならログイン画面へ
	    if (session == null || session.getAttribute("user_id") == null) {
	        response.sendRedirect(request.getContextPath() + "/LoginServlet");
	        return;
	    }

	    // ログイン済みでもGETでDeleteServletは使わせない
	    response.sendRedirect(request.getContextPath() + "/TopServlet");
	}
    // 削除ボタンが押されたときの処理
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
    	 // セッション取得
        HttpSession session = request.getSession(false);

        // 未ログインならログイン画面へ
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/LoginServlet");
            return;
        }
        // リクエストパラメータから削除対象のIDを取得
        int id = Integer.parseInt(
                request.getParameter("id"));

        // DAOを生成
        LostItemsDao dao = new LostItemsDao();

        // 指定されたIDのデータを削除
        dao.delete(id);

        // 削除完了メッセージをリクエストスコープに保存
        request.setAttribute("message","削除が完了しました。");

        // 削除完了画面へ遷移
        request.getRequestDispatcher("/WEB-INF/jsp/delete_result.jsp").forward(request, response);
    }
}