<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>

<script type="text/javascript">
	$(function() {
		var ids='${project.enjoyUserids}';
		$('#insertForm').form({
			url: '${path }/oa/project/updateState',
			onSubmit: function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if(!isValid) {
					progressClose();
				}
				return isValid;
			},
			success: function(result) {
				progressClose();
				result = $.parseJSON(result);
				if(result.status == 200) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload'); //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		$('#enjoyUserids').combotree({
			url: '${path }/sys/organization/getOrgTreeWithUsers',
			lines: true,
			multiple: true,
			onlyLeafCheck: true,
			value: ids,
			onLoadSuccess: function(node, data) {
				$("#viewer").combotree('tree').tree("collapseAll");
			},
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="padding: 20px;">
		<form id="insertForm" method="post">
			<input type="hidden" name="id" value="${project.id}" />
			<input type="hidden" name="state" value="${project.state}" />
			<select id="enjoyUserids" name="enjoyUserids" style=" height: 50px;width:100%" multiline="true" class="easyui-validatebox" "></select></input>
		</form>
	</div>
</div>