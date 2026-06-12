package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TopDao;
import dto.Top;

@WebServlet("/TopServlet")
public class TopServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {

            TopDao dao = new TopDao();

            List<Top> ranking = dao.getRanking();

            int yearlyCount = dao.getYearlyCount();
            int[] monthlyCount = dao.getMonthlyCount();

            request.setAttribute("ranking", ranking);
            request.setAttribute("yearlyCount", yearlyCount);
            request.setAttribute("monthlyCount", monthlyCount);
            

            request.getRequestDispatcher("/WEB-INF/jsp/top.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

}