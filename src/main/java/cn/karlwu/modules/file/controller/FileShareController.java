package cn.karlwu.modules.file.controller;


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
import cn.karlwu.common.utils.ObjectCompare;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.common.service.CommonOperationLogService;
import cn.karlwu.modules.file.pojo.FileShare;
import cn.karlwu.modules.file.service.FileShareService;
import cn.karlwu.modules.sys.controller.BaseController;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: FileDiskSysController.java
 * @Prject: Karl_Sys
 * @Package: cn.karlwu.modules.file.controller
 * @Description: TODO
 * @author: KarlWu  
 * @date: 2017年11月21日 下午3:43:20
 * @version: V1.0  
 */
@Controller
@RequestMapping("/file/share")
public class FileShareController extends BaseController implements BaseControllerInterface<FileShare>{
	@Autowired
	private FileShareService fileShareService;
	@Autowired
	private CommonOperationLogService commonOperationLogService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "file/fileShareListPage";
	}
	
	@RequestMapping("/toPersonViewerListPage")
	public String toPersonViewerListPage(Model model) {
		return "file/fileSharePersonViewerListPage";
	}
	
	@RequestMapping("/toPersonManagerListPage")
	public String toPersonManagerListPage(Model model) {
		return "file/fileSharePersonManagerListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, FileShare record) {
		model.addAttribute("type", record.getType());
		return "file/fileShareInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, FileShare record) {
		FileShare fileShare=fileShareService.selectByPrimaryKey(record.getId());
        model.addAttribute("fileShare", fileShare);
		return "file/fileShareUpdatePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,
			String search_name, String search_value, FileShare record) {
		Map<String, String> map=new HashMap<String, String>();
		map.put(search_name, search_value);
		map.put("manager", record.getManager());
		map.put("viewer", record.getViewer());
		return fileShareService.selectByPage(page, rows, map);
	}

	@Override
	@RequestMapping("/insert")
	@ResponseBody
	public SunResult insert(FileShare record) {
		SunResult result = new SunResult();
        try {
        	
        	fileShareService.insert(record);
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
	public SunResult update(FileShare record) {
		SunResult result = new SunResult();
        try {
        	FileShare shareBefore=fileShareService.selectByPrimaryKey(record.getId());
        	fileShareService.updateByPrimaryKeySelective(record);
        	FileShare shareAfter=fileShareService.selectByPrimaryKey(record.getId());
        	System.out.println("shareBefore.name:"+shareBefore.getName());
        	System.out.println("shareAfter.name:"+shareAfter.getName());
        	
        	ObjectCompare<FileShare> compare=new ObjectCompare<FileShare>();
        	commonOperationLogService.insert(record.getId(), "更新",compare.contrastObj(shareBefore, shareAfter),"");
        	result.setMsg("更新完成");
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
        	fileShareService.deleteByPrimaryKey(ids);
        	result.setMsg("删除完成");
            result.setStatus(200);;
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}

	@Override
	public String toExportPage(Model model, FileShare record) {
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		return null;
	}

}

