package cn.karlwu.modules.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.modules.sys.pojo.SysScheduleJobHistory;
import cn.karlwu.modules.sys.service.SysScheduleJobHistoryService;

@Controller
@RequestMapping("/sys/scheduleJob/history")
public class SysScheduleJobHistoryController {
	
	@Autowired
	private SysScheduleJobHistoryService jobHistoryService;
	 
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult list(SysScheduleJobHistory history) {
		return jobHistoryService.findByExample(history);
	}
}
