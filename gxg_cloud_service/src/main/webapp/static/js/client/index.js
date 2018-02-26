
$(function (){
	loadTableData();
	//tobtns();
});

function tobtns(){
	$('#tb_machines').on("click-row.bs.table",function(e, row, $element) {     
		 
		window.location.href="btns/"+row.id;
 	});	
	
}

//加载表格数据
function loadTableData(){
	 $('#tb_machines').bootstrapTable({
	        url: '/machineController/getRowsisv',         //请求后台的URL（*）
	        method: 'post',                      //请求方式（*）
	       // toolbar: '#toolbar',                //工具按钮用哪个容器
	        striped: true,                      //是否显示行间隔色
	        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	      //  pagination: true,                   //是否显示分页（*）
	         sortable: false,                     //是否启用排序
	         sortOrder: "asc",                   //排序方式
	       // queryParams: oTableInit.queryParams,//传递参数（*）
	         sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	       // pageNumber:1,                       //初始化加载第一页，默认第一页
	      //  pageSize: 10,                       //每页的记录行数（*）
	       // pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	     //    search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	      //  strictSearch: true,
	       // showColumns: true,                  //是否显示所有的列
	        showRefresh: true,                  //是否显示刷新按钮
	       // minimumCountColumns: 2,             //最少允许的列数
	      //  clickToSelect: true,                //是否启用点击选中行
	        // height: 100,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
	      //  showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	        cardView: false,                    //是否显示详细视图
	        detailView: false,                   //是否显示父子表
	        columns: [{
	            field: 'name',
	            title: '设备名称',
	            align:'center' 
	        }, {
	            field: 'heartbeat',
	            title: '状态',
	            align:'center',
	            formatter:function(value){
	             	if(0==value){
	            		return "离线";
	            	}else if(1==value){
	            		return "在线";
	            	}
	            }
	        },{
	            field: 'action',
	            title: '操作',
	            align:'center',
	            formatter:function(value,row){
	              	str = '<button type="button" class="btn1 btn btn-success btn-lg" onclick="systemOrder('+row.id+','+row.heartbeat+',\'addvoice\')">音量+</button>';
	             	str +='<button type="button" class="btn1 btn btn-danger btn-lg" onclick="systemOrder('+row.id+','+row.heartbeat+',\'quietvoice\')">静音</button>';
	            	str +='<button type="button" class="btn1 btn btn-info btn-lg"  onclick="systemOrder('+row.id+','+row.heartbeat+',\'lessvoice\')">音量-</button>';
	            	str +='<button type="button" class="btn1 btn btn-primary btn-lg"  onclick="systemOrder('+row.id+','+row.heartbeat+',\'restart\')">重启</button>';
	            	str +='<button type="button" class="btn1 btn btn-danger btn-lg"  onclick="systemOrder('+row.id+','+row.heartbeat+',\'shutdown\')">关机</button>';
	            	str +='<button type="button" class="btn1 btn btn-danger btn-lg"  onclick="systemOrder('+row.id+','+row.heartbeat+',\'destroy\')">退出</button>';  
	            	return str;
	               }
	        }]
	    }); 
 }
 



 //系统指令
 function systemOrder(id,state,order){
	 
	  if(0==state){
		   layer.alert('设备不在线，无法发送指令！', {
			  skin: 'layui-layer-molv' //样式类名
			  ,closeBtn: 0,
			  icon: 2,
			  anim: 4 //动画类型
			});
	 	 return;
	 } 
		
	  $.post("dwrController/systemOrder",{id:id,order:order},
				 function(data){
		  if("0"==data){
				 layer.alert('操作成功', {
					    skin: 'layui-layer-molv',
					    closeBtn: 0,
					    icon: 1,
					    anim: 4 //动画类型
					});
			    }
		 	     },"json");	 
 } 
 