package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlertDao;
import dto.Alert;

// 通知設定を行うサーブレット
@WebServlet("/AlertServlet")
public class AlertServlet extends HttpServlet {
	
	// 通知画面を表示する
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	        throws ServletException, IOException {

		// alert.jspへ画面遷移
	    request.getRequestDispatcher("/WEB-INF/jsp/alert.jsp").forward(request, response);
	}

	// 通知設定ボタン押下時の処理
    @Override
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

    	// リクエストの文字コードをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // フォームから入力された情報を取得
        String date = request.getParameter("date");
        String time = request.getParameter("time");

        // "2026-06-11 09:00" の形式で保存
        String alertDate = date + " " + time;

        Alert alert = new Alert();
        alert.setAlertDate(alertDate);

        try {
        	// Alertsテーブル操作用DAOを生成
            AlertDao dao = new AlertDao();
            dao.insert(alert);

            // result.jspへ遷移
            request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}