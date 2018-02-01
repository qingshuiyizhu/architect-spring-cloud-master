<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>按钮管理页面</title>
<%@ include file= "../common/resource.jsp" %>
<link rel="stylesheet" type="text/css" href="static/css/manager/button.css"/>
 <script type="text/javascript" src="static/js/manager/button.js"></script>
</head>
<body>

 <div id="edit_row_dialog" buttons="#edit_row_buttons" data-options="modal:true" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_row_id" value=""/> 
	  <table>
<tr> 
<td>按钮名称:</td>  
<td><input id ="edit_row_name" class="easyui-textbox" name="edit_row_name" value="" style="width: 200px;"/> </td>
</tr>
<tr> 
<td>按钮类型：</td>
<td><input  id="edit_row_type" name="edit_row_type"  value=""  style="width: 200px;"></td>
</tr>
</table>  	
</div>

<div id="edit_row_buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:save_row()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_row_dialog').dialog('close')">Cancel</a>
</div>
 
	<div class="easyui-layout" data-options="fit:true">
		<div id="bdiv" style="width: 570px"
			data-options="region:'west',title:'按钮列表',minWidth:150,tools:[
								{
									iconCls:'icon-add',
									handler:function(){add_row()}
								},'-',{
									iconCls:'icon-remove',
									handler:function(){del_row()}
								},'-',{
									iconCls:'icon-edit',
									handler:function(){edit_row()}
								}]">
        <!-- 数据网格-按钮列表 -->
		<table id="blist"  data-options="fit:true"></table>
		</div>

		<div  border="true" style="width: 600px"
			data-options="minWidth:300,region:'center',title:'程序列表'"> 
			<input type="hidden" id="b_id" value="" />
	  <table id="plist" data-options="fit:true"></table> 
	 </div>
			</div>	
</body>
</html>