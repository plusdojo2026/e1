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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		// ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		 throws ServletException, IOException {

			// リクエストパラメータを取得する
			request.setCharacterEncoding("UTF-8");
			String item_name = request.getParameter("lost_items");
			String name = request.getParameter("name");
			String location = request.getParameter("location");
			String date = request.getParameter("date");
			String weather = request.getParameter("weather");
			String reason = request.getParameter("reason");
			
			// 登録処理を行う
			RegistDao bDao = new RegistDao();

			Regist list = new Regist();
			

			if (bDao.insert(list)) {
		  response.sendRedirect("/e1/RegistServlet");
		}

	}
	}

