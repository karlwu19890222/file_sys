<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<link rel="stylesheet" type="text/css" href="${base}/static/css/index/base.css" />
		<link rel="stylesheet" type="text/css" href="${base}/static/css/index/index.css" />

		<script type="text/javascript" src="${base}/static/css/index/echarts-all.js" charset="utf-8"></script>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="shortcut icon" href="${base}/static/img/b.png">
		<link href="${base}/static/layer-v2.3/layim/layui/css/layui.css" type="text/css" rel="stylesheet"/>
		
		<title>文件管理系统</title>
		<style type="text/css">
			.content {
				padding: 10px 10px 10px 10px;
			}
			
			.dropdown {
				padding-left: 8px;
				padding-right: 8px;
				float: left;
				padding-top: 7px;
			}
			
			.dropdown-user {
				padding-left: 8px;
				padding-right: 8px;
				margin-top: 10px;
				color: #000000;
				float: left;
			}
			
			.head-img {
				height: 30px;
				width: 30px;
				vertical-align: middle;
				border: 0;
			}
			
			.username {
				color: #FFF;
			}
			
			.head-user-content {
				margin: 2px 4px 7px 9px;
			}
			
			.head-model {
				float: left;
				margin: 0px;
				padding: 0px;
				height: 100%;
				color: #FFFFFF;
			}
			
			.model-detail {
				float: left;
				background-color: #DD4B39;
				height: 52px;
				border-left: 1px solid #D73A27;
				border-right: 1px solid #D73A27;
				padding-left: 8px;
				padding-right: 8px;
			}
			
			.model-detail:hover {
				background-color: #D73925;
			}
			
			.model-detail .model-title {
				text-align: center;
				padding-top: 8px;
				padding-bottom: 5px;
				font-size: 12px;
			}
			
			.model-detail .model-title i {
				font-size: 18px;
				color: #FFF;
				display: inline-block;
				width: 1.25em;
				text-align: center;
			}
			
			.dropdown-toggle i {
				font-size: 18px;
				color: #FFF;
				display: inline-block;
				width: 1.25em;
				text-align: center;
			}
			
			.dropdown-toggle i:hover {
				color: blue;
			}
			
			a {
				text-decoration: none;
				color: #000;
			}
			
			a:hover {
				color: #D73A27;
			}
			
			span.l-btn-icon.fa.fa-bank {
				font-size: 16;
			}
			
			span.l-btn-icon.fa.fa-times-circle {
				font-size: 16;
			}
		</style>

		<script type="text/javascript">
			var index_layout;
			var index_tabs;
			$(function() {
				index_tabs = $('#index_tabs').tabs({
					fit: true,
					border: false,
					tools: [{
						iconCls: 'fa fa-bank',
						handler: function() {
							index_tabs.tabs('select', 0);
						}
					}, {
						iconCls: 'fa fa-times-circle',
						handler: function() {
							var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
							var tab = index_tabs.tabs('getTab', index);
							if(tab.panel('options').closable) {
								index_tabs.tabs('close', index);
							}
						}
					}]
				});
			});

			function addTab(title, href, icon) {
				var tt = $('#index_tabs');
				icon = icon || 'menu_icon_service';
				if(tt.tabs('exists', title)) {
					tt.tabs('select', title);
					var currTab = tt.tabs('getTab', title);
					tt.tabs('update', {
						tab: currTab,
						options: {
							content: content,
							iconCls: icon,
							closable: true
						}
					});
				} else {
					if(href) {
						var content = '<iframe frameborder="0" src="' + href + '" style="border:0;width:99%;height:98%; margin-top: 0.5%; margin-left: 0.5%;"></iframe>';
					} else {
						var content = '未实现';
					}
					tt.tabs('add', {
						title: title,
						content: content,
						iconCls: icon,
						closable: true,
					});
				}
			}

			function editUserPwd() {
				parent.$.modalDialog({
					title: '修改密码',
					width: 300,
					height: 250,
					href: '${path}/sys/user/toEditPwdPage',
					buttons: [{
						text: '确定',
						handler: function() {
							var f = parent.$.modalDialog.handler
								.find('#editUserPwdForm');
							f.submit();
						}
					}]
				});
			}
			
			
			function updateUserImg() {
				parent.$.modalDialog({
					title: '更新头像',
					width: 300,
					height: 250,
					href: '${path}/sys/user/toUpdateUserImg',
					buttons: [{
						text: '确定',
						handler: function() {
							var f = parent.$.modalDialog.handler.find('#editForm');
							f.submit();
						}
					}]
				});
			}

			function logout() {
				$.messager.confirm('提示', '确定要退出?', function(r) {
					if(r) {
						progressLoad();
						$.post('${path }/logout', function(result) {
							if(result.status == 200) {
								progressClose();
								window.location.href = '${path }';
							}
						}, 'json');
					}
				});
			}
			
			
			var currentId = '${user.uname}';
			var currentName = '${user.name}';
			var currentFace ='${user.portrait}';
			var url="${base}";
			var static_url="${base}/static";
			var wsServer = 'ws://'+window.document.domain+':8668'; 
		</script>
		
		<script src="${base}/static/layer-v2.3/layim/layui/layui.js"></script>
        <script src="${base}/static/layer-v2.3/layim/layim.js"></script>
	</head>

	<body class="easyui-layout">
		<div data-options="region:'north',border:false" style="height:52px;background:#776262;border-bottom: 2px solid #00A65A !important;">
			<div class="head-left"><span><i class="fa  fa-fire"></i><a style="color: #FFF;padding-left: 12px;">文件管理系统</a></span></div>
			<div class="head-model">
			</div>
			<div class="head-right">
				<ul>
					<li class="dropdown-user head-user">
						<a href="#" class="easyui-menubutton " data-options="menu:'#mmSub'">
							<c:if test="${empty user.portrait}">
								<img style="border-radius:10px" class="head-img" src="${base}/static/img/avatar1.jpg" />
							</c:if>
							<c:if test="${not empty user.portrait}">
								<img style="border-radius:10px" class="head-img" src="${user.portrait}" />
							</c:if>
							<span class="username"><shiro:principal property="name"/></span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="mmSub" style="width:80px;">
			<div data-options="iconCls:'fa  fa-sign-out'" onclick="logout()">
				<a href="#" onclick="logout()"></a>安全登出</div>
			<div data-options="iconCls:'fa  fa-eye-slash'" onclick="editUserPwd()">
				<a href="#" onclick="editUserPwd()"></a>密码修改</div>
			<div data-options="iconCls:'fa  fa-file-image-o'" onclick="updateUserImg()">
				<a href="#" onclick="updateUserImg()"></a>更新头像</div>
		</div>

		<div data-options="region:'west',title:'系统导航',split:true,hideCollapsedContent:true,iconCls:'fa fa-qrcode'" style="width:180px;">
			<div class="easyui-accordion" id="nav" data-options="selected:false" style="width:100%;height:auto; margin: 0;padding: 0;border: 0px;">
                
                <div class="sys" title="文件共享" data-options="iconCls:'fa fa-folder'">
                    <span class="l-btn-text  fa  fa-user-md index-left-nav"><a href="javascript:addTab('我能查看的文件','${path}/file/share/toPersonViewerListPage','fa  fa-user-md')">我能查看的文件</a></span>
                    <span class="l-btn-text  fa  fa-bug index-left-nav"><a href="javascript:addTab('我能管理的文件','${path}/file/share/toPersonManagerListPage','fa  fa-bug')">我能管理的文件</a></span>
					<span class="l-btn-text  fa  fa-wheelchair  index-left-nav"><a href="javascript:addTab('管理所有文件','${path}/file/share/toListPage','fa  fa-wheelchair ')">管理所有文件</a></span>
				</div>

                <div class="sys" title="网盘共享" data-options="iconCls:'fa fa-hdd-o'">
					<span class="l-btn-text  fa  fa-laptop index-left-nav"><a href="javascript:addTab('公司网盘','${path}/file/disk/sys/toListPage','fa  fa-laptop')">公司网盘</a></span>
					<span class="l-btn-text  fa  fa-bug index-left-nav"><a href="javascript:addTab('我的网盘','${path}/file/disk/sys/toPersonListPage','fa  fa-bug')">我的网盘</a></span>
				</div>
				
                <div class="sys" title="项目管理" data-options="iconCls:'fa fa-coffee'">
					<span class="l-btn-text  fa  fa-user-md index-left-nav"><a href="javascript:addTab('我参与的项目','${path}/oa/project/toEnjoyListPage','fa  fa-user-md')">我参与的项目</a></span>
					<span class="l-btn-text  fa  fa-linux index-left-nav"><a href="javascript:addTab('我发起的项目','${path}/oa/project/toCreateListPage','fa    fa-linux')">我发起的项目 </a></span>
					<span class="l-btn-text  fa  fa-stack-exchange index-left-nav"><a href="javascript:addTab('管理所有项目','${path}/oa/project/toListPage','fa  fa-stack-exchange')">管理所有项目</a></span>
				</div>
				
				<div class="sys" title="系统管理" data-options="iconCls:'fa fa-gears'">
					<span class="l-btn-text  fa  fa-users index-left-nav"><a href="javascript:addTab('员工列表','${path}/sys/user/toListPage','fa  fa-users')">员工列表</a></span>
					<span class="l-btn-text  fa fa-sitemap index-left-nav"><a href="javascript:addTab('部门管理','${path}/sys/organization/toListPage','fa fa-sitemap')">部门管理 </a></span>
					<span class="l-btn-text  fa fa-wheelchair index-left-nav"><a href="javascript:addTab('角色管理','${path}/sys/role/toListPage','fa fa-wheelchair')">角色管理</a></span>
					<span class="l-btn-text  fa  fa-rss index-left-nav"><a href="javascript:addTab('资源管理','${path}/sys/resource/toListPage','fa   fa-rss')">资源管理</a></span>
					<span class="l-btn-text  fa  fa-cube index-left-nav"><a href="javascript:addTab('数据字典','${path}/sys/dict/toListPage','fa fa-cube')">数据字典</a></span>
					<span class="l-btn-text  fa fa-envelope-o index-left-nav"><a href="javascript:addTab('邮件设定','${path}/sys/mail/toListPage','fa fa-envelope-o')">邮件设定</a></span>
					<span class="l-btn-text  fa fa-clock-o index-left-nav"><a href="javascript:addTab('定时任务','${path}/sys/scheduleJob/toListPage','fa   fa-clock-o')">定时任务</a></span>
					<span class="l-btn-text  fa fa-file-text-o index-left-nav "><a href="javascript:addTab('访问日志','${path}/sys/log/toListPage',' fa fa-file-text-o')">访问日志</a></span>
					<span class="l-btn-text  fa fa-bug index-left-nav "><a href="javascript:addTab('系统监控','${path}/druid/index.html','fa fa-bug')">系统监控</a></span>
				</div>
			</div>
		</div>
		<div data-options="region:'center'" id="mainFrame">
			<div id="index_tabs" style="overflow: hidden;">
				<div title="工作台" href='${path}/workbench' style=" width: 98%; height: 95%;padding-top: 2%;" data-options="border:false,iconCls:'fa fa-home',closable: false">
				</div>
			</div>
		</div>
	</body>