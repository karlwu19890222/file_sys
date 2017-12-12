<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#parentId').combotree({
			url: '${path }/file/disk/sys/findAllTreeForSys?type=${type}',
			parentField: 'parentId',
			lines: true,
			required:true,
			panelHeight: 'auto',
			value: '${objectId}',
			onLoadSuccess: function(node, data) {
				$("#parentId").combotree('tree').tree("collapseAll");
			},
		});
		$('#updateForm').form({
			url: '${path }/common/attachment/update',
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
	    <input name="id" type="hidden" value="${id}">
		<table class="grid">
			<tr>
				<td class="name">选择目录</td>
				<td>
					<select id="parentId" name="objectId" style="height: 29px;"></select>
				</td>
			</tr>
		</table>
	</form>
</div>