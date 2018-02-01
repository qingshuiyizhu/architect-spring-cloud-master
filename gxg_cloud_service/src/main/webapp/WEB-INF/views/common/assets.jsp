<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
    <!-- 新 Bootstrap 核心 CSS 文件 -->  
    <link rel="stylesheet" href="static/framework/bootstrap-3.3.7-dist/css/bootstrap.min.css" charset="utf-8">  
      
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->  
    <!-- <link rel="stylesheet" href="static/framework/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">   -->
 <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
</head>
<body>

</body>
 <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->  
  <script src="static/framework/jquery-3.2.1.min.js"></script>       
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->  
  <script src="static/framework/bootstrap-3.3.7-dist/js/bootstrap.min.js" charset="utf-8"></script> 
  <!-- 弹出层框架 -->
  <script src="static/framework/layer/layer.js" charset="utf-8"></script> 
</html> 