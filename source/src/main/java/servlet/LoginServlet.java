package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;
import dto.User;

// ログイン処理を行うサーブレット
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ログイン画面を表示する
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// login.jspへ画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

		dispatcher.forward(request, response);
	}

	// ログインボタン押下時の処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストの文字コードをUTF-8に設定
		request.setCharacterEncoding("UTF-8");

		// フォームから入力された情報を取得
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");

		// Usersテーブル操作用DAOを生成
		UsersDao usersDao = new UsersDao();

		// IDとパスワードが一致するか確認
		if (usersDao.isLoginOK(new User(user_id, password))) {

			// ログイン成功

			// セッションを取得
			HttpSession session = request.getSession();

			// ログイン中ユーザーのIDを保存
			session.setAttribute("user_id", user_id);

			// TOPページにリダイレクトする
			response.sendRedirect("/e1/TopServlet");

		} else {

			// ログイン失敗

			// エラーメッセージを設定
			request.setAttribute("errorMsg", "ログインIDまたはパスワードに誤りがあります。");

			// ログイン画面へ戻る
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		}
	}
}