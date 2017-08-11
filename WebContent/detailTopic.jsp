<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>detailTopic</title>
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript">
	var uname = "${uname}";
	var tid = "${topic.tid}";
	$(function(){
		$.post("do",
			{m:"queryMsgs",tid:tid,rm:new Date().getTime()},
			function(data){
				 $("#tby").html("");
				 for(var i=0;i<data.length;i++){
					 var obj = data[i];
					 var rauthor = obj.rauthor;
					 var author = rauthor == null ? 
							 obj.author : obj.author+"@"+rauthor;
					 var mhtml = "<tr><td>"+(i+1)+"</td><td>"+author+
					 "</td><td>"+obj.content+"</td><td>"+obj.rtime+"</td>"+
					 "<td><a href='javascript:ranswer("+obj.mid+",\""+obj.author+"\");'>回复</a></td></tr>";
					 $("#tby").append(mhtml);
				 }
			},'json');
	});
	
	function ranswer(mid,author){
		$("#rauthor").html(" @ "+author);
		$("#rid").val(mid);
	}
	
	function publish(){
		var rid = $("#rid").val().trim();
		var author = uname;
		var content = $("#ctt").val().trim();
		
		$.post("do",
			{m:"saveMsg",tid:tid,rid:rid,author:author,content:content,rm:new Date().getTime()},
			function(data){
				if(data==0){
					$("#cttMsg").html("回复失败").css("color","red");
				}else{
					location.href="do?m=detailTopic&tid="+tid;
				}
			});
		
	}
</script>
</head>
<body style="background:url(images/topicbk.jpg) no-repeat; width:1024px;height:640px;">
	<hr/>
	<table style="text-align:center;">
		<tr>
			 
			<th width="180px">话题</th>
			<th width="150px">作者</th>
			<th width="200px">发布时间</th>
			<th width="200px">更新时间</th>
			<th>赞</th>
			<th>踩</th>
		</tr>
		<tr>
			 <td>${topic.content}</td><td>${topic.author}</td>
			<td>${topic.publishTime}</td><td>${topic.updateTime}</td>
			<td>${topic.agree}</td><td>${topic.disagree}</td>
		</tr>
	</table>
	<hr/>
	<table style="text-align:center;">
		<thead><tr>
			<th>楼层</th><th width="200px">回复者</th><th width="250px">回复内容</th>
			<th width="200px">回复时间</th> 
		</tr></thead>
		<tbody id="tby">
			<tr><td>--</td><td>--</td><td>--</td><td>--</td><th>--</th></tr>
		</tbody>
	</table>
	<hr/>
	<h1>回复<span id="rauthor"></span></h1>
	<input type="hidden" name="rid" value="0" id="rid"/>
	内容:<input id="ctt"/> <input type="button" value=" 回复 " onclick="publish();"/>
	<span id="cttMsg"></span>
</body>
</html>