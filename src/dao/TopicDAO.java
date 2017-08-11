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
import entity.Topic;

//操作和话题相关的dao
public class TopicDAO {
	//新增话题
	public int saveTopic(Topic t){
		int stus = 0;
		//获得链接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql语句: insert into t_topic(xxxx) values(?,?,?,?)
			String sql = "insert into t_topic(content,author,publishTime,updateTime) "+
					" values(?,?,now(),now())";
			//创建Statement
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, t.getContent());
			pstm.setString(2, t.getAuthor());
			//pstm.setTimestamp(3, t.getPtime());
			stus = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stus;
	}
	//修改话题：参数--new topic
	public int updateTopic(Topic t){
		int stus = 0; // 用来存储修改后影响的条数
		//获取链接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql: update t_topic set orderIndex = 1,agree = 1,
			// disagree = (disagree + 1)  where tid = 1;
			String sql = "update t_topic set content = ?,orderIndex = ?,updateTime = now() " +
					" where tid = ?";
			//获取Statement
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setString(1, t.getContent());
			pstm.setInt(2, t.getOrderIndex());
			pstm.setInt(3, t.getTid());
			//执行sql
			stus = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return stus;
	}
	//查找话题--参数
	//查找所有的话题
	public List<Topic> findAllTopic(){
		List<Topic> ts = new ArrayList<Topic>();
		//获取连接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql:select * from t_topic;
			String sql = "select * from t_topic order by updateTime desc";
			//statement
			Statement stm = conn.createStatement();
			//返回的是查询后结果集
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				int tid = rs.getInt("tid");
				String content = rs.getString("content");
				String author = rs.getString("author");
				int orderIndex = rs.getInt("orderIndex");
				int agree = rs.getInt("agree");
				int disagree = rs.getInt("disagree");
				Timestamp publishTime = rs.getTimestamp("publishTime");
				Timestamp updateTime = rs.getTimestamp("updateTime");
				//把数据库当前条 记录保存为java对象
				Topic t = new Topic(tid, content, author, agree, disagree,
						orderIndex,publishTime, updateTime);
				//将该对象添加到list集合中
				ts.add(t);
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
		return ts;
	}
	
	//删除话题--参数
	public int deleteTopic(int tid){
		int stus = 0;
		//conn
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql:delete from t_topic where tid = 1
			String sql = "delete from t_topic where tid = "+tid;
			//statement
			Statement stm = conn.createStatement();
			stus = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stus ;
	}
	
	//修改话题的 点赞/吐槽数量: update t_topic set agree = (agree+1), updateTime = now() where tid = 1;
	public int updateTopic(int tid, int type, int num) {
		int stus = 0; // 用来存储修改后影响的条数
		//获取链接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql:update t_topic set agree = (agree+1), updateTime = now() where tid = 1;
			String sql = "update t_topic set agree = (agree+?), updateTime = now() where tid = ?";
			if(type==1){
				sql = "update t_topic set disagree = (disagree+?), updateTime = now() where tid = ?";
			}
			//获取Statement
			PreparedStatement pstm = conn.prepareStatement(sql);
			pstm.setInt(1, num);
			pstm.setInt(2, tid);
			//执行sql
			stus = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			//关闭连接
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return stus;
	}
	//根据话题和作者进行模糊查询
	public List<Topic> findTopics(String content, String author) {
		List<Topic> ts = new ArrayList<Topic>();
		//获取连接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql:
			// 如果content/author为空的时候,则模糊条件不需要content/author参与
			// select * from t_topic where 1=1 and content like '%str1%' 
		 	//		and author like '%str2%';
			
			String isContent = "".equals(content) ? "" : "and content like '%"+content+"%' ";
			String isAuthor = "".equals(author) ? "" : "and author like '%"+author+"%' ";
			String sql = "select * from t_topic where 1=1 " + isContent + isAuthor 
					+" order by updateTime desc";
			
			//statement
			Statement stm = conn.createStatement();
			//返回的是查询后结果集
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				int tid = rs.getInt("tid");
				String content1 = rs.getString("content");
				String author1 = rs.getString("author");
				int orderIndex = rs.getInt("orderIndex");
				int agree = rs.getInt("agree");
				int disagree = rs.getInt("disagree");
				Timestamp publishTime = rs.getTimestamp("publishTime");
				Timestamp updateTime = rs.getTimestamp("updateTime");
				//把数据库当前条 记录保存为java对象
				Topic t = new Topic(tid, content1, author1, agree, disagree,
						orderIndex,publishTime, updateTime);
				//将该对象添加到list集合中
				ts.add(t);
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
		return ts;
	}
	
	//根据点赞/吐槽数量排序: select * from t_topic order by agree/disagree (asc)/desc;
	public List<Topic> findTopics(int orderType, int orderWay) {
		List<Topic> ts = new ArrayList<Topic>();
		//获取连接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql:select * from t_topic order by agree/disagree (asc)/desc;
			
			String sql = "select * from t_topic order by agree";
			if(orderType == 0 && orderWay == 1){
				sql = "select * from t_topic order by agree desc";
			}
			if(orderType == 1 && orderWay == 0){
				sql = "select * from t_topic order by disagree";
			}
			if(orderType == 1 && orderWay == 1){
				sql = "select * from t_topic order by disagree desc";
			}
			
			//statement
			Statement stm = conn.createStatement();
			//返回的是查询后结果集
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				int tid = rs.getInt("tid");
				String content = rs.getString("content");
				String author = rs.getString("author");
				int orderIndex = rs.getInt("orderIndex");
				int agree = rs.getInt("agree");
				int disagree = rs.getInt("disagree");
				Timestamp publishTime = rs.getTimestamp("publishTime");
				Timestamp updateTime = rs.getTimestamp("updateTime");
				//把数据库当前条 记录保存为java对象
				Topic t = new Topic(tid, content, author, agree, disagree,
						orderIndex,publishTime, updateTime);
				//将该对象添加到list集合中
				ts.add(t);
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
		return ts;
	}
	//查看某个topic
	public Topic findTopicByTid(int tid) {
		Topic t = null;
		//获取连接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			//sql:select * from t_topic;
			String sql = "select * from t_topic where tid = "+tid;
			//statement
			Statement stm = conn.createStatement();
			//返回的是查询后结果集
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				String content = rs.getString("content");
				String author = rs.getString("author");
				int orderIndex = rs.getInt("orderIndex");
				int agree = rs.getInt("agree");
				int disagree = rs.getInt("disagree");
				Timestamp publishTime = rs.getTimestamp("publishTime");
				Timestamp updateTime = rs.getTimestamp("updateTime");
				//把数据库当前条 记录保存为java对象
				t = new Topic(tid, content, author, agree, disagree,
						orderIndex,publishTime, updateTime);
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
		return t;
	}
	
	//拓展----------------------------
	//前面保持一致,后面采取模糊匹配  -- 查询前几top
	public List<Topic> findTopicsByContent(String scontent, int top) {
		List<Topic> ts = new ArrayList<Topic>();
		//获取连接
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "select * from t_topic where content like '"+scontent+"%' " +
					" order by orderIndex desc,(agree+disagree) desc limit "+ top;
			//statement
			Statement stm = conn.createStatement();
			//返回的是查询后结果集
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()){
				int tid = rs.getInt("tid");
				String content = rs.getString("content");
				String author = rs.getString("author");
				int orderIndex = rs.getInt("orderIndex");
				int agree = rs.getInt("agree");
				int disagree = rs.getInt("disagree");
				Timestamp publishTime = rs.getTimestamp("publishTime");
				Timestamp updateTime = rs.getTimestamp("updateTime");
				//把数据库当前条 记录保存为java对象
				Topic t = new Topic(tid, content, author, agree, disagree,
						orderIndex,publishTime, updateTime);
				//将该对象添加到list集合中
				ts.add(t);
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
		return ts;
	}
	
	//修改orderIndex
	public int updateTopicOrderIndex(int tid,int num){
		int stus = 0;
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "update t_topic set orderIndex = "+num+ " where tid = "+tid;
			Statement stm = conn.createStatement();
			stus = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stus;
	}
	public int updateTopicOrderIndexs(String tid, int num) {
		int stus = 0;
		DBUtil dbu = new DBUtil();
		Connection conn = null;
		try {
			conn = dbu.getConnection();
			String sql = "update t_topic set orderIndex = "+num+ " where tid in("+tid+")";
			System.out.println(sql);
			Statement stm = conn.createStatement();
			stus = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stus;
	}
	
}
