package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Checklist;

public class ChecklistsDao extends Dao {

	
	//チェックリスト登録機能
	public boolean insert(Checklist list) {
		Connection conn = null;
		boolean result = false;

		try {
			// データベース接続
	        Connection con = getConnection();

			// SQL文を準備する
			String sql = "INSERT INTO checklists (user_id, item_name, checked_flag) VALUES (?, ?, ?)";
			PreparedStatement pStmt = con.prepareStatement(sql);

			
			// SQL文を完成させる（INSERT）
			pStmt.setString(1, list.getUser_id());
			
			// ユーザー情報をSQLへ設定
			if (list.getItem_name() != null) {
				pStmt.setString(2, list.getItem_name());
			} else {
				pStmt.setString(2, "");
			}
			
			pStmt.setBoolean(3, list.isChecked_flag());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}


	// チェックリスト全件表示
	public List<Checklist> findByUserId(String user_id) {

	    List<Checklist> list = new ArrayList<>();

	    Connection conn = null;
	    PreparedStatement pStmt = null;
	    ResultSet rs = null;

	    try {
	    	// データベース接続
	        Connection con = getConnection();

	        // SQL文を準備する
	        String sql = "SELECT id, user_id, item_name, checked_flag FROM checklists WHERE user_id = ?";
	        pStmt = con.prepareStatement(sql);
	        
	        pStmt.setString(1, user_id);
	        
	        rs = pStmt.executeQuery();
	        
	        
	     // SQLを全表示
	        while (rs.next()) {

	            Checklist c = new Checklist();

	            c.setId(rs.getInt("id"));
	            c.setUser_id(rs.getString("user_id"));
	            c.setItem_name(rs.getString("item_name"));
	            c.setChecked_flag(rs.getBoolean("checked_flag"));

	            list.add(c);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	     // データベースを切断
	    } finally {
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }

	    return list;
	}

//チェックリスト削除機能
public boolean delete(String item_name) {
	 Connection conn = null;
	    PreparedStatement pStmt = null;
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");

    	// データベース接続
        Connection con = getConnection();
		
		// SQL文を完成させる（削除）
		  String sql = "DELETE FROM checklists WHERE item_name = ?";
	        pStmt = con.prepareStatement(sql);

	        pStmt.setString(1, item_name);

	        int result = pStmt.executeUpdate();
	        return result > 0;

        

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
        return false;
    }

//チェックリスト内のチェックが付いたら上書きを行う
public boolean updateChecked(int id, boolean checked) {

    Connection conn = null;

    try {
    	// データベース接続
        Connection con = getConnection();
        

        String sql = "UPDATE checklists SET checked_flag = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setBoolean(1, checked);
        ps.setInt(2, id);

        return ps.executeUpdate() == 1;

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
		// データベースを切断
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
    return false;
}
}
