package test;

import java.util.List;

import dao.TopicDAO;
import entity.Topic;

public class Test {
	public static void main(String[] args) {
		//从请求中获取参数值   String content = request.getParameter("content");
		String content = "两会热点";  
		String author = "仙居新闻网";
		
		Topic t = new Topic();
		t.setContent(content);
		t.setAuthor(author);
		
		//保存操作
		//saveExample(t);
		//修改操作
		//updateExample(t);
		//删除
		//deleteExample(1);
		
		//修改话题的 点赞/吐槽数量: update t_topic set agree = (agree+1), updateTime = now() where tid = 1;
		int tid = 3;
		int num = 1;//num = 1/-1;
		int type = 0;// type- 0:点赞,1:吐槽
		//updateExample(tid,type,num);
		
		//查询所有的topic
		selectExample();
		
		System.out.println("**********************************************");
		//两个模糊查询
		//select * from t_topic where 1=1 and content like '%str1%' 
	 	//		and author like '%str2%';
		content = "热点";
		author = "";
		selectExample(content,author);
		
		//根据点赞/吐槽数量排序: select * from t_topic order by agree/disagree (asc)/desc;
		int orderType = 1; //0:点赞/1:吐槽
		int orderWay = 0; // 0:asc/1:desc
		//selectExample(orderType,orderWay);
		
		//System.out.println(Integer.MAX_VALUE+1);
		//System.out.println(Integer.MIN_VALUE);
		
	}
	//根据点赞/吐槽数量排序: select * from t_topic order by agree/disagree (asc)/desc;
	public static void selectExample(int orderType, int orderWay) {
		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findTopics(orderType,orderWay);
		for(Topic t : ts){
			System.out.println(t);
		}
		
	}
	//根据话题和作者进行模糊查询
	public static void selectExample(String content, String author) {
		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findTopics(content,author);
		for(Topic t : ts){
			System.out.println(t);
		}
		
	}

	//修改话题的 点赞/吐槽数量: update t_topic set agree = (agree+1), updateTime = now() where tid = 1;
	public static void updateExample(int tid, int type, int num) {
		TopicDAO tdao = new TopicDAO();
		int stus = tdao.updateTopic(tid,type,num);
		if(stus == 1){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

	//进行保存操作演示
	public static void saveExample(Topic t){
		TopicDAO tdao = new TopicDAO();
		int stus = 0;
		//添加操作
		stus = tdao.saveTopic(t);
		if(stus == 1){
			System.out.println("成功");
		}else{
			System.out.println("失败");
		}
	}

	//修改操作
	public static void updateExample(Topic t){
		TopicDAO tdao = new TopicDAO();
		int tid = 1;
		String content = "两会热点";
		int orderIndex = 5;
		t.setTid(tid);
		t.setContent(content);
		t.setOrderIndex(orderIndex);
		int stus = tdao.updateTopic(t);
		
		if(stus == 1){
			System.out.println("成功！");
		}else{
			System.out.println("失败！");
		}
	}
	//查询
	public static void selectExample(){
		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findAllTopic();
		for(Topic topic : ts){
			System.out.println(topic);
		}
	}
	//删除操作
	//理解为从页面接收一个tid  -- request.getParameter("tid");
	public static void deleteExample(int tid){
		TopicDAO tdao = new TopicDAO();
		int stus = tdao.deleteTopic(tid);
		if(stus == 1){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}
	}
}
