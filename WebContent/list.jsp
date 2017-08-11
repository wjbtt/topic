<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>商品购买</title>
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<style type="text/css">
	#dz,#tc,#ag,#da{
		cursor:pointer;
	}
</style>
<script type="text/javascript">
	var uname = "${uname}";
	$(function(){
		//json格式字符串:  {"属性名":"属性值","属性名":"属性值"...}
		//$.post(url,data,callback,type);
		
		$.post("do",
			{m:"queryTopics",rm:new Date().getTime()},
			function(data){
				showData(data);
			},"json");
	});
	
	//将查询的数据显示在页面上
	function showData(data){
		$("#tbd").html("");
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			var detail = "<a href='do?m=detailTopic&tid="+obj.tid+"'>查看</a>";
			var astr = "<img id='dz' src='images/z.gif' onclick='dAndt("+obj.tid+",0)'/>";
			var dstr = "<img id='tc' src='images/c.gif' onclick='dAndt("+obj.tid+",1)'/>";
			var thtml = "<tr><td>"+obj.content
			+"</td><td>"+obj.author+"</td><td>"+obj.publishTime+"</td>"+
				"<td>"+obj.updateTime+"</td><td>"+detail+"</td><td>"+astr+obj.agree
				+"</td><td>"+dstr+obj.disagree+"</td><td>"+
				"<input type='button' value='移除' onclick='buySP("+obj.tid+")'/></td></tr>";
			$("#tbd").append(thtml);	
		}
	}
	
	
	function buySP(tid){
		$.post("do",
			{m:"buyTopic",tid:tid,num:1,rm:new Date().getTime()},
			function(data){
				if(data == 0){
					alert("已下架");
				}else{
					alert("成功移除进垃圾箱");
				}
			});
		
	}
	
	function dAndt(tid,type){
		$.post("do",
			{m:"updateAD",tid:tid,type:type,num:1,rm:new Date().getTime()},
			function(data){
				if(data==0){
					alert("failed!");
				}else{
					alert("success!");
					location.reload();
				}	
		});
	}
	function orderAandD(type){
		//通过id获取id值为ag的对象的标记中间夹的那部分内容  < xxx >  html()  < /xxx >
		var ag = $("#ag").html();
		var da = $("#da").html();
		var way = 0;  // 0表示升序,1表示降序
		if(type==0){
			if(ag == '赞↓'){
				way = 0;
				$("#ag").html("赞↑");
			}else{
				way = 1;
				$("#ag").html("赞↓");
			}
		}else{
			if(da == '踩↓'){
				way = 0;
				$("#da").html("踩↑");
			}else{
				way = 1;
				$("#da").html("踩↓");
			}
		}
		
		$.post("do",
			{m:"orderAandD",type:type,way:way,rm:new Date().getTime()},
			function(data){
				showData(data);
			},'json');
	}
	
	function queryCandA(){
		var content = $("#ht").val().trim();
		var author = $("#zz").val().trim();
		
		$.post("do",
			 {m:"queryCandA",content:content,author:author,rm:new Date().getTime()},
			 function(data){
				 showData(data);
			 },'json');
	}
	
	function publish(){
		var content = $("#ctt").val().trim();
		//alert(uname+":"+content);
		$.post("do",
			 {m:"publishTopic",author:uname,ct:content,rm:new Date().getTime()},
			 function(data){
				 if(data == 0){
					 //向id为cttMsg对象的标记中间添加内容("发布失败")
					 //并修改他的css样式中 color的值为red
					 $("#cttMsg").html("发布失败!").css("color","red");
				 }else{
					 alert("发布成功！");
					 //重新加载当前页面
					 location.reload();
				 }
			 });
	}
</script>
</head>
<body style="background:url(images/topicbk.jpg) no-repeat; width:1024px;height:640px;">

	 <a href="showdellist.jsp">垃圾箱</a>
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
				<th width="100px">移除</th>
			</tr>
		</thead>
		<tbody id="tbd">
			<tr><td>--</td><td>--</td><td>--</td><td>--</td>
			<td>--</td><td>--</td><td>--</td><td>--</td></tr>
		</tbody>
	</table>
	<br/>
	<br/>
	根据话题:<input id="ht" onkeyup="queryCandA();"/> 
	根据作者:<input id="zz" onkeyup="queryCandA();"/> 
	<br/>
	<h1>发布新话题</h1>
	<textarea id="ctt" rows="11" cols="111"></textarea>
	 <input type="button" value=" 发布 " onclick="publish();"/>
	<span id="cttMsg"></span>
</body>
</html>