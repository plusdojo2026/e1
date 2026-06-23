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

	    ChecklistsDao dao = new ChecklistsDao();

	    List<Checklist> checklist = dao.findByUserId(userId);

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
		    
		    String userId = (String) request.getSession().getAttribute("user_id");
		    String action = request.getParameter("action");
		    String item_name = request.getParameter("item_name");

		    ChecklistsDao dao = new ChecklistsDao();
		    
		    
		   

		    if ("登録".equals(action)) {
		    		
		    	//文字数チェック
		    	if (item_name.length() > 50) {
		    	    request.setAttribute("error_message", "名称は50文字以内で入力してください");
		    	    doGet(request, response);
		    	    return;
		    	}
		    	
		    	if (item_name.matches(".*<[^>]*>.*")) {
		    	    request.setAttribute("error_message", "HTMLタグは使用できません");
		    	    doGet(request, response);
		    	    return;
		    	}
		    	
		    
		    	
		    	
		        Checklist list = new Checklist();
		        list.setUser_id(userId);         
		        list.setItem_name(item_name);
		        list.setChecked_flag(false);      

		        boolean result = dao.insert(list);

		        if (!result) {
		            request.setAttribute("error_message", "同じ名称のチェックリストは登録できません");
		            doGet(request, response);
		            return;
		        }
		  

		       

		    } else if ("削除".equals(action)) {

		        dao.delete(userId, item_name);

		    }
		    
		    
		  //チェックリストでクリックされた際にTRUEにアップデートする
		    else if ("toggle".equals(action)) {

		        int id = Integer.parseInt(request.getParameter("id"));
		        boolean checked = Boolean.parseBoolean(request.getParameter("checked"));

		        dao.updateChecked(id, checked);
		    }
		    //チェックリストページにリダイレクト
		    response.sendRedirect("/e1/ChecklistServlet");
		}
}
	


