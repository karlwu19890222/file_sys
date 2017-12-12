<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#parentId').combotree({
			url: '${path }/sys/resource/findAllTree',
			parentField: 'parentId',
			lines: true,
			panelHeight: 'auto',
			onLoadSuccess: function(node, data) {
				$("#parentId").combotree('tree').tree("collapseAll");
			},
		});
		$('#resourceAddForm').form({
			url: '${path }/sys/resource/insert',
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
					parent.$.modalDialog.openner_treeGrid.treegrid('reload'); //之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					//parent.layout_west_tree.tree('reload');
					parent.$.modalDialog.handler.dialog('close');
				}
			}
		});
	});
</script>
<div style="padding: 3px;">
	<form id="resourceAddForm" method="post">
		<table class="grid">
			<tr>
				<td style="width:80px">资源名称</td>
				<td><input name="name" type="text" placeholder="请输入资源名称" style="width:180px" class="easyui-validatebox  easyui-textbox" data-options="required:true"></td>
				<td style="width:80px">资源类型</td>
				<td>
					<select name="resourceType" class="easyui-combobox" data-options="width:180,height:29,editable:false,panelHeight:'auto'">
						<option value="0">菜单</option>
						<option value="1">按钮</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>资源路径</td>
				<td><input name="url" type="text" placeholder="请输入资源路径" class="easyui-validatebox  easyui-textbox" data-options="width:180,height:29"></td>
				<td>排序</td>
				<td><input name="seq" value="0" class="easyui-numberspinner" style="width: 180px; height: 29px;" required="required" data-options="editable:false"></td>
			</tr>
			<tr>
				<td>菜单图标</td>
				<td><input name="icon" class="easyui-textbox" style="width:180px" /></td>
				<td>状态</td>
				<td>
					<select name="status" class="easyui-combobox" data-options="width:180,height:29,editable:false,panelHeight:'auto'">
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