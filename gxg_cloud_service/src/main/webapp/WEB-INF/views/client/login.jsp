<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set scope="session" var="basePath" value="${pageContext.request.contextPath}/"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>光息谷云联控3.0平台</title>
<base href="${basePath}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="static/framework/jquery-3.2.1.min.js"></script>
<script type="text/javascript"> 
//获取验证码图片 
function changeCaptchaCode(){
     $("#changeCaptcha").attr("src","captcha/getCaptchaCode");
}

//验证输入的验证码  
$(document).ready(function(){
	  $("#submit").click(function(){
	    var captchaCode = $("#captchaCode").attr("value");
	    var username = $("#username").attr("value");
		var password = $("#password").attr("value");
	   $.post("captcha/checkCaptchaCode",{"captchaCode":captchaCode},
				 function(data){
	     if(0==data){
	    	 
	     }
		   
		   
		   
	     },"json"); 
	   });   
	  
	});

//验证输入的验证码 
function checkCaptcha(){
	    var captchaCode = $("#captchaCode").val();
      $.ajax({
        type:'post',
        async : false,
        url:'captcha/checkCaptchaCode',
        data:{"captchaCode" : captchaCode},
        success:function(res){
          alert(res);
        }
    }); 

}
</script>

<link type="text/css" href="static/assets/css/style.css" rel="stylesheet"/>
<script type="text/javascript" src="static/assets/js/jquery-1.8.3.min.js"></script>

</head>
<body>
<div class="videozz"></div>  
<div class="box">
	<div class="box-a">
    <div class="m-2">
          <div class="m-2-1">
            <form action="/userController/client/login" method="post">
                <div class="m-2-2">
                   <input type="text" name="username" placeholder="请输入账号" />
                </div>
                <div class="m-2-2">
                   <input type="password" name="password" placeholder="请输入密码"/>
                </div>
                 <div class="m-2-2-1">
                   <input type="text" id="captchaCode" placeholder="请输入验证码"/>
                 <a href="javascript:changeCaptchaCode()"><img id="changeCaptcha" src="captcha/getCaptchaCode"/></a>
                </div>  
                <div class="m-2-2">
                   <button type="submit" id="submit" value="登录" >登录</button>
                   </div>
                    
            </form>
          </div>
    </div>
    <div class="m-5"> 
    <div id="m-5-id-1"> 
    <div id="m-5-2"> 
    <div id="m-5-id-2">  
    </div> 
    <div id="m-5-id-3"></div> 
    </div> 
    </div> 
    </div>   
    <div class="m-10"></div>
    <div class="m-xz7"></div>
    <div class="m-xz8 xzleft"></div>
    <div class="m-xz9"></div>
    <div class="m-xz9-1"></div>
    <!-- <div class="m-x10"></div>
    <div class="m-x11"></div>
    <div class="m-x12 xzleft"></div>
    <div class="m-x13"></div>
    <div class="m-x14 xzleft"></div>
    <div class="m-x15"></div>
    <div class="m-x16 xzleft"></div>-->
    <div class="m-x17 xzleft"></div>
    <div class="m-x18"></div>
    <div class="m-x19 xzleft"></div>
    <div class="m-x20"></div>  
    <div class="m-8"></div>
    <div class="m-9"><div class="masked1" id="sx8">光息谷云联控3.0平台</div></div> 
    <div class="m-11">
    	<div class="m-k-1"><div class="t1"></div></div>
        <div class="m-k-2"><div class="t2"></div></div>
        <div class="m-k-3"><div class="t3"></div></div>
        <div class="m-k-4"><div class="t4"></div></div>
        <div class="m-k-5"><div class="t5"></div></div>
        <div class="m-k-6"><div class="t6"></div></div>
        <div class="m-k-7"><div class="t7"></div></div>
    </div>   
    <div class="m-14"><div class="ss"></div></div>
    <div class="m-15-a">
    <div class="m-15-k">
    	<div class="m-15xz1">
            <div class="m-15-dd2"></div>
        </div>
    </div>
    </div>
    <div class="m-16"></div>
    <div class="m-17"></div>
    <div class="m-18 xzleft"></div>
    <div class="m-19"></div>
    <div class="m-20 xzleft"></div>
    <div class="m-21"></div>
    <div class="m-22"></div>
    <div class="m-23 xzleft"></div>
    <div class="m-24" id="localtime"></div>
    </div>
</div>
<script src="static/assets/js/common.min.js"></script>
<div style="text-align:center;">
 
</div>
</body>
</html>