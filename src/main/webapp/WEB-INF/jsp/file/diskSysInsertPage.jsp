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
			onLoadSuccess: function(node, data) {
				$("#parentId").combotree('tree').tree("collapseAll");
			},
		});
		$('#insertForm').form({
			url: '${path }/file/disk/sys/insert',
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
					parent.layout_west_tree.tree('reload');
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
				<td class="name">资源名称</td>
				<td><input name="type" type="hidden" value="${type}">
				    <input name="name" type="text"  class="easyui-validatebox  easyui-textbox" data-options="required:true"></td>
			</tr>
			<tr>
				<td class="name">排序</td>
				<td><input name="seq" value="0" class="easyui-numberspinner" style="height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td class="name">上级资源</td>
				<td>
					<select id="parentId" name="parentId" style="height: 29px;"></select>
				</td>
			</tr>
		</table>
	</form>
</div>