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
	line-height: 50px;
	font-size:45px;
}
.btn1{
font-size:30px;
}
</style> 
</head>

<body>
<div id="cont1">
 <div class="btn-group btn-group-justified">
	    <div class="btn-group">
	  <a href="#" class=" buttombtn btn btn-primary btn-lg disabled" role="button">设备</a>
	    </div>
	    <div class="btn-group">
	      <a href="client/order" class="buttombtn btn btn-warning btn-lg" role="button">指令</a>
	   </div>
	</div> 
<div>
   <table id="tb_machines"  ></table> 
</div>
 	</div> 


 
</body>
</html>