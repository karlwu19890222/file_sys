package cn.karlwu.common.utils;

import java.util.ArrayList;
import java.util.List;

public class EUTreeNode {
	
	private String id;
	private String text;
	private String value;
	private String state;
	
	private boolean checked = false;
    private Object attributes;
    private List<EUTreeNode> children;
    private String iconCls;
    private String pid;
    
    private Byte seq;
    private String url;
    private Byte status;
    private String remark;
    private String keyword;
    
    
    
    public Byte getSeq() {
		return seq;
	}
	public void setSeq(Byte seq) {
		this.seq = seq;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Byte getStatus() {
		return status;
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
	private List<EUTreeNode> nodes =new ArrayList<EUTreeNode>();
	    
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Object getAttributes() {
		return attributes;
	}
	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	public List<EUTreeNode> getChildren() {
		return children;
	}
	public void setChildren(List<EUTreeNode> children) {
		this.children = children;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<EUTreeNode> getNodes() {
		return nodes;
	}
	public void setNodes(List<EUTreeNode> nodes) {
		this.nodes = nodes;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
