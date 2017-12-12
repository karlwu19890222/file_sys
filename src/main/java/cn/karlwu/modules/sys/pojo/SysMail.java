package cn.karlwu.modules.sys.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class SysMail extends DataEntity<SysMail>{
	
	private static final long serialVersionUID = 1L;

	public SysMail(String id) {
		super(id);
	}
	
	public SysMail() {
		super();
	}
	
    private String id;


    private String name;

    private String text;

    private Integer day;

    private String detail;

    private String groupUserids;


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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getGroupUserids() {
        return groupUserids;
    }

    public void setGroupUserids(String groupUserids) {
        this.groupUserids = groupUserids == null ? null : groupUserids.trim();
    }

}