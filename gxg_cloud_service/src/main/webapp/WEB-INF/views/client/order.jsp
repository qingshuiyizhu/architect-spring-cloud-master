<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../common/assets.jsp"%>
<link rel="stylesheet" href="static/css/client/order.css"
	charset="utf-8">
<script type="text/javascript" src="static/js/client/order.js"></script>
<title>指令页面</title>
<style type="text/css">
html, body {
	margin: 0px;
	padding: 0px;
	width: 100%;
	height: 100%;
	min-width: 1000px;
	min-width: 1000px !important;
	min-height: 500px;
	min-height: 500px !important;
	/*  border: 25px solid green;    */
	  font-size:30px;  
}

#cont1 {
	/* width: 352px;
    height: 520px;   */
	width: 100%;
	height: 100%;
	min-width: 352px;
	min-width: 352px !important;
	min-height: 520px;
	min-height: 520px !important;
	
}

.buttombtn {
	width: 50%;
	line-height: 52px;
	font-size:47px;
}
.buttombtn1 {
 	line-height: 47px;
   	font-size:44px;
}
.btn2{
font-size:43px;

}
</style>
</head>
<body>
<div id="cont1">
 <div class="btn-group btn-group-justified">
	    <div class="btn-group">
	      <a href="client/index" class=" buttombtn btn btn-primary btn-lg" role="button">设备</a>
	    </div>
	    <div class="btn-group">
	       <a href="#" class="buttombtn btn btn-warning btn-lg disabled" role="button">指令</a> 
	   </div>
	  </div> 
	    <hr/> 
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
			<!-- 按钮组 -->
			<div class=" btn-group btn-group-justified" id="sortbuttons">
		 	</div>
		 	</div>
		 	<div class="col-md-12">
			<ul id="buttons">
		 
		 	</ul>
		 	</div>
 	</div>
</div>
	</div>
</body>
</html>