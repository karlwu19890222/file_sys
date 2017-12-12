package cn.karlwu.modules.sys.controller;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.DateUtils;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.common.utils.UUIDUtil;
import cn.karlwu.common.utils.excel.ExportExcel;
import cn.karlwu.common.utils.excel.ImportExcel;
import cn.karlwu.modules.sys.pojo.SysScheduleJob;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysUserRoleService;
import cn.karlwu.modules.sys.service.SysUserService;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: UserController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年6月27日 下午3:34:45
 * @version: V1.0  
 */
@Controller
@RequestMapping("/sys/user")
@SuppressWarnings("unused")
public class SysUserController extends BaseController  implements BaseControllerInterface<SysUser>{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService userService;
	@Autowired
	private SysUserRoleService userRoleService;
	
	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "sys/userListPage";
	}
	
	@Override
	@RequestMapping(value = "/toInsertPage", method = RequestMethod.GET)
	public String toInsertPage(Model model, SysUser record) {
		return "/sys/userInsertPage";
	}
	
	@Override
	@RequestMapping(value = "/toUpdatePage", method = RequestMethod.GET)
	public String toUpdatePage(Model model, SysUser record) {
		SysUser user = userService.selectByPrimaryKey(record.getId());
		List<String> ids = userRoleService.findRoleIdListByUserId(record.getId());
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", user);
		return "/sys/userUpdatePage";
	}
	
	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows, String search_name,String search_value, SysUser record) {
		Map<String,String> map=new HashMap<>();
		map.put(search_name, search_value);
		return userService.selectByPage(page, rows,map);
	}
	
	
	@Override
	@RequestMapping("/insert")
	@ResponseBody
	public SunResult insert(SysUser record) {
		SunResult result = new SunResult();
		try {
			SysUser user=userService.selectByLoginName(record.getUname());
			if(user!=null){
				result.setMsg("登录名已存在，添加失败！");
				result.setStatus(200);
				return result;
			}else {
				record.setUpass(DigestUtils.md5Hex("123456"));
				userService.insert(record);
				result.setMsg("添加完成！");
				result.setStatus(200);
				return result;
			}
			
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	
	@Override
	@RequestMapping("/update")
	@ResponseBody
	public SunResult update(SysUser record) {
		SunResult result = new SunResult();
		try {
			userService.updateByPrimaryKeySelective(record);
			result.setMsg("更新完成！");
			result.setStatus(200);
			return result;
		} catch (RuntimeException e) {
			result.setStatus(300);
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
			userService.deleteByPrimaryKey(ids);
			result.setStatus(200);
			result.setMsg("删除成功！");
			return result;
		} catch (RuntimeException e) {
			result.setStatus(300);
			result.setMsg(e.getMessage());
			return result;
		}
	}
	
	@Override
	@RequestMapping(value = "/toImportPage", method = RequestMethod.GET)
	public String toExportPage(Model model, SysUser record) {
		return "/sys/userImportPage";
	}
	
	@Override
    @RequestMapping(value = "export", method=RequestMethod.GET)
	public String export(HttpServletResponse response) {
		try {
            String fileName = "用户信息"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            List<SysUser> list=userService.findListAll();
    		new ExportExcel("用户信息", SysUser.class).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			return null;
		}
	}

	@RequestMapping(value = "/toUpdateUserImg", method = RequestMethod.GET)
	public String toUpdateUserImg( Model model) {
		SysUser user = userService.selectByPrimaryKey(getCurrentUser().getId());
		model.addAttribute("user", user);
		return "/sys/updateUserImgPage";
	}
	
	@RequestMapping("/findListAll")
	@ResponseBody
	public EasyUIResult findListAll() {
		EasyUIResult result =new EasyUIResult();
		List<SysUser> list=userService.findListAll();
		result.setRows(list);
		result.setTotal(Long.valueOf(list.size()));
		return result;
	}
	
	@RequestMapping(value = "/toEditPwdPage", method = RequestMethod.GET)
	public String editPwdPage() {
		return "sys/userEditPwdPage";
	}
	

	/**
	 * 修改密码
	 */
	@RequestMapping("/editUserPwd")
	@ResponseBody
	public SunResult editUserPwd(HttpServletRequest request, String oldPwd,String pwd) {
		SunResult result = new SunResult();
		try {
			if (!getCurrentUser().getUpass().equals(DigestUtils.md5Hex(oldPwd))) {
				result.setMsg("老密码不正确!");
				return result;
			}
			SysUser user = new SysUser();
			user.setId(getCurrentUser().getId());
			user.setUpass(DigestUtils.md5Hex(pwd));
			userService.updatePwd(user);
			result.setStatus(200);
			result.setMsg("密码修改成功！");
			return result;
		} catch (Exception e) {
			result.setStatus(300);
			result.setMsg("修改失败！");
			return result;
		}
	}
	
	
	/**
	 * 密码重置()
	 */
	@RequestMapping("/updatePwdForAdmin")
	@ResponseBody
	public SunResult updatePwdForAdmin(HttpServletRequest request) {
		SunResult result = new SunResult();
		try {
			SysUser user = new SysUser();
			user.setId(getCurrentUser().getId());
			user.setUpass(DigestUtils.md5Hex("123456"));
			userService.updatePwd(user);
			result.setStatus(200);
			result.setMsg("密码重置为“123456”！");
			return result;
		} catch (Exception e) {
			result.setStatus(300);
			result.setMsg("修改失败！");
			return result;
		}
	}
	
	
	/**
	 * 更新头像
	 */
	@RequestMapping("/updateUserImg")
	@ResponseBody
	public SunResult updateUserImg(HttpServletRequest request,SysUser user) {
		SunResult result = new SunResult();
		try {
			userService.updatePwd(user);
			result.setStatus(200);
			result.setMsg("更新完成，登出后生效”！");
			return result;
		} catch (Exception e) {
			result.setStatus(300);
			result.setMsg("修改失败！");
			return result;
		}
	}
	
	
	@RequestMapping(value = "/findUserStrByDeptId", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String userStrByDeptId(String deptId) {
		String deptStr = userService.findUserStrsByOrgId(deptId);
		return deptStr;
	}

}

