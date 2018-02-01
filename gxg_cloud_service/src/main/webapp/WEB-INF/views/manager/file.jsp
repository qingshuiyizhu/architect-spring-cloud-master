<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; multipart/form-data;charset=UTF-8">
<title>文件管理</title>


<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/default/easyui.css" charset="utf-8"/>
<link rel="stylesheet" href="static/framework/jquery-easyui-1.5.2/themes/icon.css"/> 

<script type="text/javascript" src="static/framework/jquery-1.8.0.min.js"></script> 
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="static/framework/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>


<link rel="stylesheet" href="static/framework/zTree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<!-- <script type="text/javascript" src="static/framework/zTree/js/jquery.min.js"></script> -->
<script type="text/javascript" src="static/framework/zTree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="static/framework/zTree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="static/framework/zTree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="static/framework/jquery-from.js"></script>

<SCRIPT type="text/javascript">
$(function(){
	$('#uploadFile-dialog').dialog('close');
	$('#creatDir-dialog').dialog('close'); 
    $('#sendFile-dialog').dialog('close'); 	
	 init();
	 showMachines();
});
	var setting = {
	 	view: {
		 	addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,  
	 		selectedMulti: false
		},
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		edit: {
	  	enable:true,
		  showRenameBtn: false,  
	  /* 	showRemoveBtn: showRemoveBtn, */
	 /* 	showRemoveBtn:true， */
		/* removeTitle:'删除' */
			
		}, 
		callback: {  
			beforeEditName: beforeEditName,
	     /*    beforeRename:  beforeRename,   */
	      /*    onClick: zTreeOnClick, //单击事件  */ 
	   /*    	beforeRename : beforeRename,  */// rename 前验证
		/* 	onRename : zTreeOnRename, */ // rename 后处理
	       /*  onRemove: zTreeOnRemove, //移除事件  
	        onRename: onRename //修改事件   */
	        beforeRemove:beforeRemove,
	        onRemove: zTreeOnRemove  
	    } 
	};
	function showRemoveBtn(treeId, treeNode) {
		return !treeNode.isFirstNode;
	}
	function showRenameBtn(treeId, treeNode) {
		return false;
	}
	
	function showAddBtn(treeId, treeNode) {
		return false;
	}
	
	function beforeEditName(treeId, treeNode) {
		/* className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.selectNode(treeNode);
		setTimeout(function() {
			if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
				setTimeout(function() {
					zTree.editName(treeNode);
				}, 0);
			}
		}, 0); */
		return false;
	}
    function beforeRemove(treeId, treeNode) { 
    	if (treeNode == null) {
	  		 $.messager.alert('提示信息', "请先选择一个节点!");  	
	  		return false;
		}
		
		if (treeNode.name.indexOf("root_") > -1) {
			 $.messager.alert('提示信息', "根节点不能删除!");  	
			 return false;
		}

		if (treeNode.isParent && treeNode.children != null && treeNode.children.length != 0) {
			 $.messager.alert('提示信息', "请先清空子节点!");  	
			 return false;
		}
		
	/* 	var sign = false;
		 $.messager.confirm("删除操作","确认删除["+ treeNode.fileName+"]吗？",function (r) {  
	 	        if (r) {  
	 	        	return true;
	       //   sign = true;
		        }else{
		        	  return false;
		        }
		 });
		// alert("---"+sign);
	  return false; */
         return  confirm("确认删除" + treeNode.name + " 吗？");  
    }  
	
    function beforeRename(treeId, treeNode, newName, isCancel) {  
        if (newName.length == 0) {  
        	  $.messager.alert('提示信息', "节点名称不能为空!");
             return false;  
        }  
        
        return true;  
    }  
	 
    function zTreeOnRename(e, treeId, treeNode, isCancel) {  
        //需要对名字做判定的，可以来这里写~~  
    	// alert("重命名");
    	 return false;
    } 
    
	/*================================ remove begin ================================================*/

	/*
	 *   删除节点
	 */
	function zTreeOnRemove(e, treeId, treeNode) {
	  var path = treeNode.path+treeNode.fileName;
	  
	  $.post("dwrController/zTreeOnRemove",{path:path},
				 function(data){
		  $.messager.alert('提示信息', data);  
      	  init();
		 	     },"json");	
 	}

	/*================================ remove end ================================================*/
   
	/* var zNodes =[
	{id:1, pId:0, name:"[core] 基本功能 演示", open:true},
	{id:101, pId:1, name:"最简单的树 --  标准 JSON 数据",path:"fffffffffff"},
	{id:102, pId:1, name:"最简单的树 --  简单 JSON 数据",path:"fffffffffff"},
	{id:103, pId:1, name:"不显示 连接线",path:"fffffffffff"},
	{id:104, pId:1, name:"不显示 节点 图标",path:"fffffffffff"},
	{id:108, pId:1, name:"异步加载 节点数据",path:"fffffffffff"},
	{id:109, pId:1, name:"用 zTree 方法 异步加载 节点数据",path:"fffffffffff"},
	{id:110, pId:1, name:"用 zTree 方法 更新 节点数据",path:"fffffffffff"},
	{id:111, pId:1, name:"单击 节点 控制",path:"fffffffffff"},
	{id:112, pId:1, name:"展开 / 折叠 父节点 控制",path:"fffffffffff"},
	{id:113, pId:1, name:"根据 参数 查找 节点",path:"fffffffffff"},
	{id:114, pId:1, name:"其他 鼠标 事件监听",path:"fffffffffff"},

	{id:2, pId:0, name:"[excheck] 复/单选框功能 演示", open:false},
	{id:201, pId:2, name:"Checkbox 勾选操作"},
	{id:206, pId:2, name:"Checkbox nocheck 演示"},
	{id:207, pId:2, name:"Checkbox chkDisabled 演示"},
	{id:208, pId:2, name:"Checkbox halfCheck 演示"},
	{id:202, pId:2, name:"Checkbox 勾选统计"},
	{id:203, pId:2, name:"用 zTree 方法 勾选 Checkbox"},
	{id:204, pId:2, name:"Radio 勾选操作"},
	{id:209, pId:2, name:"Radio nocheck 演示"},
	{id:210, pId:2, name:"Radio chkDisabled 演示"},
	{id:211, pId:2, name:"Radio halfCheck 演示"},
	{id:205, pId:2, name:"用 zTree 方法 勾选 Radio"},

	{id:3, pId:0, name:"[exedit] 编辑功能 演示", open:false},
	{id:301, pId:3, name:"拖拽 节点 基本控制"},
	{id:302, pId:3, name:"拖拽 节点 高级控制"},
	{id:303, pId:3, name:"用 zTree 方法 移动 / 复制 节点"},
	{id:304, pId:3, name:"基本 增 / 删 / 改 节点"},
	{id:305, pId:3, name:"高级 增 / 删 / 改 节点"},
	{id:306, pId:3, name:"用 zTree 方法 增 / 删 / 改 节点"},
	{id:307, pId:3, name:"异步加载 & 编辑功能 共存"},
	{id:308, pId:3, name:"多棵树之间 的 数据交互"},

	{id:4, pId:0, name:"大数据量 演示", open:false},
	{id:401, pId:4, name:"一次性加载大数据量"},
	{id:402, pId:4, name:"分批异步加载大数据量"},
	{id:403, pId:4, name:"分批异步加载大数据量"},

	{id:5, pId:0, name:"组合功能 演示", open:false},
	{id:501, pId:5, name:"冻结根节点"},
	{id:502, pId:5, name:"单击展开/折叠节点"},
	{id:503, pId:5, name:"保持展开单一路径"},
	{id:504, pId:5, name:"添加 自定义控件"},
	{id:505, pId:5, name:"checkbox / radio 共存"},
	{id:506, pId:5, name:"左侧菜单"},
	{id:507, pId:5, name:"下拉菜单"},
	{id:509, pId:5, name:"带 checkbox 的多选下拉菜单"},
	{id:510, pId:5, name:"带 radio 的单选下拉菜单"},
	{id:508, pId:5, name:"右键菜单 的 实现"},
	{id:511, pId:5, name:"与其他 DOM 拖拽互动"},
	{id:512, pId:5, name:"异步加载模式下全部展开"},

	{id:6, pId:0, name:"其他扩展功能 演示", open:false},
	{id:601, pId:6, name:"隐藏普通节点"},
	{id:602, pId:6, name:"配合 checkbox 的隐藏"},
	{id:603, pId:6, name:"配合 radio 的隐藏"}
	]; */

 function init(){
		
		  $.post("dwrController/getFileTozTree",{},
					 function(data){
	 	  	 $.fn.zTree.init($("#treeDemo"), setting, data); 
			 	     },"json");	
	 	/*  $.post("fileController/getzTree",{},
				 function(data){
			    console.log(data);
				$.fn.zTree.init($("#treeDemo"), setting, data);
		 	     },"json");	 */	
}

