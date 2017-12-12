package cn.karlwu.common.persistence;

import java.io.Serializable;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: BaseEntity.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.common.persistence
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月16日 上午9:55:29
 * @version: V1.0  
 */
public abstract class BaseEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String id;
	
    public BaseEntity() {
		
	}
	
	public BaseEntity(String id) {
		this();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 插入之前执行方法，子类实现
	 */
	public abstract void preInsert();
	
	/**
	 * 更新之前执行方法，子类实现
	 */
	public abstract void preUpdate();
	
}

