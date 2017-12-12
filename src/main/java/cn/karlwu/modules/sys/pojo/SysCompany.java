package cn.karlwu.modules.sys.pojo;

import java.util.Date;

import cn.karlwu.common.persistence.DataEntity;

public class SysCompany extends DataEntity<SysCompany>{
	
	private static final long serialVersionUID = 1L;

	public SysCompany(String id) {
		super(id);
	}
	
	public SysCompany() {
		super();
	}
    private String id;

    private String name;

    private String pass;

    private String level;

    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}