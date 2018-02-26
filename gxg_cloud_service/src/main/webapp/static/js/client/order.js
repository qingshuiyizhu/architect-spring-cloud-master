var btns;
var color=["btn-success","btn-primary","btn-danger","btn-info","btn-danger","btn-warning"];
$(function(){
	 loadBtns();	
	 loadsortBtns();	
});
function loadBtns(){
	 $.post("buttonController/findRows1",{},
			 function(data){
		     btns=data;
	  		 },"json"); 
}

function loadsortBtns(){	 
	$.post("sortButtonController/getRows",{'load':'all'},
			 function(data){
		str='';
		var i=0;
		
		$.each(data.rows, function(i,n){
			str+='<div class="btn-group"><a href="javascript:void(0);" onclick="getbtns('+n.id+')" class="buttombtn1 btn '+color[i]+' btn-lg" role="button">'+n.name+'</a></div>';
		if(i==5){
			i=0;
		}else{
			i++;
		}
		}); 
	 	$("#sortbuttons").html("");
		$("#sortbuttons").append(str);
		getbtns(data.rows[0].id);
	  		 },"json"); 
	
}

function getbtns(id){
	    $("#buttons").html("");
 	 	  $.post("sortButtonController/getCont",{'id':id},
				 function(data){
			  if("0"==data){
				  layer.alert('没有按钮数据！', {
					    skin: 'layui-layer-lan',
					    closeBtn: 0,
					    icon: 2,
					    anim: 4 //动画类型
					});
				  return; 
			  }
			  var str="";
	 	 $.each(data,function(i,n){
			 if(n.type==1){
				   var name="";
					  $.each(btns,function(j,m){
							if(n.bid==m.id){
								name=m.name;
								return;
							}   
					  });
				      str+='<button type="button" style="width: 180px; height: 80px;  position: absolute; left: '+n.left+'px; top: '+n.top+'px;" class="btn2 btn btn-danger btn-lg" onclick="issue('+n.bid+')">'+name+'</button>'; 	 
			 }  
	 	 });
		 		$("#buttons").append(str);
	 	  		 },"json"); 
				
}

function issue(id){
	
	 $.post("dwrController/issue",{id:id},
			 function(data){
		 if('0'==data){
			 layer.alert('指令发送成功！', {
				    skin: 'layui-layer-molv',
				    closeBtn: 0,
				    icon: 1,
				    anim: 4//动画类型
			 	    
				});	
		}else {
	 		 layer.alert(data, {
				    skin: 'layui-layer-molv',
				    closeBtn: 0,
				    icon: 2,
				    anim: 4//动画类型
			 	    
				});	
		}
	 	     },"json");	 
	 
 	
}