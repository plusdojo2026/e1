package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopDao;
import dto.Top;

// Topページを表示するサーブレット
@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {

	// Topページを表示する
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    		throws ServletException, IOException {

        try {
        	// セッション取得
        	HttpSession session = request.getSession();

        	// ログイン中のユーザーIDを取得
        	String userId = (String) session.getAttribute("user_id");
        	
        	// 未ログインならログイン画面へ
            if (userId == null) {
                response.sendRedirect(
                        request.getContextPath()
                        + "/LoginServlet");
                return;
            }

        	// Lost_itemsテーブル操作用のDaoの生成
        	TopDao dao = new TopDao();

        	// ランキング表示用
        	List<Top> ranking = dao.getRanking(userId);

        	// グラフ表示用
        	int[] monthlyCount = dao.getMonthlyCount(userId);

        	request.setAttribute("ranking", ranking);
        	request.setAttribute("monthlyCount", monthlyCount);
        	
        	// top.jspへ遷移
        	request.getRequestDispatcher("/WEB-INF/jsp/top.jsp")
            .forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}