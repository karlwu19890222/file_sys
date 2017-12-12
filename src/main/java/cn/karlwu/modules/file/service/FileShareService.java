package cn.karlwu.modules.file.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.modules.file.pojo.FileShareExample.Criteria;
import cn.karlwu.modules.common.service.CommonAttachmentService;
import cn.karlwu.modules.common.service.CommonOperationLogService;
import cn.karlwu.modules.file.mapper.FileShareMapper;
import cn.karlwu.modules.file.pojo.FileShare;
import cn.karlwu.modules.file.pojo.FileShareExample;
import cn.karlwu.modules.sys.service.SysUserService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: FileShareService.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.file.service
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月23日 下午12:57:48
 * @version: V1.0  
 */

@Service
public class FileShareService  implements BaseServiceInterface<FileShare> {

	@Autowired
	private FileShareMapper fileShareMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CommonOperationLogService commonOperationLogService;
	@Autowired
	private CommonAttachmentService commonAttachmentService;
	
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows,Map<String, String> map) {
		FileShareExample example=new FileShareExample();
		example.setOrderByClause("TIMESTAMP(update_date) DESC");
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(map.get("number"))){
			criteria.andNumberLike("%"+map.get("number")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("name"))){
			criteria.andNameLike("%"+map.get("name")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("zName"))){
			criteria.andZNameLike("%"+map.get("zName")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("manager"))){
			criteria.andManagerLike("%"+map.get("manager")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("zName"))){
			criteria.andZNameLike("%"+map.get("zName")+"%");
		}
		if(StringUtils.isNotEmpty(map.get("viewer"))){
			criteria.andViewerLike("%"+map.get("viewer")+"%");
		}
		EasyUIResult result=new EasyUIResult();
		PageHelper.startPage(page, rows);
		List<FileShare> list=fileShareMapper.selectByExample(example);
		for (FileShare fileShare : list) {
			fileShare.setViewerNames(sysUserService.transIdsToNames(fileShare.getViewer()));
			fileShare.setDownerNames(sysUserService.transIdsToNames(fileShare.getDowner()));
			fileShare.setManagerName(sysUserService.transIdsToNames(fileShare.getManager()));
		}
		result.setRows(list);
		PageInfo<FileShare> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public void insert(FileShare record) {
		record.preInsert();
		commonOperationLogService.insert(record.getId(), "创建","","");
		fileShareMapper.insert(record);
	}

	@Override
	public FileShare selectByPrimaryKey(String id) {
		return fileShareMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(FileShare record) {
		record.preUpdate();
		fileShareMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		fileShareMapper.deleteByPrimaryKey(id);
		commonOperationLogService.deleteByObjectId(id);
		commonAttachmentService.deleteByObjectId(id);
	}

}

