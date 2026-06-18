package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.Regist;

//　登録を行うDaoクラス
public class RegistDao extends Dao {
	//　追加
	public boolean insert(Regist list) {
		Connection conn = null;
		PreparedStatement pStmt = null;
		boolean result = false;

		try {
			// データベースに接続
			conn = getConnection();

			// 登録用SQL
			String sql = "INSERT INTO lost_items "
					+ "(id, item_name, lost_date, weather, location, reason, user_id) "
					+ "VALUES (0, ?, ?, ?, ?, ?, ?)";

			pStmt = conn.prepareStatement(sql);
			//　値のセット
			pStmt.setString(1, list.getItem_name() != null ? list.getItem_name() : "");
			pStmt.setString(2, list.getLost_date() != null ? list.getLost_date() : "");
			pStmt.setString(3, list.getWeather() != null ? list.getWeather() : "");
			pStmt.setString(4, list.getLocation() != null ? list.getLocation() : "");
			pStmt.setString(5, list.getReason() != null ? list.getReason() : "");
			pStmt.setString(6, list.getUser_id() != null ? list.getUser_id() : "");

			// SQL実行
			result = (pStmt.executeUpdate() == 1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// PreparedStatementを閉じる
			if (pStmt != null) {
				try {
					pStmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			// Connectionを閉じる
			closeConnection(conn);
		}

		return result;
	}
}