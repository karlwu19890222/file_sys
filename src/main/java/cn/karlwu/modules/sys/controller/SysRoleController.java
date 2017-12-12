package cn.karlwu.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.common.utils.UUIDUtil;
import cn.karlwu.modules.sys.pojo.SysResource;
import cn.karlwu.modules.sys.pojo.SysRole;
import cn.karlwu.modules.sys.service.SysRoleService;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: RoleController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月28日 下午1:37:19
 * @version: V1.0
 */

@Controller
@RequestMapping("/sys/role")
@SuppressWarnings("unused")
public class SysRoleController  implements BaseControllerInterface<SysRole>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

	@Autowired
	private SysRoleService roleService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "sys/roleListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, SysRole record) {
		return "sys/roleInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, SysRole record) {
		SysRole role = roleService.selectByPrimaryKey(record.getId());
		model.addAttribute("role", role);
		return "sys/roleUpdatePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value, SysRole record) {
		Map<String, String> map=new HashMap<>();
		return roleService.selectByPage(page, rows, map);
	}

	@Override
	@RequestMapping("/insert")
	@ResponseBody
	public SunResult insert(SysRole record) {
		SunResult result = new SunResult();
		try {
			roleService.insert(record);
			result.setStatus(200);
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@Override
	@RequestMapping("/update")
	@ResponseBody
	public SunResult update(SysRole record) {
		SunResult result = new SunResult();
		try {
			roleService.updateByPrimaryKeySelective(record);
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
			roleService.deleteByPrimaryKey(ids);
			result.setStatus(200);
			result.setMsg("删除成功！");
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}

	@Override
	public String toExportPage(Model model, SysRole record) {
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		return null;
	}
	

	@RequestMapping("/toPowerPage")
	public String toPowerPage(HttpServletRequest request, String id, Model model) {
		model.addAttribute("id", id);
		return "sys/rolePowerPage";
	}
	
	@RequestMapping("/updatePower")
	@ResponseBody
	public SunResult updatePower(String id, String resourceIds) {
		SunResult result = new SunResult();
		try {
			roleService.updateRoleResource(id, resourceIds);
			result.setStatus(200);
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	@RequestMapping("/findResourceIdListByRoleId")
	@ResponseBody
	public SunResult findResourceByRoleId(HttpServletRequest request, String id) {
		SunResult result = new SunResult();
		try {
			List<String> resources = roleService.findResIdsByRoleId(id);
			result.setData(resources);
			result.setStatus(200);
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/findTree", method = RequestMethod.POST)
	@ResponseBody
	public List<EUTreeNode> findTree() {
		List<EUTreeNode> trees = roleService.findTree();
		return trees;
	}
}
