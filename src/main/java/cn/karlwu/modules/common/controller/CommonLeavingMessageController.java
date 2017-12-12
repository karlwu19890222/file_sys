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
import cn.karlwu.modules.common.pojo.CommonLeavingMessage;
import cn.karlwu.modules.common.service.CommonLeavingMessageService;
import cn.karlwu.modules.sys.controller.BaseController;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: CommonLeavingMessageController.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.common.controller
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月17日 下午2:05:30
 * @version: V1.0  
 */

@Controller
@RequestMapping("/common/leavingMessage")
public class CommonLeavingMessageController extends BaseController implements BaseControllerInterface<CommonLeavingMessage> {
	@Autowired
	private CommonLeavingMessageService commonLeavingMessageService;
	
	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "common/leavingMessageListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, CommonLeavingMessage record) {
		model.addAttribute("objectId", record.getObjectId());
        return "common/leavingMessageInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, CommonLeavingMessage record) {
		CommonLeavingMessage leavingMessage=commonLeavingMessageService.selectByPrimaryKey(record.getId());
		model.addAttribute("leavingMessage", leavingMessage);
        return "common/leavingMessageUpdatePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,
			String search_name, String search_value,CommonLeavingMessage record) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("objectId", record.getObjectId());
		return commonLeavingMessageService.selectByPage(page,rows,map);
	}

	@Override
	@RequestMapping("/insert")
    @ResponseBody
	public SunResult insert(CommonLeavingMessage record) {
		SunResult result = new SunResult();
        try {
        	commonLeavingMessageService.insert(record);
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
	public SunResult update(CommonLeavingMessage record) {
		SunResult result = new SunResult();
        try {
        	commonLeavingMessageService.updateByPrimaryKeySelective(record);
            result.setStatus(200);
            result.setMsg("更新完成");
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
        	CommonLeavingMessage message=commonLeavingMessageService.selectByPrimaryKey(ids);
        	if(getCurrentUser().getId().equals(message.getCreateBy())){
        		commonLeavingMessageService.deleteByPrimaryKey(ids);
                result.setStatus(200);
                result.setMsg("删除成功！");
                return result;
        	}else {
                result.setStatus(200);
                result.setMsg("只能删除自己的评论！");
                return result;
			}
        	
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	
	public SunResult deleteForAdmin(String ids) {
		SunResult result = new SunResult();
        try {
        	commonLeavingMessageService.deleteByPrimaryKey(ids);
            result.setStatus(200);
            result.setMsg("删除成功！");
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}
	
	@Override
	public String toExportPage(Model model, CommonLeavingMessage record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}

