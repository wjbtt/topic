package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//加载驱动
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//获取链接
	public Connection getConnection() throws SQLException{
		Connection conn = null;
		String user = "root";
		String password = "1234";
		String url = "jdbc:mysql://localhost:3306/db_news?"+
				"useUnicode=true&characterEncoding=utf-8";
		// 获得链接	
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		DBUtil dbu = new DBUtil();
		Connection conn = dbu.getConnection();
		System.out.println(conn);
	}
}
