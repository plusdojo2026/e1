package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LostItemsDao;

// 忘れ物情報の削除を行うサーブレット
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    // 削除ボタンが押されたときの処理
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // リクエストパラメータから削除対象のIDを取得
        int id = Integer.parseInt(
                request.getParameter("id"));

        // DAOを生成
        LostItemsDao dao = new LostItemsDao();

        // 指定されたIDのデータを削除
        dao.delete(id);

        // 削除完了メッセージをリクエストスコープに保存
        request.setAttribute("message","削除が完了しました。");

        // 削除完了画面へ遷移
        request.getRequestDispatcher("/WEB-INF/jsp/delete_result.jsp").forward(request, response);
    }
}