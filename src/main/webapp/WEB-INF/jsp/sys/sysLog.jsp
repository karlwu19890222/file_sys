<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<html>

<head>
<%@ include file="/static/common/base.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统日志</title>
</head>

<body class="easyui-layout">
	<%@ include file="/static/common/loading.jsp"%>
	<table id="dataGrid" data-options="fit:true,border:true" toolbar="#tb"></table>

	<div id="tb" style="float: left;width:100%">
		<div style="float: left; padding-top: 4px">
			<span style="width: 70px; display: inline-block; text-align: right;">日志内容:</span>
			<input class="easyui-textbox " style="width: 200px" id="optContent"
				name="optContent" />
		</div>
		<div style="float: left; padding-top: 4px; padding-left: 10px">
			<a href="javascript:void(0);" class="easyui-linkbutton"
				data-options="iconCls:'fa fa-search',plain:true"
				onclick="doSearch();">查询</a>
		</div>
	</div>

	<script type="text/javascript">
		var dataGrid;
		$(function() {
			dataGrid = $('#dataGrid').datagrid({
				url : '${path }/sysLog/findListPage',
				striped : true,
				rownumbers : true,
				pagination : true,
				singleSelect : true,
				pageSize : 20,
				pageList : [ 10, 20, 30, 40, 50 ],
				frozenColumns : [ [ {
					width : '100',
					title : 'id',
					field : 'id',
					align : 'center',
					hidden : true
				}, {
					width : '5%',
					title : '登录名',
					field : 'roleName',
					align : 'center'
				}, {
					width : '5%',
					title : '姓名',
					field : 'loginName',
					align : 'center'
				}, {
					width : '65%',
					title : '内容',
					align : 'left',
					field : 'optContent'
				}, {
					width : '10%',
					title : '登陆IP',
					align : 'center',
					field : 'clientIp'
				}, {
					width : '10%',
					title : '时间',
					align : 'center',
					field : 'createTime',
					formatter : TAOTAO.formatDateTime
				} ] ],

			});
		});
		
		function doSearch() {
			$('#dataGrid').datagrid('load', {
				optContent: $('#optContent').val(),
			});
		}
	</script>
</body>

</html>