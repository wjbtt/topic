<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册页面</title>
<style type="text/css">
#bkbk{ 
   width:300px;
height:200px; position:absolute; right:15%; top:50%; margin-left:-150px; margin-top:-230px;
}

</style>
<script type="text/javascript" src="js/jquery-1.7.js"></script>
<script type="text/javascript">

function check(){
	var name=$("#uname").val().trim();
	
	$.post("do",
			{m:"check",name:name,rm:new Date().getTime()},
			function(data){
				if (data!=""){
				$("#cwxx").html(data);
				}
			});
}

function ckPWD(){
	var p1 = $("#psw").val().trim();
	var p2 = $("#rpsw").val().trim();
	if(p1!=p2){
		$("#cwxx").html("密码不一致");
	}else{
		$("#cwxx").html("~~~~~~~~~~");
		
	}
}



function zc(){
	var p = $("#psw").val().trim();
	var rname=$("#uname").val().trim();
 $.post("do",
		{m:"zc",uname:rname,psw:p,rm:new Date().getTime()},
		function(data){
			if(data==1){
				$("#cwxx").html("注册成功");
			}else{
				$("#cwxx").html("注册失败");
				
			}
		             }
		
		);


}

</script>
</head>
<body style="background:url(images/timg.jpg) no-repeat; width:1024px;height:640px;">
   <div id="bkbk" style="text-align:center">
  <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  
    <form action="do">
  
     注册新帐号
      <br/><br/>
     <span id="cwxx">~~~~~~~~~~</span>
     <br/><br/>
     <span >输入帐号：</span>
      <input id="uname" name="uname" value="" onblur="check();"/><br/>
      <span >输入密码：</span>
    <input id="psw"  type="password"  name="psw" value=""  onblur="ckPWD()"/><br/>
    <span >确认密码：</span>
    <input id="rpsw" type="password" name="rpsw" value=""  onblur="ckPWD()"/><br/>
   
    
   
    </form>
     <button type="button" id="btn"  onclick="zc();">  注册一下</button>
     <br/>
     <br/>
     <a  id="btn1" href="login.html" >返回登录</a>
     </div>
</body>

</html>