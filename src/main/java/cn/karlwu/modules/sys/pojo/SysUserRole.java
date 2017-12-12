package cn.karlwu.modules.sys.pojo;

import cn.karlwu.common.persistence.DataEntity;

public class SysUserRole extends DataEntity<SysUserRole>{
	
	private static final long serialVersionUID = 1L;

	public SysUserRole(String id) {
		super(id);
	}
	
	public SysUserRole() {
		super();
	}
	
    private String id;

    private String userId;

    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}