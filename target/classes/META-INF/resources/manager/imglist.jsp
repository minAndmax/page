<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图片列表</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/sweetalert/sweetalert.min.js"></script>
<link href="css/sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<style>
.tcdPageCode a{display: inline-block;color:inherit;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
.tcdPageCode a:hover{text-decoration: none;border: 1px solid #ccc;}
.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: black;background-color:#f4f4f4;border:	1px solid #DDDDDD;border-radius: 4px;vertical-align: middle;}
.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color:#ccc;background: #f2f2f2;border:	1px solid #DDDDDD;border-radius: 4px;vertical-align: middle;}
</style>
</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">模块设计</a></li>
    <li><a href="#">图片</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    
    <ul class="imglist">
    
<!--     <li class="selected"> -->
<!--     <span><img src="images/img01.png" /></span> -->
<!--     <h2><a href="#">软件界面设计下载</a></h2> -->
<!--     <p><a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></p> -->
<!--     </li> -->
    
    
<!--     <li> -->
<!--     <span><img src="images/img10.png" /></span> -->
<!--     <h2><a href="#">软件界面设计下载</a></h2> -->
<!--     <p><a href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">删除</a></p> -->
    </li>
    
    </ul>
    
    
   
    <div class="pagin">
   	 <div class="message">共<i class="blue" id="counts"></i>条记录，当前显示第&nbsp;<i class="blue" id="page"></i>页</div>
        <ul class="paginList">
         <li><div class="tcdPageCode" style="text-align: center;"></div></li>
        </ul>
    </div>
    
    </div>
</body>
<script type="text/javascript" src="js/page/jquery-2.1.1.js"></script>
<script type="text/javascript" src="js/page/footable.all.min.js"></script>
<script type="text/javascript" src="js/page/jquery.page.js"></script>
	<script type="text/javascript">
	
	
	    var totalPage;       //总页数
	    var pageSize = 21;    //每页显示的数量
	    var current;       //dangqia
		var curPage;
    	function load(current){
   		
   		$.post("/data/manager/findAllImgs",
   				{
   					page : (current-1)*pageSize,
   					size : pageSize
   				},
   				callback);
   	    function callback(data){
   	    	    $("#page").html(current);
   	    	 	curPage=current;
   	    	    $(".tcdPageCode").createPage({  
       	    	    pageCount : data[0].totalPages,
       	    	    current : current,
   	    	    backFn:function(p){
					 }
   	    	    });
   	    	    var tabhtml = ""
					if(data[0].totalPages >= 0){
						$("#counts").html(data[0].count);
    					var len = data.length;
    					for(var i = 0 ; i <  len; i++){
    						if(i == 0){
    							tabhtml += "<li class='selected'>";
        						tabhtml += "<span><img src='"+data[i].imgsNewSrc+"' width=168 height=126 /></span>";
        						tabhtml += " <p><a onclick='loadImg("+data[i].imgsId+")'>重新下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick='deleteImg("+data[i].imgsId+")'>删除</a></p>";
        						tabhtml += "</li>";
    						}else{
    							tabhtml += "<li class='selected'>";
        						tabhtml += "<span><img src='"+data[i].imgsNewSrc+"' width=168 height=126  /></span>";
        						tabhtml += " <p><a onclick='loadImg("+data[i].imgsId+")'>重新下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick='deleteImg("+data[i].imgsId+")'>删除</a></p>";
        						tabhtml += "</li>";
    						}
    					
					}
    				$(".imglist").html(tabhtml);
        	   }
   			}
	    }
	    function pageHtml(current){
		  load(current);
        }
	
		$(document).ready(function(){
			load(1);
		})
		
		function loadImg(id){
			
			$.post("/data/manager/loadImg",{imgsId : id},function(data){
				
				if(data.success == 1){
					swal({
						confirmButtonColor: "#FF0000",
						title: '加载成功',
						confirmButtonText: "确认",
						type: "success"
					},function(inputValue){
						load(curPage);
		            }, 1000);
				}
			})
			
		}
		
		function deleteImg(id){
			
			$.post("/data/manager/deleteImg",{imgsId : id},function(data){
				
				if(data.success == 1){
					swal({
						confirmButtonColor: "#FF0000",
						title: '删除成功',
						confirmButtonText: "确认",
						type: "success"
					},function(inputValue){
						load(curPage);
		            }, 1000);
				}
			})
		}
	
	</script>
</html>
