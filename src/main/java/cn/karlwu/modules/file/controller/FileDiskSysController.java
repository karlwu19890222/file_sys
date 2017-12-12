package cn.karlwu.modules.file.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EUTreeNode;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.file.pojo.FileDiskSys;
import cn.karlwu.modules.file.service.FileDiskSysService;
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
@RequestMapping("/file/disk/sys")
public class FileDiskSysController extends BaseController implements BaseControllerInterface<FileDiskSys>{
	@Autowired
	private FileDiskSysService fileDiskSysService;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "file/diskSysListPage";
	}
	
	@RequestMapping("/toPersonListPage")
	public String toPersonListPage(Model model) {
		FileDiskSys record=new FileDiskSys();
		record.setCreateBy(getCurrentUser().getId());
		record.setName("文件根目录");
		record.setType("person");
		FileDiskSys fileDiskSys =fileDiskSysService.selectByNameAndCreateBy(record);
		if(fileDiskSys==null){
			fileDiskSysService.insert(record);
		}
		return "file/diskPersonSysListPage";
	}

	@Override
	@RequestMapping("/toInsertPage")
	public String toInsertPage(Model model, FileDiskSys record) {
		model.addAttribute("type", record.getType());
		return "file/diskSysInsertPage";
	}

	@Override
	@RequestMapping("/toUpdatePage")
	public String toUpdatePage(Model model, FileDiskSys record) {
        FileDiskSys diskSys=fileDiskSysService.selectByPrimaryKey(record.getId());
        model.addAttribute("diskSys", diskSys);
		return "file/diskSysUpdatePage";
	}
	
	
	@RequestMapping("/toMoveFilePage")
	public String toUpdatePage(Model model, String id,String type,String objectId) {
        model.addAttribute("id", id);
        model.addAttribute("type", type);
        model.addAttribute("objectId", objectId);
		return "file/diskSysMoveFilePage";
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,
			String search_name, String search_value, FileDiskSys record) {
		return null;
	}

	@Override
	@RequestMapping("/insert")
	@ResponseBody
	public SunResult insert(FileDiskSys record) {
		SunResult result = new SunResult();
        try {
        	fileDiskSysService.insert(record);
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
	public SunResult update(FileDiskSys record) {
		SunResult result = new SunResult();
        try {
        	fileDiskSysService.updateByPrimaryKeySelective(record);
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
        	fileDiskSysService.deleteByPrimaryKey(ids);
        	result.setMsg("删除完成");
            result.setStatus(200);;
            return result;
        } catch (RuntimeException e) {
        	 result.setMsg(e.getMessage());
             return result;
        }
	}

	@Override
	public String toExportPage(Model model, FileDiskSys record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	@RequestMapping("/findGrandFatherTrees")
	@ResponseBody
	public List<EUTreeNode> findGrandFatherTrees(String type,String id) {
		try {
			if(StringUtils.isEmpty(id)){
				return fileDiskSysService.findGrandFatherTrees(type);
			}else {
				return fileDiskSysService.findTreesByFatherId(id);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping("/findAllTreeForSys")
	@ResponseBody
	public List<EUTreeNode> findAllTreeForSys(String type) {
		
		if(type.equals("person")){
			return fileDiskSysService.findAllTreeForPerson(type,getCurrentUser().getId());
		}else {
			return fileDiskSysService.findAllTreeForSys(type);
		}
		
		
	}

}

