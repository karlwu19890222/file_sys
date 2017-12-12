package cn.karlwu.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.modules.sys.mapper.SysRoleMapper;
import cn.karlwu.modules.sys.mapper.SysRoleResourceMapper;
import cn.karlwu.modules.sys.pojo.SysRole;
import cn.karlwu.modules.sys.pojo.SysRoleExample;
import cn.karlwu.modules.sys.pojo.SysRoleResource;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: RoleService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月27日 下午1:02:51
 * @version: V1.0
 */

@Service
public class SysRoleService  implements BaseServiceInterface<SysRole>{
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysRoleResourceMapper roleResourceMapper;
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		SysRoleExample example = new SysRoleExample();
		example.setOrderByClause("seq ASC");
		PageHelper.startPage(page, rows);
		List<SysRole> list = roleMapper.selectByExample(example);
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		PageInfo<SysRole> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	@Override
	public void insert(SysRole record) {
		record.preInsert();
		roleMapper.insert(record);
	}
	
	@Override
	public SysRole selectByPrimaryKey(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public void updateByPrimaryKeySelective(SysRole record) {
		record.preUpdate();
		roleMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public void deleteByPrimaryKey(String id) {
		roleMapper.deleteByPrimaryKey(id);
	}


	/** 根据角色的ID获取资源的ID列表 **/
	public List<String> findResIdsByRoleId(String id) {
		List<String> list = roleMapper.findResIdsByRoleId(id);
		return list;
	}

	/** 更新角色的资源表 **/
	public void updateRoleResource(String id, String resourceIds) {
		// 先删除后添加
		List<String> roleResourceIdList = roleMapper.findRoleResIdsByRoleId(id);
		if (roleResourceIdList != null && (!roleResourceIdList.isEmpty())) {
			for (String roleResourceId : roleResourceIdList) {
				roleResourceMapper.deleteByPrimaryKey(roleResourceId);
			}
		}
		String[] resources = resourceIds.split(",");
		SysRoleResource roleResource = new SysRoleResource();
		for (String string : resources) {
			roleResource.setRoleId(id);
			roleResource.setResourceId(string);
			roleResource.preInsert();
			roleResourceMapper.insert(roleResource);
		}
	}

	/** 数列表 **/
	public List<EUTreeNode> findTree() {
		List<EUTreeNode> trees = Lists.newArrayList();
		SysRoleExample example = new SysRoleExample();
		List<SysRole> roles = roleMapper.selectByExample(example);
		if (roles != null) {
			for (SysRole role : roles) {
				EUTreeNode node = new EUTreeNode();
				node.setId(role.getId());
				node.setText(role.getName());
				trees.add(node);
			}
		}
		return trees;
	}

}
