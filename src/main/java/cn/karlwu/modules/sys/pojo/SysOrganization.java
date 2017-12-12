package cn.karlwu.modules.sys.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class SysOrganization extends DataEntity<SysOrganization>{
	
	private static final long serialVersionUID = 1L;

	public SysOrganization(String id) {
		super(id);
	}
	
	public SysOrganization() {
		super();
	}
	
    private String id;


    private String name;

    private String code;

    private String parentId;

    private Byte seq;

    private String icon;

    private String address;


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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Byte getSeq() {
        return seq;
    }

    public void setSeq(Byte seq) {
        this.seq = seq;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

}