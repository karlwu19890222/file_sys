package cn.karlwu.modules.sys.pojo;

import cn.karlwu.common.persistence.DataEntity;

public class SysRoleResource extends DataEntity<SysRoleResource>{
	
	private static final long serialVersionUID = 1L;

	public SysRoleResource(String id) {
		super(id);
	}
	
	public SysRoleResource() {
		super();
	}
	
    private String id;


    private String roleId;

    private String resourceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId == null ? null : resourceId.trim();
    }
}