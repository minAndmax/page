<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    </ul>    
    </div>
    <div class="loginbody">
    <span class="systemlogo"></span> 
    <div class="loginbox">
    <ul>
    <li><input type="text" id="username" class="loginuser" onclick="JavaScript:this.value=''"/></li>
    <li><input type="password" id="password" class="loginpwd" onclick="JavaScript:this.value=''"/></li>
    <li><input type="button" class="loginbtn" value="登录" id="button"/><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
    </ul>
    </div>
    </div>
<!--     <div class="loginbm"></div> -->
</body>
<script>
 $("#button").click(function(){
	    	   var unm = $("#username").val();
	    	   var pwd = $("#password").val();
	    	   
	    	   $.post("/data/login",
	    			  {"userName" : unm,
	    		   	   "userPassword" : pwd},
	    			  function(data){
	    		   		if(data.tipStatus == 1){
	    		   			window.location.href = "/manager/main.html";
	    		   		} else{
	    		   			alert(data.loginTip);
	    		   		}
	    	   });
	       });
</script>
</html>
