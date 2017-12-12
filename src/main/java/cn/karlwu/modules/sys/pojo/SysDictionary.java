package cn.karlwu.modules.sys.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class SysDictionary extends DataEntity<SysDictionary>{
	
	private static final long serialVersionUID = 1L;

	public SysDictionary(String id) {
		super(id);
	}
	
	public SysDictionary() {
		super();
	}
	
    private String id;


    private String typeKey;

    private String name;

    private String textValue;

    private Integer seq;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }


    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey == null ? null : typeKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTextValue() {
        return textValue;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue == null ? null : textValue.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

}