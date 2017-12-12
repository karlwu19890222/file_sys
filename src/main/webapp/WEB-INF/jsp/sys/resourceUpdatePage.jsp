<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {

		$('#parentId').combotree({
			url: '${path }/sys/resource/findAllTree',
			parentField: 'parentId',
			lines: true,
			panelHeight: 'auto',
			value: '${resource.parentId}',
			onLoadSuccess: function(node, data) {
				$("#parentId").combotree('tree').tree("collapseAll");
			},
		});

		$('#resourceEditForm').form({
			url: '${path }/sys/resource/update',
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
					parent.$.modalDialog.openner_treeGrid.treegrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});

		$("#status").val('${resource.status}');
		$("#resourceType").val('${resource.resourceType}');
	});
</script>
<div style="padding: 3px;">
	<form id="resourceEditForm" method="post">
		<table class="grid">
			<tr>
				<td style="width:80px">资源名称</td>
				<td><input name="id" type="hidden" value="${resource.id}">
					<input name="name" type="text" placeholder="请输入资源名称" value="${resource.name}" style="width:180px" class="easyui-validatebox  easyui-textbox" data-options="required:true"></td>
				<td style="width:80px">资源类型</td>
				<td>
					<select id="resourceType" name="resourceType" class="easyui-combobox " data-options="width:180,height:29,editable:false,panelHeight:'auto'">
						<option value="0">菜单</option>
						<option value="1">按钮</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>资源路径</td>
				<td><input name="url" type="text" value="${resource.url}" placeholder="请输入资源路径" style="width:180px" class="easyui-validatebox  easyui-textbox"></td>
				<td>排序</td>
				<td><input name="seq" value="${resource.seq}" class="easyui-numberspinner" style="width: 180px; height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td>菜单图标</td>
				<td><input name="icon" value="${resource.icon}" style="width:180px" class="easyui-textbox" /></td>
				<td>状态</td>
				<td>
					<select id="status" name="status" class="easyui-combobox" data-options="width:180,height:29,editable:false,panelHeight:'auto'">
						<option value="0">正常</option>
						<option value="1">停用</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>上级资源</td>
				<td colspan="3">
					<select id="parentId" name="parentId" style="width: 180px; height: 29px;"></select>
					<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#parentId').combotree('clear');">清空</a>
				</td>
			</tr>
		</table>
	</form>
</div>