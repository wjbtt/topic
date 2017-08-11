package test;

import java.util.List;

import dao.MessageDAO;
import entity.Message;

public class TestMsg {
	public static void main(String[] args) {

		String content = "能想还不想美点吗?";
		String author = "话痨";
		int tid = 1;// 话题topic的id
		int rid = 3;// 0表示评论的是话题
		
		
		//新增评论/留言
		Message msg = new Message(content,author,tid,rid);
		//saveMsg(msg);
		
		//查询某条话题下的所有评论
		tid = 1;
		findMsgs(tid);
	}
	
	//查询某话题下的所有评论/留言
	public static void findMsgs(int tid) {
		MessageDAO mdao = new MessageDAO();
		List<Message> msgs = mdao.findAllMsgs1(tid);
		for(Message msg : msgs){
			//System.out.println(msg);
			// xxx : xxxxxxxx (2017-01-01 01:01:01)
			String str = msg.getAuthor() + ":" + msg.getContent() +
					"("+msg.getRtime()+")";
			System.out.println(str);
		}
		System.out.println("*******************");
		for(Message msg : msgs){
			// xxx@xxx : xxxxxxxx (2017-01-01 01:01:01)
			String ra = msg.getRauthor() == null ? "" : "@"+ msg.getRauthor();
			String str = msg.getAuthor()+ra + ":" + msg.getContent() +
					"("+msg.getRtime()+")";
			System.out.println(str);
		}
	}

	// 新增留言
	public static void saveMsg(Message msg){
		MessageDAO mdao = new MessageDAO();
		int stus = mdao.saveMsg(msg);
		if (stus == 1) {
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}
}
