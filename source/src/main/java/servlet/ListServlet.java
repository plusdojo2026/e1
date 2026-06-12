package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LostItemsDao;
import dto.LostItems;

@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {

    //一覧画面を表示する処理
	@Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

    	// JSPから送られてきた並び替え条件を取得
    	String sort = request.getParameter("sort");

    	// 新しい順をデフォルトに設定
    	if (sort == null) {
            sort = "new";
        }

    	// DAOのインスタンスを生成
    	LostItemsDao dao = new LostItemsDao();

    	// DAOを呼び出して忘れ物一覧を取得
        // sortの値によって並び替えを変更
    	List<LostItems> itemList = dao.selectAll(sort);

    	// 取得した一覧データをJSPへ渡す
    	request.setAttribute("itemList", itemList);
    	// 現在の並び順もJSPへ渡す
        request.setAttribute("sort", sort);

        // list.jspへ画面遷移
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp")
                .forward(request, response);
    }
}