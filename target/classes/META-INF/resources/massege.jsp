<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>留言</title>
<link href="../source/css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
<link href="../source/css/style.css" rel="stylesheet" type="text/css" media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Kaisersosa Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href='https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../source/css/flexslider.css" type="text/css" media="screen" />
<script src="../source/js/jquery.min.js"></script>
<link rel="shortcut icon" href="../source/images/favicon.ico" type="image/x-icon"> 
<link href="manager/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>

    
    <div class="formbody">
    
    <div class="formtitle"><span>给我留言</span></div>
    
    <ul class="forminfo">
    <li><label>邮箱</label><input name="" type="text" id="email" class="dfinput" /><i>请输入正确的邮箱</i></li>
    <li><label>留言内容</label><textarea name="" cols="" id="msgContent" rows="" class="textinput"></textarea><i>请输入200字以内的内容</i></li>
    <li><label>&nbsp;</label><input name="" type="button" class="btn" value="提交留言"/></li>
    </ul>
    
    </div>
</body>
	<script type="text/javascript">
	
		$(".btn").click(function(){
			var email = $("#email").val();
			var reg=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			
			if(!reg.test(email)){
				alert("邮箱格式不正确");
				return false;
			}
			var msgContent = $("#msgContent").val();
			if(msgContent.length > 400){
				alert("留言内容不能超过400字");
				return false;
			}
			if(msgContent == ''){
				alert("留言内容不能为空");
				return false;
			}
			$.post("/data/submitmsg",{email : email,msgContent : msgContent},function(data){
				if(data.success == 1){
					alert("提交成功,谢谢你的留言,我们会及时反馈你的留言");
				} else{
					alert("提交失败")
				}
			})
			
		}) 
		
	</script>
</html>
