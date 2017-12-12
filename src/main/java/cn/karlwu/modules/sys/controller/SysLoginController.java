package cn.karlwu.modules.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysUserService;
import cn.karlwu.modules.tools.service.FtpService;

/**
 * Copyright © 2017karlwu. All rights reserved.
 * 
 * @Title: LoginController.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.controller
 * @Description: TODO
 * @author: Karl
 * @date: 2017年6月27日 下午12:50:48
 * @version: V1.0
 */

@Controller
public class SysLoginController {
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLoginController.class);
	@Autowired
	private SysUserService userService;
	@Autowired
	private FtpService ftpService;

	/**
	 * 首页
	 */
	@RequestMapping(value = "/")
	public String index() {
		return "redirect:/index";
	}

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(Model model) {
		return "/index";
	}

	/**
	 * GET 登录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		LOGGER.info("GET请求登录");
		if (SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/index";
		}
		return "/login";
	}

	/**
	 * POST登陆
	 * 
	 * @param response
	 * @param username
	 * @param password
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpServletResponse response, String username,
			String password, HttpServletRequest request, Model model)
			throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		LOGGER.info("POST请求登录:");
		if (StringUtils.isBlank(username)) {
			model.addAttribute("msg", "用户名不能为空！");
			return "login";
		}
		if (StringUtils.isBlank(password)) {
			model.addAttribute("msg", "密码不能为空！");
			return "login";
		}
		Subject user = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				DigestUtils.md5Hex(password).toCharArray());
		token.setRememberMe(true);
		try {
			user.login(token);
			SysUser user2=userService.selectByLoginName(username);
			user2.setPortrait(ftpService.getServerFileUrl()+user2.getPortrait());
			model.addAttribute("user", user2);
			return "index";
		} catch (UnknownAccountException e) {
			LOGGER.error("账号不存在：{}", e);
			model.addAttribute("msg", "账号不存在");
			return "login";
		} catch (DisabledAccountException e) {
			LOGGER.error("账号未启用：{}", e);
			model.addAttribute("msg", "账号未启用");
			return "login";
		} catch (IncorrectCredentialsException e) {
			LOGGER.error("密码错误：{}", e);
			model.addAttribute("msg", "密码错误");
			return "login";
		} catch (RuntimeException e) {
			LOGGER.error("未知错误,请联系管理员：{}", e);
			model.addAttribute("msg", "未知错误,请联系管理员");
			return "login";
		}
	}

	/**
	 * 未授权
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/unauth")
	public String unauth(Model model) {
		if (SecurityUtils.getSubject().isAuthenticated() == false) {
			return "redirect:/login";
		}
		return "/unauth";
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout")
	@ResponseBody
	public SunResult logout(HttpServletRequest request) {
		LOGGER.info("登出");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return SunResult.ok();
	}
	
	

	/**
	 * GET 登录
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/workbench", method = RequestMethod.GET)
	public String workbench(Model model, HttpServletRequest request) {
		return "/workbench";
	}

}
