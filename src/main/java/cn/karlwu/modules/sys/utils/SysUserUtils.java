package cn.karlwu.modules.sys.utils;

import org.apache.shiro.SecurityUtils;



import cn.karlwu.common.shiro.ShiroUser;
import cn.karlwu.common.utils.SpringBeanUtils;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysUserService;


/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: UserUtils.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.sys.utils
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月16日 上午10:06:42
 * @version: V1.0  
 */
public class SysUserUtils {
	
	private static SysUserService sysUserService = SpringBeanUtils.getBean(SysUserService.class);
	
	
	/**
	 * 获取当前用户
	 */
	public static SysUser getSysUser(){
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        SysUser currentUser = sysUserService.selectByPrimaryKey(user.id);
        return currentUser;
	}

	public static SysUser getByLoginName(String username) {
		return sysUserService.selectByLoginName(username);
	}

}

