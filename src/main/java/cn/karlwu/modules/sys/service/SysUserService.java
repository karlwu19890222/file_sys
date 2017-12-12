package cn.karlwu.modules.sys.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.karlwu.common.persistence.BaseServiceInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.StringUtils;
import cn.karlwu.modules.sys.mapper.SysUserMapper;
import cn.karlwu.modules.sys.pojo.SysOrganization;
import cn.karlwu.modules.sys.pojo.SysRole;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.pojo.SysUserExample;
import cn.karlwu.modules.sys.pojo.SysUserExample.Criteria;
import cn.karlwu.modules.sys.pojo.SysUserRole;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: UserServcie.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年6月27日 下午12:56:44
 * @version: V1.0  
 */

@Service
public class SysUserService implements BaseServiceInterface<SysUser>{
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysUserRoleService useRoleService;
	@Autowired
	private SysRoleService roleService;
	@Autowired
	private SysOrganizationService organizationService;
	@Override
	public EasyUIResult selectByPage(Integer page, Integer rows, Map<String, String> map) {
		SysUserExample example=new SysUserExample();
		example.setOrderByClause("organization_id desc");
		PageHelper.startPage(page, rows);
		List<SysUser> list=userMapper.selectByExample(example);
		for (SysUser sunvouUser : list) {
			List<SysUserRole> roles=useRoleService.findUserRoleByUserId(sunvouUser.getId());
			if(roles!=null && roles.size()>0){
				String rolenames="";
				for (SysUserRole sunvouUserRole : roles) {
					SysRole role=roleService.selectByPrimaryKey(sunvouUserRole.getRoleId());
					if(role!=null){
						rolenames+=role.getName()+",";
					}
				}
				sunvouUser.setRolenames(rolenames);
			}
			if(sunvouUser.getOrganizationId()!=null){
				SysOrganization organization=organizationService.selectByPrimaryKey(sunvouUser.getOrganizationId());
				if(organization!=null){
					sunvouUser.setOrganizationname(organization.getName());
				}
			}
		}
		EasyUIResult result=new EasyUIResult();
		result.setRows(list);
		PageInfo<SysUser> pageInfo=new PageInfo<>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	
	public List<SysUser> findListAll() {
		SysUserExample example=new SysUserExample();
		example.setOrderByClause("organization_id desc");
		Criteria criteria=example.createCriteria();
		criteria.andSituationNotEqualTo("离职");
		List<SysUser> list=userMapper.selectByExample(example);
		for (SysUser sunvouUser : list) {
			List<SysUserRole> roles=useRoleService.findUserRoleByUserId(sunvouUser.getId());
			if(roles!=null && roles.size()>0){
				String rolenames="";
				for (SysUserRole sunvouUserRole : roles) {
					SysRole role=roleService.selectByPrimaryKey(sunvouUserRole.getRoleId());
					if(role!=null){
						rolenames+=role.getName()+",";
					}
				}
				sunvouUser.setRolenames(rolenames);
			}
			if(sunvouUser.getOrganizationId()!=null ){
				SysOrganization organization=organizationService.selectByPrimaryKey(sunvouUser.getOrganizationId());
				if(organization!=null){
					sunvouUser.setOrganizationname(organization.getName());
				}
			}
		}
		return list;
	}
	
	@Override
	public SysUser selectByPrimaryKey(String id) {
		SysUser user=userMapper.selectByPrimaryKey(id);
		if(user!=null && user.getOrganizationId()!=null){
			SysOrganization organization=organizationService.selectByPrimaryKey(user.getOrganizationId());
			if(organization!=null){
				user.setOrganizationname(organization.getName());
			}
		}
		return user;
	}
	
	public SysUser selectByName(String name) {
		SysUserExample example=new SysUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andSituationNotEqualTo("离职");
		List<SysUser> list=userMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	
	public SysUser selectByLoginName(String username) {
		SysUserExample example=new SysUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUnameEqualTo(username);
		criteria.andSituationNotEqualTo("离职");
		List<SysUser> list=userMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}else {
			return null;
		}
	}
	
	@Override
	public void insert(SysUser record) {
		record.preInsert();
		System.out.println("record.getCompanyId():"+record.getCompanyId());
		userMapper.insert(record);
		SysUserExample example=new SysUserExample();
		example.setOrderByClause("id desc");
		List<SysUser> list=userMapper.selectByExample(example);
		String[] roles = record.getRoleIds().split(",");
        for (String role : roles) {
        	SysUserRole userRole = new SysUserRole();
            userRole.setUserId(list.get(0).getId());
            userRole.setRoleId(role);
            userRole.preInsert();
            useRoleService.insert(userRole);
        }
	}
	
	
	@Override
	public void updateByPrimaryKeySelective(SysUser record) {
		record.preUpdate();
		userMapper.updateByPrimaryKeySelective(record);
	    List<SysUserRole> userRoles = useRoleService.findUserRoleByUserId(record.getId());
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (SysUserRole userRole : userRoles) {
            	useRoleService.deleteByKey((userRole.getId()));
            }
        }
        String[] roles = record.getRoleIds().split(",");
        for (String role : roles) {
        	SysUserRole userRole = new SysUserRole();
            userRole.setUserId(record.getId());
            userRole.setRoleId(role);
            userRole.preUpdate();
            useRoleService.insert(userRole);
        }
	}
	
