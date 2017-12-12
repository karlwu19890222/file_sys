package cn.karlwu.modules.sys.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.sys.pojo.SysMail;
import cn.karlwu.modules.sys.service.SysMailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: SysMailController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年8月17日 下午2:15:39
 * @version: V1.0  
 */

@Controller
@RequestMapping("/sys/mail")
public class SysMailController implements BaseControllerInterface<SysMail>{
	
    private static final Logger LOGGER = LoggerFactory.getLogger(SysMailController.class);
    @Autowired
    private SysMailService mailService;
    
	@Override
	@RequestMapping(value = "/toListPage", method = RequestMethod.GET)
	public String toListPage(Model model) {
		return "/sys/mailListPage";
	}
	
	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, SysMail record) {
		return "sys/mailInsertPage";
	}
	
	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, SysMail record) {
		SysMail mail = mailService.selectByPrimaryKey(record.getId());
		model.addAttribute("mail", mail);
		return "sys/mailUpdatePage";
	}
	
	@Override
	@RequestMapping(value = "/selectByPage", method = RequestMethod.POST)
    @ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value,SysMail record) {
		Map<String, String> map=new HashMap<>();
        return mailService.selectByPage(page, rows, map);
	}
	
	@Override
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
	public SunResult insert(SysMail record) {
		SunResult result = new SunResult();
    	try {
    		mailService.insert(record);
            result.setMsg("添加成功！");
            result.setStatus(200);
            return result;
        } catch (RuntimeException e) {
            LOGGER.info("添加失败：{}", e);
            result.setMsg(e.getMessage());
            result.setStatus(300);
            return result;
        }
	}
	
	@Override
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
	public SunResult update(SysMail record) {
		SunResult result = new SunResult();
    	try {
    		mailService.updateByPrimaryKeySelective(record);
    	    result.setStatus(200);
            result.setMsg("更新成功！");
            return result;
        } catch (RuntimeException e) {
            LOGGER.info("更新失败：{}", e);
            result.setStatus(300);
            result.setMsg(e.getMessage());
            return result;
        }
	}
	
	@Override
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
	public SunResult delete(String ids) {
		SunResult result = new SunResult();
		try {
			mailService.deleteByPrimaryKey(ids);
			result.setStatus(200);
			result.setMsg("删除成功！");
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	
	@Override
	public String toExportPage(Model model, SysMail record) {
		return null;
	}
	
	@Override
	public String export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
 
}

