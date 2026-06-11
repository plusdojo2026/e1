package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Lost_items;

public class Lost_itemsDAO {

public List<Lost_items> select(Lost_items item) {

Connection conn = null;
List<Lost_items> list = new ArrayList<>();

try {
Class.forName("com.mysql.cj.jdbc.Driver");

conn = DriverManager.getConnection(
"jdbc:mysql://localhost:3306/データベース名",
"ユーザー名",
"パスワード");

String sql =
"SELECT * FROM lost_items " +
"WHERE name LIKE ? " +
"AND location LIKE ? " +
"AND lost_date LIKE ?";

PreparedStatement pStmt =
conn.prepareStatement(sql);

pStmt.setString(1, "%" + item.getName() + "%");
pStmt.setString(2, "%" + item.getLocation() + "%");
pStmt.setString(3, "%" + item.getDate() + "%");

ResultSet rs = pStmt.executeQuery();

while (rs.next()) {

Lost_items li = new Lost_items();

li.setId(rs.getInt("id"));
li.setName(rs.getString("name"));
li.setLocation(rs.getString("location"));
li.setDate(rs.getString("date"));
li.setWeather(rs.getString("weather"));
li.setReason(rs.getString("reason"));

list.add(li);
}

} catch (Exception e) {
e.printStackTrace();

} finally {

try {
if (conn != null) {
conn.close();
}
} catch (SQLException e) {
e.printStackTrace();
}
}

return list;
}
}