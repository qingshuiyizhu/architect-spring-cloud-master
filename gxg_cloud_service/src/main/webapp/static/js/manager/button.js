var machineGroups;
var machines;
var programs;
$(function() {
	$('#edit_row_dialog').dialog('close');
  	// 加载所有设备
	loadMachines();
	// 加载所有程序
	loadPrograms();
	// 加载所有的设备组
	loadMachineGroups();
	showButton();
 	// 加载按钮
	loadButton();
	
});
// 加载所有的设备
function loadMachines(){
 	$.post("machineController/getRows1", {}, function(data) {
		machines = data;
		console.log("加载所有设备:");
		console.log(machines);
	}, "json");
	
	
}

// 加载所有的程序
function loadPrograms(){
 	$.post("programController/getRows", {}, function(data) {
 	 	programs = data.rows;
		console.log("加载所有程序:");
		console.log(programs);
	}, "json");
	
	
}

// 加载所有设备组
function loadMachineGroups() {
	$.post("machineGroupController/getRows", {}, function(data) {
		machineGroups = data.rows;
		console.log("加载所有设备组:");
		console.log(machineGroups);
	}, "json");
}

//加载所有按钮和显示第一个按钮的内容
function loadButton(){
	$.post("dwrController/getButtons", {}, function(data) {
	 	$('#blist').datagrid('loadData', data);
		$("#b_id").val(data.rows[0].id);
		// 显示第一个按钮的内容
		findcontent(data.rows[0].id);
	}, "json");
	 
}

