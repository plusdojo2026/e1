package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {
	private String database = "e1";
    private String option = "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true";
//	private String user = "root";
//  private String password = "password";
    private String user = "e1";
    private String password = "c3Us6Vdg2KPavBE3";

	public Dao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	// TODO 自動生成されたコンストラクター・スタブ
	@Override
	public String toString() {
		return "jdbc:mysql://localhost:3306/" + database + "?" + option;
	}
	
	protected Connection getConnection() {
		try {
			return DriverManager.getConnection(this.toString(), this.user, this.password);			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	protected void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		}
}