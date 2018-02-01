<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>程序列表</title>

<%@ include file= "../common/resource.jsp" %>
<script type="text/javascript" src="static/js/manager/program.js"></script>

</head>
<body>
<div id="tb"> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:add_row()">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit_row()">修改</a>
 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:del_row()">删除</a>  
 </div>
  <table id="data_table" data-options="fit:true" class="easyui-datagrid"  rownumbers="true" 
    		url="programController/getRows" 	singleSelect="true" pagination="true" toolbar="#tb">
    	<thead>
    		<tr><th field="ck" checkbox="true"></th>
    		    <th hidden="true" field="id"  align="center">ID</th>
    			<th field="name" width="25%" align="center">程序名称</th>
    			<th field="appPath" width="65%" align="center">程序路径</th>
    		</tr>
    	</thead>	 	
	    </table>         
 <div id="edit_dialog" buttons="#edit_buttons" data-options="modal:true" class="easyui-dialog" title="编辑数据" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_id" value="0"/> 
	  	          程序名称:<input type="text" id ="edit_name" value=""/><br/><br/> 
	  		程序路径:<input type="text" id ="edit_appPath" size="35" value=""/> 
</div>
 	<div id="edit_buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:save_Row()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_dialog').dialog('close')">Cancel</a>
	</div>
	    
</body>
</html>