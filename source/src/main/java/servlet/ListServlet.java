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

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String sort = request.getParameter("sort");

        if (sort == null) {
            sort = "new";
        }

        LostItemsDao dao = new LostItemsDao();

        List<LostItems> itemList = dao.selectAll(sort);

        request.setAttribute("itemList", itemList);
        request.setAttribute("sort", sort);

        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp")
                .forward(request, response);
    }
}