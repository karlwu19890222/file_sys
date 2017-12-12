package cn.karlwu.common.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: ContextFilter.java
 * @Prject: Sunvou_Main
 * @Package: com.sunvou.common
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年8月22日 下午2:17:12
 * @version: V1.0  
 */

public class ContextFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse httpServletResponse=(HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.setHeader("Accept", "*");
		httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		httpServletResponse.setHeader("Access-Control-Max-Age", "0");
		httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
		httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
		httpServletResponse.setHeader("XDomainRequestAllowed","1");
		SimpleDateFormat sdfDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		httpServletResponse.setHeader("Cookie", "JSESSIONID=121212"+sdfDateFormat.format(new Date()));
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

}

