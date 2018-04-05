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
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>

</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>通讯录</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="images/leftico01.png" /></span>管理信息
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="index.jsp" target="rightFrame">首页模版</a><i></i></li>
        
        <li><cite></cite><a href="news/newstab.html" target="rightFrame">新闻管理</a><i></i></li>
        <li><cite></cite><a href="news/reptilenews.html" target="rightFrame">系统新闻</a><i></i></li>
    	<li><cite></cite><a href="vedio/vediotab.html" target="rightFrame">电影管理</a><i></i></li>
    	 <li><cite></cite><a href="vedio/tvtab.html" target="rightFrame">电视管理</a><i></i></li>
    	 <li><cite></cite><a href="music/musictab.html" target="rightFrame">音乐管理</a><i></i></li>
    	 <li><cite></cite><a href="userManager/userManager.html" target="rightFrame">用户管理</a><i></i></li>
    	 
        <li><cite></cite><a href="imgtable.html" target="rightFrame">图片数据表</a><i></i></li>
        <li><cite></cite><a href="imglist.jsp" target="rightFrame">图片列表</a><i></i></li>
        <li><cite></cite><a href="filelist.html" target="rightFrame">历史访问量</a><i></i></li>
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>其他设置
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd><div class="title"><span><img src="images/leftico03.png" /></span>编辑器</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>    
    </dd>  
    
    
    <dd><div class="title"><span><img src="images/leftico04.png" /></span>日期管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>
    
    </dd>   
    
    </dl>
</body>
</html>
