<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>文件管理系统</title>
	    <link rel="shortcut icon" href="${base}/static/style/images/favicon.ico" />
		<script type="text/javascript" src="${base}/static/easyui/jquery-1.8.1.js" charset="utf-8"></script>
		<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${base }/static/easyui/themes/gray/easyui.css" />
        <link id="easyuiTheme" rel="stylesheet" type="text/css" href="${base }/static/easyui/themes/icon.css" />
        <script type="text/javascript" src="${base}/static/easyui/jquery.easyui.min.js" charset="utf-8"></script>
        <script type="text/javascript" src="${base}/static/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
		<link rel="shortcut icon" href="${base}/static/img/icon.png">
		<style type="text/css">
			body{margin: 0px;padding: 0px;height: 100%;}
			a{te}
			.login{}
			.login .login-head{width:960px;margin:10px auto;height: 70px;}
			.login .login-head .login-head-a{text-align: center;float: left;}
			.login .login-head .login-head-a img{}
			.login .login-head .login-head-a p{font-size: 20px;margin: 0;}
			.login .login-head .login-head-b{border-left: 1px solid #666666;float: left;height: 75px;line-height: 75px;margin-left: 20px;padding-left: 30px;width: 400px;font-size: 25px;}
			.login .login-con{text-align: center;background-color: #efefef;height:450px;}
			.login .login-con .login-a{background: url(${base }/static/img/bg-login.jpg)no-repeat;width:1000px;margin: 0 auto;text-align: left;padding-top: -100px;min-height: 450px;}
			.login .login-con .login-a form{}
			.login .login-con .login-a .login-b{float: right;width: 344px;height: 300px;border: solid 2px #e6e5e5;margin-right: 10px;margin-top: 120px;background: #fff;}
			.login .login-con .login-a .login-b .login-b-a{background-color: #E3E3E3;height: 46px;line-height: 46px;font-size: 16px;font-weight: bold;padding-left: 15px;}
			.login .login-con .login-a .login-b .login-b-b{padding-top: 20px;height: 255px;}
			.login .login-con .login-a .login-b .login-b-b ul li{height: 65px;_height: 60px;list-style-type: none;font-family: "微软雅黑","宋体",Arial;font-size: 14px;}
			.login .login-con .login-a .login-b .login-b-b ul li label{display: inline-block;font-size: 16px;color: #666666;}
			.login .login-con .login-a .login-b .login-b-b ul li input{width: 180px;color: rgb(153, 153, 153);height: 32px;line-height: 32px;border: solid 1px #d3d3d3;font-size: 14px;}
			.login .login-con .login-a .login-b .login-b-b ul li h1{font-weight: normal;color: #ff0000;background: url(${staticPath }/static/login/icon.png)no-repeat 95px -28px;font-size: 12px;padding-left: 120px;padding-top: 6px;margin: 3px 0px 0px -30px;}
			.login .login-con .login-a .login-b .login-b-b ul li #checkCode{height: 32px;line-height: 32px;width: 60px;}
			.login .login-con .login-a .login-b .login-b-b ul li img{vertical-align: middle;width: 76px;height: 33px;}
			.login .login-con .login-a .login-b .login-b-b ul li a{color: #447bd6;font-family: "微软雅黑","宋体",Arial;font-size: 12px;}
			.login .login-con .login-a .login-b .login-b-b ul .login-x{padding-left: 65px;padding-top: 0px;height: 65px;}
			.login .login-con .login-a .login-b .login-b-b ul .login-x .login-x-a{width: 100px;height: 50px;float: left;}
			.login .login-con .login-a .login-b .login-b-b ul .login-x .login-x-a .x{background-position: right -208px;background-image: url(${base }/static/img/btn-type2.png);background-repeat: no-repeat;height: 37px;float: left;width: 3px;}
			.login .login-con .login-a .login-b .login-b-b ul .login-x .login-x-a input{width:80px;float: left;border: none;background-position: 0 -120px;background-color: orange;height: 37px;line-height: 37px;color: #fff;font-size: 14px;font-weight: bold; border-radius:2px;}
			.login .login-con .login-a .login-b .login-b-b ul .login-x .login-x-b{}
			.login .login-con .login-a .login-b .login-b-c{text-align: center;padding-top: 10px;}
			.login .login-con .login-a .login-b .login-b-c a{color: #447bd6;font-family: "微软雅黑","宋体",Arial;font-size: 12px;}
			.login .login-con .login-a .login-b .login-b-c a:hover{color: #f3ab3a;}
			.login .login-foot{ text-align: center;font-size: 14px;color: #666666;position: absolute;bottom: 0;left: 0;height: 40px;width: 100%;text-align: center;}
			.login .login-foot a{text-decoration:none;color: #474747;}
		 	#input1{width: 50px;}   
		 	.error_msg{display: inline-block;font-size: 16px;float:right;color:red;margin-right: 30px;}
		</style>
	</head>
	<body>
		<div class="login">
			<div class="login-head">
				<div class="login-head-a">
					<img src="${base}/static/img/logo.png" title="" alt="" />
				</div>
				<div class="login-head-b">
					文件管理系统
				</div>
			</div>
			<div class="login-con">
				<div class="login-a">
					<form id="loginForm" method="POST" action="${base}/login" >
						<div class="login-b">
							<div class="login-b-a">用户登陆</div>
							<div class="login-b-b">
								<ul>
									<li>
										<label for="userName">用户名：</label><input type="text" id="username" name="username" />
										<h1 id="m-tip"></h1>
									</li>
									<li>
										<label for="password">密 &nbsp;&nbsp;码：</label><input type="password" id="password" name="password" />
										<h1 id="p-tip"></h1>
									</li>

									</li>
									<li class="login-x">
										<div class="login-x-a"><input type="submit" name="登录" value="登录" id="Button1" href="javascript:;" onClick="return validate();" />
											<div class="x"></div>
										</div>
									</li>
                                    <span class="error_msg">${msg}</span>
								</ul>
							</div>
							<div class="login-b-c">
							
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="login-foot">
				Copyright © 2017 power by <a href="http://www.sunvou.com/" target="_blank">无锡市尚沃医疗电子股份有限公司 </a><a href="http://www.miitbeian.gov.cn/" style="color: red;"target="_blank">（苏ICP备12055739号）</a><span style="float: right;"> <a href="http://192.168.1.100/" target="_blank">老平台入口（内部）</a></span>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
		function validate() {
			var username = $("#username").val();
			var password = $("#password").val();
			if(username == "" || username == null || username == "请输入账号") {
				$("#m-tip").html("请输入账号");
				return false;
			} else if(password == "" || password == null){
				  $("#p-tip").html("请输入密码！")
				  return false;
			}else{
				$("#m-tip").html("")
				$("#p-tip").html("")
			    return true;
		    }
		}
	</script>

</html>

