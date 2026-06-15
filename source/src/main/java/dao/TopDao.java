package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Top;

// Topページを表示するDaoクラス
public class TopDao extends Dao {

    // ランキング取得
	public List<Top> getRanking(String userId) throws Exception {

	    List<Top> list = new ArrayList<>();
	    // データベース接続
	    Connection con = getConnection();
	    // ランキング表示用SQL
	    String sql =
	        "SELECT item_name, COUNT(*) AS count " +
	        "FROM lost_items " +
	        "WHERE user_id = ? " +
	        "GROUP BY item_name " +
	        "ORDER BY count DESC " +
	        "LIMIT 5";

	    PreparedStatement st = con.prepareStatement(sql);
	    st.setString(1, userId);
	    // SQLの実行
	    ResultSet rs = st.executeQuery();

	    while (rs.next()) {
	        Top top = new Top();

	        top.setItemName(rs.getString("item_name"));
	        top.setCount(rs.getInt("count"));
	        

	        list.add(top);
	    }

	    rs.close();
	    st.close();
	    con.close();

	    return list;
	}
    // 月間忘れ物数
    public int[] getMonthlyCount(String userId) throws Exception {

        int[] monthly = new int[12];
        // データベース接続
        Connection con = getConnection();
        // グラフ表示用SQL
        String sql =
            "SELECT MONTH(STR_TO_DATE(lost_date,'%Y-%m-%d')) AS month," +
            " COUNT(*) AS total" +
            " FROM lost_items" +
            " WHERE user_id = ? " +
            " GROUP BY MONTH(STR_TO_DATE(lost_date,'%Y-%m-%d'))";

        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, userId);
        // データベース接続
        ResultSet rs = st.executeQuery();

        while(rs.next()){
            monthly[rs.getInt("month") - 1] = rs.getInt("total");
            
        }

        rs.close();
        st.close();
        con.close();

        return monthly;
    }
}