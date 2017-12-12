package cn.karlwu.modules.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.TreeNodeUtils;
import cn.karlwu.modules.sys.mapper.SysResourceMapper;
import cn.karlwu.modules.sys.pojo.SysResource;
import cn.karlwu.modules.sys.pojo.SysResourceExample;
import cn.karlwu.modules.sys.pojo.SysResourceExample.Criteria;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: ResourceService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月27日 下午2:02:52
 * @version: V1.0
 */

@Service
public class SysResourceService  implements BaseServiceInterface<SysResource>{

	@Autowired
	private SysResourceMapper resourceMapper;

	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		EasyUIResult easyUIResult = new EasyUIResult();
		SysResourceExample example = new SysResourceExample();
		example.setOrderByClause("seq asc");
		List<SysResource> list = resourceMapper.selectByExample(example);
		easyUIResult.setRows(list);
		easyUIResult.setTotal((long) list.size());
		return easyUIResult;
	}

	@Override
	public void insert(SysResource record) {
		record.preInsert();
		resourceMapper.insert(record);
	}

	@Override
	public SysResource selectByPrimaryKey(String id) {
		return resourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(SysResource record) {
		record.preUpdate();
		resourceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		resourceMapper.deleteByPrimaryKey(id);
	}
	
	public List<EUTreeNode> findAllTree() {
		SysResourceExample example = new SysResourceExample();
		example.setOrderByClause("seq asc");
		List<SysResource> resources = resourceMapper.selectByExample(example);
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (SysResource sunvouResource : resources) {
			EUTreeNode treeData = new EUTreeNode();
			treeData.setId(sunvouResource.getId());
			treeData.setPid(sunvouResource.getParentId());
			treeData.setText(sunvouResource.getName());
			treeData.setUrl(sunvouResource.getUrl());
			treeData.setSeq(sunvouResource.getSeq());
			treeData.setStatus(sunvouResource.getStatus());
			treeDataList.add(treeData);
		}
		List<EUTreeNode> newTreeDataList = TreeNodeUtils.getfatherNode(treeDataList);
		return newTreeDataList;
	}
	
	public List<SysResource> findResourceAllByTypeAndPidNull(Byte type) {
		SysResourceExample example = new SysResourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdIsNull();
		criteria.andResourceTypeEqualTo(type);
		return resourceMapper.selectByExample(example);
	}
	
	public List<SysResource> findResourceAllByTypeAndPid(Byte type,String parentId) {
		SysResourceExample example = new SysResourceExample();
		Criteria criteria = example.createCriteria();
		criteria.andResourceTypeEqualTo(type);
		criteria.andParentIdEqualTo(parentId);
		return resourceMapper.selectByExample(example);
	}

	
}
