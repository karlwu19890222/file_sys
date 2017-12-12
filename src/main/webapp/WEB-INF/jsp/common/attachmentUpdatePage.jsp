<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<link href="${base}/static/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="${base}/static/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/static/js/kindeditor-4.1.10/lang/zh_CN.js"></script>

<script type="text/javascript">
	$(function() {
		$('#editForm').form({
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
					parent.$.messager.alert('提示', result.msg, 'info');
					parent.$.modalDialog.openner_dataGrid.datagrid('reload'); //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
	function upFileFun() {
		KindEditor.editor(TT.kingEditorParams).loadPlugin('image', function() {
			this.plugin.imageDialog({
				showRemote: false,
				clickFn: function(url, title, width, height, border, align) {
					var input = $("#fileUrl");
					input.val(url);
					$('#linkbutton').linkbutton({text: "上传成功"});
					$("#name").textbox("setValue", title);
					this.hideDialog();
				}
			});
		});
	}

	//时间格式化
	function myformatter(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
	}
	function myparser(s) {
		if(!s) return new Date();
		var ss = (s.split('-'));
		var y = parseInt(ss[0], 10);
		var m = parseInt(ss[1], 10);
		var d = parseInt(ss[2], 10);
		if(!isNaN(y) && !isNaN(m) && !isNaN(d)) {
			return new Date(y, m - 1, d);
		} else {
			return new Date();
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="padding: 3px;">
	    <div style="padding:15px;">
			<span class="fa  fa-star"></span> <span style="color:red">文件格式仅支持pdf、doc/docx、xls/xlsx、ppt/pptx、jpg/jpeg/png!</span>
		</div>
		<form id="editForm" method="post">
		     <input type="hidden" name="id" value="${attachment.id}" />
		     <table class="grid">
				<tr>
					<td class="name">名称:</td>
					<td><input class="easyui-textbox"  name="name" id="name" data-options="required:true" value="${attachment.name}"></input>
					</td>
				</tr>
				<tr>
					<td class="name">描述:</td>
					<td><input class="easyui-textbox" style="" name="des" value="${attachment.des}"></input>
					</td>
				</tr>
				<tr>
					<td class="name">上传:</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton " id="linkbutton"  style="height:30px;width: 100px;" onclick="upFileFun()">上传附件</a>
						<input name="fileUrl" id="fileUrl" hidden="true" value="${attachment.fileUrl}"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>