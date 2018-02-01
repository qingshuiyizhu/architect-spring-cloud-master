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
<title>按钮类型管理页面</title>
<script type="text/javascript" src="static/framework/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/default/easyui.css" charset="utf-8"/>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/icon.css"/>   

 
<script type="text/javascript">
$(function(){
	$('#edit_row_dialog').dialog('close');
 	addtools();
  	})  
function addtools(){
		   var pager = $("#rows_table").datagrid("getPager");     
	 	   pager.pagination({
	 	            pageSize:10,
			        pageList:[10,20,30],
			       	showPageList:true,
			    	onBeforeRefresh:function(){
			    		 	location.reload();   
			    			return true;
			    		}       
			    	});  		
	}

	  	
function add_row(){
	     $("#edit_row_id").val("0");
	 	 $("#edit_row_name").val("");
	  	 $('#edit_row_dialog').dialog('open');
	  }
	
function delmg(){
	 	var rows = $('#rows_table').datagrid('getSelections');
	 	if(rows.length<1){
	 	 	$.messager.alert('警告', "请选择数据!");
	 	}else{
	 		var ids = [];
		 	for(var i=0; i<rows.length; i++){
				ids.push(rows[i].id);
			}
		  	dwrService.deleteMachineGroup(ids,function(data){
		  		if(data.success){
		  		   $.messager.alert('提示信息', data.msg);
		  		}else{
		  			 $.messager.alert('错误信息', '删除数据失败，请重新删除！');
		  		}});
	 	} 
	}
	 
var Row_name="";
function edit_row(){
	 	var row = $('#rows_table').datagrid('getSelected');
	 	if (row){
			Row_name = row.name;
			$("#edit_row_id").val(row.id);
			$("#edit_row_name").val(row.name);
	 	    $('#edit_row_dialog').dialog('open');
	 	  return;
		}else{
			  $.messager.alert('提示信息', '请选择数据！'); 
		}
		
	}
function save_row(){
	var id = $("#edit_row_id").val();
	var name = $("#edit_row_name").val();
  	if(name==Row_name){
  		//未修改
  	    $('#edit_row_dialog').dialog('close');
  		return;
 	}else if(name !="" && name.length >0 ){ 
  		$.post("buttonTypeController/saveRow",{'id':id,'name':name},
				 function(data){
		     if(data.success){
				   $('#edit_row_dialog').dialog('close');
				  	location.reload();
	 	     }else{
	 	    	  $.messager.alert('提示信息', data.msg);
	 	     }
				 },"json"); 
	 	 }		
 	 	 }	  
 
</script>  
</head>
<body>
<div id="tb"> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:add_row()">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:edit_row()">修改</a>
<!-- 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:delmg()">删除</a>  -->
 </div>
  <table id="rows_table" data-options="fit:true" class="easyui-datagrid" singleSelect="true" rownumbers="true"
    		url="/buttonTypeController/getRows"  pagination="true" toolbar="#tb">
    	<thead>
    		<tr><th field="ck" checkbox="true"></th>
    		    <th hidden="true" field="id" width="100" align="center">按钮类型ID</th>
    			<th field="name" width="300" align="center">按钮类型名称</th>
    		</tr>
    	</thead>	 	
	    </table>     
 
 <div id="edit_row_dialog" buttons="#edit_row-buttons" data-options="modal:true" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_row_id" value="0"/> 
	 按钮类型名称:<input type="text" id ="edit_row_name" value=""/> 
</div>
 	<div id="edit_row-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:save_row()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_row_dialog').dialog('close')">Cancel</a>
	</div>
</body>
</html>