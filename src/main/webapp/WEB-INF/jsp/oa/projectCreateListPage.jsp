<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="X-UA-Compatible" content="edge" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<script type="text/javascript">
			var dataGrid;
			var dataGridFile;
			var dataGridMessage;
			var dataGridHistory;
			var userId = '<shiro:principal property="id"/>';
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/oa/project/selectByPage?createBy=' + userId,
					fit: true,
					nowrap: false,
					rownumbers: true,
					pagination: true,
					singleSelect: true,
					toolbar: '#tb',
					title: '我发起的项目',
					iconCls: 'fa  fa-reorder',
					frozenColumns: [
						[{
							width: '150',
							title: '项目类型',
							field: 'type',
							align: 'left'
						},{
							width: '200',
							title: '项目名称',
							field: 'name',
							align: 'left'
						}, {
							width: '120',
							title: '项目进度',
							align: 'center',
							field: 'speedProcess',
							formatter: function (value, rec) {  
						        var tempval=value;  
						        var htmlstr = '<div class="easyui-progressbar progressbar" style="width: 120; height: 20px;" value="'+tempval +'" text="'+tempval+'%">'+  
						        '<div class="progressbar-text" style="width: 120px; height: 20px; line-height: 20px;">'+tempval+'%</div>'+  
						            '<div class="progressbar-value" style="width: '+tempval+'%; height: 20px; line-height: 20px;">'+  
						                '<div class="progressbar-text" style="width: 120px; height: 20px; line-height: 20px;">'+tempval+'%</div>'+  
						            '</div>'+  
						        '</div>';  
						        return htmlstr;  
						    }  
						}]
					],
					columns: [
						[{
							width: '80',
							title: '发起人',
							field: 'startBy',
							align: 'center',
						}, {
							width: '150',
							title: '项目参与人员',
							align: 'center',
							field: 'enjoyUserids',
						}, {
							width: '300',
							title: '项目内容',
							align: 'left',
							field: 'des'
						}, {
							width: '80',
							title: '项目状态',
							align: 'center',
							field: 'state'
						}, {
							width: '100',
							title: '发起时间',
							align: 'center',
							field: 'startTime',
							formatter : TAOTAO.formatDayTime
						}, {
							width: '100',
							title: '预计完成时间',
							align: 'center',
							field: 'estimatedEndTime',
							formatter : TAOTAO.formatDayTime
						}, {
							width: '100',
							title: '实际完成时间',
							align: 'center',
							field: 'overDate',
							formatter : TAOTAO.formatDayTime
						}]
					],
					onCheck: function(rowIndex, rowData) {
						dataGridFile.datagrid('load', {
							objectId: rowData.id
						});
						dataGridMessage.datagrid('load', {
							objectId: rowData.id
						});
						dataGridHistory.datagrid('load', {
							objectId: rowData.id
						});
						$("#layout_main").layout('panel', 'east').panel('setTitle', '【' + rowData.name + '】的项目信息');
						$('#layout_main').layout('expand', 'east');
						if(rowData.state=='进行中'){
						    $('#updateProjectStateStartButton').linkbutton('disable');
							$('#updateProjectStateStopButton').linkbutton('enable');
							$('#updateProjectEnjoyButton').linkbutton('enable');
							$('#updateProjectSpeedButton').linkbutton('enable');
							$('#updateProjectDesButton').linkbutton('enable');
						}
						if(rowData.state=='暂停'){
						    $('#updateProjectStateStartButton').linkbutton('enable');
							$('#updateProjectStateStopButton').linkbutton('disable');
							$('#updateProjectEnjoyButton').linkbutton('enable');
							$('#updateProjectSpeedButton').linkbutton('enable');
							$('#updateProjectDesButton').linkbutton('enable');
						}
						if(rowData.state=='完成'){
						    $('#updateProjectStateStartButton').linkbutton('disable');
							$('#updateProjectStateStopButton').linkbutton('disable');
							$('#updateProjectEnjoyButton').linkbutton('disable');
							$('#updateProjectSpeedButton').linkbutton('disable');
							$('#updateProjectDesButton').linkbutton('disable');
						}
					},
				});

				dataGridFile = $('#dataGridFile').datagrid({
					url: '${path }/common/attachment/selectByPage',
					fit: true,
					nowrap: false,
					singleSelect: true,
					toolbar: '#tb_file',
					columns: [
						[{
							width: '30%',
							title: '名称',
							align: 'left',
							field: 'name',
						}, {
							width: '35%',
							title: '描述',
							align: 'left',
							field: 'des'
						}, {
							width: '15%',
							title: '上传人',
							field: 'createBy',
							align: 'center'
						}, {
							width: '20%',
							title: '上传时间',
							align: 'center',
							formatter: TAOTAO.formatDateTime,
							field: 'createDate'
						}]
					],
				});

				dataGridMessage = $('#dataGridMessage').datagrid({
					url: '${path }/common/leavingMessage/selectByPage',
					fit: true,
					nowrap: false,
					singleSelect: true,
					toolbar: '#tb_message',
					columns: [
						[{
							width: '30%',
							title: '时间',
							align: 'center',
							formatter: TAOTAO.formatDateTime,
							field: 'createDate'
						}, {
							width: '20%',
							title: '发表人',
							field: 'userName',
							align: 'center'
						}, {
							width: '50%',
							title: '发表内容',
							align: 'left',
							field: 'content',
						}]
					],
				});
				
				dataGridHistory = $('#dataGridHistory').datagrid({
					url: '${path }/common/log/selectByPage',
					fit: true,
					nowrap: false,
					singleSelect: true,
					columns: [
						[{
							width: '20%',
							title: '时间',
							align: 'center',
							formatter: TAOTAO.formatDateTime,
							field: 'createDate'
						}, {
							width: '12%',
							title: '操作人',
							field: 'userId',
							align: 'center'
						}, {
							width: '15%',
							title: '类型',
							field: 'type',
							align: 'center'
						}, {
							width: '50%',
							title: '记录明细',
							align: 'left',
							field: 'content',
						}]
					],
				});
			});

			function insertFun() {
				parent.$.modalDialog({
					title: '发起项目',
					width: document.body.clientWidth * 0.5,
					height: document.body.clientHeight * 0.8,
					iconCls: 'fa fa-plus-square',
					href: '${path }/oa/project/toInsertPage',
					buttons: [{
						text: '提交',
						handler: function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#insertForm');
							f.submit();
						}
					}]
				});
			}

			function updateEnjoyFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '编辑',
						width: document.body.clientWidth * 0.5,
						height: document.body.clientHeight * 0.8,
						iconCls: 'fa   fa-edit',
						href: '${path }/oa/project/toUpdateEnjoyUsersPage?id=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								var f = parent.$.modalDialog.handler.find('#userEditForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中一条数据',
					});
				}
			}

			function deleteFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					$.post('${path }/oa/project/delete', {
						ids: row.id
					}, function(result) {
						if(result.status == 200) {
							dataGrid.datagrid('reload');
							$.messager.show({
								title: '<%=CommonString.NOTICE%>',
								msg: result.msg,
							});
						}
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中一条数据',
					});
				}
			}
			
			function updateProjectStateFun(state) {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: state+'项目-'+row.name,
						width: 600,
						height: 180,
						iconCls: 'fa fa-play',
						href: '${path }/oa/project/toUpdateStatePage?id=' + row.id+"&state="+state,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								var f = parent.$.modalDialog.handler.find('#insertForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中项目',
					});
				}
			}
			
			
			function updateProjectDesFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '【'+row.name+'】更新项目描述',
						width: 600,
						height: 300,
						iconCls: 'fa fa-play',
						href: '${path }/oa/project/toUpdateDesPage?id=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								var f = parent.$.modalDialog.handler.find('#insertForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中项目',
					});
				}
			}
			
			
			function updateProjectSpeedFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '【'+row.name+'】更新进度',
						width: 600,
						height: 300,
						iconCls: 'fa fa-play',
						href: '${path }/oa/project/toUpdateProcessSpeedPage?id=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								var f = parent.$.modalDialog.handler.find('#insertForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中项目',
					});
				}
			}
			
			function updateEnjoyFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '【'+row.name+'】更新参与人',
						width: 600,
						height: 300,
						iconCls: 'fa fa-play',
						href: '${path }/oa/project/toUpdateEnjoyPage?id=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								var f = parent.$.modalDialog.handler.find('#insertForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中项目',
					});
				}
			}

			function doSearch(value, name) {
				dataGrid.datagrid('load', {
					search_name: name,
					search_value: value,
				});
			}

			function insertAttachmentFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '新增附件',
						width: document.body.clientWidth * 0.5,
						height: document.body.clientHeight * 0.8,
						iconCls: 'fa fa-plus-square',
						href: '${path }/common/attachment/toInsertPage?objectId=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGridFile;
								var f = parent.$.modalDialog.handler.find('#insertForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中项目',
					});
				}
			}


			function viewAttachmentFun() {
				var mainRow = dataGrid.datagrid('getSelected');
				var row = dataGridFile.datagrid('getSelected');
				if(row) {
					if(row.fileUrl != '' && row.fileUrl != null) {
						window.open('${path }/ftp/show?fileUrl=' + row.fileUrl+'&name='+row.name+'&objectId='+mainRow.id, '', 'height=1000,width=1000,scrollbars=yes,status =yes');
					} else {
						$.messager.alert('提示', '没有可供预览的文件,请联系管理员进行确认！', 'info');
					}
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中右侧列表中需子项',
					});
				}
			}

			function downAttachmentFun() {
				var mianRow = dataGrid.datagrid('getSelected');
				var row = dataGridFile.datagrid('getSelected');
				if(row) {
					if(row.fileUrl != '' && row.fileUrl != null) {
						self.location = '${path }/ftp/down?fileUrl=' + row.fileUrl + '&name=' + row.name +'&objectId='+mianRow.id;
					} else {
						$.messager.alert('提示', '没有可供下载的文件,请联系管理员进行确认！', 'info');
					}
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中右侧列表中需子项',
					});
				}
			}

			function deleteAttachmentFun() {
				var row = dataGridFile.datagrid('getSelected');
				if(row) {
					$.post('${path }/common/attachment/delete', {
						ids: row.id
					}, function(result) {
						if(result.status == 200) {
							dataGridFile.datagrid('reload');
							$.messager.show({
								title: '<%=CommonString.NOTICE%>',
								msg: result.msg,
							});
						}
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中一条数据',
					});
				}
			}

			function insertLeaveMessageFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '我要说话',
						width: 600,
						height: 180,
						iconCls: 'fa fa-volume-up',
						href: '${path }/common/leavingMessage/toInsertPage?objectId=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGridMessage;
								var f = parent.$.modalDialog.handler.find('#insertForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中项目',
					});
				}
			}

			function deleteMyLeaveMessageFun() {
				var row = dataGridMessage.datagrid('getSelected');
				if(row) {
					$.post('${path }/common/leavingMessage/delete', {
						ids: row.id
					}, function(result) {
						if(result.status == 200) {
							dataGridMessage.datagrid('reload');
							$.messager.show({
								title: '<%=CommonString.NOTICE%>',
								msg: result.msg,
							});
						}
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中一条数据',
					});
				}
			}
			
		</script>
	</head>

	<body class="easyui-layout" id="layout_main" style="overflow-y: hidden" scroll="no">
		<%@ include file="/static/common/loading.jsp"%>

		<div id="mainPanle" region="center" style="background: #E3E3E3; overflow-y:hidden">
			<table id="dataGrid" data-options="fit:true,border:false"></table>
		</div>

		<div data-options="region:'east',split:true,hideCollapsedContent:false,collapsed:true" title="项目信息" style="width:30%;">
			<div class="easyui-accordion" data-options="fit:true">
				<div title="项目文件" data-options="iconCls:'fa fa-clipboard'">
					<table id="dataGridFile" data-options="fit:true,border:false"></table>
				</div>

				<div title="项目交流" data-options="iconCls:'fa   fa-comments'" style="overflow-y:hidden;">
					<table id="dataGridMessage" data-options="fit:true,border:false"></table>
				</div>

				<div title="历史记录" data-options="iconCls:'fa fa-history'" style="overflow-y:hidden;">
				     <table id="dataGridHistory" data-options="fit:true,border:false"></table>
				</div>
			</div>
		</div>

		<div id="tb" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="insertButton"  data-options="iconCls:'fa fa-plus-square',plain:true" onclick="insertFun()">创建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="updateProjectStateStartButton" data-options="iconCls:'fa fa-play',plain:true" onclick="updateProjectStateFun('进行中')">启动</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="updateProjectStateStopButton" data-options="iconCls:'fa fa-stop',plain:true" onclick="updateProjectStateFun('暂停')">暂停</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="updateProjectEnjoyButton" data-options="iconCls:'fa fa-group',plain:true" onclick="updateEnjoyFun()">邀请参与人</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="updateProjectSpeedButton" data-options="iconCls:'fa fa-calendar',plain:true" onclick="updateProjectSpeedFun()">更新进度</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="updateProjectDesButton" data-options="iconCls:'fa fa-credit-card',plain:true" onclick="updateProjectDesFun()">内容更新</a>
			<div style="float:right;width:30%">
				<input class="easyui-searchbox" data-options="prompt:'请输入查询内容',menu:'#search',searcher:doSearch" style="width:95%">
			</div>
		</div>
		
		                   
         <div id="tb_file" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="新增" data-options="iconCls:'fa fa-plus-square',plain:true" onclick="insertAttachmentFun()"></a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="预览" data-options="iconCls:'fa fa-eye-slash',plain:true" onclick="viewAttachmentFun()"></a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="下载" data-options="iconCls:'fa fa-download',plain:true" onclick="downAttachmentFun()"></a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="删除" data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteAttachmentFun()"></a>
		</div>

		<div id="tb_message" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa  fa-volume-up',plain:true" onclick="insertLeaveMessageFun()">我要发言</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteMyLeaveMessageFun()">删除我的留言</a>
		</div>

		<div id="search">
			<div data-options="name:'type', iconCls:'fa  fa-angle-double-right'">项目类型</div>
			<div data-options="name:'name', iconCls:'fa  fa-angle-double-right'">项目名称</div>
			<div data-options="name:'state',iconCls:'fa  fa-angle-double-right'">项目状态</div>
		</div>

	</body>

</html>