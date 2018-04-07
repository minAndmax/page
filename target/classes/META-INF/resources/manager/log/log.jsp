<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
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
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="vediotab.html">日志管理</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    
    <div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    <li><a href="#tab2" class="selected">日志列表</a></li>

<!--     <li><a href="#tab1">添加电视</a></li>  -->
  	</ul>
    </div> 
    
    </div> 
    
  	<div id="tab2" class="tabson">
    <ul class="seachform">
    	 <li><label>通知时间:</label><input name="date" type="text" id="date" onclick="new Calendar().show(this);" size="10" maxlength="10" class="scinput" /></li> 
	   	<li><label>&nbsp;</label><input name="" type="button" class="scbtn" id="scbtn" value="查询"/></li>
	    <li><label>&nbsp;</label><input name="" type="button" class="scbtn"  id ="reset" value="重置"/></li>
    </ul>
    <table class="tablelist">
    	<thead>
    	<tr>
	        <th>日志名称</th>
	        <th>大小</th>
	        <th>创建时间</th>
	        <th>操作</th>
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
 <script type="text/javascript">
             
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
 			
		     	function load(){
	        		
	        		var date = $("#date").val();
	        		if(date == ''){
	        			date = new Date().getTime();
	        		}
	        		$.post("/data/manager/findLog",
	        				{date : date},
	        				callback);
	        	    function callback(data){
	        	    	    var tabhtml = ""
	         					var len = data.length;
	         					for(var i = 0 ; i <  len; i++){
	         						var a = data[i].fileSize;
	         						tabhtml += "<tr onclick=show(this) id='p"+i+"' style='cursor: pointer;'>";
	         						tabhtml += "<td>"+data[i].fileName+"</td>";
	         						tabhtml += "<td>"+Number(a.toString().match(/^\d+(?:\.\d{0,3})?/))+"MB</td>";
	        						tabhtml += "<td>"+data[i].createTime+"</td>";
	        						tabhtml += "<td><label onclick=deletesrc('"+data[i].fileName+"') class='tablelink'>删除</label></td>";
	         						tabhtml += "</tr>";
         							tabhtml += "<tr class='c_p"+i+"'  style='display: none;background-color: #ffe7ba;'>";
	         						tabhtml += "<td colspan='4'>"+data[i].fileContent+"</td>";
	         						tabhtml += "</tr>";
	         					}
	         				$("#tbody").html(tabhtml);
	                }
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
    						load();
        	            }, 2000);
     				
     					})
					}
       			 });
			}
	    $("#reset").click(function(){
	    	$("#date").val('');
	    })
 		$("#scbtn").click(function(){
     		load();
 		})
     	$(document).ready(function(){
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
