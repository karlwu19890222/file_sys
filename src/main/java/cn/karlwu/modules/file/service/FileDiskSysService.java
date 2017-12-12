package cn.karlwu.modules.file.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.TreeNodeUtils;
import cn.karlwu.modules.file.mapper.FileDiskSysMapper;
import cn.karlwu.modules.file.pojo.FileDiskSys;
import cn.karlwu.modules.file.pojo.FileDiskSysExample;
import cn.karlwu.modules.file.pojo.FileDiskSysExample.Criteria;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: FileDiskService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.file.service
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月21日 下午3:16:16
 * @version: V1.0  
 */

@Service
public class FileDiskSysService implements BaseServiceInterface<FileDiskSys> {
    @Autowired
	private FileDiskSysMapper fileDiskSysMapper;
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows,Map<String, String> map) {
		FileDiskSysExample example=new FileDiskSysExample();
		example.setOrderByClause("TIMESTAMP(update_date) DESC");
		EasyUIResult result=new EasyUIResult();
		List<FileDiskSys> list=fileDiskSysMapper.selectByExample(example);
		result.setRows(list);
		PageInfo<FileDiskSys> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public void insert(FileDiskSys record) {
		record.preInsert();
		fileDiskSysMapper.insert(record);
	}

	@Override
	public FileDiskSys selectByPrimaryKey(String id) {
		return fileDiskSysMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(FileDiskSys record) {
		record.preUpdate();
		fileDiskSysMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 递归删除
	 */
	@Override
	public void deleteByPrimaryKey(String id) {
		try {
			List<FileDiskSys> rlist = getChildren(id);
			for(int i = 0 ; i < rlist.size();i++){
				String cid = rlist.get(i).getId();
				fileDiskSysMapper.deleteByPrimaryKey(cid);	//删除操作 
				deleteByPrimaryKey(cid);
			}
			fileDiskSysMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public  List<EUTreeNode> findGrandFatherTrees(String type) throws Exception{
		FileDiskSysExample example=new FileDiskSysExample();
		Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		criteria.andParentIdIsNull();
		List<FileDiskSys> list=fileDiskSysMapper.selectByExample(example);
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (FileDiskSys fileDiskSys : list) {
			EUTreeNode tree = new EUTreeNode();
			tree.setId(fileDiskSys.getId());
			tree.setPid(fileDiskSys.getParentId());
			tree.setText(fileDiskSys.getName());
			if(hasChildren(fileDiskSys.getId())){
				tree.setState("closed");
			}else {
				tree.setState("open");
			}
			treeDataList.add(tree);
		}
		return treeDataList;
    }
	
	public  List<EUTreeNode> findTreesByFatherId(String parentId) throws Exception{
		FileDiskSysExample example=new FileDiskSysExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<FileDiskSys> list=fileDiskSysMapper.selectByExample(example);
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (FileDiskSys fileDiskSys : list) {
			EUTreeNode tree = new EUTreeNode();
			tree.setId(fileDiskSys.getId());
			tree.setPid(fileDiskSys.getParentId());
			tree.setText(fileDiskSys.getName());
			if(hasChildren(fileDiskSys.getId())){
				tree.setState("closed");
			}else {
				tree.setState("open");
			}
			treeDataList.add(tree);
		}
		return treeDataList;
    }
	
	public boolean hasChildren(String mainId) throws Exception{
		Boolean flag=false;
		FileDiskSysExample example=new FileDiskSysExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(mainId);
		List<FileDiskSys> list=fileDiskSysMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			flag=true;
		}
        return flag;
    }
	
	
	public List<EUTreeNode> findAllTreeForSys(String type) {
		FileDiskSysExample example = new FileDiskSysExample();
		example.setOrderByClause("seq asc");
		Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo(type);
		List<FileDiskSys> list = fileDiskSysMapper.selectByExample(example);
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (FileDiskSys diskSys : list) {
			EUTreeNode treeData = new EUTreeNode();
			treeData.setId(diskSys.getId());
			treeData.setPid(diskSys.getParentId());
			treeData.setText(diskSys.getName());
			treeData.setSeq(diskSys.getSeq());
			treeDataList.add(treeData);
		}
		List<EUTreeNode> newTreeDataList = TreeNodeUtils.getfatherNode(treeDataList);
		return newTreeDataList;
	}
	
	
	public List<EUTreeNode> findAllTreeForPerson(String type,String userId) {
		FileDiskSysExample example = new FileDiskSysExample();
		example.setOrderByClause("seq asc");
		Criteria criteria=example.createCriteria();
		criteria.andTypeEqualTo("person");
		criteria.andCreateByEqualTo(userId);
		List<FileDiskSys> list = fileDiskSysMapper.selectByExample(example);
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (FileDiskSys diskSys : list) {
			EUTreeNode treeData = new EUTreeNode();
			treeData.setId(diskSys.getId());
			treeData.setPid(diskSys.getParentId());
			treeData.setText(diskSys.getName());
			treeData.setSeq(diskSys.getSeq());
			treeDataList.add(treeData);
		}
		List<EUTreeNode> newTreeDataList = TreeNodeUtils.getfatherNode(treeDataList);
		return newTreeDataList;
	}
	
	
	
	public List<FileDiskSys> getChildren(String mainId) throws Exception{
		FileDiskSysExample example=new FileDiskSysExample();
		Criteria criteria=example.createCriteria();
		criteria.andParentIdEqualTo(mainId);
		List<FileDiskSys> list=fileDiskSysMapper.selectByExample(example);
        return list;
    }

	public FileDiskSys selectByNameAndCreateBy(FileDiskSys record) {
		FileDiskSysExample example=new FileDiskSysExample();
		Criteria criteria=example.createCriteria();
		criteria.andNameEqualTo(record.getName());
		criteria.andCreateByEqualTo(record.getCreateBy());
		List<FileDiskSys> list=fileDiskSysMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

}