var newCount = 1;	
	//编辑
	function addHoverDom(treeId, treeNode) {
	 	var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		if(treeNode.isParent){	
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
			addStr += "<span class='button edit' id='editBtn_" + treeNode.tId
				+ "' title='edit node' onfocus='this.blur();'></span>";	
	 	sObj.after(addStr);
		//绑定新建文件夹
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			//新建文件夹的路径
			 $("#creatDirPath").val(treeNode.path+treeNode.name);
		     $('#creatDir-dialog').dialog('open'); 
		  });
	 
		
	 	//绑定文件上传
		var btn = $("#editBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			//var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			//zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
			//上传文件
			if(treeNode.isParent){
			 	 $("#uploadPath").val(treeNode.path+treeNode.name);
			     $('#uploadFile-dialog').dialog('open'); 
			}
		 	return false;
		  });
	 	
		}
	};
	function removeHoverDom(treeId, treeNode) {
	 	  	$("#editBtn_"+treeNode.tId).unbind().remove();
	 	  	$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	 
var num = 2;
function _add(){
    var tb = document.getElementById("tb");
    //写入一行
    var tr = tb.insertRow();
    //写入列
    var td = tr.insertCell();
     //写入数据
     td.innerHTML="文件：";
     //再声明一个新的td
    var td2 = tr.insertCell();
    //写入一个input
    td2.innerHTML='<input type="file" name="files"/><button onclick="_del(this);">删除</button>';
  }
  function _del(btn){
    var tr = btn.parentNode.parentNode;
    //alert(tr.tagName);
    //获取tr在table中的下标
    var index = tr.rowIndex;
    //删除
    var tb = document.getElementById("tb");
    tb.deleteRow(index);
  }
  
//上传文件
  function uploadFile(){
    //遍历所的有文件
    var files = document.getElementsByName("files");
    if(files.length==0){
    	$.messager.alert("没有可以上传的文件");
      return false;
    }
    for(var i=0;i<files.length;i++){
      if(files[i].value==""){
    	  $.messager.alert("第"+(i+1)+"个文件不能为空");
        return false;
      }
    }
    var path =	$("#uploadPath").val();
   
//  	document.getElementById("formid").submit();
 
    /** 验证文件是否导入成功  */ 
    var form = $("#formid"); 
 var options = {  
 url:'/dwrController/uploadFile', //上传文件的路径  
 type:'post',
 success:function(data){ 
   	 $('#uploadFile-dialog').dialog('close');
	 init(); 
	 
	/*  alert(data);
  console.log(data); */ 
  //....       //异步上传成功之后的操作
  }
 };  
 form.ajaxSubmit(options); 
  /*   $("#formid").ajaxForm(function(data){  
    	alert('ddd');
        if(data=="0"){  
            alert("提交成功！");  
       	 $('#uploadFile-dialog').dialog('close');
    	 init();
        }  
    });        */
 
   
	  	
   /*  for(var i=0;i<files.length;i++){
     	 dwrService.uploadFile(files[i],files[i].value,path,function(result){  
     		 	 $('#uploadFile-dialog').dialog('close');
     		 	init();
     	 }); 	
    } */
     
  }
  
  /**
	 * 删除选中节点
	 */
	function removeNodes(){
	  
	    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	    console.log(treeObj);
	    //选中节点
		var nodes = treeObj.getSelectedNodes();
		 console.log(nodes);
		for (var i=0;i < nodes.length; i++) 
		{
			 console.log(nodes[i]);
		    //删除选中的节点
			treeObj.removeNode(nodes[i]);
		}
	}
  
  
  function creatDir(){
	  var path = $("#creatDirPath").val();
	  var name = $("#creatDirPath_name").textbox('getValue');
 	  $.post("dwrController/creatDir", {path:path,name:name}, function(data) {
		  if(data=="0"){
			  $('#creatDir-dialog').dialog('close'); 
			 	init();
		  }
	 
  	 	}, "json");
	   	 
  }
  
  
  function delFile(){  
      var zTree = $.fn.zTree.getZTreeObj("treeDemo");  
                var nodes=zTree.getChangeCheckedNodes(true);  
              if(nodes.length==0){  
            	  $.messager.alert('提示信息', "请选择文件");     
                   return false;  
               }else{
             	 $.messager.confirm("删除操作","确认删除吗？",function (r) {  
             		 if (r) {  

                         for (var i = 0; i < nodes.length; i++) {  
                            var halfCheck = nodes[i].getCheckStatus();  
                             if (!halfCheck.half){ 
                            	  	$.post("dwrController/zTreeOnRemove", {path:nodes[i].path}, function(data) {
                            	  		 $('#uploadFile-dialog').dialog('close');
                            	 	 	 init();
                            	  	}, "json");
                            	 
                            	 
                             }  
                          
                        } 
                         $.messager.alert('提示信息', "删除成功！");  
                    	  init();
			        }
             		 });
                 }             
     
   } 
  function showMachines() {
		$('#machines')
				.datagrid(
						{
							singleSelect : false,
							idField : 'id',
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
										field : 'name',
										title : '设备名称',
										width : 200,
										align : 'center'
									}
									  ] ]
	 					});
	}
  
 //文件同步 将要下发的文件先同步到数据库中，客户端启动时自动拉取同步数据 
 function sendFile(){
	 //判断是否选择文件
	  var zTree = $.fn.zTree.getZTreeObj("treeDemo");  
      var nodes=zTree.getChangeCheckedNodes(true);  
   
     if(nodes.length==0){  
    	 $.messager.alert('信息', '请选择数据！');	
         return false;  
     }
   
     $.post("machineController/getRows3", {}, function(data) {
	 	$('#machines').datagrid('loadData', data);
  	 	}, "json");	
	  $('#sendFile-dialog').dialog('open'); 		
  			}
 
 //取得
 function saveSendFile(){
	 var rows = $('#machines').datagrid('getSelections');
	 if(rows.length<=0){
		 	$.messager.alert('信息', '请选择数据！');	
		 	return;
	 }
		 var JsonData = {
				    pId:"",
					name : "",
		  		    path:"",
		  		    schedule:"",
		  		    isdel:"",
		  		    cid:"",
		  	  	}
	       var ClientFiles = [];  
		    
		     var zTree = $.fn.zTree.getZTreeObj("treeDemo");  
		     var nodes=zTree.getChangeCheckedNodes(true);  
			 for(var i=0; i<rows.length; i++){
		 	 	//循环
		 	  for (var j = 0; j < nodes.length; j++) {  
				         var halfCheck = nodes[j].getCheckStatus();  
				          if (!halfCheck.half && nodes[j].isParent==false ){
				             //result += nodes[i].id +','; 
				               var JsonData = new Object();
				               JsonData.pId=rows[i].id;
				               JsonData.cid=rows[i].id;
				               JsonData.name=nodes[j].fileName;
				               JsonData.path =nodes[j].path;
				               JsonData.schedule=0,
				               JsonData.isdel=0,
				               ClientFiles.push(JsonData);
				          }  
				      }    
		 	 }
	 		 var obj = JSON.stringify(ClientFiles);
		     console.log("保存文件下发列表："+obj);
		//	
		     $.post("clientFileController/saveRows", {clientFiles:obj}, function(data) {
		    	 $('#sendFile-dialog').dialog('close'); 	
		    	 	}, "json");	
	 
 }
 
 
