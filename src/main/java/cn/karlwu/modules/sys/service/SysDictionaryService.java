package cn.karlwu.modules.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.common.utils.TreeNodeUtils;
import cn.karlwu.modules.sys.mapper.SysDictionaryMapper;
import cn.karlwu.modules.sys.pojo.SysDictionary;
import cn.karlwu.modules.sys.pojo.SysDictionaryExample;
import cn.karlwu.modules.sys.pojo.SysDictionaryExample.Criteria;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: SysDictionaryService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月21日 上午10:13:47
 * @version: V1.0  
 */

@Service
public class SysDictionaryService  implements BaseServiceInterface<SysDictionary>{
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;
	
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		EasyUIResult easyUIResult=new EasyUIResult();
		SysDictionaryExample example = new SysDictionaryExample();
		example.setOrderByClause("type_key ASC");
		Criteria criteria =example.createCriteria();
		if(StringUtils.isNotEmpty(map.get("typeKey"))){
			criteria.andTypeKeyLike("%"+map.get("typeKey")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("name"))){
			criteria.andNameLike("%"+map.get("name")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("textValue"))){
			criteria.andTextValueLike("%"+map.get("textValue")+"%");
		}
		PageHelper.startPage(page, rows);
		List<SysDictionary> list= sysDictionaryMapper.selectByExample(example);
		easyUIResult.setRows(list);
		PageInfo<SysDictionary> pageInfo=new PageInfo<>(list);
		easyUIResult.setTotal(pageInfo.getTotal());
		return easyUIResult;
	}
	@Override
	public void insert(SysDictionary record) {
		record.preInsert();
		sysDictionaryMapper.insert(record);
	}
	@Override
	public SysDictionary selectByPrimaryKey(String id) {
		return sysDictionaryMapper.selectByPrimaryKey(id);
	}
	
	
	@Override
	public void updateByPrimaryKeySelective(SysDictionary record) {
		record.preUpdate();
		sysDictionaryMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public void deleteByPrimaryKey(String id) {
		sysDictionaryMapper.deleteByPrimaryKey(id);
	}
	
	
	public String selectByTextKey(String textKey) {
		SysDictionaryExample example = new SysDictionaryExample();
		example.setOrderByClause("seq ASC");
		Criteria criteria =example.createCriteria();
		criteria.andTypeKeyEqualTo(textKey);
		List<SysDictionary> list= sysDictionaryMapper.selectByExample(example);
		JSONArray jArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("text", list.get(i).getName());
			obj.put("value", list.get(i).getTextValue());
			jArray.add(obj);
		}
		String result = jArray.toString().replace("\"", "'");
		return result;
	}
	
	
	public List<EUTreeNode> selectTreesTextKey(String textKey) {
		SysDictionaryExample example = new SysDictionaryExample();
		example.setOrderByClause("seq ASC");
		Criteria criteria =example.createCriteria();
		criteria.andTypeKeyEqualTo(textKey);
		List<SysDictionary> list = sysDictionaryMapper.selectByExample(example);
		System.out.println("list.size:"+list.size());
		List<EUTreeNode> treeDataList = new ArrayList<EUTreeNode>();
		for (SysDictionary dictionary : list) {
			EUTreeNode treeData = new EUTreeNode();
			treeData.setId(dictionary.getTextValue());
			treeData.setText(dictionary.getName());
			treeData.setValue(dictionary.getTextValue());
			treeDataList.add(treeData);
		}
		List<EUTreeNode> newTreeDataList = TreeNodeUtils.getfatherNode(treeDataList);
		return newTreeDataList;
	}
}

