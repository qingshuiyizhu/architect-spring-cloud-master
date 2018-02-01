<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/assets.jsp"%>

<title>设备按钮</title>
<script type="text/javascript">
var id="${machineid}";
$(function(){
	var mid= id;
	 
	$.post("buttonController/getOrderByMid",{ 'id':mid} ,
			function(data){
	console.log(data);
 			  },"json");
	
	
		
});

</script>
</head>
<body>
 
<div>
<ul id="buttons">
		 
</ul>
 <div class="btn-group btn-group-justified">
	    <div class="btn-group">
	      <a href="client/index" class=" buttombtn btn btn-primary btn-lg" role="button">设备</a>
	    </div>
	    <div class="btn-group">
	       <a href="client/order" class="buttombtn btn btn-warning btn-lg" role="button">指令</a> 
	   </div>
	</div> 
	</div>
</body>
</html>