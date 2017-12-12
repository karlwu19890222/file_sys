<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	var gridHistory;
	$(function() {
		gridHistory = $('#grid_history').datagrid({
			url: '${path }/logWrite/findByTypeAndMainId?type=${type}&mainId=${mainId}',
			fit: true,
			rownumbers: true,
			pagination: true,
			nowrap: false,
			singleSelect: true,
			columns: [
				[{
					width: '20%',
					title: '时间',
					align: 'center',
					field: 'createTime'
				},{
					width: '10%',
					title: '操作人',
					align: 'center',
					field: 'uname'
				},{
					width: '60%',
					title: '内容',
					align: 'left',
					field: 'content'
				},{
					width: '10%',
					title: '附件',
					field: 'filename',
					align: 'center',
					formatter: function(value, row, index) {
						if(value!=null && value!="" && value != undefined){
							return '<a onclick="downloadFun(' + "'" + row.content + "-file',"+ "'" + row.filename + "'" + ')"  href="#" title="点击下载" class="easyui-tooltip" iconCls="fa fa-truck" plain="true">√</a>';
						}else{
							return "";
						}
					}
				}]
			],
		});
	});
	
	function downloadFun(name,url){
    	self.location='${path }/ftp/down?fileName='+url+'&name='+name;
    }
</script>
<div style="padding: 0.2%;">
	<div class="easyui-layout" style="width:100%;height:99%;">
		<table id="grid_history"></table>
	</div>
</div>
