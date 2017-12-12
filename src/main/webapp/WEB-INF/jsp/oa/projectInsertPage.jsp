<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#insertForm').form({
			url: '${path }/oa/project/insert',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});

		$('#enjoyUserids').combotree({
			url: '${path }/sys/organization/getOrgTreeWithUsers',
			lines: true,
			multiple: true,
			onlyLeafCheck: true,
			onLoadSuccess: function(node, data) {
				$("#enjoyUserids").combotree('tree').tree("collapseAll");
			},
		});

		$('#type').combotree({
			 url : '${path }/sys/dict/selectTreesTextKey?textKey=Project_Type',
		     lines : true,
		     multiple: true,
		     onlyLeafCheck : true,
		     onLoadSuccess: function(node, data) {
				$("#type").combotree('tree').tree("collapseAll");
			 },
		});
		
		function myformatter(date) {
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
		}
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="padding: 3px;">
		<form id="insertForm" method="post">
			<input type="hidden" name="objectId" value="${objectId}" />
			<table class="grid">
				<tr>
					<td class="name">项目类型</td>
					<td><input class="easyui-combobox" name="type" id="type" data-options="required:true"></input>
					</td>
				</tr>
				<tr>
					<td class="name">项目名称</td>
					<td><input class="easyui-textbox" name="name" data-options="required:true"></input>
					</td>
				</tr>
				<tr>
					<td class="name">项目内容</td>
					<td><input class="easyui-textbox" style="height: 150px" name="des" data-options="required:true"></input>
					</td>
				</tr>
				<tr>
					<td class="name">项目参与人</td>
					<td>
						<select id="enjoyUserids" name="enjoyUserids" style=" height: 50px" multiline="true" class="easyui-validatebox" data-options="required:true"></select></input>
					</td>
				</tr>
				<tr>
					<td class="name ">开始时间</td>
					<td><input class="easyui-datebox"  name="startTime" data-options="required:true"></input>
					</td>
				</tr>
				<tr>
					<td class="name ">预计结束时间</td>
					<td><input class="easyui-datebox"  name="estimatedEndTime" ></input>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>