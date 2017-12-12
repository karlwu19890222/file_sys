package cn.karlwu.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.modules.sys.mapper.SysLogMapper;
import cn.karlwu.modules.sys.pojo.SysLog;
import cn.karlwu.modules.sys.pojo.SysLogExample;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: SysLogService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年6月29日 下午1:14:14
 * @version: V1.0  
 */

@Service
public class SysLogService implements BaseServiceInterface<SysLog>{
	@Autowired
	private SysLogMapper sysLogMapper;
	
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		SysLogExample example=new SysLogExample();
		example.setOrderByClause("id desc");
		PageHelper.startPage(page, rows);
		List<SysLog> list = sysLogMapper.selectByExample(example);
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		PageInfo<SysLog> pageInfo = new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	

	@Override
	public void insert(SysLog record) {
		record.preInsert();
		sysLogMapper.insert(record);
	}

	@Override
	public SysLog selectByPrimaryKey(String id) {
		return sysLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(SysLog record) {
		record.preUpdate();
		sysLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		sysLogMapper.deleteByPrimaryKey(id);
	}
	
}

