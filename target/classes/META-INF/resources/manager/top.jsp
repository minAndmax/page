<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.html" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="default.jsp" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
<!--     <li><a href="imgtable.html" target="rightFrame"><img src="images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li> -->
<!--     <li><a href="imglist.html"  target="rightFrame"><img src="images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li> -->
<!--     <li><a href="tools.html"  target="rightFrame"><img src="images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li> -->
<!--     <li><a href="computer.html" target="rightFrame"><img src="images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li> -->
<!--     <li><a href="tab.html"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li> -->
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="javascript:;" target="_parent" onclick="outLogin()">退出</a></li>
    </ul>
     
    <div class="user">
    <span id="showuser"></span>
    </div>    
    
    </div>
</body>
<script>
$(document).ready(function(){
	  $.post("/data/manager/getUser",{},function(data){
		  if(data.userName != null){
  		  $("#showuser").html(data.roleName + ":" + data.userName);
//   		  $("#prelogin").html(data.);
		  } else{
			  window.top.location = "/manager/login.jsp";
		  }
	  })
	  
})
      function outLogin(){
    	  $.post("/data/manager/outLogin",{},function(data){
    		  if(data.userName != null){
	    		  $("#showuser").html(data.roleName+":"+data.userName);
    		  } else{
    			  window.top.location = "/manager/login.jsp";
    		  }
    	  })
      }
	</script>
      	
</html>
