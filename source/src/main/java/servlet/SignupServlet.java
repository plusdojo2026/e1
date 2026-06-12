package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsersDao;
import dto.User;

// 新規登録処理を行うサーブレット
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 新規登録画面を表示する処理
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// signup.jspへ画面遷移
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");

		dispatcher.forward(request, response);
	}

	// 新規登録ボタン押下時の処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストの文字コードをUTF-8に設定
		request.setCharacterEncoding("UTF-8");

		// フォームから入力された情報を取得
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String mail_address = request.getParameter("mail_address");

		// Usersテーブル操作用DAOを生成
		UsersDao usersDao = new UsersDao();

		// メールアドレスまたはIDの重複の確認
		if (!usersDao.existsUser(mail_address, user_id)) {

			// 重複がなければユーザー情報を登録
			usersDao.insert(new User(user_id, password, name, mail_address));

			// 登録成功後、ログイン画面へリダイレクト
			response.sendRedirect("/e1/LoginServlet");

		} else {

			// 重複している場合はエラーメッセージを設定
			request.setAttribute("errorMsg", "メールアドレスまたはIDは既に登録されています");

			// 新規登録画面へ戻る
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signup.jsp");

			dispatcher.forward(request, response);
		}
	}
}