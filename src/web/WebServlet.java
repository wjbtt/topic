package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import test.User;
import test.UserDAO;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.MessageDAO;
import dao.TopicDAO;
import entity.Message;
import entity.Topic;

public class WebServlet extends HttpServlet {
	// uname值为中文,所以需要在接收参数之前设置 请求 的编码
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String method = request.getParameter("m");
		// http://localhost/topic/do?m=login&uname=zhangsan
		// 点击list页面的查询链接会跳转到 http://localhost/topic/do?m=detailTopic&tid=4
		if ("login".equals(method)) {
			login(request, response);
		} else if ("queryTopics".equals(method)) {
			queryTopics(request, response);
		} else if ("updateAD".equals(method)) {
			updateAD(request, response);
		} else if ("orderAandD".equals(method)) {
			orderAandD(request, response);
		} else if ("queryCandA".equals(method)) {
			queryCandA(request, response);
		} else if ("publishTopic".equals(method)) {
			publishTopic(request, response);
		} else if ("detailTopic".equals(method)) {
			detailTopic(request, response);
		} else if ("queryMsgs".equals(method)) {
					queryMsgs(request, response);
		} else if ("saveMsg".equals(method)) {
			saveMsg(request, response);
		}else if ("check".equals(method)) {
			check(request, response);
		}else if ("zc".equals(method)) {
			zc(request, response);
		}else if("deletet".equals(method)){
			deletet(request, response);
		}

