package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

// usersテーブルを操作するDAOクラス
public class UsersDao extends Dao {

	// ログイン認証を行う ユーザーIDとパスワードが一致した場合trueを返す
	public boolean isLoginOK(User user) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"e1", "c3Us6Vdg2KPavBE3");

			// ユーザーIDとパスワードを条件に検索するSQL
			String sql = "SELECT count(*) FROM users WHERE user_id=? AND password=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// プレースホルダへ値を設定
			pStmt.setString(1, user.getUser_id());
			pStmt.setString(2, user.getPassword());

			// SQLを実行
			ResultSet rs = pStmt.executeQuery();

			// 一致するレコードが1件存在する場合ログイン成功
			rs.next();
			if (rs.getInt("count(*)") == 1) {
				loginResult = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		} finally {

			// データベース接続を終了
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// ログイン結果を返す
		return loginResult;
	}

	// ユーザー情報をusersテーブルへ登録する
	public boolean insert(User user) {
		Connection conn = null;
		boolean signupResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースへ接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/e1?"
					+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true",
					"e1", "c3Us6Vdg2KPavBE3");

			// ユーザー登録用SQL
			String sql = "INSERT INTO users VALUES (0, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// ユーザー情報をSQLへ設定
			if (user.getUser_id() != null) {
				pStmt.setString(1, user.getUser_id());
			} else {
				pStmt.setString(1, "");
			}

			if (user.getPassword() != null) {
				pStmt.setString(2, user.getPassword());
			} else {
				pStmt.setString(2, "");
			}

			if (user.getName() != null) {
				pStmt.setString(3, user.getName());
			} else {
				pStmt.setString(3, "");
			}

			if (user.getMail_address() != null) {
				pStmt.setString(4, user.getMail_address());
			} else {
				pStmt.setString(4, "");
			}

			// SQLを実行し、1件登録できたら成功
			if (pStmt.executeUpdate() == 1) {
				signupResult = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			// データベース接続を終了
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 登録結果を返す
		return signupResult;
	}

	// メールアドレスまたはIDが既に登録されているか確認する
	public boolean existsUser(String mailAddress, String userId) {

		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");

			// データベースへ接続
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/e1?" + "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9",
					"e1", "c3Us6Vdg2KPavBE3");

			// メールアドレスまたはIDの重複を確認するSQL
			String sql = "SELECT COUNT(*) FROM users WHERE mail_address=? OR user_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// 入力された値を設定
			pStmt.setString(1, mailAddress);
			pStmt.setString(2, userId);

			// SQLを実行
			ResultSet rs = pStmt.executeQuery();
			rs.next();

			// 1件以上存在する場合は重複あり
			if (rs.getInt(1) > 0) {
				result = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// データベース接続を終了
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 重複チェック結果を返す
		return result;
	}
}