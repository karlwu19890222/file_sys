package cn.karlwu.modules.oa.pojo;

import java.util.Date;

import cn.karlwu.common.persistence.DataEntity;

public class OaProject  extends DataEntity<OaProject>{
	
	private static final long serialVersionUID = 1L;

	public OaProject(String id) {
		super(id);
	}
	
	public OaProject() {
		super();
	}
	
    private String id;


    private String name;
    
    private String type;

    private String enjoyUserids;
    
    private String viewUserids;

    private String startBy;

    private Date startTime;
    
    private Date estimatedEndTime;

    private String overBy;

    private Date overTime;

    private Double speedProcess;

    private String state;

    private String des;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public String getEnjoyUserids() {
        return enjoyUserids;
    }

    public void setEnjoyUserids(String enjoyUserids) {
        this.enjoyUserids = enjoyUserids == null ? null : enjoyUserids.trim();
    }
    
    
    public String getViewUserids() {
        return viewUserids;
    }

    public void setViewUserids(String viewUserids) {
        this.viewUserids = viewUserids == null ? null : viewUserids.trim();
    }

    public String getStartBy() {
        return startBy;
    }

    public void setStartBy(String startBy) {
        this.startBy = startBy == null ? null : startBy.trim();
    }

    
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEstimatedEndTime() {
        return estimatedEndTime;
    }

    public void setEstimatedEndTime(Date estimatedEndTime) {
        this.estimatedEndTime = estimatedEndTime;
    }

    public String getOverBy() {
        return overBy;
    }

    public void setOverBy(String overBy) {
        this.overBy = overBy == null ? null : overBy.trim();
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Double getSpeedProcess() {
        return speedProcess;
    }

    public void setSpeedProcess(Double speedProcess) {
        this.speedProcess = speedProcess;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

}