</SCRIPT>
</HEAD>

<body>
<div style="padding:5px;background:#fafafa;width:200px;border:1px solid #ccc">
<!-- 	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="delFile()">删除</a> -->
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-reload" onclick="init()">刷新</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="sendFile()">下发</a>
</div>
<h1>云联控3.0中控端文件树</h1>

<ul id="treeDemo" class="ztree"></ul>
<div id="uploadFile-dialog" buttons="#uploadFile-buttons" data-options="modal:true" class="easyui-dialog" title="文件上传" data-options="iconCls:'icon-edit'" style="width:400px;height:400px;padding:10px">
	<form id ="formid" action="dwrController/uploadFile" method="post" enctype="multipart/form-data" >
	    <input type="hidden" id ="uploadPath" name = "path" value=""/> 
	 <table id="tb">
    <tr>
      <td>
       文件：
      </td>
      <td>
        <input type="file" name="files">
        <button onclick="_del(this);">删除</button>
      </td>
    </tr>
  </table> 
<!-- <input type="submit" value="上传"/> -->
 	</form>
	  	    
</div>

 	<div id="uploadFile-buttons">
 	    <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="javascript:_add()">添加文件</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:uploadFile()">上传</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#uploadFile-dialog').dialog('close')">取消</a>
	</div>
	
<!-- 新建文件夹名称 -->	
<div id="creatDir-dialog" buttons="#creatDir-buttons" data-options="modal:true" class="easyui-dialog" title="新建文件夹" data-options="iconCls:'icon-edit'" style="width:400px;height:400px;padding:10px">
  	        <input type="hidden" id ="creatDirPath" value=""/> 
	文件夹名称： <input id ="creatDirPath_name" class="easyui-textbox" name="creatDirPath_name" value="" style="width: 200px;"/>
	 
</div>
<div id="creatDir-buttons">
 	 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:creatDir()">确定</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#creatDir-dialog').dialog('close')">取消</a>
</div>
	<!-- 文件下发  -->
	
<div id="sendFile-dialog" buttons="#sendFile-buttons" data-options="modal:true" class="easyui-dialog" title="客户端列表" data-options="iconCls:'icon-edit'" style="width:400px;height:400px;padding:10px">
	<table id="machines"  data-options="fit:true"></table>	 
</div>
<div id="sendFile-buttons">
 	 	<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:saveSendFile()">确定</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#sendFile-dialog').dialog('close')">取消</a>
</div>	
	
	
