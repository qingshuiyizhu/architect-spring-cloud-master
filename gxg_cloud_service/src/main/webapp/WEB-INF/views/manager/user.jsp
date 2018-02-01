<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理页面</title>
<%@ include file= "../common/resource.jsp" %>
<script type="text/javascript" src="static/js/manager/user.js"></script>

</head>
<body class="easyui-layout" fit="true">
<table id="RowData"  data-options="fit:true"></table>
<div id="tb"> 
    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:addRow()">增加</a> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editRow()">修改</a>
 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:delRow()">删除</a>  
 </div>

<div id ="editRow-dialog" buttons="#edit_save-buttons" data-options="modal:true" class="easyui-dialog" 
     title="编辑" data-options="iconCls:'icon-edit'" 
     style="width:400px;height:300px;padding:10px">
       <input type="hidden" id ="edit_saveRow_id" value=""/> 
<table>
<tr> 
<td>用户名：</td>
<td style="width: 200px;"><input id="edit_row_name" class="easyui-textbox" data-options="prompt:'账号',validType:'name'" iconCls="icon-man" iconAlign="left" style="width:100%;height:32px"/></td>
</tr>
<tr> 
<td>密码：</td>
<td><input id="password" name="password" validType="length[4,32]" class="easyui-textbox" required="true" type="password" value=""  style="width:100%;height:32px"/></td>
</tr>
<tr> 
<td>确认密码：</td>
<td><input type="password" name="repassword" id="repassword" required="true" class="easyui-textbox" style="width:100%;height:32px" validType="equalTo['#password']" invalidMessage="两次输入密码不匹配"/></td>
</tr>
 
<tr> 
<td>角色：</td>
<td><input id="edit_row_role" name="edit_row_role"  value=""  style="width: 200px;"></td>
</tr>   

</table>
</div>
 	<div id="edit_save-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saveRow()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#editRow-dialog').dialog('close')">Cancel</a>
	</div>
 
</body>
</html>