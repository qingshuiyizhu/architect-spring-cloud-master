<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>区域按钮</title>
<base href="${basePath}">
<link rel="stylesheet" type="text/css" href="static/framework/jquery-gridly/stylesheets/jquery.gridly.css"/>
<link rel="stylesheet" type="text/css" href="static/framework/jquery-gridly/stylesheets/sample.css"/>
<script src="static/framework/jquery-gridly/javascripts/jquery.js" type="text/javascript"></script>
<script src="static/framework/jquery-gridly/javascripts/jquery.gridly.js" type="text/javascript"></script>
<script src="static/framework/jquery-gridly/javascripts/sample.js" type="text/javascript"></script>
<script src="static/framework/jquery-gridly/javascripts/rainbow.js" type="text/javascript"></script>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.3/themes/default/easyui.css" charset="utf-8"/>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.3/themes/icon.css"/> 
 
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="static/js/manager/sortbutton.js"></script>
<link rel="stylesheet" type="text/css" href="static/css/manager/sortbutton.css"/> 
   
</head>
<body> 
 
 <div id="edit_row-dialog" buttons="#edit_row-buttons" data-options="modal:true" class="easyui-dialog" title="编辑" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
	  	        <input type="hidden" id ="edit_row_id" value=""/> 
	  	区域名称:<input id ="edit_row_name" class="easyui-textbox" name="edit_row_name" value="" style="width: 200px;"/> 
	   
</div>
<div id="edit_row-buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saveRow()">Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_row-dialog').dialog('close')">Cancel</a>
	</div>
 <div class="easyui-layout" data-options="fit:true">
		<div id="div_west" style="width: 300px" data-options="region:'west',title:'按钮集合',minWidth:350,tools:[
								{
									iconCls:'icon-add',
									handler:function(){addRow()}
								},'-',{
									iconCls:'icon-remove',
									handler:function(){delRow()}
								},'-',{
									iconCls:'icon-edit',
									handler:function(){editRow()}
								}]">
		 
	 	<table id="plist"  data-options="fit:true"></table>
		</div>

		<div border="false" style="width: 300px, padding:0px 0px 0px 0px "
			data-options="minWidth:300,region:'center',title:'排序按钮',tools:[
				{    
					iconCls:'icon-save',
					handler:function(){savecontent()}
				}]"> 
			 
	<div class='content'>
 	<p class='actions'>
           <a class='add button' href='#'>增加按钮</a>
           <a class='addblank button' href='#'>增加空白</a>
        </p>
       <section class='example'>
         <div class='gridly'>
      
         </div>
        
      </section>
  </div>
	  </div>
	 		</div>	 
	 		
 <div id="edit_row1_dialog" buttons="#edit_row1_buttons" data-options="modal:true" class="easyui-dialog" title="选择按钮" data-options="iconCls:'icon-edit'" style="width:400px;height:200px;padding:10px">
  <table>
<tr> 
<td>按钮：</td>
<td><input  id="btn" name="btn"  value=""  style="width: 200px;"></td>
</tr>
</table>  	
</div>	 		
	 		
<div id="edit_row1_buttons">
		<a href="javascript:void(0);" class="easyui-linkbutton addbtn" iconCls="icon-ok" >Ok</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#edit_row1_dialog').dialog('close')">Cancel</a>
</div>	 		
	 		 		
</body>
</html>