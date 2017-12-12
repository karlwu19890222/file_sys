<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="X-UA-Compatible" content="edge" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>我能管理的文件</title>
		<script type="text/javascript">
			var dataGrid;
			var dataGridFile;
			var dataGridHistory;
			var userId = '<shiro:principal property="id"/>';
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/file/share/selectByPage?manager='+userId,
					fit: true,
					nowrap: false,
					rownumbers: true,
					pagination: true,
					singleSelect: true,
					toolbar: '#tb',
					title: '管理所有文件',
					iconCls: 'fa  fa-reorder',
					frozenColumns: [
						[{
							width: '150',
							title: '编号',
							field: 'number',
							align: 'center'
						},{
							width: '200',
							title: '名称',
							field: 'name',
							align: 'left'
						},]
					],
					columns: [
						[{
							width: '200',
							title: '别名',
							field: 'zName',
							align: 'left',
						},{
							width: '150',
							title: '版本',
							field: 'version',
							align: 'center',
						}, {
							width: '200',
							title: '预览组',
							align: 'left',
							field: 'viewerNames',
						}, {
							width: '150',
							title: '下载组',
							align: 'left',
							field: 'downerNames'
						}, {
							width: '150',
							title: '管理组',
							align: 'left',
							field: 'managerName'
						}, {
							width: '100',
							title: '最后更新时间',
							align: 'center',
							field: 'updateDate',
							formatter : TAOTAO.formatDayTime
						}]
					],
					onCheck: function(rowIndex, rowData) {
						dataGridFile.datagrid('load', {
							objectId: rowData.id
						});
						dataGridHistory.datagrid('load', {
							objectId: rowData.id
						});
						$("#layout_main").layout('panel', 'east').panel('setTitle', '【' + rowData.name + '】的文件列表');
						$('#layout_main').layout('expand', 'east');
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
			              	title:'',
			              	field:'image',
			              	width: 45,
			              	align:'center',
			                formatter:function(value,row,index){return '<img src="'+row.remarks+'" style="width:32px;height:32px;padding-top: 3px;"/>';}
			            },{
							width: '50%',
							title: '名称',
							align: 'left',
							field: 'name',
						}, {
							width: '35%',
							title: '描述',
							align: 'left',
							field: 'des',
							hidden:true
						}, {
							width: '15%',
							title: '上传人',
							field: 'createBy',
							align: 'center',
						}, {
							width: '20%',
							title: '上传时间',
							align: 'center',
							formatter: TAOTAO.formatDateTime,
							field: 'createDate',
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
					title: '创建文件',
					width: document.body.clientWidth * 0.5,
					height: document.body.clientHeight * 0.8,
					iconCls: 'fa fa-plus-square',
					href: '${path }/file/share/toInsertPage',
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

			function updateFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '编辑',
						width: document.body.clientWidth * 0.5,
						height: document.body.clientHeight * 0.8,
						iconCls: 'fa   fa-edit',
						href: '${path }/file/share/toUpdatePage?id=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGrid;
								var f = parent.$.modalDialog.handler.find('#updateForm');
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
					$.post('${path }/file/share/delete', {
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
		</script>
	</head>

	<body class="easyui-layout" id="layout_main" style="overflow-y: hidden" scroll="no">
		<%@ include file="/static/common/loading.jsp"%>

		<div id="mainPanle" region="center" style="background: #E3E3E3; overflow-y:hidden">
			<table id="dataGrid" data-options="fit:true,border:false"></table>
		</div>

		<div data-options="region:'east',split:true,hideCollapsedContent:false,collapsed:true" title="文件列表" style="width:30%;">
			<div class="easyui-accordion" data-options="fit:true">
				<div title="文件明细" data-options="iconCls:'fa fa-clipboard'">
					<table id="dataGridFile" data-options="fit:true,border:false"></table>
				</div>
				<div title="历史记录" data-options="iconCls:'fa fa-history'" style="overflow-y:hidden;">
				     <table id="dataGridHistory" data-options="fit:true,border:false"></table>
				</div>
			</div>
		</div>

		<div id="tb" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'fa fa-plus-square',plain:true" onclick="insertFun()">创建</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'fa  fa-edit',plain:true" onclick="updateFun()">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton"  data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteFun()">删除</a>
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


		<div id="search">
			<div data-options="name:'number', iconCls:'fa  fa-angle-double-right'">编号</div>
			<div data-options="name:'name',   iconCls:'fa  fa-angle-double-right'">名称</div>
		</div>

	</body>

</html>