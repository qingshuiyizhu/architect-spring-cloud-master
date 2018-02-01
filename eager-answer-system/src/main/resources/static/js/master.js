var websocket;
var lockReconnect = false;// 避免重复连接
// 当前试题编号
var now_exam_num = 0;

// 是否显示抢答按钮
var show_eager = true;

//是否隐藏解析(竞赛结束不隐藏)
var no_define = true;

$(function() {
	connect();
	controllbutton();
	
	// 禁用浏览器菜单事件
	$(document).bind("contextmenu",function(e){
		return false;
	});
	// 禁用浏览器缩放	
	$('body').bind('touchmove', function(event) {
	     // 如果这个元素的位置内有多个手指的话
	    if (event.targetTouches.length > 1) {
	    	event.preventDefault();// 阻止浏览器默认事件，重要 
	    }
	}, false); 	
});

// 控制按钮的显示和隐藏
function controllbutton() {
	if (now_exam_num == 0) {
		$("#btn_begin").show();
		$("#btn_restart").hide();
		$("#btn_previous").hide();
		$("#btn_next").hide();
		$("#btn_eager").hide();
	}else {
		$("#btn_restart").show();
		if(show_eager){
			$("#btn_eager").show();	
			$("#btn_next").hide();
		}else{
			$("#btn_next").show();
		}
	
	
	}
	

	// 监控
	$(document).ready(function() {
		$("button").click(function() {
			if (now_exam_num == 0) {
				// $("#btn_restart").hide();
				/*
				 * $("#btn_previous").hide(); $("#btn_next").hide();
				 */
				// $("#btn_begin").hide();
			} else {
				// $("#btn_begin").hide();
				// $("#btn_restart").show();
				/*
				 * $("#btn_previous").show(); $("#btn_next").show();
				 */
			}
		});
	});
}

function connect() {
  //检查浏览器是否支持WebSocket ('WebSocket' in window)
	    if(window.WebSocket){
	        console.log('This browser supports WebSocket');
	     }else{
	        console.log('This browser does not supports WebSocket');
	    }


	
	
	if ('WebSocket' in window) {
	      console.log("连接websocket成功"+"ws://" + path + "/ws?username=" + username);
           websocket = new WebSocket("ws://" + path + "/ws?username=" + username);
		
	} else if ('MozWebSocket' in window) {
		websocket = new MozWebSocket("ws://" + path + "/ws?username="
				+ username);
	} else {
		websocket = new SockJS("http://" + path + "/ws/sockjs?username="
				+ username);
	}
	// 打开连接
	websocket.onopen = function(event) {
		console.log("WebSocket消息:成功与服务器建立连接");
		heartCheck.reset().start();
	};
	// 接收消息
	websocket.onmessage = function(event) {
		// 如果获取到消息，心跳检测重置
		// 拿到任何消息都说明当前连接是正常的
		heartCheck.reset().start();

		var data = JSON.parse(event.data);
		console.log("WebSocket消息:收到服务端消息", data);
		operate(data);
	}
	// 断线重连
	websocket.onclose = function() {
		console.log("断线重新连接");
		if (lockReconnect)
			return;
		lockReconnect = true;
		// 没连接上会一直重连，设置延迟避免请求过多
		setTimeout(function() {
			connect();
			lockReconnect = false;
		}, 2000);
	};
	// 连接错误
	websocket.onerror = function() {
		console.log("连接错误-断线重新连接");
		connect();
	};

}
// 心跳检测
var heartCheck = {
	timeout : 20000,// 30秒
	timeoutObj : null,
	serverTimeoutObj : null,
	reset : function() {
		clearTimeout(this.timeoutObj);
		clearTimeout(this.serverTimeoutObj);
		return this;
	},
	start : function() {
		var self = this;
		this.timeoutObj = setTimeout(function() {
			// 这里发送一个心跳，后端收到后，返回一个心跳消息，
			// onmessage拿到返回的心跳就说明连接正常
			websocket.send("heartBeat");
			self.serverTimeoutObj = setTimeout(function() {// 如果超过一定时间还没重置，说明后端主动断开了
				websocket.close();// 如果onclose会执行reconnect，我们执行ws.close()就行了.如果直接执行reconnect
									// 会触发onclose导致重连两次
			}, self.timeout)
		}, this.timeout)
	}
}
// 给服务端发送数据
function send(data) {
	console.log("给服务端发送消息", data);
	websocket.send(data);
}

