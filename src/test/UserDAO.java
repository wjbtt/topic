package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import util.DBUtil;

public class UserDAO {
	 
	
	//preparedStatement
	public static User findUserByUnameAndPwd1(String uname,String pwd){
		User user = null;
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "select * from user where uname = ? and pwd = ?";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, uname);
			pstm.setString(2, pwd);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setPwd(rs.getString("pwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
   
	public User findUserByUname(String name) {
		 
		User user = null;
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "select * from user where uname = ? ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setPwd(rs.getString("pwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	public int zhuce(String name, String psw) {
		 int stus = 0;
		DBUtil dbu=new DBUtil();
		Connection conn=null;
		try {
			conn=dbu.getConnection();
			String sql="insert into user(uname,pwd) values(?,?)";
			PreparedStatement pstm=conn.prepareStatement(sql);
			pstm.setString(1, name);
			pstm.setString(2, psw);
			stus =pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return stus;
	}

	
}
