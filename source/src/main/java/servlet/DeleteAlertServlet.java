package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AlertDao;

// 通知を削除するサーブレット
@WebServlet("/DeleteAlertServlet")
public class DeleteAlertServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// リクエストパラメータから削除対象のIDを取得
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            AlertDao dao = new AlertDao();
            dao.delete(id);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        // AlertServletへ遷移
        response.sendRedirect("AlertServlet");
    }
}