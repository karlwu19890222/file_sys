<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		var ids='${mail.groupUserids}';
		$('#idgroup').combotree({
			  url : '${path }/sys/organization/getOrgTreeWithUsers',
		     lines : true,
		     multiple: true,
		     onlyLeafCheck : true,
		     value: ids,
		     onLoadSuccess: function(node, data) {
				$("#idgroup").combotree('tree').tree("collapseAll");
			 },
		});
		 
		$('#editForm').form({
			url: '${path }/sys/mail/update',
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
		<form id="editForm" method="post">
			<table class="grid">
			    <tr>
                    <td class="name">名称:</td>
                    <td><input type="text" name="id" value="${mail.id}"  hidden="true"/><input class="easyui-textbox" type="text" value="${mail.name}" name="name" data-options="required:true"></input></td>
                </tr>
                <tr>
                    <td class="name">值:</td>
                    <td><input class="easyui-textbox" type="text" name="text" value="${mail.text}" class="easyui-validatebox"></input></td>
                </tr>
                <tr>
                    <td class="name">说明:</td>
                    <td><input class="easyui-textbox" name="detail" value="${mail.detail}" data-options="multiline:true" style="height:60px"></input></td>
                </tr>
                 <tr>
                    <td class="name">提前天数</td>
                    <td><input name="day" value="${mail.day}"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
                </tr>
                <tr>
                    <td class="name">成员:</td>
                    <td><select id="idgroup" name="groupUserids" style="width: 140px; height: 20px;" class="easyui-validatebox" data-options="required:true"></select></td>
                </tr>
			</table>
		</form>
	</div>
</div>