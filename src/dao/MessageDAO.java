package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import entity.Message;

public class MessageDAO {
	// 新增
	public int saveMsg(Message msg) {
		int stus = 0;
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "insert into t_message(content,author,tid,rid,rtime) "
					+ " values(?,?,?,?,now())";
			PreparedStatement pstm = conn.prepareStatement(sql) ;
			pstm.setString(1, msg.getContent());
			pstm.setString(2, msg.getAuthor());
			pstm.setInt(3, msg.getTid());
			pstm.setInt(4, msg.getRid());

			stus = pstm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return stus;
	}

	// 显示评论/留言
	public List<Message> findAllMsg(int tid) {
		List<Message> msgs = new ArrayList<Message>();

		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "select * from t_message where tid = " + tid;
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int mid = rs.getInt("mid");
				String content = rs.getString("content");
				String author = rs.getString("author");
				// int tid = rs.getInt("tid");
				int rid = rs.getInt("rid");
				Timestamp rtime = rs.getTimestamp("rtime");
				Message msg = new Message(mid, content, author, tid, rid, rtime);
				msgs.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return msgs;
	}

	//显示评论/留言 -- @ 
	public List<Message> findAllMsgs(int tid) {
		List<Message> msgs = new ArrayList<Message>();

		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "select m.*,rm.author from t_message m " +
					" left join t_message rm  on m.rid = rm.mid";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int mid = rs.getInt(1);
				String content = rs.getString(2);
				String author = rs.getString(3);
				// int tid = rs.getInt("tid");
				int rid = rs.getInt(5);
				Timestamp rtime = rs.getTimestamp(6);
				Message msg = new Message(mid, content, author, tid, rid, rtime);
				msg.setRauthor(rs.getString(7));
				msgs.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return msgs;
	}
	//显示评论/留言 -- @ 
	public List<Message> findAllMsgs1(int tid) {
		List<Message> msgs = new ArrayList<Message>();
		
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "select rm.*,m.author rauthor from t_message m " +
					" right join (select * from t_message where tid = "+tid+") rm" +
						"  on rm.rid = m.mid";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int mid = rs.getInt("mid");
				String content = rs.getString("content");
				String author = rs.getString("author");
				// int tid = rs.getInt("tid");
				int rid = rs.getInt("rid");
				Timestamp rtime = rs.getTimestamp("rtime");
				Message msg = new Message(mid, content, author, tid, rid, rtime);
				msg.setRauthor(rs.getString("rauthor"));
				msgs.add(msg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return msgs;
	}
}
