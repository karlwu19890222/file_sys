package cn.karlwu.modules.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.modules.sys.mapper.SysUserRoleMapper;
import cn.karlwu.modules.sys.pojo.SysUserRole;
import cn.karlwu.modules.sys.pojo.SysUserRoleExample;
import cn.karlwu.modules.sys.pojo.SysUserRoleExample.Criteria;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: UserRoleService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月27日 下午1:02:36
 * @version: V1.0
 */

@Service
public class SysUserRoleService {

	@Autowired
	private SysUserRoleMapper userRoleMapper;
    
	/**根据用户的ID获取他的角色ID集合**/
	public List<String> findRoleIdListByUserId(String id) {
		List<String> result = new ArrayList<>();
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		List<SysUserRole> list = userRoleMapper.selectByExample(example);
		if (list != null) {
			for (SysUserRole sunvouUserRole : list) {
				result.add(sunvouUserRole.getRoleId());
			}
		}
		return result;
	}
	
	/**根据用户的ID获取他的角色集合**/
	public List<SysUserRole> findUserRoleByUserId(String id) {
		SysUserRoleExample example = new SysUserRoleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(id);
		List<SysUserRole> list = userRoleMapper.selectByExample(example);
		return list;
	}
	
	/**删除**/
	public void deleteByKey(String id) {
		userRoleMapper.deleteByPrimaryKey(id);
	}

	/**新增**/
	public void insert(SysUserRole userRole) {
		userRole.preInsert();
		userRoleMapper.insert(userRole);
	}

}
