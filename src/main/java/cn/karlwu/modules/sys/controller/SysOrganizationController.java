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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.sys.pojo.SysOrganization;
import cn.karlwu.modules.sys.service.SysOrganizationService;
/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: DeptController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年6月28日 下午1:29:14
 * @version: V1.0  
 */

@Controller
@RequestMapping("/sys/organization")
public class SysOrganizationController implements BaseControllerInterface<SysOrganization>{
	
	@Autowired
	private  SysOrganizationService organizationService;
    
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SysOrganizationController.class);

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "sys/organizationListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, SysOrganization record) {
		return "sys/organizationInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, SysOrganization record) {
		SysOrganization organization=organizationService.selectByPrimaryKey(record.getId());
		model.addAttribute("organization", organization);
        return "sys/organizationUpdatePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value,SysOrganization record) {
		Map<String, String> map=new HashMap<>();
		return organizationService.selectByPage(page, rows, map);
	}

	@Override
	@RequestMapping("/insert")
    @ResponseBody
	public SunResult insert(SysOrganization record) {
		SunResult result = new SunResult();
		
		organizationService.insert(record);
        result.setStatus(200);;
        return result;
        /*try {
        	organizationService.insert(record);
            result.setStatus(200);;
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }*/
	}

	@Override
	@RequestMapping("/update")
    @ResponseBody
	public SunResult update(SysOrganization record) {
		SunResult result = new SunResult();
        try {
        	organizationService.updateByPrimaryKeySelective(record);
            result.setStatus(200);;
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
        	organizationService.deleteByPrimaryKey(ids);
            result.setStatus(200);
            result.setMsg("删除成功！");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}

	@Override
	public String toExportPage(Model model, SysOrganization record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	    
	    
    /** 部门资源树  **/
    @RequestMapping("/getOrgFatherTree")
    @ResponseBody
	public String getOrgFatherTree(Model model) {
		String dept = organizationService.getOrgFatherTree();
		return dept;
	}
    
    /** 部门资源树  **/
    @RequestMapping(value = "/finOrgAllTree", method = RequestMethod.POST)
    @ResponseBody
    public List<EUTreeNode> finOrgAllTree() {
        List<EUTreeNode> trees = organizationService.finOrgAllTree();
        return trees;
    }
    
    /** 人员资源树  **/
    @RequestMapping(value = "/getOrgTreeWithUsers", method = RequestMethod.POST)
    @ResponseBody
    public List<EUTreeNode> findTreeUsers() {
        List<EUTreeNode> trees = organizationService.getOrgTreeWithUsers();
        return trees;
    }

}

