<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="source/js/jquery-2.1.1.js"></script>
<title>Insert title here</title>
   <style type="text/css">
       #show_text{
          border:1px solid black ;
          height: 60px;
          width: 360px;
          text-align: center;
          background-color:gray;
          position: relative;
       }
       #text_kuang{
          margin-top:10px;
          font-size:30px;
          height: 40px;
          width: 360px;
          text-align: center;
          background-color: white;
       }
       #input_text{
          height: 360px;
          width: 360px;
       }
       td{
          border:1px solid black ;
          font-size:20px;
          font-style:italic;
          height: 90px;
          width: 90px;
          cursor:pointer;
          background-color:gray;
          text-align: center;
       }
       td:hover{
          background-color: #CCFFFF;
       }
   </style>
</head>
<body>
<center>
            <div id="show_text">
               <div id="text_kuang"></div>
            </div>
            <div id="input_text">
              <table>
                <tr>
                    <td id="t1" onclick="run(this)">1</td>
                    <td id="t2" onclick="run(this)">2</td>
                    <td id="t3" onclick="run(this)">3</td>
                    <td id="exit">←</td>
                </tr>
                <tr>
                   <td id="t4" onclick="run(this)">4</td>
                   <td id="t5" onclick="run(this)">5</td>
                   <td id="t6" onclick="run(this)">6</td>
                   <td id="ac">ac</td>
                </tr>
                <tr>
                   <td id="t7" onclick="run(this)">7</td>
                   <td id="t8" onclick="run(this)">8</td>
                   <td id="t9" onclick="run(this)">9</td>
                    <td id="tp" onclick="run(this)">/</td>
                </tr>
                <tr>
                   <td id="ta" onclick="run(this)">0</td>
                   <td id="xsd" onclick="run(this)">.</td>
                   <td id="tx" onclick="run(this)">*</td>
                   <td id="tl" onclick="run(this)">-</td>
                </tr>
                <tr>
                   <td id="pfg">sqrt</td>
                   <td id="qyu" onclick="run(this)">%</td>
                    <td id="tj" onclick="run(this)">+</td>
                   <td id="ts">=</td>
                </tr>
              </table>
            </div>
</center>
</body>
      <script type="text/javascript">
           function run(obj){ //点击按键，输入数值
        	   var str = document.getElementById(obj.id).innerHTML;
        	   $("#text_kuang").append(str);
           }
           $(document).ready(function(){
        	   $("#ac").click(function(){ //清空
        		   $("#text_kuang").html("");
        	   });
        	   $("#ts").click(function(){  //算法运算
        		   var result = 0;
        		   var text = $("#text_kuang").html();
        		   for(var i = 0 ; i < text.length ; i++){
        			   var c = text.charAt(i);
        			   if(c == '+'){
        		        var arr = text.split('+');
        		        result = parseInt(arr[0]) + parseInt(arr[1]);
        			   } else if(c == '-'){
        		        var arr = text.split('-');
        		        result = arr[0] - arr[1];
        			   } else if(c == '*'){
        		        var arr = text.split('*');
        		        result = arr[0] * arr[1];
        			   } else if(c == '/'){
        		        var arr = text.split('/');
        		        result = arr[0] / arr[1];
        			   } else if(c == '%'){
        		        var arr = text.split('%');
        		        result = arr[0] % arr[1];
        			   } 
        		   }
        		        $("#text_kuang").append('<font color="red">='+result+"</font>");
        	   });
        	   $("#pfg").click(function(){ //求平方根
        		   var result = Math.sqrt(document.getElementById("text_kuang").innerHTML);
        		   $("#text_kuang").html("");
        		   $("#text_kuang").append(result+'½='+result);
        	   });
        	   $("#exit").click(function(){ //退一格
        		   var str = document.getElementById("text_kuang").innerHTML;
        	       var st = str.substring(0,str.length-1);
        		   $("#text_kuang").html("");
        		   $("#text_kuang").append(st);
        	   });
           });
      
      </script>

</html>














