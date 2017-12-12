<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#updateForm').form({
			url: '${path }/sys/dict/update',
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
	    <input name="id" type="hidden" value="${dict.id}">
		<table class="grid">
			<tr>
				<td class="name">系统标识</td>
				<td><input name="typeKey" type="text" value="${dict.typeKey}"  class="easyui-textbox" data-options="required:true"></td>
			</tr>		
			<tr>
				<td class="name">字段含义</td>
				<td><input name="name" type="text" value="${dict.name}"  class="easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">字段数值</td>
				<td><input name="textValue" type="text" value="${dict.textValue}"  class="easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">排序</td>
				<td><input name="seq" class="easyui-numberspinner"  value="${dict.seq}" style="height: 29px;" required="required" data-options="editable:true" value="1"></td>
			</tr>
		</table>
	</form>
</div>