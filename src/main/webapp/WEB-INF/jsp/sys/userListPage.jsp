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
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/sys/user/selectByPage',
					fit: true,
					striped: true,
					nowrap: false,
					rownumbers: true,
					pagination: true,
					singleSelect: true,
					pageSize: 20,
					toolbar: '#tb',
					pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
					frozenColumns: [
						[{
							field: 'ck',
							checkbox: true
						}, {
							width: '120',
							title: '登录名',
							field: 'uname',
							align: 'center'
						}, {
							width: '120',
							title: '姓名',
							field: 'name',
							align: 'center',
							sortable: true
						}, ]
					],
					columns: [
						[{
							width: '80',
							title: '部门ID',
							align: 'center',
							field: 'organizationId',
							hidden: true
						}, {
							width: '80',
							title: '所属部门',
							align: 'center',
							field: 'organizationname'
						}, {
							width: '100',
							title: '入职状态',
							align: 'center',
							field: 'situation'
						}, {
							width: '120',
							title: '是否是部门领导',
							align: 'center',
							hidden: true,
							field: 'isLeader'
						}, {
							width: '130',
							title: '手机号码',
							align: 'center',
							field: 'tel'
						}, {
							width: '170',
							title: '邮件',
							field: 'mail',
							align: 'left',
						}, {
							width: '170',
							title: '邮件组',
							field: 'mailgroup',
							align: 'left'
						}, {
							width: '300',
							title: '角色',
							field: 'rolenames',
						}, {
							width: '100',
							title: '账户状态',
							align: 'center',
							field: 'status',
							formatter: function(value, row, index) {
								switch(value) {
									case 0:
										return '在用';
									case 1:
										return '停用';
								}
							}
						}]
					],
				});
			});
			
			function insertFun() {
				parent.$.modalDialog({
					title: '新增',
					width: document.body.clientWidth * 0.5,
					height: document.body.clientHeight * 0.8,
					iconCls: 'fa   fa-plus',
					href: '${path }/sys/user/toInsertPage',
					buttons: [{
						text: '提交',
						handler: function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;
							var f = parent.$.modalDialog.handler.find('#userInsertForm');
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
						href: '${path }/sys/user/toUpdatePage?id=' + row.id,
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
					$.post('${path }/sys/user/delete', {
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
			
			
			function updatePwdFun() {
				var row = dataGrid.datagrid('getSelected');
				if(row) {
					$.post('${path }/sys/user/updatePwdForAdmin', {
						id: row.id
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
			
			function exportFun(){
				$.messager.confirm('提示', '全部数据导出用时较长，确定要导出？ ', function(r){
			        if (r){
			        	  window.open('${path }/sys/user/export');
			        }
				 });
			}

			function doSearch(value, name) {
				dataGrid.datagrid('load', {
					search_name: name,
					search_value: value,
				});
			}
		</script>
	</head>

	<body class="easyui-layout" data-options="fit:true,border:false">
		<%@ include file="/static/common/loading.jsp"%>

		<div id="tb" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-plus-square-o',plain:true" onclick="insertFun()">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-edit',plain:true" onclick="updateFun()">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-refresh',plain:true" onclick="updatePwdFun()">密码重置</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteFun()">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-download',plain:true" onclick="exportFun()">导出</a>
			<div style="float:right;width:30%">
				<input class="easyui-searchbox" data-options="prompt:'请输入查询内容',menu:'#search',searcher:doSearch" style="width:95%">
			</div>
		</div>

		<div id="search">
			<div data-options="name:'name',iconCls:'fa  fa-angle-double-right'">姓名</div>
			<div data-options="name:'uname',iconCls:'fa  fa-angle-double-right'">登录名</div>
			<div data-options="name:'situation',iconCls:'fa  fa-angle-double-right'">状态</div>
		</div>

		<div data-options="region:'center',border:true,title:'用户列表'">
			<table id="dataGrid" data-options="fit:true,border:false"></table>
		</div>
	</body>

</html>