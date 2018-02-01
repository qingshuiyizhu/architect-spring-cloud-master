$(function(){
		$('#editRow-dialog').dialog('close');
		loadRows();
	 	
		$('#edit_row_role').combobox({
			url : '/userRoleController/getAllRow',
			valueField : 'id',
			textField : 'name'
		});
	  	})

function loadRows(){
 $('#RowData').datagrid({
	 	idField:'id',
	 	pagination:true,
	 	rownumbers:true,
	 //	singleSelect:true,
	  	toolbar:'#tb',
		url:'/userController/getRows',
		columns:[[
			{field:'ck',checkbox:true},
		 	{field:'id',title:'用户ID',width:60,hidden:'true'},
			{field:'name',title:'用户名',width:150,align:'center'},
		 	{field:'rname',title:'角色',width:100}, 
		 	{field:'alter_time',title:'修改时间',width:150,align:'center',
				formatter: function (date) {
				 if(undefined != date){
					  return formatDate(date);
				 }else{
					 return "未定义";
				 } 
     			}
			},
			{field:'create_time',title:'创建时间',width:150,align:'center',
				formatter: function (date) {
				 if(undefined != date){
					  return formatDate(date);
				 }else{
					 return "未定义";
				 } 
     			}
			}
			 
	 	]],
	 	 
	});
	
	
}  	
function addRow(){
	    $("#edit_saveRow_id").val("0");
     	$("#edit_row_name").textbox("setValue","");
	  	$('#editRow-dialog').dialog('open');	
	}
function delRow(){
	 	var rows = $('#RowData').datagrid('getSelections');
	 	if(rows.length<1){
	 	 	$.messager.alert('警告', "请选择数据!");
	 	}else{
	 		 $.messager.confirm("删除操作","确认删除选择的用户吗？",function (r) {  
			        if (r) {  
	 		var ids = [];
	 		  //var arr = new Array();   
		 	for(var i=0; i<rows.length; i++){
				ids.push(rows[i].id);
			}
		 	ids = ids.toString();
		   	 $.post("userController/delRows",{ 'ids':ids},
		    			function(data){
		 		if(data.success){
			  		  // $.messager.alert('提示信息', data.msg);
			  			location.reload();   
			  		}else{
			  			 $.messager.alert('错误信息', '删除数据失败，请重新删除！');
			  		}	  
		     			  },"json");
			        }
			 });
		 	}	 
	}
	 	

var Row_name="";
function editRow(){
	 	var row = $('#RowData').datagrid('getSelected');
		if (row){
	 		Row_name = row.name;
			$("#edit_saveRow_id").val(row.id);
			$("#edit_row_name").textbox("setValue",row.name);
			$('#edit_row_role').combobox("select", row.rid);
			/*data = '';
		 	for (var j = 0; j < userRoles.length; j++) {
		 		if(userRoles[j].id==row.role){
		 		  	data += '<option selected = \"selected\"  value=\"' + userRoles[j].id + '\">'+ userRoles[j].name + '</option>';
		 		}else{
		 		  	data += '<option value=\"' + userRoles[j].id + '\">'+ userRoles[j].name + '</option>';
		 		}
			
				}	
		 	$("#edit_saveRow_role").html("");
	 		$("#edit_saveRow_role").append(data);*/
		    $('#editRow-dialog').dialog('open');
	   	  return;
		}else{
			  $.messager.alert('提示信息', '请选择数据！'); 
		}
		
	}
function saveRow(){
	var id = $("#edit_saveRow_id").val();
	var name = $("#edit_row_name").textbox('getValue');
	var pwd = $("#password").textbox('getValue');
    var role = $("#edit_row_role").combobox('getValue'); 
	   $.post("userController/saveRow",{ 'id':id,'name':name,'pwd':pwd,'role':role},
	    			function(data){
	  		if(data.success){
	 			 $.messager.alert('提示信息', data.msg);
	 	 	     if(data.success){
	 				   $('#edit_saveRow').dialog('close');
	 				  	location.reload();   
	 			   }
	 		}
	     			  },"json");
 		  }
 	  

function formatDate(data) { 
	//如果记得时间戳是毫秒级的就需要*1000 不然就错了,记得转换成整型
	//var d=new Date(1230999938); 
	 var now=new Date(data);
	  var year=now.getFullYear(); 
   // var month=now.getMonth()+1; 
    var month=(now.getMonth()+1 < 10 ? '0'+(now.getMonth()+1) : now.getMonth()+1);
   // var date=now.getDate(); 
    var date=(now.getDate() < 10 ? '0'+(now.getDate()) : now.getDate());
   // var hour=now.getHours(); 
    var hour=(now.getHours() < 10 ? '0'+(now.getHours()) : now.getHours());
    var minute=now.getMinutes(); 
    var minute=(now.getMinutes() < 10 ? '0'+(now.getMinutes()) : now.getMinutes());
    //var second=now.getSeconds(); 
     return year+"-"+month+"-"+date+" "+hour+":"+minute;   
} 
 
//密码再次验证
$.extend($.fn.validatebox.defaults.rules, {  
   /*必须和某个字段相等*/
   equalTo: {
       validator:function(value,param){
    	    return $(param[0]).val() == value;
       },
       message:'字段不匹配'
   }
});