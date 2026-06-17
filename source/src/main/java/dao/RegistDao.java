package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Regist;

public class RegistDao {

	public boolean insert(Regist list) {
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
			String sql = "INSERT INTO lost_items (id,item_name,lost_date,weather,location,reason,user_id) " + "VALUES (0,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			// SQL文を完成させる（INSERT）
			if (list.getItem_name() != null) {
				pStmt.setString(1, list.getItem_name());
			} else {
				pStmt.setString(1, "");
			}
			if (list.getLost_date() != null) {
				pStmt.setString(2, list.getLost_date());
			} else {
				pStmt.setString(2, "");
			}
			if (list.getWeather() != null) {
				pStmt.setString(3, list.getWeather());
			} else {
				pStmt.setString(3, "");
			}
			if (list.getLocation() != null) {
				pStmt.setString(4, list.getLocation());
			} else {
				pStmt.setString(4, "");
			}
			if (list.getReason() != null) {
				pStmt.setString(5, list.getReason());
			} else {
				pStmt.setString(5, "");
			}
			if(list.getUser_id()!=null) {
				pStmt.setString(6,list.getUser_id());
			} else {
				pStmt.setString(6, "");
			}

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
}