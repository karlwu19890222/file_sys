package cn.karlwu.modules.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.modules.common.mapper.CommonOperationLogMapper;
import cn.karlwu.modules.common.pojo.CommonOperationLog;
import cn.karlwu.modules.common.pojo.CommonOperationLogExample.Criteria;
import cn.karlwu.modules.common.pojo.CommonOperationLogExample;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysUserService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: CommonOperationLogService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.common.service
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月20日 下午5:08:10
 * @version: V1.0  
 */

@Service
public class CommonOperationLogService implements BaseServiceInterface<CommonOperationLog> {
	
	@Autowired
	private CommonOperationLogMapper commonOperationLogMapper;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		EasyUIResult result=new EasyUIResult();
		CommonOperationLogExample example=new CommonOperationLogExample();
		example.setOrderByClause("TIMESTAMP(update_date)  DESC");
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(map.get("objectId"))){
			criteria.andObjectIdEqualTo(map.get("objectId"));
		}else {
			criteria.andObjectIdEqualTo("未知");
		}
		List<CommonOperationLog> list=commonOperationLogMapper.selectByExample(example);
		for (CommonOperationLog log : list) {
			SysUser user=sysUserService.selectByPrimaryKey(log.getUserId());
			if(user!=null){
				log.setUserId(user.getName());
			}
		}
		result.setRows(list);
		result.setTotal(Long.valueOf(list.size()));
		return result;
	}

	@Override
	public void insert(CommonOperationLog record) {
		record.preInsert();
		commonOperationLogMapper.insert(record);
	}

	@Override
	public CommonOperationLog selectByPrimaryKey(String id) {
		return commonOperationLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(CommonOperationLog record) {
		record.preUpdate();
		commonOperationLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		commonOperationLogMapper.deleteByPrimaryKey(id);
	}

	public void deleteByObjectId(String objectId) {
		CommonOperationLogExample example=new CommonOperationLogExample();
		Criteria criteria =example.createCriteria();
		criteria.andObjectIdEqualTo(objectId);
		commonOperationLogMapper.deleteByExample(example);
	}
	
	public void insert(String objectId,String type,String content,String fileUrl) {
		CommonOperationLog record=new CommonOperationLog();
		record.preInsert();
		record.setUserId(record.getCreateBy());
		record.setObjectId(objectId);
		record.setType(type);
		record.setContent(content);
		record.setFileUrl(fileUrl);
		commonOperationLogMapper.insert(record);
	}
}

