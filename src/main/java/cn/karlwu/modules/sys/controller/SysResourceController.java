package cn.karlwu.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
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
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.sys.pojo.SysResource;
import cn.karlwu.modules.sys.service.SysResourceService;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: ResourceController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月28日 下午1:35:17
 * @version: V1.0
 */

@Controller
@RequestMapping("/sys/resource")
public class SysResourceController implements BaseControllerInterface<SysResource>{

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SysResourceController.class);

	@Autowired
	private SysResourceService resourceService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "sys/resourceListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, SysResource record) {
		return "sys/resourceInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, SysResource record) {
		SysResource resource = resourceService.selectByPrimaryKey(record.getId());
		model.addAttribute("resource", resource);
		return "sys/resourceUpdatePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value, SysResource record) {
		Map<String, String> map=new HashMap<>();
		return resourceService.selectByPage(page, rows, map);
	}

	@Override
	@RequestMapping("/insert")
	@ResponseBody
	public SunResult insert(SysResource record) {
		SunResult result = new SunResult();
		try {
			resourceService.insert(record);
			result.setStatus(200);
			result.setMsg("新增成功！");
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@Override
	@RequestMapping("/update")
	@ResponseBody
	public SunResult update(SysResource record) {
		SunResult result = new SunResult();
		try {
			resourceService.updateByPrimaryKeySelective(record);
			result.setStatus(200);
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@Override
	@RequestMapping("/delete")
	@ResponseBody
	public SunResult delete(String ids) {
		SunResult result = new SunResult();
		try {
			resourceService.deleteByPrimaryKey(ids);
			result.setStatus(200);
			result.setMsg("删除成功！");
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@Override
	public String toExportPage(Model model, SysResource record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@RequestMapping("/findAllTree")
	@ResponseBody
	public List<EUTreeNode> findAllTree() {
		return resourceService.findAllTree();
	}


}