	public void updatePwd(SysUser user) {
		user.preUpdate();
		userMapper.updateByPrimaryKeySelective(user);
	}
	
	
	@Override
	public void deleteByPrimaryKey(String id) {
		userMapper.deleteByPrimaryKey(id);
	    List<SysUserRole> userRoles = useRoleService.findUserRoleByUserId(id);
        if (userRoles != null && (!userRoles.isEmpty())) {
            for (SysUserRole userRole : userRoles) {
            	useRoleService.deleteByKey((userRole.getId()));
            }
        }
	}
	
	public String findUserStrsByOrgId(String orgId) {
		if(orgId!=null){
			SysUserExample exampleUser=new SysUserExample();
			Criteria criteriaUser=exampleUser.createCriteria();
			criteriaUser.andOrganizationIdEqualTo(orgId);
			criteriaUser.andSituationNotEqualTo("离职");
			List<SysUser> uList=userMapper.selectByExample(exampleUser);
			JSONArray jArray02 = new JSONArray();
			for(int i=0;i<uList.size();i++){
				JSONObject obj = new JSONObject();
				obj.put("text", uList.get(i).getName());
				obj.put("value", uList.get(i).getId());
				jArray02.add(obj);
			}
			String Str=jArray02.toString().replace("\"", "'");
			return Str;
		}else {
			return "";
		}
	}


	public List<SysUser> findUsersByOrgId(String orgId) {
		SysUserExample example=new SysUserExample();
		Criteria criteria=example.createCriteria();
		criteria.andOrganizationIdEqualTo(orgId);
		criteria.andSituationNotEqualTo("离职");
		List<SysUser> list=userMapper.selectByExample(example);
		return list;
	}
	
	
	public String transIdsToNames(String ids) {
		if(StringUtils.isNotEmpty(ids)){
			String names="";
			if(ids.contains(",")){
				String[] strs=ids.split(",");
				for (String string : strs) {
					SysUser user=userMapper.selectByPrimaryKey(string);
					if(user!=null){
						names+=user.getName()+",";	
					}
				}
			}else {
				SysUser user=userMapper.selectByPrimaryKey(ids);
				if(user!=null){
					names+=user.getName();	
				}
			}
			return names;
		}else{
			return "";
		}
	}

	public List<SysUser> findUser(SysUser user) {
		SysUserExample example=new SysUserExample();
		List<SysUser> list=userMapper.selectByExample(example);
		return list;
	}
	
}

