package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ChecklistsDao;
import dto.Checklist;

/**
 * Servlet implementation class MenuServlet
 */
@WebServlet("/ChecklistServlet")
public class ChecklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */


		protected void doGet(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {

		    ChecklistsDao dao = new ChecklistsDao();

		    List<Checklist> checklist = dao.findAll(); 

		    request.setAttribute("checklist", checklist);
		    
		    
		    //チェックリストページにフォワード
		    RequestDispatcher dispatcher =
		        request.getRequestDispatcher("/WEB-INF/jsp/checklist.jsp");

		    dispatcher.forward(request, response);
		}
		
		//チェックリスト登録削除機能
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {

		    request.setCharacterEncoding("UTF-8");

		    String action = request.getParameter("action");
		    String item_name = request.getParameter("item_name");

		    ChecklistsDao dao = new ChecklistsDao();

		    if ("登録".equals(action)) {

		        Checklist list = new Checklist();
		        list.setItem_name(item_name);

		        dao.insert(list);

		    } else if ("削除".equals(action)) {

		        dao.delete(item_name);

		    }

		    response.sendRedirect("/e1/ChecklistServlet");
		}
}
	


