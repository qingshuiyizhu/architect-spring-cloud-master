var websocket;
var lockReconnect = false;// 避免重复连接

// 当前试题的答案
var now_answer;
// 当前试题的分数
var now_score;
// 是否可以提交答案;
var is_reply = false;
//是否隐藏解析(竞赛结束不隐藏)
var no_define = true;


// 页面初始化
$(function() {
	connect();
	// 监控选项点击
	reply();
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

// webSocket 通信
function connect() {
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://" + path + "ws?username=" + username);
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
	websocket.send(data);
}

// 判断调用方法
function operate(data) {
	switch (data.code) {
	case 'exam': // 处理试题信息
		exam(data.obj);
		break;
	case 'eager_begin': // 开始抢答，显示抢答按钮
		eager_begin();
		break;
	case 'eagerResult': // 抢答结果
		eagerResult(data.success);
		break;
	case 'showdefine': // 显示解析
		showdefine(data.msg);
		break;
	case 'restart': // 重新开始，刷新浏览器
		restart();
		break;
	case 'exam_null': // 最后一题
		exam_null();
		break;
	case 'msg': // 显示消息
		msg(data.msg);
		break;
	default:
		break;
	}

}

//提示已经是最后一题了
function exam_null() {
	// 将抢答按钮隐藏
	$(".btns button").hide();
	$(".msg").html("");
	// 将说明标题隐藏
	$(".explain").hide();
	// 将标题隐藏 
	$(".title").hide();
	$("#exam_content").html("");
	var str = "<h1>本轮竞赛已经结束！</h1><h3>可在大屏幕上的排行榜查看竞赛得分排名情况</h3>";
	$("#exam_content").append(str);
	$("#btn_previous").show();
	//显示解析
	no_define = false;
}

// 重新开始，刷新浏览器
function restart() {
	window.location.reload();
}

// 处理试题信息
function exam(data) {
	// 将抢答按钮隐藏
	$(".btns button").hide();
	$(".msg").html("");
	// 将说明标题隐藏
	$(".explain").hide();
	// 将标题显示
	$(".title").show();
	// 1.将正确答案暂存
	now_answer = data.answer;
	// 2.将分数暂存
	now_score = data.score;
	// 3.显示试题 title(题目) toptions(选项)
	// 分割选项
	var options = data.toptions.split("|"); // 切割选项
	var str = "<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + data.title
			+ "</p>";
	str += "<ul>";
	var codeNum = 65; // 获得A的ascii码
	for (var i = 0; i < options.length; i++) {
		codeChar = String.fromCharCode(codeNum); // ascii码转换为字母
		str += "<li><a href=\"javascript:void(0);\" class='option'>" + "<span>"
				+ codeChar + "</span>" + "." + options[i] + "</a></li>";
		codeNum++;
	}
	str += "</ul>";
	if (data.define != undefined) {
		str += "<p class=\"define\" style=\"font-size:15px;\">【答案解析】：" + data.define + "</p>";
	}
	$("#exam_content").html("");
	$("#exam_content").append(str);
	
	if(!no_define){
		$(".define").show();
	}
}
// 显示解析
function showdefine(data) {
	$(".btns button").hide();
	$(".msg").html("");
	$(".define").show();
	var str = "<p style=\"color:red;\">" + data + "</p>"
	$("#exam_content").append(str);

}
// 显示信息
function msg(message) {

}
// 抢答
function eager() {
	send("first");
}
function eager_begin() {
	$(".btns button").show();
}
// 提交答案 -由系统监控
function reply() {
	$(document).on("click", ".option", function(event) {
		// 可以提交答案
		if (is_reply) {
			is_reply = false;
			// 取得答案
			var $this = $(event.target);
			// var key = key; //A,B,C,D
			var key = $this.find('span').text();
			send(key);
			// 进行答案对比
			if (key == now_answer) {
				// 答对
				console.log(0 - now_score);
			} else {
				// 答错
				console.log("答错了");
			}

		}
	});

}

// 抢答结果
function eagerResult(data) {
	$(".btns button").hide();
	var str = "";
	if (data) {
		str += "<span>恭喜你抢到啦！请作答题目</span>";
		is_reply = true;
	} else {
		str += "<span>很遗憾，继续加油，请等待第一个抢答的学员作答</span>";
	}
	$(".msg").append(str);
}