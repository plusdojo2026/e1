package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Regist;

public class RegistDao {

	public boolean insert(Regist card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/motta_db?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"root", "password");

			// SQL文を準備する
			String sql = "INSERT INTO lost_items (name,location,date,weather,reason) " + "VALUES (?,?.?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			// SQL文を完成させる（INSERT）
			if (card.getName() != null) {
				pStmt.setString(1, card.getName());
			} else {
				pStmt.setString(1, "");
			}
			if (card.getLocation() != null) {
				pStmt.setString(2, card.getLocation());
			} else {
				pStmt.setString(2, "");
			}
			if (card.getDate() != null) {
				pStmt.setString(3, card.getDate());
			} else {
				pStmt.setString(3, "");
			}
			if (card.getWeather() != null) {
				pStmt.setString(4, card.getWeather());
			} else {
				pStmt.setString(4, "");
			}
			if (card.getReason() != null) {
				pStmt.setString(5, card.getReason());
			} else {
				pStmt.setString(5, "");
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