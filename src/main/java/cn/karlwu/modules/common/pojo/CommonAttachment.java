package cn.karlwu.modules.common.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class CommonAttachment extends DataEntity<CommonAttachment>{
	
	private static final long serialVersionUID = 1L;

	public CommonAttachment(String id) {
		super(id);
	}
	public CommonAttachment() {
		super();
	}
	
	
    private String id;


    private String objectId;

    private String name;

    private String des;

    private String fileUrl;

    private String state;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

}