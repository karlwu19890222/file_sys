<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="X-UA-Compatible" content="edge" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>文件共享</title>
		<script type="text/javascript">
			var dataGrid;
			var dataGridFile;
			var userId = '<shiro:principal property="id"/>';
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/file/share/selectByPage?viewer='+userId,
					fit: true,
					nowrap: false,
					rownumbers: true,
					pagination: true,
					singleSelect: true,
					toolbar: '#tb',
					title: '我能查看的文件',
					iconCls: 'fa  fa-reorder',
					frozenColumns: [
						[{
							width: '150',
							title: '编号',
							field: 'number',
							align: 'center'
						},{
							width: '300',
							title: '名称',
							field: 'name',
							align: 'left'
						},]
					],
					columns: [
						[{
							width: '120',
							title: '别名',
							field: 'zName',
							align: 'left',
						},{
							width: '120',
							title: '版本',
							field: 'version',
							align: 'center',
						}, {
							width: '160',
							title: '最后更新时间',
							align: 'center',
							field: 'updateDate',
							formatter : TAOTAO.formatDateTime
						}]
					],
					onCheck: function(rowIndex, rowData) {
						dataGridFile.datagrid('load', {
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
							hidden:true,
							field: 'des'
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
			});

			function doSearch(value, name) {
				dataGrid.datagrid('load', {
					search_name: name,
					search_value: value,
				});
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
						var downerOrg=mianRow.downer;
				    	if(downerOrg== undefined){
				    		$.messager.alert('提示','您没有权利下载该文件，请联系文档管理员~');
				    	}else{
				    		var obj = downerOrg.split(","); 
				    		var flag=false;
				    		for (var i = 0, length = obj.length; i < length; i++) {
				    			 if(obj[i] == userId){
				    				 flag=true;
				    			 }
				    		}
				    		if(flag==true){
				    			self.location = '${path }/ftp/down?fileUrl=' + row.fileUrl + '&name=' + row.name +'&objectId='+mianRow.id;
				    		}else{
				    			$.messager.alert('提示','不好意思，您没有权利下载该文件，请联系文档管理员~');
				    		}
				    	}
					
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

		</script>
	</head>

	<body class="easyui-layout" id="layout_main" style="overflow-y: hidden" scroll="no">
		<%@ include file="/static/common/loading.jsp"%>

		<div id="mainPanle" region="center" style="background: #E3E3E3; overflow-y:hidden">
			<table id="dataGrid" data-options="fit:true,border:false"></table>
		</div>

		<div data-options="region:'east',split:true,hideCollapsedContent:false,collapsed:false" title="文件列表" style="width:30%;">
			<table id="dataGridFile" data-options="fit:true,border:false"></table>
		</div>

		<div id="tb" style="height:auto;width:100%;">
			<div style="width:30%">
				<input class="easyui-searchbox" data-options="prompt:'请输入查询内容',menu:'#search',searcher:doSearch" style="width:95%">
			</div>
		</div>
		
		 <div id="tb_file" style="height:auto;width:100%;">
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="预览" data-options="iconCls:'fa fa-eye-slash',plain:true" onclick="viewAttachmentFun()"></a>
			<a href="javascript:void(0)" class="easyui-linkbutton easyui-tooltip" title="下载" data-options="iconCls:'fa fa-download',plain:true" onclick="downAttachmentFun()"></a>
		</div>

		<div id="search">
			<div data-options="name:'number', iconCls:'fa  fa-angle-double-right'">编号</div>
			<div data-options="name:'name',   iconCls:'fa  fa-angle-double-right'">名称</div>
		</div>

	</body>

</html>