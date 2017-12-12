package cn.karlwu.modules.file.pojo;


import cn.karlwu.common.persistence.DataEntity;

public class FileShare extends DataEntity<FileShare>{
	
	private static final long serialVersionUID = 1L;

	public FileShare(String id) {
		super(id);
	}
	
	public FileShare() {
		super();
	}
    private String id;


    private String type;

    private String name;

    private String zName;
    
    private String number;

    private String version;

    private String viewer;

    private String downer;

    private String manager;
    
    private String viewerNames;

    private String downerNames;

    private String managerName;


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

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName == null ? null : zName.trim();
    }



    public String getViewer() {
        return viewer;
    }

    public void setViewer(String viewer) {
        this.viewer = viewer == null ? null : viewer.trim();
    }

    public String getDowner() {
        return downer;
    }

    public void setDowner(String downer) {
        this.downer = downer == null ? null : downer.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

	public String getViewerNames() {
		return viewerNames;
	}

	public void setViewerNames(String viewerNames) {
		this.viewerNames = viewerNames;
	}

	public String getDownerNames() {
		return downerNames;
	}

	public void setDownerNames(String downerNames) {
		this.downerNames = downerNames;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
    
    
    

}