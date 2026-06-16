package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.LostItems;

public class LostItemsDao extends Dao{
	
	Connection con = null;
    // 検索 + 並び替え
    public List<LostItems> select(LostItems item, String userId, String sort) {

        List<LostItems> list = new ArrayList<LostItems>();

        try {
            con = getConnection();

            String sql =
                    "SELECT * FROM lost_items " +
                    "WHERE user_id = ? " +
                    "AND item_name LIKE ? " +
                    "AND location LIKE ? " +
                    "AND lost_date LIKE ? ";

            if ("old".equals(sort)) {
                sql += "ORDER BY lost_date ASC";
            } else {
                sql += "ORDER BY lost_date DESC";
            }

            PreparedStatement pStmt = con.prepareStatement(sql);

            pStmt.setString(1, userId);
            pStmt.setString(2, "%" + item.getItem_name() + "%");
            pStmt.setString(3, "%" + item.getLocation() + "%");
            pStmt.setString(4, "%" + item.getLost_date() + "%");

            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {

                LostItems li = new LostItems();

                li.setId(rs.getInt("id"));
                li.setItem_name(rs.getString("item_name"));
                li.setLost_date(rs.getString("lost_date"));
                li.setWeather(rs.getString("weather"));
                li.setLocation(rs.getString("location"));
                li.setReason(rs.getString("reason"));
                li.setUser_id(rs.getString("user_id"));

                list.add(li);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }
	//削除機能
    public boolean delete(int id) {

        try {
            Connection con = getConnection();

            String sql =
                "DELETE FROM lost_items WHERE id=?";

            PreparedStatement pStmt =
                con.prepareStatement(sql);

            pStmt.setInt(1, id);

            int result = pStmt.executeUpdate();

            con.close();

            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	// 一覧画面用
    public List<LostItems> selectAll(String userId, String sort){

	    List<LostItems> list = new ArrayList<LostItems>();

	    try {
	        con = getConnection();

	        String sql = "SELECT * FROM lost_items " + "WHERE user_id = ? ";

	        if ("old".equals(sort)) {
	            sql += "ORDER BY lost_date ASC";
	        } else {
	            sql += "ORDER BY lost_date DESC";
	        }

	        PreparedStatement pStmt =
	        	    con.prepareStatement(sql);

	        pStmt.setString(1, userId);

	        ResultSet rs =
	        	    pStmt.executeQuery();

	        while (rs.next()) {

	            LostItems li = new LostItems();

	            li.setId(rs.getInt("id"));
	            li.setItem_name(rs.getString("item_name"));
	            li.setLost_date(rs.getString("lost_date"));
	            li.setWeather(rs.getString("weather"));
	            li.setLocation(rs.getString("location"));
	            li.setReason(rs.getString("reason"));
	            li.setUser_id(rs.getString("user_id"));

	            list.add(li);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();

	    } finally {

	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return list;
	}
}
