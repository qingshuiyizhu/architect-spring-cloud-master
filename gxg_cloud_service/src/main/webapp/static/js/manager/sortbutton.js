$(function(){
	 	$('#edit_row-dialog').dialog('close');
	 	 $('#edit_row1_dialog').dialog('close');
	 	 loadBtns();
	 	 loadsortButton();
	 
});
 
var btns;

function loadBtns(){
	 $.post("buttonController/findRows1",{},
			 function(data){
		     btns=data;
	  		 },"json"); 
}


var sortid;
//加载页面
function loadsortButton(){
	 $('#plist').datagrid({
		  	singleSelect:true,
		 	idField:'id',
		 	pagination:true,
			url:'/sortButtonController/getRows',
		  	columns:[[
				{field:'ck',checkbox:true},
			 	{field:'id',title:'ID',width:1,hidden:'true'},
				{field:'name',title:'集合名',width:150,align:'center'}
	 	 		]],
	 	 		onClickRow:function(index,row){
	 	 		     findcontent(row.id);
			 		} 	 
		}); 
}
 function findcontent(id){
 	  $(".gridly").html("");
     sortid=id;
     $.post("sortButtonController/getCont",{'id':id},
			 function(data){
		  if("0"==data){
			   $.messager.alert('提示信息', "暂无数据！"); 
		  }else{
			  var str ="";
			  $.each(data,function(i,n){
				  if(n.type==1){
				      var name="";
					  $.each(btns,function(j,m){
							if(n.bid==m.id){
								name=m.name;
								return;
							}   
					  });
					  if(name !=""){
			 	       str+='<div class="brick small" style="position: absolute; left: '+n.left+'px; top: '+n.top+'px;"><a class="delete">×</a><small>'+n.bid+'</small><b>'+name+'</b></div>';  
					     }
					  }else if(n.type==0){
			 			str+='<div class="brick blank" style="position: absolute; left: '+n.left+'px; top: '+n.top+'px;"><a class="delete">×</a></div>';  
			  	  }
		 	 	  
			  });
			  $(".gridly").append(str);	  
			  return $('.gridly').gridly();
		  }
	  		 },"json"); 
	
} 

//得到class为gridly的html内容

function savecontent(){
	var JsonData = {
			bid : "",
		    left:"",
			top : "",
			type:""
	 	}
	var contents = [];
	var html =   $(".gridly").html();
 
 	$('.gridly>div').each(function(){
 		var bid = $(this).find('small').html();
 		var JsonData = new Object();
   	if(bid !== undefined){
 	 	 JsonData.bid = bid;
 	 	 	JsonData.type=1;
 		}else{
 			JsonData.type=0;
 	 	}
         JsonData.left = parseInt($(this).css('left'));
		 JsonData.top = parseInt($(this).css('top'));
		 contents.push(JsonData);	
  	});
 		 var obj= JSON.stringify(contents);
 		 //持久化
 		 var id =sortid;
 		 $.post("sortButtonController/editRow",{ 'id':id,'cont':obj} ,
 				function(data){
 			if(data.success){
 				 $.messager.alert('提示信息',"操作成功！");
 	   		}else{
 	  			 $.messager.alert('错误信息',"操作失败！");
 	  		}	  
 	 			  },"json");	 
 		 
 		 
 		 
 		 
 		 
}

//增加页面
function addRow(){
	$("#edit_row_id").val("0");
    $("#edit_row_name").textbox("setValue","");
	$('#edit_row-dialog').dialog('open');
}
//编辑页面
//编辑前显示数据
var edit_row_name;
function editRow(){
 	var row = $('#plist').datagrid('getSelected');
	if(row){
      	  $("#edit_row_id").val(row.id);
		  edit_row_name = row.name;
	       $("#edit_row_name").textbox("setValue", row.name);
	   	  $('#edit_row-dialog').dialog('open');
	      }else{
    	   $.messager.alert('提示信息', "请选择编辑数据！"); 
       }
}

//删除页面
function delRow(){
	var row = $('#plist').datagrid('getSelected');
	if(row){
		 $.messager.confirm("删除操作","确认删除["+row.name+"]的数据吗？",function (r) {  
		        if (r) {  
		 $.post("sortButtonController/delRow",{'id':row.id},
				 function(data){
				if(data.success){
			  		   $.messager.alert('提示信息', data.msg);
			  			location.reload();   
			  		}else{
			  			 $.messager.alert('错误信息', '删除数据失败，请重新删除！');
			  		}	  
		 },"json"); 
		        }
		 });
	      }else{
    	   $.messager.alert('提示信息', "请选择编辑数据！"); 
       }	
}
function saveRow(){	
	var id = $("#edit_row_id").val();
	var name = $("#edit_row_name").textbox('getValue');
	if(name==edit_row_name){
  	     $('#edit_row-dialog').dialog('close');
		 return false;
 	}else{ 
  		 $.post("sortButtonController/saveRow",{'id':id,'name':name},
 				 function(data){
 			  $.messager.alert('提示信息', data.msg);
 	 	     if(data.success){
 				   $('#edit_row-dialog').dialog('close');
 				  	location.reload();
 	 	     }
 				 },"json"); 
 	 	 }		
}
function getButtonsByType() {
	var tid =  $("#tid").combobox('getValue');
	if(""==tid){
		tid="0";
	 }
 	 $.post("dwrController/getButtonsByType",{tid:tid},
				 function(data){
		 $('#button_data').datagrid('loadData', data); 	
				 },"json"); 
	 
}

 