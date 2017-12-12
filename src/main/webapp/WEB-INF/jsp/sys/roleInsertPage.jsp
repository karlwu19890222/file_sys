<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#insertForm').form({
			url: '${path }/sys/role/insert',
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
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
		<form id="insertForm" method="post">
			<table class="grid">
				<tr>
					<td class="name">角色名称</td>
					<td><input name="name" type="text" placeholder="请输入角色名称" style="width: 180px;" class="easyui-validatebox span2 easyui-textbox" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td class="name">排序</td>
					<td><input name="seq" value="0" class="easyui-numberspinner" style="width: 180px; height: 29px;" required="required" data-options="editable:false"></td>
				</tr>
				<tr>
					<td class="name">状态</td>
					<td>
						<select id="status" name="status" class="easyui-combobox" data-options="width:180,height:29,editable:false,panelHeight:'auto'">
							<option value="0">正常</option>
							<option value="1">停用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="name">备注</td>
					<td colspan="3"><textarea name="description" style="width: 180px;" class=" easyui-textbox"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>