package cn.karlwu.modules.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.utils.EasyUIResult;
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
@RequestMapping("/common/log")
public class CommonOperationLogController{
	@Autowired
	private CommonOperationLogService commonOperationLogService;

	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,
			String search_name, String search_value,CommonAttachment record) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("objectId", record.getObjectId());
		return commonOperationLogService.selectByPage(page,rows,map);
	}
}

