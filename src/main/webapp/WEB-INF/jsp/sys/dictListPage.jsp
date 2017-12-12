<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>字典管理</title>
	</head>

	<body class="easyui-layout">
		<%@ include file="/static/common/loading.jsp"%>
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:true" style="overflow: hidden;">
				<table id="dataGrid"></table>
			</div>

			<div id="toolbar" style="height:auto;width:100%;">
				<a onclick="insertFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-plus-square-o'">添加字典</a>
				<div style="float:right;width:30%">
					<input class="easyui-searchbox" data-options="prompt:'请输入查询内容',menu:'#search',searcher:doSearch" style="width:95%">
				</div>

			</div>

			<div id="search">
				<div data-options="name:'typeKey', iconCls:'fa  fa-angle-double-right'">字典标识</div>
				<div data-options="name:'name', iconCls:'fa  fa-angle-double-right'">字典含义</div>
				<div data-options="name:'textValue',iconCls:'fa  fa-angle-double-right'">字典值</div>
			</div>

		</div>
		<script type="text/javascript">
			var dataGrid;
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/sys/dict/selectByPage',
					fit: true,
					fitColumns: false,
					singleSelect: true,
					pagination : true,
					toolbar: '#toolbar',
					rownumbers: true,
					columns: [
						[{
							field: 'typeKey',
							title: '系统唯一标识',
							align: 'center',
							width: '15%'
						}, {
							field: 'name',
							title: '字段含义',
							align: 'center',
							width: '15%'
						}, {
							width: '15%',
							title: '字段值',
							align: 'center',
							field: 'textValue',
						}, {
							field: 'seq',
							title: '排序',
							align: 'center',
							width: '5%'
						}, {
							field: 'action',
							title: '操作按钮',
							align: 'center',
							width: '25%',
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
					title: '添加字典',
					width: 550,
					height: 500,
					iconCls: 'fa fa-plus-square',
					href: '${path }/sys/dict/toInsertPage',
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
					title: '编辑字典',
					iconCls: 'fa fa-edit',
					width: 550,
					height: 500,
					href: '${path }/sys/dict/toUpdatePage?id=' + id,
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
						$.post('${path }/sys/dict/delete', {
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

			function doSearch(value, name) {
				dataGrid.datagrid('load', {
					search_name: name,
					search_value: value,
				});
			}
		</script>
	</body>

</html>