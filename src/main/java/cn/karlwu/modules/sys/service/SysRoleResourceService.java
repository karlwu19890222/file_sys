package cn.karlwu.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.modules.sys.mapper.SysRoleResourceMapper;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: RoleResourceService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年6月27日 下午2:10:25
 * @version: V1.0  
 */

@Service
public class SysRoleResourceService {
	
	@Autowired
	private SysRoleResourceMapper roleResourceMapper;

	public List<Map<String, String>> findRoleResourceListByRoleId(String roleId) {
		return roleResourceMapper.findRoleResourceListByRoleId(roleId);
	}

}

