<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {

		$('#pid').combotree({
			parentField: 'pid',
			lines: true,
			panelHeight: 'auto',
		});

		$('#updateForm').form({
			url: '${path }/sys/organization/update',
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
	<form id="updateForm" method="post">
		<table class="grid">
			<tr>
				<td class="name">编号</td>
				<td><input name="id" type="hidden" value="${organization.id}"><input name="code" type="text" style="width: 180px;" class="easyui-textbox" value="${organization.code}" /></td>
			</tr>
			<tr>
				<td class="name">资源名称</td>
				<td><input name="name" type="text" value="${organization.name}" style="width: 180px;" placeholder="请输入部门名称" class="easyui-validatebox easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">排序</td>
				<td><input name="seq" class="easyui-numberspinner" value="${organization.seq}" style="width: 180px; height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td class="name">菜单图标</td>
				<td><input name="icon" class="easyui-textbox" style="width: 180px;" value="${organization.icon}" /></td>
			</tr>
		</table>
	</form>
</div>