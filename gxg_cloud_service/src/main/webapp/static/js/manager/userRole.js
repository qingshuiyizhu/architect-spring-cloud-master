function addtools(){
		   var pager = $("#RowData").datagrid("getPager");    // get the pager of datagrid
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
		$('#edit_saveRow').dialog('close');
	 	addtools();
	  	})
	  	
function addrow(){
		$("#edit_saveRow_id").val("0");
		$("#edit_saveRow_name").val("");
	  	$('#edit_saveRow').dialog('open');
	  }
	
function delrow(){
	 	var rows = $('#RowData').datagrid('getSelections');
	 	if(rows.length<1){
	 	 	$.messager.alert('警告', "请选择数据!");
	 	}else{
	 		var ids = [];
		 	for(var i=0; i<rows.length; i++){
				ids.push(rows[i].id);
			}
		  	 $.post("userRoleController/delRow",{ ids:ids} ,
		    			function(data){
		 		if(data.success){
			  		   $.messager.alert('提示信息', data.msg);
			  		}else{
			  			 $.messager.alert('错误信息', '删除数据失败，请重新删除！');
			  		}	  
		     			  },"json");
		 	}	 
	}
	 	
	var Row_name="";
function editrow(){
		var row = $('#RowData').datagrid('getSelected');
		if (row){
	 		Row_name = row.name;
			$("#edit_saveRow_id").val(row.id);
			$("#edit_saveRow_name").val(row.name);
	 	    $('#edit_saveRow').dialog('open');
	 	  return;
		}else{
			  $.messager.alert('提示信息', '请选择数据！'); 
		}
		
	}
function saverow(){
	var id = $("#edit_saveRow_id").val();
	var name = $("#edit_saveRow_name").val();
  	if(name==Row_name){
		 	$.messager.alert('警告', "请修改设备组名!");
 	}else{ 
  		  $.post("userRoleController/saveRow",{ 'id':id,'name':name} ,
	    			function(data){
 		 	if(data.success){
	 			 $.messager.alert('提示信息', data.msg);
	 	 	 		   $('#edit_saveRow').dialog('close');
	 				  	location.reload();   
 		 	}else if( "exist" ==data.obj){
	 			 $.messager.alert('提示信息', data.msg);
	 	 		
	 		}
	     			  },"json");
 		  }
 	 }	  
 