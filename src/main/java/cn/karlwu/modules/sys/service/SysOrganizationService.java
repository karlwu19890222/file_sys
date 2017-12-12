package cn.karlwu.modules.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.TreeNodeUtils;
import cn.karlwu.modules.sys.mapper.SysOrganizationMapper;
import cn.karlwu.modules.sys.pojo.SysOrganization;
import cn.karlwu.modules.sys.pojo.SysOrganizationExample;
import cn.karlwu.modules.sys.pojo.SysOrganizationExample.Criteria;
import cn.karlwu.modules.sys.pojo.SysUser;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: OrganizationService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月27日 下午1:52:12
 * @version: V1.0
 */

@Service
public class SysOrganizationService implements BaseServiceInterface<SysOrganization>{

	@Autowired
	private SysOrganizationMapper organizationMapper;
	@Autowired
	private SysUserService userService;
	
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		EasyUIResult easyUIResult=new EasyUIResult();
		SysOrganizationExample example = new SysOrganizationExample();
		example.setOrderByClause("seq ASC");
		List<SysOrganization> list= organizationMapper.selectByExample(example);
		easyUIResult.setRows(list);
		easyUIResult.setTotal(Long.valueOf(list.size()));
		return easyUIResult;
	}
	@Override
	public void insert(SysOrganization record) {
		record.preInsert();
		organizationMapper.insert(record);
	}
	@Override
	public SysOrganization selectByPrimaryKey(String id) {
		return organizationMapper.selectByPrimaryKey(id);
	}
	
	public SysOrganization selectByName(String name) {
		SysOrganizationExample example = new SysOrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<SysOrganization> list = organizationMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	@Override
	public void updateByPrimaryKeySelective(SysOrganization record) {
		record.preUpdate();
		organizationMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public void deleteByPrimaryKey(String id) {
		organizationMapper.deleteByPrimaryKey(id);
	}

	/*
	 * 部门树
	 */
	public List<EUTreeNode> finOrgAllTree() {
		SysOrganizationExample example = new SysOrganizationExample();
		example.setOrderByClause("seq ASC");
		List<SysOrganization> list = organizationMapper.selectByExample(example);
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (SysOrganization organization : list) {
			EUTreeNode treeData = new EUTreeNode();
			treeData.setId(organization.getId());
			treeData.setPid(organization.getParentId());
			treeData.setText(organization.getName());
			treeDataList.add(treeData);
		}
		List<EUTreeNode> newTreeDataList = TreeNodeUtils.getfatherNode(treeDataList);
		return newTreeDataList;
	}
	
	/*
	 * 顶级树
	 */
	public String getOrgFatherTree() {
		SysOrganizationExample example = new SysOrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdIsNull();
		List<SysOrganization> organizations = organizationMapper.selectByExample(example);
		JSONArray jArray = new JSONArray();
		for (int i = 0; i < organizations.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("text", organizations.get(i).getName());
			obj.put("value", organizations.get(i).getId());
			jArray.add(obj);
		}
		String dept = jArray.toString().replace("\"", "'");
		return dept;
	}

	
	/*
	 * 人力资源数
	 */
	public List<EUTreeNode> getOrgTreeWithUsers() {
		List<EUTreeNode> trees = Lists.newArrayList();
		SysOrganizationExample example = new SysOrganizationExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdIsNull();
		List<SysOrganization> organizationFather = organizationMapper.selectByExample(example);
		if (organizationFather != null) {
			for (SysOrganization organizationOne : organizationFather) {
				EUTreeNode treeOne = new EUTreeNode();
				treeOne.setId(organizationOne.getId());
				treeOne.setText(organizationOne.getName());
				treeOne.setIconCls(organizationOne.getIcon());
				List<SysUser> users = userService.findUsersByOrgId(organizationOne.getId());
				if (users.size() > 0) {
					List<EUTreeNode> tree = Lists.newArrayList();
					for (SysUser userVo : users) {
						EUTreeNode treeTwo = new EUTreeNode();
						treeTwo.setId(userVo.getId());
						treeTwo.setText(userVo.getName());
						treeTwo.setIconCls("icon-man");
						tree.add(treeTwo);
					}
					treeOne.setChildren(tree);
				}
				trees.add(treeOne);
			}
		}
		return trees;
	}
	public List<SysOrganization> findList(boolean b) {
		SysOrganizationExample example = new SysOrganizationExample();
		example.setOrderByClause("seq ASC");
		List<SysOrganization> list = organizationMapper.selectByExample(example);
		return list;
	}

/*	public SysOrganization findLastest() {
		SysOrganizationExample example = new SysOrganizationExample();
		example.setOrderByClause("id DESC");
		List<SysOrganization> list= organizationMapper.selectByExample(example);
		return list.get(0);
	}
	

	
*/

}
