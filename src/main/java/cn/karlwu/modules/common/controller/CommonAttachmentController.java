package cn.karlwu.modules.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.common.service.CommonAttachmentService;
import cn.karlwu.modules.common.service.CommonOperationLogService;
import cn.karlwu.modules.common.pojo.CommonAttachment;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: SysAttachmentController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年9月19日 下午12:37:38
 * @version: V1.0  
 */

@Controller
@RequestMapping("/common/attachment")
public class CommonAttachmentController implements BaseControllerInterface<CommonAttachment>{
	
	@Autowired
	private CommonAttachmentService commonAttachmentService;
	@Autowired
	private CommonOperationLogService commonOperationLogService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "common/attachmentListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, CommonAttachment record) {
		model.addAttribute("objectId", record.getObjectId());
        return "common/attachmentInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, CommonAttachment record) {
		CommonAttachment attachment=commonAttachmentService.selectByPrimaryKey(record.getId());
		model.addAttribute("attachment", attachment);
        return "common/attachmentUpdatePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,
			String search_name, String search_value,CommonAttachment record) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("objectId", record.getObjectId());
		return commonAttachmentService.selectByPage(page,rows,map);
	}

	@Override
	@RequestMapping("/insert")
    @ResponseBody
	public SunResult insert(CommonAttachment record) {
		SunResult result = new SunResult();
        try {
        	commonAttachmentService.insert(record);
        	commonOperationLogService.insert(record.getObjectId(), "上传附件", record.getName(), record.getFileUrl());
        	result.setMsg("附件上传完成");
            result.setStatus(200);;
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}

	@Override
	@RequestMapping("/update")
    @ResponseBody
	public SunResult update(CommonAttachment record) {
		SunResult result = new SunResult();
        try {
        	commonAttachmentService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("附件更新完成");
            commonOperationLogService.insert(record.getObjectId(), "更新附件", record.getName(), record.getFileUrl());
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	

	@RequestMapping("/delete")
    @ResponseBody
	public SunResult delete(String ids) {
		SunResult result = new SunResult();
        try {
        	commonAttachmentService.deleteByPrimaryKey(ids);
            result.setStatus(200);
            result.setMsg("删除成功！");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	

	@Override
	public String toExportPage(Model model, CommonAttachment record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

