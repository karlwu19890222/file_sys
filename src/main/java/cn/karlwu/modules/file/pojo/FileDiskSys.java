package cn.karlwu.modules.file.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class FileDiskSys  extends DataEntity<FileDiskSys>{
	
	private static final long serialVersionUID = 1L;

	public FileDiskSys(String id) {
		super(id);
	}
	
	public FileDiskSys() {
		super();
	}
	
    private String id;


    private String type;

    private String name;

    private String parentId;

    private Byte seq;

    private String icon;

    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}