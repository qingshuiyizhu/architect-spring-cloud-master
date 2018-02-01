$(function(){
	$('#edit_save').dialog('close');
	$('#verify').dialog('close');
	loadMachineGroup();
	loadMachine();
});
  
var machineGroups;
//加载所有设备组
function loadMachineGroup(){
	 $.post("machineGroupController/getRows",{},
			 function(data){
 	  machineGroups = data.rows;
		 console.log("加载所有设备组:");
		 console.log(machineGroups);
	 	     },"json");	
}
function loadMachine(){
  	$('#tt').datagrid({
	 	idField:'id',
	 	pagination:true,
	 	rownumbers:true,
		singleSelect : true, 
	  	toolbar:'#tb',
		url:'/machineController/getRows',
		columns:[[
			{field:'ck',checkbox:true},
		 	{field:'id',title:'设备ID',width:60,hidden:'true'},
			{field:'name',title:'设备名称',width:150,align:'center'},
			{field:'mgid',title:'设备组',width:100,align:'center',
				formatter:function(value){
					var name = "未定义";
				 	 $.each(machineGroups, function (i, n){
				        if(value == n.id)  {
				         	 name = n.name;
						        return;
				         }
				  	 });
				  return name;
		 		}
	 		},
			{field:'ip',title:'IP地址',width:150,align:'center'},
		 	{field:'mac',title:'MAC地址',width:150,align:'center'},
		 	{field:'port',title:'通信端口',width:150,align:'center'},
			{field:'heartbeat',title:'在线状态',width:150,align:'center',
		 		formatter:function(value){
		 		 	if(value==1){
						return "<b style=\"color:green\">在线</b>";
					}else{
				 		return "<b style=\"color:red\">离线</b>";
					}
	 			}},
		 	{field:'state',title:'审核状态',width:150,align:'center',
				formatter:function(value){
			  		if(value==1){
						return "已审核";
					}else{
						return "未审核";	
					}
		 			 
				}},
		 
			 {field:'createTime',title:'创建时间',width:150,align:'center',
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

  	 
function verify(){
	var row = $('#tt').datagrid('getSelected');
	if (row){
	  	$("#verify_id").val(row.id);
		$("#mstate").html("");
		var states;
		if(row.state==1){
			 states='<input  type="radio" name="state" value="1" checked>审核通过<input type="radio" name="state" value="0">审核不通过';
		}else{
			  states='<input  type="radio" name="state" value="1">审核通过<input type="radio" name="state" value="0" checked>审核不通过';
	 	}
		$('#mstate').append(states);
 	    $('#verify').dialog('open');
 	  return;
	}else{
		  $.messager.alert('警告消息','请选择审核数据！');
	 }
	
}	 
function del(){
	var row = $('#tt').datagrid('getSelected');
	if (row){
	   
		 $.messager.confirm("删除操作","确认删除["+row.name+"]的数据吗？",function (r) {  
		        if (r) {  
		        	 var id = row.id;
		    	 
		        $.post("machineController/delRows",{'ids':id},
		   			 function(data){
		        	 if(data.success){
	    	 			 $.messager.alert('提示消息','操作成功！');
	    					location.reload();  
	    			 }else{
	    				 $.messager.alert('错误消息','操作失败！');
	    			 }	 
		   	 	     },"json");	
	 	        }
		 });
	 	        
	   }else{
		   $.messager.alert('警告消息','请选择删除数据！');
	   }	
}

function save_verify(){
	var state = $("input[name='state']:checked").val();
	var id = $("#verify_id").val();
	
	
	$.post("dwrController/save_verify",{id:id,state:state},
  			 function(data){
       	 if(data.success){
       		     $('#verify').dialog('close');
	 			 $.messager.alert('提示消息','操作成功！');
					location.reload();  
			 }else{
				 $.messager.alert('错误消息','操作失败！');
			 }	 
  	 	     },"json");	
	  
}
var edit_save_name;
var mgindex;
//弹出的修改框中设备组内容
function edit(){
	var row = $('#tt').datagrid('getSelected');
	if (row){
 	  	$("#edit_save_id").val(row.id);
		$("#edit_save_name").val(row.name);
		edit_save_name = row.name;
	 	mgindex = row.gindex;
     	var selmg='';
		 $.each(machineGroups, function (i, n){
		        if( n.id == row.gindex)  {
		        	selmg +='<option value="'+n.id+'"selected="selected">'+n.name+'</option>';
			      }else{
			 	 		selmg +='<option value="'+n.id+'">'+n.name+'</option>';
			 	}
		  	 });
		     $('#mg').html("");
		     $('#mg').append(selmg);
	     $('#edit_save').dialog('open');
 	  return;
	}else{
		$.messager.alert('警告消息','请选择编辑数据！');
 	}
}

//保存数据
function save_edit(){
	var id = $("#edit_save_id").val();
	var name = $("#edit_save_name").val();
	var mgid =  $('#mg').val();
  	if(edit_save_name ==name && mgindex==mgid){
		 $('#edit_save').dialog('close');
		 return;
 	}else{
 	 		$.post("machineController/saveRow",{'id':id,'name':name,'mgid':mgid},
 				 function(data){
 			 if(data.success){
 				 $('#edit_save').dialog('close');
 				 $.messager.alert('提示消息',data.msg);
 			   	location.reload();  
 		 	}else{
 		 		$.messager.alert('错误消息',data.msg);
 		 		return;
 		 	}
 		 	     },"json");	
 	}
 	  
	
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