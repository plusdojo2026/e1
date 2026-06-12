package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Top;

public class TopDao extends Dao {

    // ランキング取得
	public List<Top> getRanking() throws Exception {

	    List<Top> list = new ArrayList<>();

	    Connection con = getConnection();

	    String sql =
	        "SELECT item_name, COUNT(*) AS count " +
	        "FROM lost_items " +
	        "GROUP BY item_name " +
	        "ORDER BY count DESC " +
	        "LIMIT 5";

	    PreparedStatement st = con.prepareStatement(sql);

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

    // 年間忘れ物数
    public int getYearlyCount() throws Exception {

        Connection con = getConnection();

        String sql =
        		"SELECT COUNT(*) FROM lost_items " +
        		"WHERE YEAR(STR_TO_DATE(lost_date,'%Y-%m-%d'))=YEAR(CURDATE())";

        PreparedStatement st = con.prepareStatement(sql);

        ResultSet rs = st.executeQuery();

        int count = 0;

        if(rs.next()){
            count = rs.getInt(1);
        }

        rs.close();
        st.close();
        con.close();

        return count;
    }
    // 月間忘れ物数
    public int[] getMonthlyCount() throws Exception {

        int[] monthly = new int[12];

        Connection con = getConnection();

        String sql =
            "SELECT MONTH(STR_TO_DATE(lost_date,'%Y-%m-%d')) AS month," +
            " COUNT(*) AS total" +
            " FROM lost_items" +
            " GROUP BY MONTH(STR_TO_DATE(lost_date,'%Y-%m-%d'))";

        PreparedStatement st = con.prepareStatement(sql);
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