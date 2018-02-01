$(function(){
	$('#edit_dialog').dialog('close');
	 	
});
 
function del_row(){
  	 var row = $('#data_table').datagrid('getSelected');
		if (row){
	 		 $.messager.confirm("删除操作","确认删除["+row.name+"]的数据吗？",function (r) {  
			        if (r) {  
			        	 var id = row.id;
			         	 $.post("programController/delRow",{ id:id} ,
			        				function(data){
			        		 $.messager.alert('消息',data.msg);
			    	 	 	 if(data.success){
			    	 		 		location.reload();  
			    			 } 
			        	 			  },"json");
			   		         
			        }
			 });
		 	        
		   }else{
			   $.messager.alert('警告消息','请选择删除数据！');
		   }	 
	}

function add_row(){
	 $("#edit_id").val("0");
     $("#edit_name").val("");
	 $("#edit_appPath").val("");
	 $('#edit_dialog').dialog('open');
 }
 
var edit_name;
var edit_appPath;

function edit_row(){
	var row = $('#data_table').datagrid('getSelected');
	if(row){
		 $("#edit_id").val(row.id);
	     $("#edit_name").val(row.name);
		 $("#edit_appPath").val(row.appPath);
		 $('#edit_dialog').dialog('open');
	 	 edit_name =row.name;
		 edit_appPath = row.appPath;
	}else{
	 	  $.messager.alert('信息','请选择数据！');
  	}
}
function save_Row(){
	var  id = $("#edit_id").val();
	var  name = $("#edit_name").val();
	var  appPath = $("#edit_appPath").val();
  if(edit_name ==name && edit_appPath ==appPath){
		$('#edit_dialog').dialog('close');
	  return;
	  
  }
 	$.post("programController/editRow",{ 'id':id ,'name':name,'appPath':appPath} ,
			function(data){
		if(data.success){
			$('#edit_dialog').dialog('close');
  		    $.messager.alert('提示信息', data.msg);
  		 	location.reload(); 
  		   
  		}else{
  			 $.messager.alert('错误信息',data.msg);
  		}	  
 			  },"json");
	 
}
