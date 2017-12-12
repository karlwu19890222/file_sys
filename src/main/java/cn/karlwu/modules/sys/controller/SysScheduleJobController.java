package cn.karlwu.modules.sys.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.karlwu.common.persistence.BaseControllerInterface;
import cn.karlwu.common.utils.EasyUIResult;
import cn.karlwu.common.utils.SunResult;
import cn.karlwu.modules.sys.pojo.SysScheduleJob;
import cn.karlwu.modules.sys.service.SysScheduleJobService;

@Controller
@RequestMapping("/sys/scheduleJob")
public class SysScheduleJobController implements BaseControllerInterface<SysScheduleJob>{
	
	 @Autowired
	 private SysScheduleJobService service;

	@Override
	@RequestMapping("/toListPage")
	public String toListPage(Model model) {
		return "sys/scheduleJobPage";
	}

	@Override
	public String toInsertPage(Model model, SysScheduleJob record) {
		return null;
	}

	@Override
	public String toUpdatePage(Model model, SysScheduleJob record) {
		return null;
	}

	@Override
	@RequestMapping("/selectByPage")
	@ResponseBody
	public EasyUIResult selectByPage(Integer page, Integer rows,String search_name,String search_value, SysScheduleJob record) {
		Map<String, String> map=new HashMap<>();
		return service.selectByPage(page,rows,map);
	}

	@Override
	public SunResult insert(SysScheduleJob record) {
		return null;
	}

	@Override
	public SunResult update(SysScheduleJob record) {
		return null;
	}

	@Override
	public SunResult delete(String ids) {
		return null;
	}

	@Override
	public String toExportPage(Model model, SysScheduleJob record) {
		return null;
	}

	@Override
	public String export(HttpServletResponse response) {
		return null;
	}
	
	
	@RequestMapping(value = "runOnce", method = RequestMethod.POST)
	@ResponseBody
	public SunResult runOnce(SysScheduleJob o) {
		SunResult sunvResult = new SunResult();
		try {
			int res = service.runOnce(o);
			if (res == 1){
				sunvResult.setStatus(200);
				sunvResult.setMsg("运行成功");
			}else if (res == 2){
				sunvResult.setStatus(300);
				sunvResult.setMsg("请先停用任务");
			} else{
				sunvResult.setStatus(400);
			    sunvResult.setMsg("运行失败");
			}
		} catch (Exception e) {
			sunvResult.setStatus(400);
			sunvResult.setMsg("运行失败");
		}
		return sunvResult;
	}
		
	@RequestMapping(value = "resumeJob", method = RequestMethod.POST)
	@ResponseBody
	public SunResult resumeJob(SysScheduleJob o) {
		SunResult sunvResult = new SunResult();
			try {
				int res = service.resumeJob(o);
				if (res == 1){
					sunvResult.setMsg("启动成功");
				}
				else if (res == 2){
					sunvResult.setMsg("项目已启动");
				}
				else{
					sunvResult.setMsg("启动失败");
				}
			} catch (Exception e) {
				sunvResult.setMsg("启动失败");
			}
		return sunvResult;
	}
		
	@RequestMapping(value = "pauseJob", method = RequestMethod.POST)
	@ResponseBody
	public SunResult pauseJob(SysScheduleJob o) {
		SunResult sunvResult = new SunResult();
			try {
				int res = service.pauseJob(o);
				if (res == 1){
					sunvResult.setMsg("暂停成功");
				}
				else if (res == 2){
					sunvResult.setMsg("项目没启动，不用暂停");
				}
				else{
					sunvResult.setMsg("暂停失败");
				}
			} catch (Exception e) {
				sunvResult.setMsg("暂停失败");
			}
		return sunvResult;
	}
	

}
