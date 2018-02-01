package org.cloud.microservice.eager.websocket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cloud.microservice.eager.entity.ExamPaper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.gson.GsonBuilder;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Component
public class ExamWebSocketHandler implements WebSocketHandler {
 
	private String username;
	private static List<Student> students = new ArrayList<Student>();
	private static Student master = null;
	private JsonMsg jsonMsg;
	private static List<Rank> rankLists = new ArrayList<Rank>();
	private static String eager = null;
	private static ExamPaper examPaper = null;
	/**
	 * 建立连接后
	 */
	
	public synchronized void connection(WebSocketSession session){
		username = (String) session.getAttributes().get("username");
		System.out.println(username + "开始连接！");
		if ("master".equals(username)) {
			if (master == null) {
				master = new Student();
				master.setName("master");
				master.setSession(session);
				System.out.println("日志信息：" + "大屏幕登录成功");

			} else {
				master = new Student();
				master.setName("master");
				master.setSession(session);
				System.out.println("日志信息：" + "大屏幕重新登录成功");
			}
			// 发送排行榜信息
			jsonMsg = new JsonMsg();
			jsonMsg.setCode("ranklist");
			jsonMsg.setMsg("排行榜信息");
			jsonMsg.setObj(rankLists);
			sendMessageToMaster(jsonMsg);
			if (examPaper != null) {
				jsonMsg = new JsonMsg();
				jsonMsg.setCode("exam");
				jsonMsg.setMsg("试题信息");
				jsonMsg.setObj(examPaper);
				sendMessageToMaster(jsonMsg);
			}
		} else {
			boolean marker = true;
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getName().equals(username)) {
					students.get(i).setSession(session);
					marker = false;
					System.out.println("日志信息：" + username + "重新登录成功");
					break;
				}
			}
			if (marker) {
				Student student_new = new Student();
				student_new.setName(username);
				student_new.setSession(session);
				students.add(student_new);
				System.out.println("日志信息：" + student_new.getName() + "登录成功");
			}
			// 给学员发送当前题目
			if (examPaper != null) {
				jsonMsg = new JsonMsg();
				jsonMsg.setCode("exam");
				jsonMsg.setMsg("试题信息");
				jsonMsg.setObj(examPaper);
				sendMessageToStudent(session, jsonMsg);
			}

			// 初始化排行榜
			boolean sign = true;
			for (int i = 0; i < rankLists.size(); i++) {
				if (rankLists.get(i).getName().equals(username)) {
					rankLists.get(i).setState(1);
					sign = false;
					break;
				}
			}

