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

	public List<LostItems> select(LostItems item) {

		List<LostItems> list = new ArrayList<LostItems>();

		try {
			con = getConnection();

			String sql =
					"SELECT * FROM lost_items " +
					"WHERE item_name LIKE ? " +
					"AND location LIKE ? " +
					"AND lost_date LIKE ?";

			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, "%" + item.getItem_name() + "%");
			pStmt.setString(2, "%" + item.getLocation() + "%");
			pStmt.setString(3, "%" + item.getLost_date() + "%");

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

	            LostItems li = new LostItems();

	            li.setId(rs.getInt("id"));
	            li.setItem_name(rs.getString("item_name"));
	            li.setLost_date(rs.getString("lost_date"));
	            li.setWeather(rs.getString("weather"));
	            li.setLocation(rs.getString("location"));
	            li.setReason(rs.getString("reason"));

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
