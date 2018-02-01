<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>客戶端文件管理</title>

<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/default/easyui.css" charset="utf-8"/>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/icon.css"/> 

<script type="text/javascript" src="static/framework/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>


<link rel="stylesheet" href="static/framework/zTree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<!-- <script type="text/javascript" src="static/framework/zTree/js/jquery.min.js"></script> -->
<script type="text/javascript" src="static/framework/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="static/framework/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="static/framework/zTree/js/jquery.ztree.exedit.js"></script>
 
<script type="text/javascript">
$(function(){
	init();
	
	
});
var setting = {
	 	view: {
		 	 
	 		selectedMulti: false
		},
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		edit: {
	  	enable:true,
		  showRenameBtn: false,  
	      showRemoveBtn: showRemoveBtn,  
	 	  removeTitle:'删除'  
			
		}, 
		callback: {  
	         beforeRemove:beforeRemove,
	        onRemove: zTreeOnRemove  
	    } 
	};
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isParent;
	}
	function showRenameBtn(treeId, treeNode) {
		return false;
	}
	
	function showAddBtn(treeId, treeNode) {
		return false;
	}
	
 
    function beforeRemove(treeId, treeNode) { 
          return confirm("确认删除[" + treeNode.path + "]吗？");  
    }  
   
	/*================================ remove begin ================================================*/

	/*
	 *   删除节点
	 */
	function zTreeOnRemove(e, treeId, treeNode) {
	
		var id = treeNode.id;
 	
		  $.post("clientFileController/editRow",{id:id},
					 function(data){
			  init();
			 	     },"json");	   
	 
	}

	function init(){
	  $.post("clientFileController/getClientTree",{},
				 function(data){
			  
				$.fn.zTree.init($("#treeDemo"), setting, data);
		 	     },"json");	 
}


</script>




</head>
<body>
<h1>云联控3.0客户端文件树</h1>
<!--  客户端文件树-->
<!--  可以删除  -->
<ul id="treeDemo" class="ztree"></ul>
</body>
</html>