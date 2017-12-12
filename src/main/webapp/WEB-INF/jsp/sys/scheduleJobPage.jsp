<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

	<head>
		<%@ include file="/static/common/base.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>定时任务</title>
	</head>

	<body class="easyui-layout">
		<%@ include file="/static/common/loading.jsp"%>
		
		<div id="mainPanle" region="center" style="background: #E3E3E3; overflow-y:hidden">
		    <table id="dataGrid" data-options="fit:true,border:true"></table>
		</div>
	    <div data-options="region:'east',split:true,hideCollapsedContent:true,collapsed:true" title="任务执行情况" style="width:30%;">
		   <table id="grid_history">
				<thead>
					<tr>
					    <th field="startTime" width="180" align="center" formatter="TAOTAO.formatDateTime">开始时间</th>
					    <th field="endTime" width="180" align="center" formatter="TAOTAO.formatDateTime">结束时间</th>
						<th field="content" width="300" align="left">执行情况</th>
					</tr>
				</thead>
			</table>
		</div>
		
		<script type="text/javascript">
			var dataGrid;
			$(function() {
				dataGrid = $('#dataGrid').datagrid({
					url: '${path }/sys/scheduleJob/selectByPage',
					striped: true,
					rownumbers: true,
					pagination: true,
					singleSelect: true,
					fit: true,
					title:'定时任务列表',
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
							width: '160',
							title: '名称',
							field: 'jobName',
							align: 'center'
						}, {
							width: '80',
							title: '任务组',
							field: 'jobGroup',
							align: 'center'
						}]
					],

					columns: [
						[{
							width: '130',
							title: '执行时间',
							align: 'center',
							field: 'cronExpression',
						}, {
							width: '200',
							title: '执行描述',
							align: 'left',
							field: 'description'
						}, {
							width: '280',
							title: '任务类Class',
							align: 'left',
							field: 'jobClass'
						}, {
							width: '80',
							title: '状态',
							align: 'center',
							field: 'status',
							formatter: function(value, row, index) {
								switch(value) {
									case 0:
										return '暂停';
									case 1:
										return '运行';
								}
							}
						}, {
							width: '130',
							title: '创建时间',
							align: 'center',
							field: 'createDate',
							formatter: TAOTAO.formatDateTime
						}, {
							width: '130',
							title: '更新时间',
							align: 'center',
							field: 'updateDate',
							formatter: TAOTAO.formatDateTime
						}, {
							field: 'action',
							title: '操作按钮',
							align: 'center',
							width: 280,
							formatter: function(value, row, index) {
								var str = '';
								str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-one" data-options="plain:true,iconCls:\'fa  fa-play-circle-o\'" onclick="goOneFun(\'{0}\');" >执行</a>', row.schedulejobid);
								str += '|';
								str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-start" data-options="plain:true,iconCls:\'fa  fa-play\'" onclick="startFun(\'{0}\');" >启动</a>', row.schedulejobid);
								str += '|';
								str += $.formatString('<a href="javascript:void(0)" class="role-easyui-linkbutton-stop" data-options="plain:true,iconCls:\'fa  fa-pause\'" onclick="stopFun(\'{0}\');" >暂停</a>', row.schedulejobid);
								return str;
							}
						}]
					],

					onLoadSuccess: function(data) {
						$('.role-easyui-linkbutton-one').linkbutton({
							text: '执行'
						});
						$('.role-easyui-linkbutton-start').linkbutton({
							text: '启动'
						});
						$('.role-easyui-linkbutton-stop').linkbutton({
							text: '暂停'
						});
					},
					
					onClickRow: function (index, row) { 
						$("#grid_history").datagrid({title:'【'+row.jobName+'】的执行情况'}); 
						$('#grid_history').datagrid('load', {
							jId: row.id,
						});
	                }
				});
				
				$('#grid_history').datagrid({
					url: '${path }/sys/scheduleJob/history/list',
					rownumbers: true,
					nowrap:false,
					fitColumns:true,
					fit: true,
				});
			});

			function goOneFun(schedulejobid) {
				if(schedulejobid == undefined) {
					var rows = dataGrid.datagrid('getSelections');
					schedulejobid = rows[0].schedulejobid;
				} else {
					dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
				}
				$.ajax({
					type: 'post',
					url: '${path }/sys/scheduleJob/runOnce',
					cache: false,
					data: {
						schedulejobid: schedulejobid
					},
					dataType: 'json',
					success: function(result) {
						if(result.status == "200") {
							$.messager.show({
								title: '消息提示',
								msg: result.msg,
								timeout: 0,
								showType: 'fade'
							});
						} else {
							$.messager.show({
								title: '消息提示',
								msg: result.msg,
								timeout: 0,
								showType: 'fade'
							});
						}
					}
				});
			}
		</script>
	</body>

</html>