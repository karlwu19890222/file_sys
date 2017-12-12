package cn.karlwu.modules.sys.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.modules.sys.mapper.SysScheduleJobHistoryMapper;
import cn.karlwu.modules.sys.pojo.SysScheduleJobHistory;
import cn.karlwu.modules.sys.pojo.SysScheduleJobHistoryExample;
import cn.karlwu.modules.sys.pojo.SysScheduleJobHistoryExample.Criteria;

/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: ScheduleJobHistoryService.java
 * @Prject: Sunvou_Main
 * @Package: cn.karlwu.modules.sys.service
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年8月25日 下午4:22:10
 * @version: V1.0  
 */

@Service
public class SysScheduleJobHistoryService {
	@Autowired
	private SysScheduleJobHistoryMapper scheduleJobHistoryMapper;
	
	public void insert(Integer jId,Date startTime,Date endTime,String content) {
		SysScheduleJobHistory record=new SysScheduleJobHistory();
		record.setjId(jId);
		record.setStartTime(startTime);
		record.setEndTime(endTime);
		record.setContent(content);
		scheduleJobHistoryMapper.insert(record);
	}

	public EasyUIResult findByExample(SysScheduleJobHistory history) {
		SysScheduleJobHistoryExample example=new SysScheduleJobHistoryExample();
		Criteria criteria=example.createCriteria();
		if(history.getjId()!=null){
			criteria.andJIdEqualTo(history.getjId());
		}else {
			criteria.andJIdEqualTo(0);
		}
		List<SysScheduleJobHistory> list = scheduleJobHistoryMapper.selectByExample(example);
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		result.setTotal(Long.valueOf(list.size()));
		return result;
	}

}