function showButton() {
	$('#blist')
			.datagrid(
					{
						singleSelect : true,
						idField : 'id',
						pagination : true,
						rownumbers : true,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : 'ID',
									width : 1,
									hidden : 'true'
								},
								{
									field : 'tname',
									title : '按钮类型',
									width : 150,
									align : 'center'
								},
								{
									field : 'name',
									title : '按钮名称',
									width : 200,
									align : 'center'
								}
								  ] ], 
								 onClickRow:function(index,row){
									        findcontent(row.id);
				 					 		}
					});
}
//显示程序列表
var editing ; // 判断用户是否处于编辑状态
var flag ;	  // 判断新增和修改方法
var newVal;
function findcontent(id) {	
	 $('#plist').datagrid({
	 	  	rownumbers : true,
			singleSelect : true, 
		    fitColumns: false  ,
			striped: true ,	 
			loadMsg: '数据正在加载,请耐心的等待...' ,
			columns : [ [
					{
						field : 'ck',
						checkbox : true
					},
					{
						field : 'mgname',
						title : '设备组',
						width : 100,
						align:'center',
						formatter : function(value,row) {
					 		//设备组id
					  		var mgid = 0;
					 		var name ="未定义";
					  	$.each(machines,function(i,n){
							 		if(row.mid == n.id){
										mgid = n.mgid; 
								 	 	}
								 	});  
					 		$.each(machineGroups,function(i,n){
			 			 	if(mgid==n.id){
								name = n.name;
						 		}
							 	});
				 		
			 			 	return name;
					  		},
					  	editor : {
							type : 'combobox',
							options : {
								valueField : 'id',
								textField : 'name',
							 	 data : machineGroups,
							 	 required:true,
								 missingMessage:'设备组必选!',
								onChange:function(data){
									var row = $('#plist').datagrid('getSelected');
								 	var rowIndex = $('#plist').datagrid('getRowIndex',row);//获取行号
								  	var thisTarget = $('#plist').datagrid('getEditor', {'index':rowIndex,'field':'mgname'}).target;
	                             	var value = thisTarget.combobox('getValue');
	                             	var target = $('#plist').datagrid('getEditor', {'index':rowIndex,'field':'mid'}).target;
									target.combobox('clear'); //清除原来的数据
									var url = 'machineController/getMachineBymgid?mgid='+value;
									target.combobox('reload', url);//联动下拉列表重载
							     } 
				 			}
						}
					}, {
						field : 'mid',
						title : '设备名称',
						width : 100,
						align:'center',
						formatter : function(value) {
					 	  var name ="";
							$.each(machines,function(i,n){
								if(value==n.id){
									name = n.name;
						 	}
						 	});  
						 	return name;
				  		},
						editor : {
							type : 'combobox',
							options : {
								valueField : 'id',
								textField : 'name',
								data : machines,
								 required:true , 
								 missingMessage:'设备名称必选!',
								 onChange:function(n,o){
									  if(n==o||o==""){
									    return;	 
									 }
									  if(n==newVal){
											$('#plist').datagrid('endEdit', editing);
							 		  return;
									  }
								 	    var json = $('#plist').datagrid('getRows');
		                                  for(var i=0;i<json.length;i++){
									     	if(n==json[i].mid){
									      	 	$.messager.alert('提示信息', "一个按钮不能添加多个相同设备！");
										     	  return; 	 
									      	}
 									    }	
		                               	$('#plist').datagrid('endEdit', editing);
									         
							     } 
							}
						}
					},
					{
						field : 'pid',
						title : '程序',
						width : 150,
						align:'center',
						formatter : function(value,row) {
							var name = "";
						 	 $.each(programs,function(i,n){
						 		if(value==n.id){
						 			name= n.name;
						 		} 
						   	 });
						 	 	return name;
					  		},
						editor : {
							type : 'combobox',
						 	 options : {  
		                  			valueField:'id' , 
									textField:'name',
									data:programs,
			                        /* required:true*/ 
							      }  
					 			
						}
					},{
						field : 'appPath',
						title : '参数',
						width : 150,
						align:'center',
						editor : 'text'
					}

			] ],
			/*onDblClickCell: function(index,field,value){
				$(this).datagrid('beginEdit', index);
				var ed = $(this).datagrid('getEditor', {index:index,field:field});
				 $(ed.target).focus();
			},*/
			 // 点击行时触发事件
			onDblClickRow:function(rowIndex,rowData){
				//双击开启编辑行
                if (editing != undefined) {
                	$('#plist').datagrid("endEdit", editRow);
                }
                if (editing == undefined) {
                	$('#plist').datagrid("beginEdit", rowIndex);
                    editing = rowIndex;
                    var thisTarget = $('#plist').datagrid('getEditor', {'index':editing,'field':'mgid'}).target;
                	var value = thisTarget.combobox('setValue',rowData.mgid);
                	 var thisTarget1 = $('#plist').datagrid('getEditor', {'index':editing,'field':'mid'}).target;
                 	 var value1 = thisTarget1.combobox('setValue',rowData.mid);
                    
                    
                }
			
				
			}, 
			
			/*onClickRow:function(index,row){
			   	$('#plist').datagrid('beginEdit', index);
			
			    //$('#plist').datagrid('beginEdit', getRowIndex(row));
			   	
			   	
			   	
			   	
			  if(index != editing){
				    newVal=row.mid;
					$('#plist').datagrid('endEdit', editing); 
			 	   	editing = index;
			  }
			
			  
			  
			  
			  
		 		},*/
			toolbar:[
				{
					text:'新增程序',
					iconCls:'icon-add' , 
					handler:function(){
									if(editing != undefined){
										$('#plist').datagrid('endEdit', editing); 
									}
										flag = 'add';
										// 1 先取消所有的选中状态
										$('#plist').datagrid('unselectAll');
										// 2追加一行
										$('#plist').datagrid('appendRow',{description:''});
										// 3获取当前页的行号
										editing = $('#plist').datagrid('getRows').length -1;
										// 4选中并开启编辑状态
										$('#plist').datagrid('selectRow',editing);
										$('#plist').datagrid('beginEdit', editing);
										var thisTarget = $('#plist').datagrid('getEditor', {'index':editing,'field':'mgid'}).target;
		                            	var value = thisTarget.combobox('setValue',machineGroups[0].id);
 			 				 
							}
				},{
					text:'保存程序',
					iconCls:'icon-save' , 
					handler:function(){
							// 保存之前进行数据的校验 , 然后结束编辑并编辑状态字段
							if($('#plist').datagrid('validateRow',editing)){
									$('#plist').datagrid('endEdit', editing);
									editing = undefined;
							//进行持久化操作
								var json = $('#plist').datagrid('getRows');
								console.log("数据网格的数据：");
							    console.log(json);		
							    var cont = JSON.stringify(json);
							    var id = $("#b_id").val();
						 	$.post("buttonController/editRow", {
									'id' : id,
									'cont' : cont
									}, function(data) {
									if (data.success) {
										$.messager.alert('提示信息', "操作成功！");
									} else {
										$.messager.alert('错误信息', "操作失败！");
									}
								}, "json");  
							}
					}
				},{
					text:'删除程序',
					iconCls:'icon-remove' , 
					handler:function(){
						if(editing != undefined){
							$('#plist').datagrid('endEdit', editing); 
						}
				 		var row = $('#plist').datagrid('getSelected');
						if(row){
							var rowIndex = $('#plist').datagrid('getRowIndex',row);//获取行号
						 	$('#plist').datagrid('deleteRow',rowIndex);
					 	}else{
					 		$.messager.alert('信息', '请选择数据！');										
					 	    }
			}  
					  
				},{
					text:'取消操作',
					iconCls:'icon-cancel' , 
					handler:function(){
						// 回滚数据
						$('#plist').datagrid('rejectChanges');
						editing = undefined;
					}
				}	
			] 
	 	});
	 
            $("#b_id").val(id);
	        $.post("buttonController/getRowContent", {'id':id}, function(data) {
	        	if(data!=null){
	        		$('#plist').datagrid('loadData', data);
	        	}
	    	
	    	 	}, "json");
	  
}
function getRowIndex(target){
    var tr = $(target).closest('tr.datagrid-row');
    return parseInt(tr.attr('datagrid-row-index'));
}
// 增加数据
function add_row() {
	$("#edit_row_id").val("0");
	$("#edit_row_name").textbox("setValue", "");
	$('#edit_row_type').combobox({
		url : '/buttonTypeController/getAllRow',
		valueField : 'id',
		textField : 'name'
	})
	$('#edit_row_dialog').dialog('open');
}

