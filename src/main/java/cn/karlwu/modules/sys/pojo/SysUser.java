package cn.karlwu.modules.sys.pojo;

import java.util.Date;

import cn.karlwu.common.persistence.DataEntity;

public class SysUser extends DataEntity<SysUser>{
	
	private static final long serialVersionUID = 1L;

	public SysUser(String id) {
		super(id);
	}
	
	public SysUser() {
		super();
	}
	
    private String id;


    private String uname;

    private String upass;

    private String name;

    private String tel;

    private String mail;

    private String mailGroup;

    private String levelName;

    private String situation;

    private String workPosition;

    private String organizationId;

    private Byte status;

    private String portrait;

    private Date lgTime;

    
    
    private String rolenames;
    
    private String roleIds;
    
    private String organizationname;
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass == null ? null : upass.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getMailGroup() {
        return mailGroup;
    }

    public void setMailGroup(String mailGroup) {
        this.mailGroup = mailGroup == null ? null : mailGroup.trim();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName == null ? null : levelName.trim();
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation == null ? null : situation.trim();
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition == null ? null : workPosition.trim();
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait == null ? null : portrait.trim();
    }

    public Date getLgTime() {
        return lgTime;
    }

    public void setLgTime(Date lgTime) {
        this.lgTime = lgTime;
    }


	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getOrganizationname() {
		return organizationname;
	}

	public void setOrganizationname(String organizationname) {
		this.organizationname = organizationname;
	}
}