package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Lost_itemsDAO;
import dto.Lost_items;

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

protected void doPost(HttpServletRequest request,
HttpServletResponse response)
throws ServletException, IOException {

request.setCharacterEncoding("UTF-8");

Lost_items item = new Lost_items();

item.setName(request.getParameter("name"));
item.setLocation(request.getParameter("location"));
item.setDate(request.getParameter("date"));

Lost_itemsDAO dao = new Lost_itemsDAO();

List<Lost_items> result =
dao.select(item);

request.setAttribute("resultList", result);

RequestDispatcher dispatcher =
request.getRequestDispatcher(
"/WEB-INF/jsp/search_result.jsp");

dispatcher.forward(request, response);
}
}