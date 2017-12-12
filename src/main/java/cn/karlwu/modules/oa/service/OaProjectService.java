package cn.karlwu.modules.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.modules.common.service.CommonOperationLogService;
import cn.karlwu.modules.oa.pojo.OaProjectExample.Criteria;
import cn.karlwu.modules.oa.pojo.OaProjectExample;
import cn.karlwu.modules.oa.mapper.OaProjectMapper;
import cn.karlwu.modules.oa.pojo.OaProject;
import cn.karlwu.modules.sys.service.SysUserService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: OaProjectService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.oa.pojo
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月17日 上午10:24:17
 * @version: V1.0  
 */

@Service
public class OaProjectService implements BaseServiceInterface<OaProject> {
	
    @Autowired
	private OaProjectMapper oaProjectMapper;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
	private CommonOperationLogService commonOperationLogService;
    
    @Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		OaProjectExample example=new OaProjectExample();
		example.setOrderByClause("TIMESTAMP(update_date) DESC");
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(map.get("createBy"))){
			criteria.andCreateByEqualTo(map.get("createBy"));
		}
		if(StringUtils.isNotEmpty(map.get("enjoyUserids"))){
			criteria.andEnjoyUseridsLike("%"+map.get("enjoyUserids")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("type"))){
			criteria.andTypeLike("%"+map.get("type")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("name"))){
			criteria.andNameLike("%"+map.get("name")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("state"))){
			criteria.andStateLike("%"+map.get("state")+"%");
		}
		PageHelper.startPage(page, rows);
		List<OaProject> list=oaProjectMapper.selectByExample(example);
		for (OaProject oaProject : list) {
			oaProject.setStartBy(sysUserService.transIdsToNames(oaProject.getStartBy()));
			oaProject.setEnjoyUserids(sysUserService.transIdsToNames(oaProject.getEnjoyUserids()));
		}
		EasyUIResult result=new EasyUIResult();
		result.setRows(list);
		PageInfo<OaProject> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public void insert(OaProject record) {
		record.preInsert();
		commonOperationLogService.insert(record.getId(), "创建项目", "", "");
		oaProjectMapper.insert(record);
	}

	@Override
	public OaProject selectByPrimaryKey(String id) {
		return oaProjectMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(OaProject record) {
		record.preUpdate();
		oaProjectMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		oaProjectMapper.deleteByPrimaryKey(id);
	}

}

