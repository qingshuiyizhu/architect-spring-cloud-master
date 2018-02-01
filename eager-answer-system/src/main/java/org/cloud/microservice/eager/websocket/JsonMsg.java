package org.cloud.microservice.eager.websocket;

public class JsonMsg {
private boolean success = true;
private String msg = "成功返回信息";
private Object obj;
private String from;
private String to;
private String  code;

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
public String getMsg() {
	return msg;
}
public void setMsg(String msg) {
	this.msg = msg;
}
public Object getObj() {
	return obj;
}
public void setObj(Object obj) {
	this.obj = obj;
}
public String getFrom() {
	return from;
}
public void setFrom(String from) {
	this.from = from;
}
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}
@Override
public String toString() {
	return "JsonMsg [success=" + success + ", msg=" + msg + ", obj=" + obj + ", from=" + from + ", to=" + to + "]";
}
 
 
}
