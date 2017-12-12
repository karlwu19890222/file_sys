package cn.karlwu.modules.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.modules.common.pojo.CommonAttachmentExample.Criteria;
import cn.karlwu.modules.common.mapper.CommonAttachmentMapper;
import cn.karlwu.modules.common.pojo.CommonAttachment;
import cn.karlwu.modules.common.pojo.CommonAttachmentExample;
import cn.karlwu.modules.sys.service.SysUserService;
import cn.karlwu.modules.tools.service.FtpService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: SysAttachmentService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年9月19日 下午12:37:30
 * @version: V1.0  
 */

@Service
public class CommonAttachmentService implements BaseServiceInterface<CommonAttachment>{
	@Autowired
	private CommonAttachmentMapper commonAttachmentMapper;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CommonLeavingMessageService commonLeavingMessageService;
	@Autowired
	private CommonOperationLogService commonOperationLogService;
    @Autowired
	private FtpService ftpService;
    
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		EasyUIResult result=new EasyUIResult();
		CommonAttachmentExample example=new CommonAttachmentExample();
		example.setOrderByClause("TIMESTAMP(update_date)  DESC");
		Criteria criteria=example.createCriteria();
		if(StringUtils.isNotEmpty(map.get("objectId"))){
			criteria.andObjectIdEqualTo(map.get("objectId"));
		}else {
			criteria.andObjectIdEqualTo("未知");
		}
		List<CommonAttachment> list=commonAttachmentMapper.selectByExample(example);
		for (CommonAttachment commonAttachment : list) {
			commonAttachment.setCreateBy(sysUserService.transIdsToNames(commonAttachment.getCreateBy()));
			if(StringUtils.isNotEmpty(commonAttachment.getName())){
				if(commonAttachment.getName().contains(".")){
					String type=commonAttachment.getName().substring(commonAttachment.getName().lastIndexOf(".")).toUpperCase();
					if(StringUtils.isNotEmpty(type)){
						if(".PDF.JPG.JPEG.PNG".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/picture.png");
						}else if(".XLSX.XLS".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/excel.png");
						}else if(".DOCX.DOC".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/word.png");
						}else if(".PPT.PPTX".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/ppt.png");
						}else if(".PDF".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/pdf.png");
						}else if(".RM.RMVB.WMV.AVI.MP4.3PG.MKV".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/video.png");
						}else if(".RAR.ZIP.7-ZIP.ISO".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/rar.png");
						}else if(".TXT".contains(type)){
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/txt.png");
						}else {
							commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/unknown.png");
						}
					}
				}else{
					commonAttachment.setRemarks(ftpService.getServerFileUrl()+"/common/icon/unknown.png");
				}
			}
		}
		result.setRows(list);
		result.setTotal(Long.valueOf(list.size()));
		return result;
	}

	@Override
	public void insert(CommonAttachment record) {
		record.preInsert();
		commonAttachmentMapper.insert(record);
	}

	@Override
	public CommonAttachment selectByPrimaryKey(String id) {
		return commonAttachmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateByPrimaryKeySelective(CommonAttachment record) {
		record.preUpdate();
		commonAttachmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public void deleteByPrimaryKey(String id) {
		commonAttachmentMapper.deleteByPrimaryKey(id);
		commonLeavingMessageService.deleteByObjectId(id);
		commonOperationLogService.deleteByObjectId(id);
	}

	public void deleteByObjectId(String objectId) {
		CommonAttachmentExample example=new CommonAttachmentExample();
		Criteria criteria =example.createCriteria();
		criteria.andObjectIdEqualTo(objectId);
		commonAttachmentMapper.deleteByExample(example);
		commonLeavingMessageService.deleteByObjectId(objectId);
		commonOperationLogService.deleteByObjectId(objectId);
	}

}

