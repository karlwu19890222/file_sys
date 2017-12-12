package cn.karlwu.modules.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.modules.sys.mapper.SysMailMapper;
import cn.karlwu.modules.sys.pojo.SysMail;
import cn.karlwu.modules.sys.pojo.SysMailExample;
import cn.karlwu.modules.sys.pojo.SysMailExample.Criteria;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: MailService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年8月17日 下午2:16:13
 * @version: V1.0  
 */
@Service
public class SysMailService implements BaseServiceInterface<SysMail>{
	
	@Autowired
	private SysMailMapper sysMailMapper;

	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		SysMailExample example=new SysMailExample();
		PageHelper.startPage(page, rows);
		List<SysMail> list=sysMailMapper.selectByExample(example);
		EasyUIResult result=new EasyUIResult();
		result.setRows(list);
		PageInfo<SysMail> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	public SysMail findByValue(String text) {
		SysMailExample example=new SysMailExample();
		Criteria criteria=example.createCriteria();
		criteria.andTextEqualTo(text);
		List<SysMail> list=sysMailMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}

	
	@Override
	public SysMail selectByPrimaryKey(String id) {
		return sysMailMapper.selectByPrimaryKey(id);
	}
	

	@Override
	public void updateByPrimaryKeySelective(SysMail record) {
		record.preUpdate();
		sysMailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void insert(SysMail record) {
		record.preInsert();
		sysMailMapper.insert(record);
	}
	
	@Override
	public void deleteByPrimaryKey(String id) {
		sysMailMapper.deleteByPrimaryKey(id);
	}
	
}

