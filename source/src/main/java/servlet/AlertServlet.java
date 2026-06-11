package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlertDao;
import dto.Alert;

@WebServlet("/AlertServlet")
public class AlertServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request,
	        HttpServletResponse response)
	        throws ServletException, IOException {

	    request.getRequestDispatcher(
	        "/WEB-INF/jsp/alert.jsp")
	        .forward(request, response);
	}

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String date = request.getParameter("date");
        String time = request.getParameter("time");

        // "2026-06-11 09:00" の形式で保存
        String alertDate = date + " " + time;

        Alert alert = new Alert();
        alert.setAlertDate(alertDate);

        try {
            AlertDao dao = new AlertDao();
            dao.insert(alert);

            request.getRequestDispatcher(
                "/WEB-INF/jsp/result.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}