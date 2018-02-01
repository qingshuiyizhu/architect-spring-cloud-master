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
<title>设备组管理页面</title>
<script type="text/javascript" src="static/framework/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/default/easyui.css" charset="utf-8"/>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/icon.css"/>   
 
<script type="text/javascript">
   function addtools(){
		   var pager = $("#table_Rows").datagrid("getPager");     
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
	$(function(){
		$('#edit_Row-dialog').dialog('close');
	 	addtools();
	  	})
	  	
function addRow(){
		$("#edit_Row_id").val("0");
		$("#edit_Row_name").val("");
	  	 $('#edit_Row-dialog').dialog('open');
	  }
	
function delRow(){
	var row = $('#table_Rows').datagrid('getSelected');
	if (row){
		
		 $.messager.confirm("删除操作","确认删除吗？",function (r) {  
		        if (r) {  
		
		$.post("machineGroupController/delRow",{id:row.id, },
				 function(data){
			  var msg; 
	 			(data=="0") ? msg = "操作成功" : "操作失败";
	 	     	 $.messager.alert('提示信息', msg); 
	 	    	location.reload();
				 },"json"); 
		        }
		 });
		  
  	}else{
		  $.messager.alert('提示信息', '请选择数据！'); 
	}
	}
	 
var Mg_name="";
function editRow(){
		var row = $('#table_Rows').datagrid('getSelected');
		if (row){
	 		Mg_name = row.name;
			$("#edit_Row_id").val(row.id);
			$("#edit_Row_name").val(row.name);
	 	    $('#edit_Row-dialog').dialog('open');
	 	  return;
		}else{
			  $.messager.alert('提示信息', '请选择数据！'); 
		}
		
	}
function saveRow(){
	var id = $("#edit_Row_id").val();
	var name = $("#edit_Row_name").val();
  	if(name==Mg_name){
  		 $('#edit_Row-dialog').dialog('close')
 	}else{ 
  		$.post("machineGroupController/saveRow",{'id':id,'name':name},
				 function(data){
			  $.messager.alert('提示信息', data.msg);
	 	     if(data.success){
				   $('#edit_Row-dialog').dialog('close');
				  	location.reload();
	 	     }
				 },"json"); 
	 	 }		
 	 	 }	  
 
</script>  
</head>
<body>
<div id="tb"> 
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:addRow()">增加</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="javascript:editRow()">修改</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:delRow()">删除</a>  
 </div>
  <table id="table_Rows" data-options="fit:true" class="easyui-datagrid" singleSelect="true" rownumbers="true"
    		url="/machineGroupController/getRows"  pagination="true" toolbar="#tb">
    	<thead>
    		<tr><th field="ck" checkbox="true"></th>
    		    <th hidden="true" field="id" width="100" align="center">设备组ID</th>
    			<th field="name" width="300" align="center">设备组名称</th>
    		</tr>
    	</thead>	 	
	    </table>     
 
 <div id="edit_Row-dialog" buttons="#edit_Row-buttons" data-options="modal:true" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_Row_id" value=""/> 
	  	设备组名称:<input type="text" id ="edit_Row_name" value=""/> 
</div>
 	<div id="edit_Row-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saveRow()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_Row-dialog').dialog('close')">Cancel</a>
	</div>
</body>
</html>