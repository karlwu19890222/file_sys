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
import cn.karlwu.modules.sys.pojo.SysDictionary;
import cn.karlwu.modules.sys.service.SysDictionaryService;

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
@RequestMapping("/sys/dict")
public class SysDictionaryController implements BaseControllerInterface<SysDictionary> {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(SysDictionaryController.class);
	
	@Autowired
	private SysDictionaryService sysDictionaryService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "/sys/dictListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, SysDictionary record) {
		return "/sys/dictInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, SysDictionary record) {
		SysDictionary dict=sysDictionaryService.selectByPrimaryKey(record.getId());
		model.addAttribute("dict", dict);
		return "/sys/dictUpdatePage";
	}
	
	@Override
	public String toExportPage(Model model, SysDictionary record) {
		return null;
	}	
	

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value, SysDictionary record) {
		Map<String, String> map=new HashMap<>();
		map.put(search_name, search_value);
		return sysDictionaryService.selectByPage(page, rows, map);
	}

	@Override
	@RequestMapping("/insert")
	@ResponseBody
	public SunResult insert(SysDictionary record) {
		SunResult sunResult=new SunResult();
		try {
			sysDictionaryService.insert(record);
			sunResult.setStatus(200);
			sunResult.setMsg("添加完成");
			return sunResult;
		} catch (Exception e) {
			sunResult.setStatus(300);
			sunResult.setMsg(e.toString());
			return sunResult;
		}
	}

	@Override
	@RequestMapping("/update")
	@ResponseBody
	public SunResult update(SysDictionary record) {
		SunResult sunResult=new SunResult();
		try {
			sysDictionaryService.updateByPrimaryKeySelective(record);
			sunResult.setStatus(200);
			sunResult.setMsg("更新完成");
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
	
	
    @RequestMapping(value = "/selectTreesTextKey", method = RequestMethod.POST)
    @ResponseBody
    public List<EUTreeNode> selectTreesTextKey(String textKey) {
        List<EUTreeNode> trees = sysDictionaryService.selectTreesTextKey(textKey);
        return trees;
    }
}

