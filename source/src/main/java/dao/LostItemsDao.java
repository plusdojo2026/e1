package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LostItems;

// 忘れ物情報の検索・削除などを行うDAOクラス
public class LostItemsDao extends Dao {

    // データベース接続用
    Connection con = null;

    // 検索条件と並び替え条件に従ってデータを取得する
    public List<LostItems> select(LostItems item, String userId, String sort, String month) {

        // 検索結果を格納するリスト
        List<LostItems> list = new ArrayList<LostItems>();

        try {
            // データベースへ接続
            con = getConnection();

            // 基本のSQL文
            String sql =
                    "SELECT * FROM lost_items " +
                    "WHERE user_id = ? " +
                    "AND item_name LIKE ? " +
                    "AND location LIKE ? ";

            // 月検索が指定されている場合
            if (month != null && !month.isEmpty()) {
                sql += "AND MONTH(lost_date) = ? ";
            }

            // 開始日が指定されている場合
            if (item.getStartDate() != null &&
                    !item.getStartDate().isEmpty()) {
                sql += "AND lost_date >= ? ";
            }

            // 終了日が指定されている場合
            if (item.getEndDate() != null &&
                    !item.getEndDate().isEmpty()) {
                sql += "AND lost_date <= ? ";
            }

            // 並び替え条件
            if ("old".equals(sort)) {
                // 古い順
                sql += "ORDER BY lost_date ASC";
            } else {
                // 新しい順
                sql += "ORDER BY lost_date DESC";
            }

            // SQL文を準備
            PreparedStatement pStmt = con.prepareStatement(sql);

            // プレースホルダ番号
            int idx = 1;

            // ユーザーID
            pStmt.setString(idx++, userId);

            // 名称検索
            pStmt.setString(idx++, "%" + item.getItem_name() + "%");

            // 場所検索
            pStmt.setString(idx++, "%" + item.getLocation() + "%");

            // 月検索
            if (month != null && !month.isEmpty()) {
                pStmt.setInt(idx++, Integer.parseInt(month));
            }

            // 開始日検索
            if (item.getStartDate() != null &&
                    !item.getStartDate().isEmpty()) {
                pStmt.setString(idx++, item.getStartDate());
            }

            // 終了日検索
            if (item.getEndDate() != null &&
                    !item.getEndDate().isEmpty()) {
                pStmt.setString(idx++, item.getEndDate());
            }

            // SQLを実行
            ResultSet rs = pStmt.executeQuery();

            // 検索結果をDTOに格納
            while (rs.next()) {

                LostItems li = new LostItems();

                li.setId(rs.getInt("id"));
                li.setItem_name(rs.getString("item_name"));
                li.setLost_date(rs.getString("lost_date"));
                li.setWeather(rs.getString("weather"));
                li.setLocation(rs.getString("location"));
                li.setReason(rs.getString("reason"));
                li.setUser_id(rs.getString("user_id"));

                // リストへ追加
                list.add(li);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            // データベース接続を閉じる
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 検索結果を返す
        return list;
    }

    // 指定したIDの忘れ物情報を削除する
    public boolean delete(int id) {

        try {
            // データベースへ接続
            Connection con = getConnection();

            // 削除用SQL
            String sql =
                    "DELETE FROM lost_items WHERE id=?";

            PreparedStatement pStmt =
                    con.prepareStatement(sql);

            // IDを設定
            pStmt.setInt(1, id);

            // SQLを実行
            int result = pStmt.executeUpdate();

            // 接続を閉じる
            con.close();

            // 1件削除できたらtrueを返す
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 一覧画面用のデータを取得する
    public List<LostItems> selectAll(String userId, String sort) {

        // 取得したデータを格納するリスト
        List<LostItems> list = new ArrayList<LostItems>();

        try {
            // データベースへ接続
            con = getConnection();

            // ユーザーごとのデータを取得
            String sql =
                    "SELECT * FROM lost_items " +
                    "WHERE user_id = ? ";

            // 並び替え条件
            if ("old".equals(sort)) {
                sql += "ORDER BY lost_date ASC";
            } else {
                sql += "ORDER BY lost_date DESC";
            }

            PreparedStatement pStmt =
                    con.prepareStatement(sql);

            // ユーザーIDを設定
            pStmt.setString(1, userId);

            // SQLを実行
            ResultSet rs = pStmt.executeQuery();

            // 結果をDTOへ格納
            while (rs.next()) {

                LostItems li = new LostItems();

                li.setId(rs.getInt("id"));
                li.setItem_name(rs.getString("item_name"));
                li.setLost_date(rs.getString("lost_date"));
                li.setWeather(rs.getString("weather"));
                li.setLocation(rs.getString("location"));
                li.setReason(rs.getString("reason"));
                li.setUser_id(rs.getString("user_id"));

                // リストへ追加
                list.add(li);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            // データベース接続を閉じる
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 一覧データを返す
        return list;
    }
}