// 编辑前显示数据
var edit_row_name;
var edit_row_type;
function edit_row() {
	var row = $('#blist').datagrid('getSelected');
	if (row) {
		$("#edit_row_id").val(row.id);
		edit_row_name = row.name;
		edit_row_type = row.tid;
		$("#edit_row_name").textbox("setValue", row.name);
		$('#edit_row_type').combobox({
			url : '/buttonTypeController/getAllRow',
			valueField : 'id',
			textField : 'name'
		})
 		if (row.tid != 0) {
			$('#edit_row_type').combobox("select", row.tid);
		}

		$('#edit_row_dialog').dialog('open');
	} else {
		$.messager.alert('信息', '请选择数据！');
	}

}

// 保存数据
function save_row() {
	var tid = $("#edit_row_type").combobox('getValue');// 获取当前选中的值
	var name = $("#edit_row_name").textbox('getValue');
	if (name == edit_row_name && tid == edit_row_type) {
		$('#edit_row_dialog').dialog('close');
		return;
	}
	var id = $("#edit_row_id").val();
  
	$.post("buttonController/saveRow", {
		'id' : id,
		'name' : name,
		'tid' : tid
	}, function(data) {
		if (data.success) {
			$('#edit_row_dialog').dialog('close');
			$.messager.alert('提示信息', data.msg);
			location.reload();
		} else {
			$.messager.alert('错误信息', "操作失败！");
		}
	}, "json");
}

//删除数据
function del_row() {
	var row = $('#blist').datagrid('getSelected');
	if (row) {
		
		 $.messager.confirm("删除操作","确认删除["+row.name+"]的数据吗？",function (r) {  
		        if (r) {  
		$.post("buttonController/delRow", {
			'id' : row.id
		}, function(data) {
			 $.messager.confirm("删除操作","确认删除["+row.name+"]的数据吗？",function (r) {  
			        if (r) {  
			        
			if (data.success) {
				location.reload();
			} else {
				$.messager.alert('错误信息', "操作失败！");
			}
			        }
			 });
		}, "json");
		        }
		 });
			 
	} else {
		$.messager.alert('信息', '请选择数据！');
	}
}

 
 
