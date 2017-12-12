<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>邮件配置</title>
	</head>

	<body class="easyui-layout">
		<%@ include file="/static/common/loading.jsp"%>
		<table id="dataGrid" data-options="fit:true,border:true"></table>
		<div id="tb" style="height:auto">
			<a onclick="insertFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-plus-square-o'">添加新群组</a>
		</div>
		<script type="text/javascript">
			var dataGrid;
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/sys/mail/selectByPage',
					striped: true,
					rownumbers: true,
					pagination: true,
					singleSelect: true,
					toolbar: '#tb',
					idField: 'id',
					pageSize: 20,
					pageList: [10, 20, 30, 40, 50],
					frozenColumns: [
						[{
							width: '100',
							title: 'id',
							field: 'id',
							align: 'center',
							hidden: true
						}, {
							width: '200',
							title: '名称',
							field: 'name',
							align: 'center'
						}, {
							width: '300',
							title: '用途',
							field: 'detail',
							align: 'left'
						}, {
							width: '150',
							title: '值',
							align: 'center',
							field: 'text'
						}, {
							width: '100',
							title: '提前天数',
							align: 'center',
							field: 'day'
						}, {
							width: '300',
							title: '群组成员',
							align: 'left',
							field: 'groupUserids'
						}, {
							field: 'action',
							title: '操作按钮',
							align: 'center',
							width: '200',
							formatter: function(value, row, index) {
								var str = '';
								str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fa  fa-edit\'" onclick="updateFun(\'{0}\');" >编辑</a>', row.id);
								str += '|';
								str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fa fa-trash-o\'" onclick="deleteFun(\'{0}\');" >删除</a>', row.id);
								return str;
							}
						}]
					],
					onLoadSuccess: function(data) {
						$('.role-easyui-linkbutton-edit').linkbutton({
							text: '编辑'
						});
						$('.role-easyui-linkbutton-del').linkbutton({
							text: '删除'
						});
					},
				});
			});

			function insertFun() {
				parent.$.modalDialog({
					title: '添加',
					width: 550,
					height: 500,
					href: '${path }/sys/mail/toInsertPage',
					buttons: [{
						text: '确定',
						handler: function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#addForm');
							f.submit();
						}
					}]
				});
			}

			function updateFun(id) {
				if(id == undefined) {
					var rows = dataGrid.datagrid('getSelections');
					id = rows[0].id;
				} else {
					dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
				}
				parent.$.modalDialog({
					title: '编辑',
					width: 550,
					height: 500,
					href: '${path }/sys/mail/toUpdatePage?id=' + id,
					buttons: [{
						text: '确定',
						handler: function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#editForm');
							f.submit();
						}
					}]
				});
			}

			function deleteFun(id) {
				if(id == undefined) { //点击右键菜单才会触发这个
					var rows = dataGrid.datagrid('getSelections');
					id = rows[0].id;
				} else { //点击操作里面的删除图标会触发这个
					dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
				}
				parent.$.messager.confirm('询问', '您是否要删除当前群组？', function(b) {
					if(b) {
						progressLoad();
						$.post('${path}/sys/mail/delete', {
							ids: id
						}, function(result) {
							if(result.status == 200) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							}
							progressClose();
						}, 'JSON');
					}
				});
			}
		</script>
	</body>

</html>