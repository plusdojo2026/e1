package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Checklist;


public class ChecklistsDAO  extends Dao{

	Connection con = getConnection();
	List<Checklist> list = new ArrayList<Checklist>();
	
	public List<Checklist> select(Checklist item) {

		try {
			con = getConnection();

			String sql =
			"SELECT * FROM Checklists " +
			"WHERE name LIKE ? " ;
			

			PreparedStatement pStmt = con.prepareStatement(sql);
			
			pStmt.setString(1, "%" + item.getItem_name() + "%");

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {

				Checklist li = new Checklist();

	            li.setItem_name(rs.getString("name"));


	           

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
