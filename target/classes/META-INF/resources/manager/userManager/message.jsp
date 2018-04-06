<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户反馈</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<!-- <link href="../css/select.css" rel="stylesheet" type="text/css" /> -->
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>

<link href="../js/page/footable.core.css" rel="stylesheet" type="text/css" />
<link href="../css/sweetalert/sweetalert.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/sweetalert/sweetalert.min.js"></script>
<script language="JavaScript" type="text/javascript" src="../js/date/WebCalendar.js"></script>

<style type="text/css">
  .tablelink{
  	cursor: pointer;
  }
  .noww{
  	display: none;
  }
</style>
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
    <li><a href="vediotab.html">用户反馈</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab2" class="selected">反馈列表</a></li> 
<!--     <li><a href="#tab1">添加电视</a></li>  -->
  	</ul>
    </div> 
    
    </div> 
    
 	<div id="tab2" class="tabson">
    
    
    <ul class="seachform">
    
    <li><label>状态:</label><select id="msgType" onchange="changem(this)" style="border: 1PX solid black;width: 120PX; height: 30PX;">
    		<option value="">选择读取状态</option>
    		<option value="Y">未读</option>
    		<option value="N">已读</option>
    </select></li>
<!--     <li><label>&nbsp;</label><input name="" type="button" class="scbtn" id="scbtn" value="查询"/></li> -->
<!--     <li><label>&nbsp;</label><input name="" type="button" class="scbtn"  id ="reset" value="重置"/></li> -->
    
    </ul>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>留言内容</th>
        <th>邮箱</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>查看</th>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
         <tfoot>
        	<tr>
        		<td colspan="8"><div class="tcdPageCode" style="text-align: center;"></div></td>
        	</tr>
        </tfoot>
    </table>
    
    </div>  
       
	</div> 
 
	<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    </div>
<input type="hidden" id="hiddenname"/>
</body>
<script type="text/javascript" src="../js/page/jquery-2.1.1.js"></script>
<script type="text/javascript" src="../js/page/footable.all.min.js"></script>
<script type="text/javascript" src="../js/page/jquery.page.js"></script>
 <script type="text/javascript">
             	
	 var totalPage;       //总页数
	 var pageSize = 20;    //每页显示的数量
	 var current;       //dangqia
	
 	function load(current){
		
		var msgType = $("#msgType").val();
		 
		$.post("/data/manager/findAllMsg", 
				{	msgType : msgType,
					page : (current-1)*pageSize,
					size : pageSize
				},
				callback);
	    function callback(data){
	    	    $(".tcdPageCode").createPage({  
    	    	    pageCount : data[0].totalPages,
    	    	    current : current,
	    	    backFn:function(p){
				 }
	    	    });
	    	    var tabhtml = ""
				if(data[0].totalPages >= 0){
					
 					var len = data.length;
 					for(var i = 0 ; i <  len; i++){
 					
						tabhtml += "<tr onclick=show(this) id='p"+i+"' style='cursor: pointer;'>";
						tabhtml += "<td>"+data[i].msgContent.substring(0,50)+"......</td>";
 						tabhtml += "<td>"+data[i].email+"</td>";
						if(data[i].msgType == 'Y'){
							tabhtml += "<td style='background-color:#FF0000'><font color='#FFFFFF'>未读</font></td>";
						}else{
	 						tabhtml += "<td>已读</td>";
						}
 						tabhtml += "<td>"+data[i].createTime+"</td>";
 						tabhtml += "<td><a onclick=reader("+data[i].msgId+") class='tablelink'>确认</a></label></td>";
						tabhtml += "</tr>";
						tabhtml += "<tr class='c_p"+i+"'  style='display: none;background-color: #ffe7ba;'>";
						tabhtml += "<td colspan='5'>"+data[i].msgContent+"</td>";
						tabhtml += "</tr>";
	 					}
					}
 					
 				$("#tbody").html(tabhtml);
				}
	}
 	function changem(obj){
 		load(1);
 	}
	
	  function pageHtml(current){
		  load(current);
     }
 	
	  function reader(obj){
		  $.post("/data/manager/reader",{msgId : obj,msgType : "Y"},function(data){
			  if(data.tipStatus == 1){
					swal({
						confirmButtonColor: "#FF0000",
						title: "确认成功",
						confirmButtonText: "确认",
						type: "success"
					},function(inputValue){
						load(1);
    	            }, 2000);
			  }
		  })
		  
	  }
 
	function change(obj){
		
		var val = $(obj).val();
		
		if(val == 'NULL'){
			$("#inputdirectory").removeClass("noww");
		} else{
			$("#inputName").val($(obj).find("option:selected").text())
			$("#inputdirectory").addClass("noww");
		}
		
	}
	 
	 function show(obj){
		 var id = $(obj).attr("id");
         $(obj).toggleClass('selected');
         $(obj).siblings('.c_'+id).toggle();
     }
 			
 			
	     function deletesrc(src){
	    	 
	    	 swal({
					title: "删除?",
		            type: "warning",
		            showCancelButton: true,
		            confirmButtonColor: "#FF0000",
		            cancelButtonText: "取消",
		            confirmButtonText: "确定 ",
		            closeOnConfirm: false,
					},function(isConfirm){
						if(isConfirm){
			     			
							swal({
								confirmButtonColor: "#FF0000",
			                    title: "请等待……",
			                    type: "warning",
			                    showConfirmButton: false,
			                    showCancelButton: true
			                });
			     			
					$.post("/data/manager/findLog",{type : "delete",src : src},function(date){
     					swal({
    						confirmButtonColor: "#FF0000",
    						title: "删除成功",
    						confirmButtonText: "确认",
    						type: "success"
    					},function(inputValue){
    						
// 			     			KindEditor.html("#content7","");
    						load();
        	            }, 2000);
     				
     					})
					}
       			 });
			}
 			  
     	$(document).ready(function(){
     		load(1);
		})
     </script>
     <script type="text/javascript">
	$(document).ready(function(){
		$.post("/data/manager/getUser",{},function(data){
  		  if(data.userName != null){
// 	    		  $("#showuser").html(data.roleName+":"+data.userName);
// 	    		  $("#hiddenname").html(data.userName);
  		  } else{
  			  window.top.location = "/manager/login.jsp";
  		  }
  	  })
	})
</script>
</html>
