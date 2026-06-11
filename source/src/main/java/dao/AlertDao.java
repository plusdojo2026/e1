package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import dto.Alert;

public class AlertDao extends Dao {

    public int insert(Alert alert) throws Exception {

        Connection con = getConnection();

        String sql =
            "INSERT INTO alerts(alert_date) VALUES(?)";

        PreparedStatement st =
            con.prepareStatement(sql);

        st.setString(1, alert.getAlertDate());

        int count = st.executeUpdate();

        st.close();
        con.close();

        return count;
    }
}