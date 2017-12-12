package cn.karlwu.common.shiro;

import com.google.common.collect.Sets;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysRoleResourceService;
import cn.karlwu.modules.sys.service.SysUserRoleService;
import cn.karlwu.modules.sys.service.SysUserService;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShiroDbRealm extends AuthorizingRealm {
	@SuppressWarnings("unused")
	private static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);
	@Autowired
	private SysUserService userService;
	@Autowired
	private SysUserRoleService userRoleService;
	@Autowired
	private SysRoleResourceService roleResourceService;

	/**
	 * Shiro登录认证(原理：用户提交 用户名和密码 --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ----
	 * shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		SysUser user = userService.selectByLoginName(token.getUsername());
		// 账号不存在
		if (user == null) {
			return null;
		}
		String dept = "部门测试";
		List<String> roleList = userRoleService.findRoleIdListByUserId(user.getId());
		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getUname(),user.getName(), dept, roleList);
		return new SimpleAuthenticationInfo(shiroUser, user.getUpass().toCharArray(), getName());
		/*
		 * try {
		 * 
		 * } catch (Exception e) { // TODO: handle exception return null; }
		 */
	}

	/**
	 * Shiro权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		List<String> roleList = shiroUser.roleList;
		Set<String> urlSet = Sets.newHashSet();
		for (String roleId : roleList) {
			List<Map<String, String>> roleResourceList = roleResourceService.findRoleResourceListByRoleId(roleId);
			if (roleResourceList != null) {
				for (Map<String, String> map : roleResourceList) {
					if (StringUtils.isNoneBlank(map.get("url"))) {
						urlSet.add(map.get("url"));
					}
				}
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);
		return info;
	}
}
