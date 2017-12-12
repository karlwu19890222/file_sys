package cn.karlwu.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: TransBean2Map.java
 * @Prject: Sunvou_Main
 * @Package: com.sunvou.common.utils
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年7月13日 上午8:57:34
 * @version: V1.0  
 */
public class TransBeanWays {
	private static final Logger LOGGER = LoggerFactory.getLogger(TransBeanWays.class);
	/**
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> trans(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
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
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			LOGGER.error("transBean2Map Error {}" ,e);
		}
		return map;
	}
	
}