		// 测试spLisk--queryUl
		if ("queryUl".equals(method)) {
			queryUl(request, response);
		}else if("buyTopic".equals(method)){
			buyTopic(request,response);
		}else if("upTopicOrdexS".equals(method)){
			upTopicOrdexS(request,response);
		}
	}

	private void deletet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		 String stid=request.getParameter("tid");
		int tid=Integer.parseInt(stid);
		TopicDAO tdao=new TopicDAO();
		 int stus=tdao.deleteTopic(tid);
		  response.setContentType("text/html;charset=utf-8");
		  PrintWriter out=response.getWriter();
		if(stus==1){
			 out.print(1);
		}else{
			 out.print(0);
		}
		out.close();
		
	}

	public void zc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name   =request.getParameter("uname");
		String psw=request.getParameter("psw");
		  UserDAO udao=new UserDAO();
		  int stus=udao.zhuce(name,psw);
		  response.setContentType("text/html;charset=utf-8");
		  PrintWriter out=response.getWriter();
		if(stus==1){
			 out.print(1);
		}else{
			 out.print(0);
		}
		out.close();
	}

	public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name=request.getParameter("name");
		response.setContentType("text/html;charset=utf-8");
		UserDAO user=new UserDAO();
		User userr =user.findUserByUname(name);
		 if(name==""){
			 PrintWriter out = response.getWriter();
				out.print("用户名不能为空");
				out.close();
		 }else{

			 if(userr==null){
				 PrintWriter out = response.getWriter();
					out.print("可注册");
					out.close();
			 }else{
				 PrintWriter out = response.getWriter();
					out.print("已被注册");
					out.close();
			 }
		 }
	}

	public void upTopicOrdexS(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String tid = request.getParameter("tid");
		String snum = request.getParameter("num");
		
		TopicDAO tdao = new TopicDAO();
		int stus = tdao.updateTopicOrderIndexs(tid,Integer.parseInt(snum));
		
		PrintWriter out = response.getWriter();
		out.print(stus);
		out.close();
	}

	public void buyTopic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String stid = request.getParameter("tid");
		String snum = request.getParameter("num");
		
		TopicDAO tdao = new TopicDAO();
		int stus = tdao.updateTopicOrderIndex(Integer.parseInt(stid),
				Integer.parseInt(snum));
		
		PrintWriter out = response.getWriter();
		out.print(stus);
		out.close();
	}

	// //测试spLisk--queryUl 查询topxxx
	public void queryUl(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/html;charset=utf-8");

		String content = request.getParameter("content");
		String stop = request.getParameter("top");
		int top = Integer.parseInt(stop);

		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findTopicsByContent(content, top);

		ObjectMapper objm = new ObjectMapper();
		objm.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String jms = objm.writeValueAsString(ts);

		PrintWriter out = response.getWriter();
		out.print(jms);
		out.close();

	}

	public void saveMsg(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String stid = request.getParameter("tid");
		String srid = request.getParameter("rid");
		String author = request.getParameter("author");
		String content = request.getParameter("content");

		Message msg = new Message();
		msg.setTid(Integer.parseInt(stid));
		msg.setRid(Integer.parseInt(srid));
		msg.setAuthor(author);
		msg.setContent(content);

		MessageDAO mdao = new MessageDAO();
		int stus = mdao.saveMsg(msg);

		PrintWriter out = response.getWriter();
		out.print(stus);
		out.close();

	}

	public void queryMsgs(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=utf-8");

		String stid = request.getParameter("tid");
		int tid = Integer.parseInt(stid);

		MessageDAO mdao = new MessageDAO();
		List<Message> ms = mdao.findAllMsgs1(tid);

		ObjectMapper objm = new ObjectMapper();
		objm.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String jms = objm.writeValueAsString(ms);

		PrintWriter out = response.getWriter();
		out.print(jms);
		out.close();
	}

	// http://localhost:8888/topic/do?m=detailTopic&tid=6
	public void detailTopic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String stid = request.getParameter("tid");

		// 将字符串类型的数字转换为int类型的数字
		int tid = Integer.parseInt(stid);

		// 访问dao,查询对应tid 的topic数据
		TopicDAO tdao = new TopicDAO();
		Topic t = tdao.findTopicByTid(tid);

		HttpSession session = request.getSession();
		session.setAttribute("topic", t);

		response.setContentType("text/html;charset=utf-8");
		response.sendRedirect("detailTopic.jsp");

	}

	// 发布话题topic
	public void publishTopic(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// $.post("do",{m:"publishTopic",author:uname,ct:content,.......
		String author = request.getParameter("author");
		String content = request.getParameter("ct");

		// 将页面上传递的信息 转化为java对象
		Topic t = new Topic();
		t.setContent(content);
		t.setAuthor(author);

		// 通过访问dao处理数据--保存新话题
		TopicDAO tdao = new TopicDAO();
		int stus = tdao.saveTopic(t);

		// 获取响应输出流 -- 主要用来返回数据
		PrintWriter out = response.getWriter();
		out.print(stus);
		out.close();

	}

	public void queryCandA(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");

		String content = request.getParameter("content");
		String author = request.getParameter("author");

		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findTopics(content, author);

		ObjectMapper objm = new ObjectMapper();
		objm.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String jts = objm.writeValueAsString(ts);

		PrintWriter out = response.getWriter();
		out.print(jts);
		out.close();
	}

	public void orderAandD(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=utf-8");

		String stype = request.getParameter("type");
		String sway = request.getParameter("way");

		int orderType = Integer.parseInt(stype);
		int orderWay = Integer.parseInt(sway);

		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findTopics(orderType, orderWay);

		ObjectMapper objm = new ObjectMapper();
		objm.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String jts = objm.writeValueAsString(ts);

		PrintWriter out = response.getWriter();
		out.print(jts);
		out.close();

	}

	public void updateAD(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String stid = request.getParameter("tid");
		String stype = request.getParameter("type");
		String snum = request.getParameter("num");

		int tid = Integer.parseInt(stid);
		int type = Integer.parseInt(stype);
		int num = Integer.parseInt(snum);

		TopicDAO tdao = new TopicDAO();
		int stus = tdao.updateTopic(tid, type, num);

		PrintWriter out = response.getWriter();
		out.print(stus);
		out.close();

	}

	public void queryTopics(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 设置响应消息头--主要是用来告诉浏览器数据格式和数据编码类型
		response.setContentType("text/html;charset=utf-8");

		TopicDAO tdao = new TopicDAO();
		List<Topic> ts = tdao.findAllTopic();

		ObjectMapper objm = new ObjectMapper();
		objm.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		String jts = objm.writeValueAsString(ts);

		PrintWriter out = response.getWriter();
		out.print(jts);
		out.close();
	}

	// 处理登录请求的方法
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String uname = request.getParameter("uname");
		String psw = request.getParameter("psw");
		// 通过 请求 对象获取HttpSession对象
		// session可以认为是存在服务器端的一种缓存对象
		HttpSession session = request.getSession();
		// 通过session将uname值绑定到"uname"字符串上
		// (你可以把"uname"当作开门的钥匙,uname为屋里物品)
		session.setAttribute("uname", uname);
		session.setAttribute("psw", psw);
		System.out.println("uname:" + uname);
		System.out.println("psw:" + psw);
		// 因为uname可能为中文,确保在页面能显示中文所以需要设置 响应 消息头
		response.setContentType("text/html;charset=utf-8");
			User user = UserDAO.findUserByUnameAndPwd1(uname, psw);
			
			if(user == null){
				System.out.println("用户名或密码错误！");
				response.sendRedirect("404.html");
			}else{
				System.out.println("welcome,"+user.getUname());
				response.sendRedirect("list.jsp");
			}
		
		
	}

}