// 判断调用方法
function operate(data) {
	switch (data.code) {
	case 'restart': // 重新开始，刷新浏览器
		restart();
		break;
	case 'exam': // 处理试题信息
		exam(data.obj);
		break;
	case 'msg': // 显示消息
		msg(data.msg);
		break;
	case 'ranklist': // 显示排行榜
		ranklist(data.obj);
		break;
	case 'showdefine': // 显示解析
		showdefine(data.msg);
		break;
	case 'exam_null': // 已经是最后一题了
		exam_null();
		break;

	default:
		break;
	}

}
// 提示已经是最后一题了
function exam_null() {
	$(".title").hide();
	$("#exam_content").html("");
	var str = "<h1>本轮竞赛已经结束！</h1><h4>请主持人点击重新开始按钮进行下一轮比赛或者更换试题</h4>";
	$("#exam_content").append(str);
	$("#btn_previous").show();
	$("#btn_next").hide();
	//显示解析
	no_define = false;
}
// 重新开始，刷新浏览器
function restart() {
	window.location.reload();
}

// 开始竞赛
function exam_begin() {
	now_exam_num = 1;
	send(1);

	$("#btn_begin").hide();
	$("#btn_restart").show();

}

// 处理试题信息
function exam(data) {
	$(".title").show();
	$(".title").addClass("exam_title");
	// 1.显示试题 title(题目) toptions(选项)
	// 分割选项
	var options = data.toptions.split("|"); // 切割选项
	var str = "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + data.title
			+ "</p>";
	str += "<ul>";
	var codeNum = 65; // 获得A的ascii码
	for (var i = 0; i < options.length; i++) {
		codeChar = String.fromCharCode(codeNum); // ascii码转换为字母
		str += "<li>" + codeChar + "." + options[i] + "</li>";
		codeNum++;
	}
	str += "</ul>";
	if (data.define != undefined) {
		str += "<p class=\"define\" style=\"font-size: 0.475em;\">【答案解析】："
				+ data.define + "</p>";
	}
	$("#exam_content").html("");
	$("#exam_content").append(str);
	// 判断是否显示抢答按钮
	if (show_eager) {
		$("#btn_eager").show();
	}
	
	if(!no_define){
		$(".define").show();
	}
}
// 显示解析
function showdefine(data) {
	$("#btn_next").show();
	$(".msg").html("");
	$(".define").show();
	var str = "<p style=\"color:red; font-size: 0.675em;\">" + data + "</p>"
	$("#exam_content").append(str);

}
// 给学员端发送显示抢答按钮的指令
function eager_begin() {
	$("#btn_eager").hide();
	send("eager_begin");
}
// 显示信息
function msg(message) {

}
// 显示排行榜
function ranklist(data) {
	// name 名字
	// score 成绩
	var str = "";
	console.log(data);
	for (var i = 0; i < data.length; i++) {
		if (data[i].state == 1) {
			str += "<li>[<span>" + data[i].name + "</span>]的当前成绩为[<span> "
					+ data[i].score + " </span>]分</li>";
		} else if (data[i].state == 0) {
			str += "<li>[<span style=\"color:gray\">" + data[i].name
					+ "</span>]的当前成绩为[<span> " + data[i].score
					+ " </span>]分</li>";
		}
	}
	$("#ranklist").html("");
	$("#ranklist").append(str);
}
// 下一题
function next() {
	// 显示抢答按钮
	show_eager = true;
	// 当前试题 +1
	now_exam_num++;
	send(now_exam_num);
	if(no_define){
 		$("#btn_next").hide();
	}else{
		show_eager =false;	
	}
	
	
	
}
// 上一题

function previous() {
	// 不显示抢答按钮
	show_eager = false;
	$("#btn_eager").hide();
	$("#btn_next").show();
	// 当前试题 -1
	if (now_exam_num == 1) {
		alert("已经是第一题了");
		return;
	} else {
		now_exam_num--;
		send(now_exam_num);
	}
}
