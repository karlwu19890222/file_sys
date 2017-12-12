package cn.karlwu.modules.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.sys.pojo.SysLog;
import cn.karlwu.modules.sys.service.SysLogService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: SysLogController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年6月29日 下午1:13:02
 * @version: V1.0  
 */

@Controller
@RequestMapping("/sys/log")
public class SysLogController implements BaseControllerInterface<SysLog> {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogController.class);
	
	@Autowired
	private SysLogService sysLogService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "/sys/logListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, SysLog record) {
		return "/sys/logInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, SysLog record) {
		return "/sys/logUpdatePage";
	}
	
	@Override
	public String toExportPage(Model model, SysLog record) {
		return null;
	}	
	

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value, SysLog record) {
		Map<String, String> map=new HashMap<>();
		return sysLogService.selectByPage(page, rows, map);
	}

	@Override
	public SunResult insert(SysLog record) {
		SunResult sunResult=new SunResult();
		try {
			sysLogService.insert(record);
			return sunResult;
		} catch (Exception e) {
			sunResult.setStatus(300);
			sunResult.setMsg(e.toString());
			return sunResult;
		}
	}

	@Override
	public SunResult update(SysLog record) {
		SunResult sunResult=new SunResult();
		try {
			sysLogService.updateByPrimaryKeySelective(record);
			return sunResult;
		} catch (Exception e) {
			sunResult.setStatus(300);
			sunResult.setMsg(e.toString());
			return sunResult;
		}
	}

	@Override
	public String export(HttpServletResponse response) {
		return null;
	}

	@Override
	public SunResult delete(String ids) {
		// TODO Auto-generated method stub
		return null;
	}


}