			if (sign) {
				rankLists.add(new Rank(username));
			}
			jsonMsg = new JsonMsg();
			jsonMsg.setCode("ranklist");
			jsonMsg.setMsg("排行榜信息");
			jsonMsg.setObj(rankLists);
			sendMessageToMaster(jsonMsg);
		}
	}
	
	public void reconnection(WebSocketSession session){
		username = (String) session.getAttributes().get("username");
		System.out.println(username + "开始连接！");
		if ("master".equals(username)) {
			if (master == null) {
				master = new Student();
				master.setName("master");
				master.setSession(session);
				System.out.println("日志信息：" + "大屏幕登录成功");

			} else {
				master = new Student();
				master.setName("master");
				master.setSession(session);
				System.out.println("日志信息：" + "大屏幕重新登录成功");
			}
			// 发送排行榜信息
			jsonMsg = new JsonMsg();
			jsonMsg.setCode("ranklist");
			jsonMsg.setMsg("排行榜信息");
			jsonMsg.setObj(rankLists);
			sendMessageToMaster(jsonMsg);
			if (examPaper != null) {
				jsonMsg = new JsonMsg();
				jsonMsg.setCode("exam");
				jsonMsg.setMsg("试题信息");
				jsonMsg.setObj(examPaper);
				sendMessageToMaster(jsonMsg);
			}
		} else {
			boolean marker = true;
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getName().equals(username)) {
					students.get(i).setSession(session);
					marker = false;
					System.out.println("日志信息：" + username + "重新登录成功");
					break;
				}
			}
			if (marker) {
				Student student_new = new Student();
				student_new.setName(username);
				student_new.setSession(session);
				students.add(student_new);
				System.out.println("日志信息：" + student_new.getName() + "登录成功");
			}
			// 给学员发送当前题目
			if (examPaper != null) {
				jsonMsg = new JsonMsg();
				jsonMsg.setCode("exam");
				jsonMsg.setMsg("试题信息");
				jsonMsg.setObj(examPaper);
				sendMessageToStudent(session, jsonMsg);
			}

			// 初始化排行榜
			boolean sign = true;
			for (int i = 0; i < rankLists.size(); i++) {
				if (rankLists.get(i).getName().equals(username)) {
					rankLists.get(i).setState(1);
					sign = false;
					break;
				}
			}

			if (sign) {
				rankLists.add(new Rank(username));
			}
			jsonMsg = new JsonMsg();
			jsonMsg.setCode("ranklist");
			jsonMsg.setMsg("排行榜信息");
			jsonMsg.setObj(rankLists);
			sendMessageToMaster(jsonMsg);
		}
	}
	
	public  void afterConnectionEstablished(WebSocketSession session) throws Exception {
		connection(session);
	}

	/**
	 * 消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	 */
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0) {
			return;
		} else {
			String msg = message.getPayload().toString();
			username = (String) session.getAttributes().get("username");
			System.out.println("日志信息：" + "收到" + username + "的消息：" + msg);
			if ("heartBeat".equals(msg)) {
			//	reconnection(session);
				jsonMsg = new JsonMsg();
				jsonMsg.setSuccess(true);
				jsonMsg.setCode("heartBeat");
				jsonMsg.setMsg("返回心跳包");
				sendMessageToStudent(session, jsonMsg);
				return;
			} else if ("master".equals(username)) {
				if ("eager_begin".equals(msg)) {
					jsonMsg = new JsonMsg();
					jsonMsg.setCode("eager_begin");
					jsonMsg.setMsg("开始抢答");
					sendMessageToStudents(jsonMsg);
					return;
				} else if ("0".equals(msg)) {
					// 重新开始
					for (int i = 0; i < rankLists.size(); i++) {
						rankLists.get(i).setScore(0);
					}
					examPaper = null;
					System.out.println("重新开始-刷新所有浏览器");
					jsonMsg = new JsonMsg();
					jsonMsg.setCode("restart");
					jsonMsg.setMsg("重新开始-刷新浏览器");
					sendMessageToStudents(jsonMsg);
					//Thread.sleep(1000);
					sendMessageToMaster(jsonMsg);
				} else {
					int num = 1;
					try {
						num = Integer.parseInt(msg);
						if (num < 1) {
							num = 1;
						}
					} catch (Exception e) {
					}
					
					examPaper = ExcelIn(num);
					jsonMsg = new JsonMsg();
					if(examPaper !=null){
				 		jsonMsg.setCode("exam");
						jsonMsg.setMsg("试题信息");
						jsonMsg.setObj(examPaper);
						// 广播题目
						broadcast(jsonMsg);
					}else{
						//已经是最后一题了
					 	jsonMsg.setCode("exam_null");
						jsonMsg.setMsg("试题信息");
 						broadcast(jsonMsg);			 	
					}
				 	
					/*
				 	// 查询数据库
					Page<ExamPaper> page = examPaperService.getPage(num, 1);
					// 将当前题目暂存
					if(page.getSize()>0){
						examPaper = page.getContent().get(0);
						jsonMsg = new JsonMsg();
						jsonMsg.setCode("exam");
						jsonMsg.setMsg("试题信息");
						jsonMsg.setObj(examPaper);
						// 广播题目
						broadcast(jsonMsg);
					}
					
					
					*/
		 			// 将抢答结果置空
					eager = null;
				}
			} else {
				// 学生端
				if ("first".equals(msg)) {
					// 抢答
					System.out.println("日志信息：" + username + "抢答题目");
					jsonMsg = new JsonMsg();
					jsonMsg.setCode("eagerResult");
					jsonMsg.setMsg("抢答结果信息");
					if (eager == null) {
						eager = username;
						jsonMsg.setObj(username + "抢到啦！");
						jsonMsg.setSuccess(true);
					} else {
						jsonMsg.setSuccess(false);
					}
					sendMessageToStudent(session, jsonMsg);
				} else {
					if (msg.equals(examPaper.getAnswer())) {
						rankList(username, examPaper.getScore());
					} else {
						// 计算分数
						rankList(username, (0 - examPaper.getScore()));
					}
					// 发送显示解析的命令
					jsonMsg = new JsonMsg();
					jsonMsg.setCode("showdefine");
					jsonMsg.setMsg(username + "的抢答的选项结果为:" + msg);
					broadcast(jsonMsg);
				}
			}
		}

	}

	private void rankList(String username, int score) {
		for (int i = 0; i < rankLists.size(); i++) {
			if (username.equals(rankLists.get(i).getName())) {
				score = rankLists.get(i).getScore() + score;
				if(score<0){
					score=0;
				}
				rankLists.get(i).setScore(score);
				break;
			}
		}
		// 排序
		Rank temp;
		for (int i = 0; i < rankLists.size(); i++) {
			for (int j = 0; j < rankLists.size() - 1 - i; j++) {
				if (rankLists.get(j).getScore() < rankLists.get(j + 1).getScore()) {
					temp = rankLists.get(j);
					rankLists.set(j, rankLists.get(j + 1));
					rankLists.set(j + 1, temp);
				}
			}
		}
		jsonMsg = new JsonMsg();
		jsonMsg.setCode("ranklist");
		jsonMsg.setMsg("排行榜信息");
		jsonMsg.setObj(rankLists);
		sendMessageToMaster(jsonMsg);
	}

	/**
	 * 消息传输错误处理
	 */
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			session.close();
		}
		String username = "";
		// 移除会话
		if (master.getSession().getId().equals(session.getId())) {
			System.out.println("Error：" + "主持大屏退出学习系统！");
			master = new Student();
		} else {
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getSession().getId().equals(session.getId())) {
					System.out.println("Error：" + students.get(i).getName() + "退出学习系统！");
					username = students.get(i).getName();
					students.remove(i);
					break;
				}
			}
		}
		for (int i = 0; i < rankLists.size(); i++) {
			if (username.equals(rankLists.get(i).getName())) {
				rankLists.get(i).setState(0);
				jsonMsg = new JsonMsg();
				jsonMsg.setCode("ranklist");
				jsonMsg.setMsg("排行榜信息");
				jsonMsg.setObj(rankLists);
				sendMessageToMaster(jsonMsg);
			}
		}
	}

	/**
	 * 关闭连接后
	 */
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		username = (String) session.getAttributes().get("username");
		System.out.println("Websocket:" + username + "已经关闭");
		// JsonMsg message = new JsonMsg();
		String username = "";
		if (session.getId().equals(master.getSession().getId())) {
			// message.setMsg("主持大屏退出学习系统！");
			System.out.println("日志信息：" + "主持大屏退出学习系统！");
			master = new Student();
		} else {
			for (int i = 0; i < students.size(); i++) {
				if (students.get(i).getSession().getId().equals(session.getId())) {
					username = students.get(i).getName();
					System.out.println("日志信息：" + students.get(i).getName() + "退出学习系统！");
					students.remove(i);
					break;
				}
			}
		}
		for (int j = 0; j < rankLists.size(); j++) {
			if (username.equals(rankLists.get(j).getName())) {
				rankLists.get(j).setState(0);
				jsonMsg = new JsonMsg();
				jsonMsg.setCode("ranklist");
				jsonMsg.setMsg("排行榜信息");
				jsonMsg.setObj(rankLists);
				sendMessageToMaster(jsonMsg);
			}
		}

	}

	/**
	 * 给所有在线用户发送消息
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void broadcast(JsonMsg message) {
		sendMessageToMaster(message);
		sendMessageToStudents(message);
	}

	public void sendMessageToStudents(JsonMsg message) {
		final TextMessage msg = new TextMessage(
				new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message));
		// 多线程群发
		int i = 0;
		while (i < students.size()) {
			final Student student = students.get(i);
			i++;
			if (student.getSession().isOpen()) {
			 			try {
							student.getSession().sendMessage(msg);
							System.out.println("日志信息：向" + student.getName() + "发送消息完毕！" + message);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
   	}
	}

	/**
	 * 给某个用户发送消息
	 * 
	 * @param userName
	 * @param message
	 * @throws IOException
	 */

	public void sendMessageToStudent(WebSocketSession session, JsonMsg message) {
		TextMessage msg = new TextMessage(
				new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message));
		try {
			if (session != null) {
				session.sendMessage(msg);
				System.out.println("日志信息：发送消息完毕！" + message);
			}
		} catch (IOException e) {
			System.out.println("日志信息： 发送消息失败！");
			e.printStackTrace();
		}

	}

	public void sendMessageToMaster(JsonMsg message) {
		TextMessage msg = new TextMessage(
				new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(message));
		try {
			if (master != null) {
				master.getSession().sendMessage(msg);
				System.out.println("日志信息：向 主持大屏发送消息完毕！" + message);
			}
		} catch (IOException e) {
			System.out.println("日志信息：向 主持大屏发送消息失败！");
			e.printStackTrace();
		}

	}
	
 //读取试题
 public ExamPaper ExcelIn(int num) {
	 		Workbook bWorkbook = null;
	 		ExamPaper exam =null;
			try {
				bWorkbook = Workbook.getWorkbook(new File("D:/exam.xls"));
				Sheet sheet = bWorkbook.getSheet(0);
				if(num<sheet.getRows()){
					exam = new ExamPaper();
					// 获取单元格对象
					Cell cell = sheet.getCell(0, num);
					// 获取单元格的值
					exam.setId(Integer.valueOf(cell.getContents()));
					exam.setTitle(sheet.getCell(1, num).getContents());
					exam.setToptions(sheet.getCell(2,num).getContents());
					exam.setScore(Integer.parseInt(sheet.getCell(3,num).getContents()));
					exam.setAnswer(sheet.getCell(4,num).getContents());
					exam.setDefine(sheet.getCell(5,num).getContents());
			 	} 
			} catch (BiffException e) {
     		e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				bWorkbook.close();
			}
			 return exam;
		}
	
	public boolean supportsPartialMessages() {
		return false;
	}
}
