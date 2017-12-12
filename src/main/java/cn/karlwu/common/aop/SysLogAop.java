package cn.karlwu.common.aop;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.karlwu.common.shiro.ShiroUser;
import cn.karlwu.modules.sys.pojo.SysLog;
import cn.karlwu.modules.sys.pojo.SysUser;
import cn.karlwu.modules.sys.service.SysLogService;
import cn.karlwu.modules.sys.service.SysUserService;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Enumeration;

/**
 * @description：AOP 日志
 * @author：karlwu
 */
@Aspect
@Component
public class SysLogAop {
    private static Logger LOGGER = LoggerFactory.getLogger(SysLogAop.class);

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserService userService;

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void cutController() {
    }
    @Around("cutController()")
    public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
        String strMethodName = point.getSignature().getName();
        String strClassName = point.getTarget().getClass().getName();
        Object[] params = point.getArgs();
        StringBuffer bfParams = new StringBuffer();
        Enumeration<String> paraNames = null;
        HttpServletRequest request = null;
        if (params != null && params.length > 0) {
            request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            paraNames = request.getParameterNames();
            String key;
            String value;
            while (paraNames.hasMoreElements()) {
                key = paraNames.nextElement();
                value = request.getParameter(key);
                bfParams.append(key).append("=").append(value).append("&");
            }
            if (StringUtils.isBlank(bfParams)) {
                bfParams.append(request.getQueryString());
            }
        }
        String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, bfParams.toString());
        LOGGER.info(strMessage);
        if (isWriteLog(strMethodName)) {
            try {
                ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
                SysUser currentUser = userService.selectByPrimaryKey(user.id);
                if (null != currentUser) {
                    /*String loginName = collection.getPrimaryPrincipal().toString();*/
                    SysLog sysLog = new SysLog();
                    sysLog.setLoginName(currentUser.getName());
                    sysLog.setRoleName(currentUser.getUname());
                    sysLog.setCompanyId(currentUser.getCompanyId());
                    sysLog.setCreateTime(new Date());
                    sysLog.setOptContent(strMessage);
                    if (request != null) {
                        sysLog.setClientIp(request.getRemoteAddr());
                    }
                    LOGGER.info(sysLog.toString());
                    sysLogService.insert(sysLog);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return point.proceed();
    }

    private boolean isWriteLog(String method) {
        String[] pattern = {"index", "logout", "add", "edit","update", "delete", "grant", "find", "select", "get", "list"};
        for (String s : pattern) {
            if (method.indexOf(s) > -1) {
                return true;
            }
        }
       
        return false;
    }
}
