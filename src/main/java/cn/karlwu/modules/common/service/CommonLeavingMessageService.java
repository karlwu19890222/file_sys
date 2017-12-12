package cn.karlwu.modules.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.modules.common.mapper.CommonLeavingMessageMapper;
import cn.karlwu.modules.common.pojo.CommonLeavingMessage;
import cn.karlwu.modules.common.pojo.CommonLeavingMessageExample.Criteria;
import cn.karlwu.modules.common.pojo.CommonLeavingMessageExample;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysUserService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: CommonLeavingMessageService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.common.service
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月17日 下午2:05:22
 * @version: V1.0  
 */
@Service
public class CommonLeavingMessageService implements BaseServiceInterface<CommonLeavingMessage> {
	
	@Autowired
	private CommonLeavingMessageMapper commonLeavingMessageMapper;
	@Autowired
	private SysUserService sysUserService;

	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		EasyUIResult result=new EasyUIResult();
		CommonLeavingMessageExample example=new CommonLeavingMessageExample();
		example.setOrderByClause("TIMESTAMP(update_date)  DESC");
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(map.get("objectId"))){
			criteria.andObjectIdEqualTo(map.get("objectId"));
		}else {
			criteria.andObjectIdEqualTo("未知");
		}
		List<CommonLeavingMessage> list=commonLeavingMessageMapper.selectByExample(example);
		for (CommonLeavingMessage commonLeavingMessage : list) {
			SysUser user=sysUserService.selectByPrimaryKey(commonLeavingMessage.getCreateBy());
			if(user!=null){
				commonLeavingMessage.setUserName(user.getName());
				commonLeavingMessage.setUserImg(user.getPortrait());
			}
		}
		result.setRows(list);
		result.setTotal(Long.valueOf(list.size()));
		return result;
	}

	@Override
	public void insert(CommonLeavingMessage record) {
		record.preInsert();
		commonLeavingMessageMapper.insert(record);
	}

	@Override
	public CommonLeavingMessage selectByPrimaryKey(String id) {
		return commonLeavingMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(CommonLeavingMessage record) {
		record.preUpdate();
		commonLeavingMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		commonLeavingMessageMapper.deleteByPrimaryKey(id);
	}

	public void deleteByObjectId(String objectId) {
		CommonLeavingMessageExample example=new CommonLeavingMessageExample();
		Criteria criteria =example.createCriteria();
		criteria.andObjectIdEqualTo(objectId);
		commonLeavingMessageMapper.deleteByExample(example);
	}

}

