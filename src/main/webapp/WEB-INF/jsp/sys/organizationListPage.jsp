<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>部门管理</title>
	</head>

	<body class="easyui-layout">
		<%@ include file="/static/common/loading.jsp"%>
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:true" style="overflow: hidden;">
				<table id="dataGrid"></table>
			</div>

			<div id="toolbar" style="display: none;">
				<a onclick="insertFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-plus-square-o'">添加新部门</a>
			</div>

		</div>
		<script type="text/javascript">
			var dataGrid;
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/sys/organization/selectByPage',
					fit: true,
					fitColumns: false,
					singleSelect: true,
					toolbar: '#toolbar',
					rownumbers: true,
					frozenColumns: [
						[{
							title: 'id',
							field: 'id',
							width: 40,
							hidden: true
						}]
					],
					columns: [
						[{
							field: 'code',
							title: '编号',
							align: 'center',
							width: '10%'
						}, {
							field: 'name',
							title: '部门名称',
							align: 'center',
							width: '30%'
						}, {
							field: 'seq',
							title: '排序',
							align: 'center',
							width: '10%'
						}, {
							width: '20%',
							title: '创建时间',
							align: 'center',
							field: 'createdate',
							formatter: TAOTAO.formatDateTime
						}, {
							field: 'parentId',
							title: '上级资源ID',
							width: 150,
							hidden: true
						}, {
							field: 'action',
							title: '操作按钮',
							align: 'center',
							width: '30%',
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
					title: '添加部门',
					width: 550,
					height: 500,
					iconCls: 'fa fa-plus-square',
					href: '${path }/sys/organization/toInsertPage',
					buttons: [{
						text: '完成',
						handler: function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#insertForm');
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
					title: '编辑部门',
					iconCls: 'fa fa-edit',
					width: 550,
					height: 500,
					href: '${path }/sys/organization/toUpdatePage?id=' + id,
					buttons: [{
						text: '完成',
						handler: function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid; //因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler.find('#updateForm');
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
				parent.$.messager.confirm('询问', '您是否要删除当前信息？', function(b) {
					if(b) {
						progressLoad();
						$.post('${path }/sys/organization/delete', {
							ids: id
						}, function(result) {
							if(result.status == 200) {
								parent.$.messager.alert('提示', result.msg, 'info');
								dataGrid.datagrid('reload');
							} else {
								parent.$.messager.alert('提示', result.msg, 'info');
							}
							progressClose();
						}, 'JSON');
					}
				});
			}
		</script>
	</body>

</html>