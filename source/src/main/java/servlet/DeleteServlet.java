package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LostItemsDao;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id"));

        LostItemsDao dao = new LostItemsDao();

        dao.delete(id);
        
        request.setAttribute("message",
                "削除が完了しました。");

        request.getRequestDispatcher(
        	    "/WEB-INF/jsp/delete_result.jsp")
        	    .forward(request, response);
    }
}