<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设备管理页面</title>
<%@ include file= "../common/resource.jsp" %>
<script type="text/javascript" src="static/js/manager/machine.js"></script>
</head>
<body class="easyui-layout" fit="true">
<table id="tt"  data-options="fit:true"></table>
<div id="tb"> 
	<!-- <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:add()">增加</a> -->
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit()">修改</a>
 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:verify()">审核</a>  
 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:del()">删除</a>  
 </div>

<div id ="edit_save" buttons="#edit_save-buttons" data-options="modal:true" class="easyui-dialog" 
     title="编辑设备" data-options="iconCls:'icon-edit'" 
     style="width:400px;height:200px;padding:10px">
       <input type="hidden" id ="edit_save_id" value=""/> 
<table>
<tr> 
<td>设备名称：</td>
<td><input id="edit_save_name" type="text" value=""></td>
</tr>
<tr> 
<td>设备组：</td>
<td><select id="mg"></select></td>
</tr>
</table>
</div>
 	<div id="edit_save-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:save_edit()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_save').dialog('close')">Cancel</a>
	</div>
	
	
   <div id ="verify" buttons="#verify-buttons" data-options="modal:true" class="easyui-dialog" 
     title="审核设备" data-options="iconCls:'icon-edit'" 
     style="width:400px;height:200px;padding:10px">
      <input type="hidden" id ="verify_id" value=""/> 
      <b id="mstate"></b>
 
 
 
</div>
 	<div id="verify-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:save_verify()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#verify').dialog('close')">Cancel</a>
	</div>
</body>
</html>