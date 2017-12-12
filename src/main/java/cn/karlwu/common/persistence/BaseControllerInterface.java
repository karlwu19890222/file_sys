package cn.karlwu.common.persistence;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;

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
public  interface BaseControllerInterface <T extends DataEntity<T>>{
	
    String toListPage(Model model);
    
    String toInsertPage(Model model,T record);
    
    String toUpdatePage(Model model,T record);
    
	EasyUIResult selectByPage(Integer page, Integer rows,String search_name,String search_value,T record);
	
	SunResult insert(T record);
	
	SunResult update(T record);
	
	SunResult delete(String ids);
	
	String toExportPage(Model model,T record);
	
	String export(HttpServletResponse response);
}

