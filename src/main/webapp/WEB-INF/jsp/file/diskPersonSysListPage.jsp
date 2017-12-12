<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${staticPath }/static/mobile/jquery.mobile-1.4.2.css" />
		<script type="text/javascript" src="${staticPath }/static/uploadify/jquery.uploadify.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${staticPath }/static/uploadify/uploadify.css">
		<title>个人共盘</title>
		<script type="text/javascript">
			var treeSys;
			var dataGridFile;
			var dataGridMessage;
			$(function() {
				treeSys = $('#treeSys').tree({
					url: '${path }/file/disk/sys/findGrandFatherTrees?type=person',
					animate: true,
					lines: true,
					onSelect: function(node){
						dataGridFile.datagrid('load', {
							objectId: node.id
						});
						dataGridMessage.datagrid('load', {
							objectId: 0
						});
						$("#layout_main").layout('panel', 'center').panel('setTitle', '【' + node.text + '】的文件列表');
					}
				});
				dataGridFile = $('#dataGridFile').datagrid({
					url: '${path }/common/attachment/selectByPage',
					fit: true,
					nowrap: false,
					singleSelect: true,
					rownumbers: true,
					pagination: true,
					toolbar: '#tb_file',
					columns: [
						[{
			              	title:'',
			              	field:'image',
			              	width: 45,
			              	align:'center',
			                formatter:function(value,row,index){return '<img src="'+row.remarks+'" style="width:32px;height:32px;padding-top: 3px;"/>';}
			            },{
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
							width: '10%',
							title: '上传人',
							field: 'createBy',
							align: 'center'
						}, {
							width: '15%',
							title: '上传时间',
							align: 'center',
							formatter: TAOTAO.formatDateTime,
							field: 'createDate'
						}]
					],
					onCheck: function(rowIndex, rowData) {
						dataGridMessage.datagrid('load', {
							objectId: rowData.id
						});
					}
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
			});
			function collapseAll(){
				$('#treeSys').tree('collapseAll');
				$('#treeSys').find('.tree-node-selected').removeClass('tree-node-selected'); 
			}
			
			function insertFun() {
				parent.$.modalDialog({
					title: '添加目录',
					width: 550,
					height: 500,
					href: '${path }/file/disk/sys/toInsertPage?type=person',
					buttons: [{
						text: '完成',
						handler: function() {
							parent.layout_west_tree = treeSys; 
							var f = parent.$.modalDialog.handler.find('#insertForm');
							f.submit();
						}
					}]
				});
			}
			
			function updateFun() {
				var node = $('#treeSys').tree('getSelected');
				if(node) {
					if(node.text=='文件根目录'){
						$.messager.show({
							title: '<%=CommonString.NOTICE%>',
							msg: '不允许编辑',
						});
					}else{
						parent.$.modalDialog({
							title: '编辑目录',
							width: 550,
							height: 500,
							href: '${path }/file/disk/sys/toUpdatePage?id=' + node.id,
							buttons: [{
								text: '确定',
								handler: function() {
									parent.layout_west_tree = treeSys; 
									var f = parent.$.modalDialog.handler.find('#updateForm');
									f.submit();
								}
							}]
						});
					}
				}else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中文件目录',
					});
				}
			}

			function deleteFun(id) {
				var node = $('#treeSys').tree('getSelected');
				if(node) {
					if(node.text=='文件根目录'){
						$.messager.show({
							title: '<%=CommonString.NOTICE%>',
							msg: '不允许删除',
						});
					}else{
						parent.$.messager.confirm('询问', '删除当前目录会连同子目录一起删除!', function(b) {
							if(b) {
								progressLoad();
								$.post('${path }/file/disk/sys/delete', {
									ids: node.id
								}, function(result) {
									if(result.status == 200) {
										parent.$.messager.alert('提示', result.msg, 'info');
										treeSys.tree('reload');
									}
									progressClose();
								}, 'JSON');
							}
						});
					}
				}
			}
			
			function insertAttachmentFun() {
				var row = $('#treeSys').tree('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '上传文件',
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
						msg: '先选中文件目录',
					});
				}
			}
			
			function updateAttachmentFun() {
				var row = dataGridFile.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '上传文件',
						width: document.body.clientWidth * 0.5,
						height: document.body.clientHeight * 0.8,
						iconCls: 'fa fa-plus-square',
						href: '${path }/common/attachment/toUpdatePage?id=' + row.id,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGridFile;
								var f = parent.$.modalDialog.handler.find('#editForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中文件目录',
					});
				}
			}
			
			
			function moveAttachmentFun() {
				var row = dataGridFile.datagrid('getSelected');
				if(row) {
					parent.$.modalDialog({
						title: '移动文件',
						width: 500,
						height:300,
						iconCls: 'fa fa-plus-square',
						href: '${path }/file/disk/sys/toMoveFilePage?id=' + row.id+"&type=person&objectId="+row.objectId,
						buttons: [{
							text: '提交',
							handler: function() {
								parent.$.modalDialog.openner_dataGrid = dataGridFile;
								var f = parent.$.modalDialog.handler.find('#updateForm');
								f.submit();
							}
						}]
					});
				} else {
					$.messager.show({
						title: '<%=CommonString.NOTICE%>',
						msg: '先选中文件目录',
					});
				}
			}


			function viewAttachmentFun() {
				var mainRow = $('#treeSys').tree('getSelected');
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
				var mianRow = $('#treeSys').tree('getSelected');
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
					parent.$.messager.confirm('询问', '是否要删除'+row.name, function(b) {
						if(b) {
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
				var row = dataGridFile.datagrid('getSelected');
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
						msg: '先选中文件',
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

	<body>
		<div class="easyui-layout" fit="true" id="layout_main">
		    <%@ include file="/static/common/loading.jsp"%>
			<div data-options="region:'west',split:true,iconCls:'fa fa-sitemap'" title="公司文件存储目录" style="width:235px;">
				<div>
					<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="新增" data-options="iconCls:'fa fa-plus-square',plain:true" onclick="insertFun()"></a>
					<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="编辑" data-options="iconCls:'fa fa-edit',plain:true" onclick="updateFun()"></a>
					<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="收起" data-options="iconCls:'fa  fa-angle-double-up',plain:true" onclick="collapseAll()"></a>
					<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="删除" data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteFun()"></a>
				</div>
				<div class="easyui-panel" fit="true" >
					<ul id="treeSys"></ul>
				</div>
			</div>
			<div data-options="region:'center',title:'文件列表',iconCls:'fa  fa-reorder'">
			     <table id="dataGridFile" data-options="fit:true,border:false"></table>
			</div>
			<div data-options="region:'east',split:true" title="留言区" style="width:300px;">
			     <table id="dataGridMessage" data-options="fit:true,border:false"></table>
			</div>
		</div>
		
		
		
		<div id="tb_file" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="上传" data-options="iconCls:'fa fa-upload',plain:true" onclick="insertAttachmentFun()">上传文件</a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="修改" data-options="iconCls:'fa fa-edit',plain:true" onclick="updateAttachmentFun()">修改信息</a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="预览" data-options="iconCls:'fa fa-eye-slash',plain:true" onclick="viewAttachmentFun()">预览文件</a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="下载" data-options="iconCls:'fa fa-download',plain:true" onclick="downAttachmentFun()">下载文件</a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="下载" data-options="iconCls:'fa fa-retweet',plain:true" onclick="moveAttachmentFun()">移动文件</a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="删除" data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteAttachmentFun()">删除文件</a>
		</div>

		<div id="tb_message" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa  fa-volume-up',plain:true" onclick="insertLeaveMessageFun()">添加留言</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'fa fa-trash-o',plain:true" onclick="deleteMyLeaveMessageFun()">删除我的</a>
		</div>
		
	</body>

</html>