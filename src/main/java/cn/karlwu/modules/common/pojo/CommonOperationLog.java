package cn.karlwu.modules.common.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class CommonOperationLog extends DataEntity<CommonOperationLog>{
	
	private static final long serialVersionUID = 1L;

	public CommonOperationLog(String id) {
		super(id);
	}
	
	public CommonOperationLog() {
		super();
	}
	
    private String id;


    private String userId;

    private String objectId;

    private String type;

    private String content;

    private String fileUrl;

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

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

}