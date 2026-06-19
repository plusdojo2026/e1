package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegistDao;
import dto.Regist;

/**
 *  * Servlet implementation class MenuServlet  
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse      
	 *      response)
	 */
	//登録画面の表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {
			// セッション取得
			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("item_name");
			String date = request.getParameter("lost_date");
			String weather = request.getParameter("weather");
			String location = request.getParameter("location");
			String reason = request.getParameter("reason");
			String user_id = (String) request.getSession().getAttribute("user_id");
			
         // 登録処理を行う
	RegistDao bDao = new RegistDao();
		
			if (bDao.insert(new Regist(0,name,date,weather,location,reason,user_id) )) {
				// result.jspへ遷移
	            request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
			} else {
				// resgistlt.jspへ遷移
	            request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp").forward(request, response);
			}
			
            
	}
		
	}

