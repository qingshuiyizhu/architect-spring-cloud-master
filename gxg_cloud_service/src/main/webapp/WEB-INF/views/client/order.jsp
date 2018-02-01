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
</head>
<body>
 <div class="btn-group btn-group-justified">
	    <div class="btn-group">
	      <a href="client/index" class=" buttombtn btn btn-primary btn-lg" role="button">设备</a>
	    </div>
	    <div class="btn-group">
	       <a href="#" class="buttombtn btn btn-warning btn-lg disabled" role="button">指令</a> 
	   </div>
	</div> 
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
			<!-- 按钮组 -->
			<div class="btn-group-vertical" id="sortbuttons"></div>
		 	</div>
			<div class="col-md-10">
			<ul id="buttons">
		 
		 	</ul>
		 	</div>
 	</div>

	</div>
</body>
</html>