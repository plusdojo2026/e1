package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.Alert;

// alertsテーブルを操作するDaoクラス
public class AlertDao extends Dao {

    public int insert(Alert alert) throws Exception {

    	// データベース接続
        Connection con = getConnection();

        // 通知データを追加するSQL
        String sql = "INSERT INTO alerts(alert_date) VALUES(?)";
        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, alert.getAlertDate());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count;
    }
}