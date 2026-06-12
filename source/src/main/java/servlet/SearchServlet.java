package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LostItemsDao;
import dto.LostItems;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

	    request.getRequestDispatcher(
		        "/WEB-INF/jsp/search.jsp")
		        .forward(request, response);
	}

protected void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException {
	System.out.println("SearchServlet doPost開始");
request.setCharacterEncoding("UTF-8");

LostItems item = new LostItems();

item.setItem_name(request.getParameter("name") == null ? "" : request.getParameter("name"));
item.setLocation(request.getParameter("location"));
item.setLost_date(request.getParameter("date"));

LostItemsDao dao = new LostItemsDao();

List<LostItems> result =dao.select(item);
System.out.println("検索結果件数：" + result.size());
request.setAttribute("resultList", result);

RequestDispatcher dispatcher =
request.getRequestDispatcher(
"/WEB-INF/jsp/search_result.jsp");

dispatcher.forward(request, response);
}
}