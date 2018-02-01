<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
 <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/jquery-easyui-1.5.2/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<link rel="stylesheet" href="js/jquery-easyui-1.5.2/themes/default/easyui.css" charset="utf-8"/>
<link rel="stylesheet" href="js/jquery-easyui-1.5.2/themes/icon.css"/>   
<script type="text/javascript" src="js/user-list.js"></script>
<script type="text/javascript" >
//加载用户类型
var userRoles;
 function loadusertype(){
	     $.post("userRoleController/getRows",{ 'load':'all'} ,
  			function(data){
	        userRoles=data.rows;
	 		  },"json");	
}
 
//load data
function loaddata(){
	$('#RowData').datagrid({
		title:'用户列表',
	   	idField:'id',
		url: 'userController/getRows',
		columns:[[
			{field:"ck", checkbox:true},
			{field:'id',title:'id',hidden:'hidden'},
			{field:'name',title:'用户名',width:100,align:'center'},
		 	{field:'role',title:'用户类型',width:200,align:'center',
				formatter:function(value){
					for(var i=0; i<userRoles.length; i++){
						if (userRoles[i].id == value) 
							return userRoles[i].name;
					}
					return "undefined";
				} 
			}
		]],
 	});
}
 
$(function(){
	loadusertype();
	loaddata();
});

</script>
</head>
<body>
<div id="tb"> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:addrow()">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editrow()">修改</a>
 <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:delrow()">删除</a>  
 </div>
  <table id="RowData" data-options="fit:true" class="easyui-datagrid" rownumbers="true"
    	 pagination = "true" toolbar="#tb">
   </table>     

 <div id="edit_saveRow" buttons="#edit_saveRow-buttons" data-options="modal:true" class="easyui-dialog" title="编辑设备组" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_saveRow_id" value=""/> 
	  用户名:<input type="text" id ="edit_saveRow_name" value=""/> <br/>
	  密码：<input type="password" id ="edit_saveRow_pwd" value=""/> <br/>
	  用户角色:<select id ="edit_saveRow_role">
	  
	        </select>
	  
</div>
 	<div id="edit_saveRow-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saverow()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_saveRow').dialog('close')">Cancel</a>
	</div>
</body>
</html>