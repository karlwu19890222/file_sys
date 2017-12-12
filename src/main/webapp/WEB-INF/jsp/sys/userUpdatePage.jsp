<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		var roleIds = '${roleIds}'.replace("[","").replace("]", "");
		$('#organizationId').combotree({
			url: '${path }/sys/organization/finOrgAllTree',
			parentField: 'pid',
			lines: true,
			value: '${user.organizationId}'
		});

		$('#roleIds').combotree({
			url: '${path }/sys/role/findTree',
			parentField: 'pid',
			lines: true,
			multiple: true,
			required: true,
			cascadeCheck: false,
			value: roleIds
		});

		$('#userEditForm').form({
			url: '${path }/sys/user/update',
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
					parent.$.messager.alert('提示', result.msg, 'info');
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
	<div data-options="region:'center',border:false" title="" style="padding: 3px;">
		<form id="userEditForm" method="post">
		    <input name="id" type="hidden" value="${user.id}">
			<table class="grid">
				<tr>
					<td class="name">姓名</td>
					<td><input name="name" type="text" class="easyui-validatebox easyui-textbox " data-options="required:true" value="${user.name}"></td>
				</tr>
				
				<tr>
					<td class="name">登陆名</td>
					<td><input name="uname" type="text" class="easyui-validatebox easyui-textbox " data-options="required:true" value="${user.uname}"></td>
				</tr>
				<tr>
					<td class="name">所属部门</td>
					<td>
						<select id="organizationId" name="organizationId" class="easyui-validatebox " data-options="required:true"></select>
					</td>
				</tr>
				
				<tr>
					<td class="name">电话</td>
					<td><input name="tel" type="text" class="easyui-validatebox easyui-textbox " value="${user.tel}"></td>
				</tr>
				
				<tr>
					<td class="name">邮箱</td>
					<td><input name="mail" type="text" class="easyui-validatebox easyui-textbox"  value="${user.mail}"></td>
				</tr>
				
				<tr>
					<td class="name">邮箱群组</td>
					<td><input name="mailGroup" type="text" class="easyui-validatebox easyui-textbox"  value="${user.mailGroup}"></td>
				</tr>
				
				<tr>
				    <td class="name">角色</td>
					<td>
						<select id="roleIds" name="roleIds" class=" "></select>
					</td>
				</tr>
				
				<tr>
					<td class="name">用户状态</td>
					<td>
						<select id="status" name="status" class="easyui-combobox " data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="0">正常</option>
							<option value="1">停用</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td class="name">入职状态</td>
					<td>
						<select id="situation" name="situation" class="easyui-combobox " data-options="width:140,height:29,editable:false,panelHeight:'auto'">
							<option value="在职">在职</option>
							<option value="离职">离职</option>
						</select>
					</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>