package cn.karlwu.common.utils;

import java.util.UUID;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: UUIDUtil.java
 * @Prject: Sunvou_Main
 * @Package: com.sunvou.common.utils
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年7月12日 上午10:36:01
 * @version: V1.0  
 */
public class UUIDUtil {
	public static String creatUUID() {  
        return UUID.randomUUID().toString().replace("-", "");  
    }  
  
    public static void main(String[] args) {  
        String str = UUIDUtil.creatUUID();  
        System.out.println(str);  
    }  

}

