<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#pid').combotree({
			parentField: 'pid',
			lines: true,
			panelHeight: 'auto'
		});
		$('#insertForm').form({
			url: '${path }/sys/organization/insert',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});

	});
</script>
<div style="padding: 3px;">
	<form id="insertForm" method="post">
		<table class="grid">
			<tr>
				<td class="name">编号</td>
				<td><input name="code" type="text" placeholder="请输入部门编号" style="width: 180px;" class="easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">部门名称</td>
				<td><input name="name" type="text" placeholder="请输入部门名称" style="width: 180px;" class="easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">排序</td>
				<td><input name="seq" class="easyui-numberspinner" style="width: 180px; height: 29px;" required="required" data-options="editable:false" value="0"></td>
			</tr>
			<tr>
				<td class="name">菜单图标</td>
				<td><input name="icon" style="width: 180px;" class=" easyui-textbox" value="icon-man" /></td>
			</tr>
		</table>
	</form>
</div>