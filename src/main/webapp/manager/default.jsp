<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jsapi.js"></script>
<script type="text/javascript" src="js/format+zh_CN,default,corechart.I.js"></script>		
<script type="text/javascript" src="js/jquery.gvChart-1.0.1.min.js"></script>
<script type="text/javascript" src="js/jquery.ba-resize.min.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">工作台</a></li>
    </ul>
    </div>
    
    
    <div class="mainbox">
    
    <div class="mainleft">
    
    
    <div class="leftinfo">
    <div class="listtitle">获取新闻记录</div>
        <ul class="tooli" id="systemrecord" style="overflow-y:scroll;height: 130px;">
    	</ul>
    <div class="maintj">  
    </div>
    	
    </div>
    <!--leftinfo end-->
    
    
    <div class="leftinfos">
    
   
    <div class="infoleft">
    
    <div class="listtitle">更新事项</div>    
    <ul class="newlist" id="newlist" style="overflow-y:scroll;height: 170px;">
    </ul>   
    
    </div>
    
    
    <div class="inforight">
    <div class="listtitle">添加事项</div>
    
    <ul class="tooli" id="insertopt" style="overflow-y:scroll;height: 130px;">
    </ul>
    
    </div>
    
    
    </div>
    
    
    </div>
    <!--mainleft end-->
    
    
    <div class="mainright">
    
    
    <div class="dflist">
    <div class="listtitle">历史访问量</div>    
    <ul class="newlist"><strong>历史总访问量</strong>
    	<ul id="recordCount"></ul>
    </ul>
    <ul class="newlist"><font><strong>昨日访问量</strong></font>
    	<ul id="todays"></ul>
    </ul>
    </div>
    
    
    <div class="dflist1">
    <div class="listtitle">数据统计(有效)</div>    
    <ul class="newlist">
    <li><i>头条新闻：</a></i><span id="nwc"></span></li>
    <li><i>系统新闻：</a></i><span id="repNum"></span></li>
    <li><i>音乐：</a></i><span id="mct"></span></li>
    <li><i>视频：</a></i><span id="vdc"></span></li>
    <li><i>通知：</a></i><span id="ntc"></span></li>    
    </ul>        
    </div>
    </div>
    </div>
</body>
<script type="text/javascript">
	setWidth();
	$(window).resize(function(){
		setWidth();	
	});
	function setWidth(){
		var width = ($('.leftinfos').width()-12)/2;
		$('.infoleft,.inforight').width(width);
	}
</script>
<script type="text/javascript">
	
	
	$(document).ready(function(){
		$.post("/data/manager/getUser",{},function(data){
			  if(data.userName != null){
	    		  $("#showuser").html(data.roleName+":"+data.userName);
			  } else{
				  window.top.location = "/manager/login.html";
			  }
		  });
			loadUpdate();
			loadInsert();
			loadCount();
			loadSystem();
	})
	
	function loadUpdate(){
		var opt = "update";
		$.post("/data/manager/findOpt",{opt : opt},function(data){
			var html = "";
			for(var i = 0 ; i < data.length ; i++){
				html += "<li>"+data[i].optName+":"+data[i].optRemark+"<b>修改时间:"+data[i].createTime+"</b></li>";
			}
			$("#newlist").html(html);
		})
	}
	
	function loadSystem(){
		var opt = "system";
		$.post("/data/manager/findOpt",{opt : opt},function(data){
			var html = "";
			for(var i = 0 ; i < data.length ; i++){
				html += "<li>"+data[i].optName+":"+data[i].optRemark+"<b>当前时间:"+data[i].createTime+"</b></li>";
			}
			$("#systemrecord").html(html);
		})
	}
	
	function loadInsert(){
		var opt = "insert";
		$.post("/data/manager/findOpt",{opt : opt},function(data){
			var html = "";
			for(var i = 0 ; i < data.length ; i++){
				html += "<li>"+data[i].optName+":"+data[i].optRemark+"&nbsp;&nbsp;<b>创建时间:"+data[i].createTime+"</b></li>";
			}
			$("#insertopt").html(html);
		})
	}
	window.setInterval(function(){
		 $.post("/data/getCount",{},function(data){
		    	if(data.record != null){
		    		var arr = data.record.split("\r\n");
		    		var todays = data.today.split("\r\n");
		    		
		    		var t = "";
		    		for(var i = 0 ; i < todays.length - 1; i++){
		    			t += "<li>";
		    			t += todays[i].split("：")[0] + "&nbsp;&nbsp;:&nbsp;&nbsp;" + "<strong>" + todays[i].split("：")[1] + "</strong><br/>";
		    			t += "</li>";
		    		}
		    		$("#todays").html(t);
		    		
		    		var h = "";
		    		for(var i = 0 ; i < arr.length - 1; i++){
		    			h += "<li>";
		    			h += arr[i].split("：")[0] + "&nbsp;&nbsp;:&nbsp;&nbsp;" + "<strong>" + arr[i].split("：")[1] + "</strong><br/>";
		    			h += "</li>";
		    		}
		    		$("#recordCount").html(h);
		    	}
		    	
		    });//访问记录
	},1000);
	
	function loadCount(){
		
		$.post("/data/manager/findAllCount",{},function(data){
			if(data != null){
				$("#nwc").html(data[0].newNum);
				$("#mct").html(data[0].musicNum);
				$("#vdc").html(data[0].vedioNum);
				$("#ntc").html(data[0].noticeNum);
				$("#repNum").html(data[0].repNum);
			}
		});
		
	}
</script>
</html>
