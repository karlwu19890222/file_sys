<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>资源管理</title>
	</head>

	<body class="easyui-layout">
		<script type="text/javascript">
			var treeGrid;
			$(function() {
				treeGrid = $('#treeGrid').treegrid({
					url: '${path }/sys/resource/findAllTree',
					idField: 'id',
					treeField: 'text',
					animate: true,
					lines: true,
					fit: true,
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
							field: 'text',
							title: '资源名称',
							width: '20%',
						}, {
							field: 'url',
							title: '资源路径',
							width: '20%',
						}, {
							field: 'seq',
							title: '排序',
							align: 'center',
							width: '10%',
						}, {
							field: 'parentId',
							title: '上级资源ID',
							width: 150,
							hidden: true
						}, {
							field: 'status',
							title: '状态',
							align: 'center',
							width: '10%',
							formatter: function(value, row, index) {
								switch(value) {
									case 0:
										return '正常';
									case 1:
										return '停用';
								}
							}
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
						$('#treeGrid').treegrid('collapseAll');
					},
					toolbar: '#toolbar'
				});
			});

			function TreeGridShow(data) {
				$(data).each(function(index, item) {
					if(!item.children) {
						item.state = "open";
					} else {
						item.state = "closed";
						TreeGridShow(item.children);
					}
				});
				return data;
			}

			function insertFun() {
				parent.$.modalDialog({
					title: '资源管理添加',
					width: 550,
					height: 500,
					href: '${path }/sys/resource/toInsertPage',
					buttons: [{
						text: '完成',
						handler: function() {
							parent.$.modalDialog.openner_treeGrid = treeGrid; 
							var f = parent.$.modalDialog.handler.find('#resourceAddForm');
							f.submit();
						}
					}]
				});
			}
			
			function updateFun(id) {
				if(id != undefined) {
					treeGrid.treegrid('select', id);
				}
				var node = treeGrid.treegrid('getSelected');
				if(node) {
					parent.$.modalDialog({
						title: '编辑',
						width: 550,
						height: 500,
						href: '${path }/sys/resource/toUpdatePage?id=' + node.id,
						buttons: [{
							text: '确定',
							handler: function() {
								parent.$.modalDialog.openner_treeGrid = treeGrid; 
								var f = parent.$.modalDialog.handler.find('#resourceEditForm');
								f.submit();
							}
						}]
					});
				}
			}

			function deleteFun(id) {
				if(id != undefined) {
					treeGrid.treegrid('select', id);
				}
				var node = treeGrid.treegrid('getSelected');
				if(node) {
					parent.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
						if(b) {
							progressLoad();
							$.post('${path }/sys/resource/delete', {
								ids: node.id
							}, function(result) {
								if(result.status == 200) {
									parent.$.messager.alert('提示', result.msg, 'info');
									treeGrid.treegrid('reload');
								}
								progressClose();
							}, 'JSON');
						}
					});
				}
			}
			
		</script>
		<%@ include file="/static/common/loading.jsp"%>
		<div class="easyui-layout" data-options="fit:true,border:false">
			<div data-options="region:'center',border:true" style="overflow: hidden;">
				<table id="treeGrid"></table>
			</div>

			<div id="toolbar" style="display: none;">
				<a onclick="insertFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fa fa-plus'">添加新资源</a>
			</div>

		</div>
	</body>

</html>