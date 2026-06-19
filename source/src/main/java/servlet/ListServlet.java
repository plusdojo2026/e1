package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    	
    	// セッション取得
        HttpSession session = request.getSession();

        // ログインユーザーID取得
        String userId =
            (String) session.getAttribute("user_id");

    	// DAOのインスタンスを生成
    	LostItemsDao dao = new LostItemsDao();

    	// ログインユーザーの忘れ物だけ取得
    	List<LostItems> itemList =
    	        dao.selectAll(userId, sort);
    	
    	//曜日の取得
    	Map<DayOfWeek, Integer> weekCount = new HashMap<>();
    	for (LostItems item : itemList) {
    	    LocalDate date =
    	            LocalDate.parse(item.getLost_date());
    	    DayOfWeek day =
    	            date.getDayOfWeek();
    	    weekCount.put(
    	            day,
    	            weekCount.getOrDefault(day, 0) + 1);
    	}
    	//　最多曜日を取得
    	DayOfWeek maxDay = null;
    	int maxCount = 0;
    	for (Map.Entry<DayOfWeek, Integer> entry
    	        : weekCount.entrySet()) {
    	    if (entry.getValue() > maxCount) {
    	        maxCount = entry.getValue();
    	        maxDay = entry.getKey();
    	    }
    	}
    	//コメント生成
    	String analysisComment;

    	if (maxDay == null) {

    	    analysisComment =
    	            "まだ分析できるデータがありません";

    	} else {

    	    String dayName = "";

    	    switch (maxDay) {

    	    case MONDAY:
    	        dayName = "月曜日";
    	        break;

    	    case TUESDAY:
    	        dayName = "火曜日";
    	        break;

    	    case WEDNESDAY:
    	        dayName = "水曜日";
    	        break;

    	    case THURSDAY:
    	        dayName = "木曜日";
    	        break;

    	    case FRIDAY:
    	        dayName = "金曜日";
    	        break;

    	    case SATURDAY:
    	        dayName = "土曜日";
    	        break;

    	    case SUNDAY:
    	        dayName = "日曜日";
    	        break;
    	    }

    	    analysisComment =
    	    		"むむっ！" +
    	            dayName +
    	            "に忘れ物が多いようです！";
    	}

    	// 取得した一覧データをJSPへ渡す
    	request.setAttribute("itemList", itemList);
    	//曜日情報をJSPへ渡す
    	request.setAttribute("analysisComment", analysisComment);
    	// 現在の並び順もJSPへ渡す
        request.setAttribute("sort", sort);

        // list.jspへ画面遷移
        request.getRequestDispatcher("/WEB-INF/jsp/list.jsp")
                .forward(request, response);
    }
}