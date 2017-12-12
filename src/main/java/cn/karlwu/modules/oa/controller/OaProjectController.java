package cn.karlwu.modules.oa.controller;

import java.util.Date;
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
import cn.karlwu.modules.common.service.CommonOperationLogService;
import cn.karlwu.modules.oa.pojo.OaProject;
import cn.karlwu.modules.oa.service.OaProjectService;
import cn.karlwu.modules.sys.controller.BaseController;
import cn.karlwu.modules.sys.service.SysDictionaryService;
import cn.karlwu.modules.sys.service.SysUserService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: OaProjectController.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.oa.controller
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月17日 上午10:30:07
 * @version: V1.0  
 */
@Controller
@RequestMapping("/oa/project")
public class OaProjectController extends BaseController implements BaseControllerInterface<OaProject>{
	
	@Autowired
	private OaProjectService oaProjectService;
	@Autowired
	private CommonOperationLogService commonOperationLogService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysDictionaryService sysDictionaryService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "oa/projectListPage";
	}
	
	@RequestMapping("/toEnjoyListPage")
	public String toEnjoyListPage(Model model) {
		return "oa/projectEnjoyListPage";
	}
	
	@RequestMapping("/toCreateListPage")
	public String toCreateListPage(Model model) {
		return "oa/projectCreateListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, OaProject record) {
        return "oa/projectInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, OaProject record) {
		OaProject project=oaProjectService.selectByPrimaryKey(record.getId());
		model.addAttribute("project", project);
        return "oa/projectUpdatePage";
	}
	

	@RequestMapping("/toUpdateStatePage")
	public String toUpdateStatePage(Model model, String id,String state) {
		model.addAttribute("id", id);
		model.addAttribute("state", state);
        return "oa/projectUpdateStatePage";
	}
	
	@RequestMapping("/toUpdateDesPage")
	public String toUpdateDesPage(Model model, String id) {
		OaProject project=oaProjectService.selectByPrimaryKey(id);
		model.addAttribute("project", project);
        return "oa/projectUpdateDesPage";
	}
	
	
	@RequestMapping("/toUpdateProcessSpeedPage")
	public String toUpdateProcessSpeedPage(Model model, String id,String state) {
		OaProject project=oaProjectService.selectByPrimaryKey(id);
		model.addAttribute("project", project);
        return "oa/projectUpdateProcessSpeedPage";
	}
	
	
	@RequestMapping("/toUpdateEnjoyPage")
	public String projectUpdateEnjoysPage(Model model, String id,String state) {
		OaProject project=oaProjectService.selectByPrimaryKey(id);
		model.addAttribute("project", project);
        return "oa/projectUpdateEnjoysPage";
	}


	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,
			String search_name, String search_value,OaProject record) {
		Map<String, String> map=new HashMap<String, String>();
		map.put(search_name, search_value);
		map.put("createBy", record.getCreateBy());
		map.put("enjoyUserids", record.getEnjoyUserids());
		return oaProjectService.selectByPage(page,rows,map);
	}

	@Override
	@RequestMapping("/insert")
    @ResponseBody
	public SunResult insert(OaProject record) {
		SunResult result = new SunResult();
        try {
        	record.setStartBy(getCurrentUser().getId());
        	record.setStartTime(new Date());
        	record.setState("暂停");
        	record.setSpeedProcess(0.0);
        	oaProjectService.insert(record);
        	result.setMsg("创建完成");
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
	public SunResult update(OaProject record) {
		SunResult result = new SunResult();
        try {
        	oaProjectService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("更新完成");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	
	@RequestMapping("/updateState")
    @ResponseBody
	public SunResult updateState(OaProject record,String reason) {
		SunResult result = new SunResult();
        try {
        	commonOperationLogService.insert(record.getId(), "状态更新", record.getState()+":"+reason,"");
        	oaProjectService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("更新完成");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	@RequestMapping("/updateDes")
    @ResponseBody
	public SunResult updateDes(OaProject record) {
		SunResult result = new SunResult();
        try {
        	commonOperationLogService.insert(record.getId(), "内容更新", record.getDes(), "");
        	oaProjectService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("更新完成");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	@RequestMapping("/updateSpeed")
    @ResponseBody
	public SunResult updateSpeed(OaProject record) {
		SunResult result = new SunResult();
        try {
        	if(String.valueOf(record.getSpeedProcess()).contains("100")){
        		record.setOverTime(new Date());
        		record.setState("完成");
        	}
        	commonOperationLogService.insert(record.getId(), "进度更新", record.getSpeedProcess()+"%", "");
        	oaProjectService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("更新完成");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	@RequestMapping("/updateEnjoy")
    @ResponseBody
	public SunResult updateEnjoy(OaProject record) {
		SunResult result = new SunResult();
        try {
        	commonOperationLogService.insert(record.getId(), "参与人员更新", sysUserService.transIdsToNames(record.getEnjoyUserids()), "");
        	oaProjectService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("更新完成");
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
        	oaProjectService.deleteByPrimaryKey(ids);
            result.setStatus(200);
            result.setMsg("删除成功！");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}

	@Override
	public String toExportPage(Model model, OaProject record) {
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		return null;
	}
	
	
	
}

