package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Checklist;

public class ChecklistsDao {

	public boolean insert(Checklist list) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO checklists (user_id, item_name, checked_flag) VALUES (?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			
			// SQL文を完成させる（INSERT）
			pStmt.setString(1, list.getUser_id());
			
			
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
		} catch (ClassNotFoundException e) {
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
	public List<Checklist> findAll() {

	    List<Checklist> list = new ArrayList<>();

	    Connection conn = null;
	    PreparedStatement pStmt = null;
	    ResultSet rs = null;

	    try {
	        // JDBCドライバを読み込む
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        // データベースに接続する
	        conn = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/e1?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
	            "root",
	            "password"
	        );

	        // SQL文を準備する
	        String sql = "SELECT id, user_id, item_name, checked_flag FROM checklists";
	        pStmt = conn.prepareStatement(sql);

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
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (pStmt != null) pStmt.close(); } catch (Exception e) {}
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

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e1?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "password");
		
		// SQL文を完成させる（削除）
		  String sql = "DELETE FROM checklists WHERE item_name = ?";
	        pStmt = conn.prepareStatement(sql);

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
    	Class.forName("com.mysql.cj.jdbc.Driver");

		// データベースに接続する
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e1?"
				+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
				"root", "password");
        

        String sql = "UPDATE checklists SET checked_flag = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

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
