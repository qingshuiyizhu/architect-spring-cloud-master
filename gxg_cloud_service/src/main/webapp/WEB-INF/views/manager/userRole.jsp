<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户类型管理页面</title>
<%@ include file= "../common/resource.jsp" %>
<script type="text/javascript" src="static/js/manager/userRole.js"></script>
</head>
<body>
<div id="tb"> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:addrow()">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editrow()">修改</a>
<!-- 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:delrow()">删除</a>  -->
 </div>
  <table id="RowData" data-options="fit:true" class="easyui-datagrid" singleSelect="true" rownumbers="true"
    		url="userRoleController/getRows"  pagination="true" toolbar="#tb">
    	<thead>
    		<tr><th field="ck" checkbox="true"></th>
    		    <th hidden="true" field="id" width="100" align="center">角色ID</th>
    			<th field="name" width="300" align="center">角色名称</th>
    		</tr>
    	</thead>	 	
	    </table>     

 <div id="edit_saveRow" buttons="#edit_saveRow-buttons" data-options="modal:true" class="easyui-dialog" title="编辑设备组" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_saveRow_id" value=""/> 
	  	角色:<input type="text" id ="edit_saveRow_name" value=""/> 
</div>
 	<div id="edit_saveRow-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saverow()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_saveRow').dialog('close')">Cancel</a>
	</div>
</body>
</html>