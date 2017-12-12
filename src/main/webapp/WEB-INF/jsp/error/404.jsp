<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>
	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>公司网盘</title>
	</head>

	<body>
		<p>默认登陆账号：sunvou,登陆密码sunvou080520</p>
		<div id="p" class="easyui-progressbar" data-options="value:0" style="width:400px;margin-top:20%;margin-left:30%"></div>
	</body>
	<script type="text/javascript">
		$(function() {
			setInterval(function() {
				$('#p').progressbar('setValue', $('#p').progressbar('getValue') + 5);
			}, 140);
			//setTimeout("window.open('http://QuickConnect.to/sunvou','_self','')", 3000 )
			setTimeout("window.open('http://192.168.1.200:5555/webman/index.cgi','_self','')", 3000)
		});

		$.messager.show({
			title: '<%=CommonString.NOTICE%>',
			height: 200,
			width: 400,
			msg: '<b>注意事项：</b><br/>① 默认登陆账号 sunvou、密码 sunvou080520。<br/>② 特殊权限或文档查看请联系管理员单独开设账户。<br/>③ 为确保平台环境，请大家养成良好使用习惯。<br/>'
		});
	</script>
<html>