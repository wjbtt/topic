

<table style="text-align:center;">
		<thead>
			<tr>
				
				<th width="180px">话题</th>
				<th width="150px">作者</th>
				<th width="200px">发布时间</th>
				<th width="200px">更新时间</th>
				<th width="110px" >详细</th>
				<th id="ag" onclick="orderAandD(0);">赞↓</th>
				<th id="da" onclick="orderAandD(1);">踩↓</th>
			</tr>
		</thead>
		<tbody id="tbd">
			<tr><td>--</td><td>--</td><td>--</td><td>--</td>
			<td>--</td><td>--</td><td>--</td><td>--</td></tr>
		</tbody>
		 
	</table>



update t_topic set orderIndex = 1 where tid in(49,32)









留言板
1、游客登陆--不考虑用户
2、热门话题--留言
话题 -- topic
	tid(int),
	content(String),
	author(String),
	agree(int),
	disagree(int),
	orderIndex(int),
	publishTime(datetime),
	updateTime(datatime)
	
留言 -- message
	mid(int),
	content(String),
	author(String),
	tid(int),
	rid(int),
	rtime(datetime)
	
create database db_news;
use db_news;
create table t_topic(
	tid int primary key auto_increment,
	content varchar(200),
	author varchar(50),
	agree int default 0,
	disagree int default 0,
	orderIndex int default 0,
	publishTime datetime ,
	updateTime datetime
);
create table t_message(
	mid int primary key auto_increment,
	content varchar(500),
	author varchar(50),
	tid int,
	rid int,
	rtime datetime 
);

xxxx
1、萨德xxxx (30) 
 xxxxxx
 wwwww
 ccccccccc
 eeeeeee
 
 
 
2、两会
 qqqqqqqqqqq
 wwwwwww
 eeeeeeeeeeee
 tttttt
 yyyyyyyyyy
 iiiii

 
 
 //任务
 1、分组-- 52/6  9
 2、完善topic相关操作DAO
 3、完成message操作DAO
 
 拓展：view(html/jsp)--servlet--database--servlet--view(html/jsp)
 
 mysql:修改表结构
   增加一列:xxtime datetime 
 alter table t_topic add xxtime datetime ;
   修改列xxtime名为updateTime
 alter table t_topic change xxtime updateTime datetime;
   修改列uname的类型 varchar(50)
 alter table t_topic modify uname varchar(50);
 
 
 完善留言板：
 1、完善话题版块功能
 	已经完成:增加一个话题,修改话题--内容/排序,删除指定话题,查询所有话题
 	今天需要完成:修改话题的 点赞/吐槽数量 ,查询话题(根据话题/作者模糊查询,根据点赞/吐槽数量排序),默认排序方式按照时间降序
 	//修改话题的 点赞/吐槽数量: update t_topic set agree = (agree+1), updateTime = now() where tid = 1;
 	//根据话题模糊查询: select * from t_topic where 1=1 and content like '%str%'; 
 	//根据作者模糊查询: select * from t_topic where 1=1 and author like '%str%';
 	// select * from t_topic where 1=1 and content like '%str1%' 
 			and author like '%str2%';
 	//根据点赞/吐槽数量排序: select * from t_topic order by agree/disagree (asc)/desc;	
 	//默认排序方式按照时间降序 select * from t_topic order by updateTime desc;
 			
  2、留言版块功能
 	新增(回复话题,回复留言)
 	// select  m.*,rm.author from t_message m,t_message rm where m.rid = rm.mid;
 	
 	//select rm.*,m.author from t_message m
 	right join (select * from t_message where tid = 1) rm
 	on rm.rid = m.mid;
 	
 	回复留言--读取：xxx@xxx:xxxx
 	
  3、完善页面功能
  	模拟登录
  	显示所有话题:显示,查询(模糊查询),排序,点赞/吐槽
  	展开话题后显示当前话题所有留言
  	
  	
  	
 
 
 
csdn
 防止sql注入
select * from user where uname = 'zs' and pwd = '123' or 1 = '1';
pwd = '    123' or 1 = '1    '  ";

pwd = '   " + 123 + "   '  ";
pwd = '"+pwd+"'";

"select * from user where uname = '"+uname+"' and pwd = '"+pwd+"'";
 
 "select * from user where uname = ? and pwd = ?";
 
 //员工登录--->商品/购物车--->评论商品
 
 
 //网景
 w3c
 //ECMAscript
 
 //DOM  document object model
 //BOM  browser object model
 
 //编码问题
一：流程
页面-->后台-->数据库-->后台-->页面

二：解决方案
1、页面
mate--content-type:text/html;charset=utf-8
(html5--> <charset="utf-8/>")
------------------
jsp: 
pageEncoding="utf-8"
contentType="text/html;charset=utf-8"

2、页面--发送请求-->服务器
get请求:
重写url地址
tomcat-config->connector(URIEncoding="utf-8")

3、后台正确收到中文
在获得请求参数之前设置请求的编码
request.setCharacterEncoding("utf-8");

4、确保mysql数据库编码正确
a、安装mysql数据库时选择 utf8
b、jdbc连接数据库时使用utf-8
	jdbc:mysql://localhost:3306/db_news?
	useUnicode=true&characterEncoding=utf-8

5、后台向浏览器发送中文
在获得PrintWriter之前设置编码
response.setContentType("text/html;charset=utf-8");

	