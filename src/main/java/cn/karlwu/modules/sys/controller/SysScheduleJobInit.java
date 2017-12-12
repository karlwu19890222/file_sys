package cn.karlwu.modules.sys.controller;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.karlwu.modules.sys.service.SysScheduleJobService;


/**
 * 定时任务初始化
 */
@Component
public class SysScheduleJobInit {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(SysScheduleJobInit.class);

    /** 定时任务service */
    @Autowired
    private SysScheduleJobService  scheduleJobService;

    /**
     * 项目启动时初始化
     */
    @PostConstruct
    public void init() {
        LOG.info("定时任务初始化init");
        scheduleJobService.initScheduleJob();
        LOG.info("定时任务初始化end");
    }
}