package cn.karlwu.common.persistence;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import cn.karlwu.common.utils.UUIDUtil;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.utils.SysUserUtils;


/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: DataEntity.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.common.persistence
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月16日 上午9:57:27
 * @version: V1.0  
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String companyId;	// 公司标识
	protected String remarks;	// 备注
	protected String createBy;	// 创建者
	protected Date createDate;	// 创建日期
	protected String updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	
	public DataEntity(String id) {
		super(id);
	}
	
	public DataEntity() {
		super();
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		setId(UUIDUtil.creatUUID());
		SysUser sysUser = SysUserUtils.getSysUser();
		if (StringUtils.isNotBlank(sysUser.getId())){
			this.updateBy = sysUser.getId();
			this.createBy = sysUser.getId();
			this.companyId=sysUser.getCompanyId();
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		SysUser SysUser = SysUserUtils.getSysUser();
		if (StringUtils.isNotBlank(SysUser.getId())){
			this.updateBy = SysUser.getId();
		}
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=255)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}


