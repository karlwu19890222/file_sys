<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/static/common/global.jsp"%>
<script type="text/javascript">
    $(function() {
    	$('#downer').combotree({
    	    url : '${path }/sys/organization/getOrgTreeWithUsers',
    	    lines : true,
    	    multiple: true,
    		onlyLeafCheck : true,
    		onLoadSuccess: function(node, data) {
				$("#downer").combotree('tree').tree("collapseAll");
			},
    	});
    	$('#viewer').combotree({
    		url : '${path }/sys/organization/getOrgTreeWithUsers',
    	    lines : true,
    	    multiple: true,
			onlyLeafCheck : true,
    	    onLoadSuccess: function(node, data) {
				$("#viewer").combotree('tree').tree("collapseAll");
			},
    	});
    	$('#manager').combotree({
    		url : '${path }/sys/organization/getOrgTreeWithUsers',
    	    lines : true,
    	    multiple: true,
		    onlyLeafCheck : true,
    	    onLoadSuccess: function(node, data) {
				$("#manager").combotree('tree').tree("collapseAll");
			},
    	});
        $('#insertForm').form({
            url : '${path }/file/share/insert',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if(result.status == 200) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="padding: 3px;" >
        <form id="insertForm" method="post">
             <table  class="grid">
	                <tr>
	                    <td class="name">名称</td>
	                    <td><input class="easyui-textbox easyui-validatebox"  type="text" name="name" data-options="required:true"></input></td>
	                </tr>
	                <tr>
	                    <td class="name">编号</td>
	                    <td><input class="easyui-textbox" type="text" name="number"  class="easyui-validatebox"></input></td>
	                </tr>
	                <tr>
	                    <td class="name">别名</td>
	                    <td><input class="easyui-textbox" name="zName"  ></input></td>
	                </tr>
	                <tr>
	                    <td class="name">版本</td>
	                    <td> <input class="easyui-combobox" data-options="data:<%=CommonString.FILE_VERSION%>"  name="version"  /></td>
	                </tr>
	                <tr>
	                    <td class="name">可预览组</td>
	                    <td><select id="viewer" name="viewer" style=" height: 50px"  multiline="true" class="easyui-validatebox" "></select></td>
	                </tr>
	                <tr>
	                    <td class="name">可下载组</td>
	                    <td><select id="downer" name="downer" style=" height: 50px" multiline="true" class="easyui-validatebox" ></select></td>
	                </tr>
	                <tr>
	                    <td class="name">可管理组</td>
	                    <td><select id="manager" name="manager" style=" height: 50px" multiline="true" class="easyui-validatebox" ></select></td>
	                </tr>
	                <tr>
	                    <td class="name">备注</td>
	                    <td><input class="easyui-textbox" style=" height: 50px" multiline="true" name="remarks" ></input></td>
	                </tr>
	            </table>
        </form>
    </div>
</div>