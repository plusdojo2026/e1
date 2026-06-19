package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.Alert;

// alertsテーブルを操作するDaoクラス
public class AlertDao extends Dao {

    public int insert(Alert alert) throws Exception {

    	// データベース接続
        Connection con = getConnection();

        // 通知データを追加するSQL
        String sql = "INSERT INTO alerts(user_id, alert_date) VALUES(?,?)";
        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, alert.getUserId());
        st.setString(2, alert.getAlertDate());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count;
    }
    
    public List<Alert> select(String userId) throws Exception {

        List<Alert> list = new ArrayList<>();
        // データベース接続
        Connection con = getConnection();
        //　一覧を取得するSQL
        String sql = "SELECT * FROM alerts WHERE user_id=? ORDER BY alert_date";
        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, userId);

        ResultSet rs = st.executeQuery();

        while(rs.next()){

            Alert alert = new Alert();

            alert.setId(rs.getInt("id"));
            alert.setUserId(rs.getString("user_id"));
            alert.setAlertDate(rs.getString("alert_date"));

            list.add(alert);
        }

        rs.close();
        st.close();
        con.close();

        return list;
    }
    
    public int delete(int id) throws Exception {
    	//　データベース接続
        Connection con = getConnection();
        // 通知データを削除するSQL
        String sql = "DELETE FROM alerts WHERE id=?";
        PreparedStatement st = con.prepareStatement(sql);

        st.setInt(1, id);

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count;
    }
    
}
