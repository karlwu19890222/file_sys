package cn.karlwu.modules.common.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class CommonLeavingMessage extends DataEntity<CommonLeavingMessage>{
	
	private static final long serialVersionUID = 1L;

	public CommonLeavingMessage(String id) {
		super(id);
	}
	
	public CommonLeavingMessage() {
		super();
	}
	
	
    private String id;

    private String userName;
    
    private String userImg;
    
    private String userId;

    private String objectId;

    private String content;

    private String parentId;


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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

}