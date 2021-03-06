<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#insertForm').form({
			url: '${path }/sys/dict/insert',
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
				<td class="name">系统标识</td>
				<td><input name="typeKey" type="text" class="easyui-textbox" data-options="required:true"></td>
			</tr>		
			<tr>
				<td class="name">字段含义</td>
				<td><input name="name" type="text"  class="easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">字段数值</td>
				<td><input name="textValue" type="text"  class="easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">排序</td>
				<td><input name="seq" class="easyui-numberspinner" style="height: 29px;" required="required" data-options="editable:true" value="1"></td>
			</tr>
		</table>
	</form>
</div>