package cn.karlwu.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: EasyUITools.java
 * @Prject: Sunvou_Main
 * @Package: com.sunvou.common.utils
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年7月20日 上午9:40:36
 * @version: V1.0  
 */
public class EasyUITools {
	
	public static List<Map<String, Object>> PropertygridTrans(Object obj,String group ) {
		
		List<Map<String, Object>> result=new ArrayList<>();
		if (obj == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if(value!=null && !"".equals(value.toString())){
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("name", key);
						map.put("value", value);
						map.put("group", group);
						result.add(map);
					}
					
				}
			}
		} catch (Exception e) {
		}
		return result;
	}

}

