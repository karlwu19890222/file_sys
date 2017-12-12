<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {

		$('#editUserPwdForm').form({
			url: '${path }/sys/user/editUserPwd',
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
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="editUserPwdForm" method="post">
			<table class="grid">
				<tr>
					<th class="name">登录名：</th>
					<td>
						<shiro:principal></shiro:principal>
					</td>
				</tr>
				<tr>
					<th>原密码：</th>
					<td><input name="oldPwd" class="name" type="password" placeholder="请输入原密码" class="easyui-validatebox easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<th>新密码：</th>
					<td><input name="pwd" type="password" class="name" placeholder="请输入新密码" class="easyui-validatebox easyui-textbox" data-options="required:true"></td>
				</tr>
				<tr>
					<th>重复密码：</th>
					<td><input name="rePwd" type="password" class="name" placeholder="请再次输入新密码" class="easyui-validatebox easyui-textbox" data-options="required:true,validType:'eqPwd[\'#editUserPwdForm input[name=pwd]\']'"></td>
				</tr>
			</table>
		</form>
	</div>
</div>