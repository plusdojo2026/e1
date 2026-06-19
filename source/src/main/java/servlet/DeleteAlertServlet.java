package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlertDao;

// 通知を削除するサーブレット
@WebServlet("/DeleteAlertServlet")
public class DeleteAlertServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        try {
            AlertDao dao = new AlertDao();
            dao.delete(id);
        } catch(Exception e) {
            throw new ServletException(e);
        }
        // AlertServletへ遷移
        response.sendRedirect("AlertServlet");
    }
}