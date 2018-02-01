<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file= "../common/assets.jsp" %>
   <link rel="stylesheet" href="static/css/client/index.css" charset="utf-8">  
    <!--  bootstrap table组件以及中文包的引用 -->
    <link href="static/framework/bootstrap-table/bootstrap-table.css" rel="stylesheet" /> 
    <script src="static/framework/bootstrap-table/bootstrap-table.js"></script>
    <script src="static/framework/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="static/js/client/index.js"></script>
<title>云联控3.0主页</title>
</head>

<body>
<div class="btn-group btn-group-justified">
	    <div class="btn-group">
	  <a href="#" class=" buttombtn btn btn-primary btn-lg disabled" role="button">设备</a>
	    </div>
	    <div class="btn-group">
	      <a href="client/order" class="buttombtn btn btn-warning btn-lg" role="button">指令</a>
	   </div>
	</div> 
<div>
   <table id="tb_machines"></table> 
</div>


 
</body>
</html>