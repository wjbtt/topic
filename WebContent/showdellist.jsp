<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主题</title>
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<style type="text/css">
	#dz,#tc,#ag,#da{
		cursor:pointer;
	}
	#ctt{
	width:300px ;
	height:250px;
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
		$("#tbdx").html("");
		for(var i=0;i<data.length;i++){
			var obj = data[i];
			
			var ck = "<input type='checkbox' name='gm_"+obj.tid+"'/>";
			var buyStus = "<input type='button' value='删除' onclick='buySP("+obj.tid+",-1)'/>";
			if(obj.orderIndex == -1){
				ck = "<input type='checkbox' name='sc_"+obj.tid+"'/>";
				buyStus = "<input type='button' value='永久删除' onclick='buySP("+obj.tid+",1)'/>";
			}
			var thtml = "<tr><td>"+ck+obj.tid+"</td><td>"+obj.content
			+"</td><td>"+obj.author+"</td><td>"+obj.publishTime+"</td>"+
				"<td>"+obj.updateTime+"</td><td>"+buyStus+"</td></tr>";
			
			if(obj.orderIndex == -1){
				$("#tbdx").append(thtml);	
			}else if(obj.orderIndex == 1){
				$("#tbd").append(thtml);	
			}
		}
	}
	
	function xxx(id,num){
		var arr = $("input[name^="+id+"]:checked");
		var ids = "";
		for(var i=0;i<arr.length;i++){
			var id = arr.get(i).name.split("_")[1];
			if(i==0){
				ids = id;
			}else{
				ids = ids + ","+id;
			}
		}
		//alert(ids);
		$.post("do",
			{m:"upTopicOrdexS",tid:ids,num:num,rm:new Date().getTime()},
			function(data){
				if(data != 0){
					location.reload();
				}
			});
	}
	
	
	function buySP(tid,num){
		$.post("do",
			{m:"deletet",tid:tid,num:num,rm:new Date().getTime()},
			function(data){
				if(data != 0){
					location.reload();
				}
			});
	}
	
</script>
</head>
<body style="background:url(images/topicbk.jpg) no-repeat; width:1024px;height:640px;">

	 <a href="list.jsp">继续浏览</a>
	 <hr/>
	 已选中的主题:<input type="button" value="删除已选中" onclick="xxx('gm',-1);"/><br/>
	<table style="text-align:center;">
		<thead>
			<tr><th width="180px">序列号</th>
				<th width="180px">话题</th>
				<th width="150px">作者</th>
				<th width="200px">发布时间</th>
				<th width="200px">更新时间</th>
				<th width="110px" >详细</th>
			</tr>
		</thead>
		<tbody id="tbd">
			<tr><td>--</td><td>--</td><td>--</td><td>--</td>
			<td>--</td><td>--</td></tr>
		</tbody>
	</table>
	<hr/>
	
</body>
</html>