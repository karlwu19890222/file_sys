package cn.karlwu.common.persistence;

import java.util.Map;

import cn.karlwu.common.utils.EasyUIResult;



/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: BaseService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.common.persistence
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月16日 上午10:47:17
 * @version: V1.0  
 */
public  interface BaseServiceInterface <T extends DataEntity<T>>{

	EasyUIResult selectByPage(Integer page, Integer rows,Map<String, String> map);
	
    void insert(T record);

    T selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(T record);
    
    void deleteByPrimaryKey(String id);

}

