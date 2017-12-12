package cn.karlwu.modules.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import cn.karlwu.common.shiro.ShiroUser;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysUserService;

public class BaseController {
	
	@Autowired
    private SysUserService userService;
 

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }
    /**
     * 获取当前登录用户对象
     * @return
     */
    public SysUser getCurrentUser() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        SysUser currentUser = userService.selectByPrimaryKey(user.id);
        return currentUser;
    }
}
