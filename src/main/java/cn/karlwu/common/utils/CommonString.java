package cn.karlwu.common.utils;

public class CommonString {
	public static final String NOTICE = "鼠标移上来，我就不会消失哦~";
	
	public static final String EXPORT_FAIL = "<span style=\"color:red;margin-right:25px;\"><b>导出失败</b></span>点击鼠标右键，选择<b>'返回(B)'</b>";
	
	/******************************************************************/
	/*******                     公共部分                                                              ************/
	/******************************************************************/
	
	// 年份
	public static final String YEAR = "[{'text':'全部','value':'全部'},{'text':'2012','value':'2012'},"
			+ "{'text':'2013','value':'2013'},{'text':'2014','value':'2014'},"
			+ "{'text':'2015','value':'2015'},{'text':'2016','value':'2016'},{'text':'2017','value':'2017'},{'text':'2018','value':'2018'},{'text':'2019','value':'2019'}]";
	// 月份
	public static final String MONTH = "[{'text':'01','value':'01'},"
			+ "{'text':'02','value':'02'},{'text':'03','value':'03'},"
			+ "{'text':'04','value':'04'},"
			+ "{'text':'05','value':'05'},{'text':'06','value':'06'},"
			+ "{'text':'07','value':'07'},{'text':'08','value':'08'},"
			+ "{'text':'09','value':'09'},{'text':'10','value':'10'},"
			+ "{'text':'11','value':'11'},{'text':'12','value':'12'}]";
	
	// 区域
	public static final String countryStr = "[{'text':'中国','value':'中国'}]";
	public static final String AREA = "[{'text':'华北','value':'华北'},{'text':'东北','value':'东北'},{'text':'华东','value':'华东'},{'text':'华中','value':'华中'},{'text':'华南','value':'华南'},{'text':'西南','value':'西南'},{'text':'西北','value':'西北'}]";
	public static final String PEOVINCE = "[{'id':'8','text':'黑龙江','value':'黑龙江'},{'id':'7','text':'吉林','value':'吉林'},"
			+ "{'id':'6','text':'辽宁','value':'辽宁'},{'id':'1','text':'北京','value':'北京'},"
			+ "{'id':'5','text':'内蒙古','value':'内蒙古'},{'id':'4','text':'山西','value':'山西'},"
			+ "{'id':'3','text':'河北','value':'河北'},{'id':'2','text':'天津','value':'天津'},"
			+ "{'id':'14','text':'福建','value':'福建'},{'id':'13','text':'浙江','value':'浙江'},"
			+ "{'id':'12','text':'上海','value':'上海'},{'id':'11','text':'安徽','value':'安徽'},"
			+ "{'id':'10','text':'江苏','value':'江苏'},{'id':'9','text':'山东','value':'山东'},"
			+ "{'id':'17','text':'海南','value':'海南'},{'id':'16','text':'广西','value':'广西'},"
			+ "{'id':'15','text':'广东','value':'广东'},{'id':'18','text':'河南','value':'河南'},"
			+ "{'id':'19','text':'湖北','value':'湖北'},{'id':'20','text':'湖南','value':'湖南'},"
			+ "{'id':'21','text':'江西','value':'江西'},{'id':'30','text':'甘肃','value':'甘肃'},"
			+ "{'id':'29','text':'青海','value':'青海'},{'id':'28','text':'陕西','value':'陕西'},"
			+ "{'id':'27','text':'新疆','value':'新疆'},{'id':'31','text':'宁夏','value':'宁夏'},"
			+ "{'id':'26','text':'西藏','value':'西藏'},{'id':'25','text':'重庆','value':'重庆'},"
			+ "{'id':'23','text':'云南','value':'云南'},{'id':'22','text':'四川','value':'四川'},"
			+ "{'id':'24','text':'贵州','value':'贵州'}]";
	public static final String positionStr = "[{'text':'--','value':''},{'text':'上海','value':'上海'},{'text':'无锡','value':'无锡'}]";
	public static final String YES_NOT = "[{'text':'--','value':''},{'text':'√','value':'√'},{'text':'×','value':'×'}]";
	public static final String YES = "[{'text':'√','value':'√'}]";
	public static final String COMMENT = "[{'text':'好','value':'好'},{'text':'中','value':'中'},{'text':'差','value':'差'}]";
	public static final String HAVE_NO_SEARCH = "[{'text':'--','value':''},{'text':'有','value':'有'},{'text':'无','value':'无'}]";
	public static final String HAVE_NO = "[{'text':'有','value':'有'},{'text':'无','value':'无'}]";
	public static final String IS_NO = "[{'text':'--','value':''},{'text':'是','value':'是'},{'text':'否','value':'否'}]";
	
	// HR
	public static final String situationStr = "[{'text':'--','value':''},{'text':'即将入职','value':'即将入职'},"
			+ "{'text':'在职-实习','value':'实习'},{'text':'在职-试用期','value':'试用期'},"
			+ "{'text':'在职-转正','value':'转正'},{'text':'在职-离职手续中','value':'离职手续中'},"
			+ "{'text':'离职','value':'离职'}]";

	// CRM
	public static final String coopTypeStr = "[{'text':'--','value':''},{'text':'省代','value':'省代'},"
			+ "{'text':'区代','value':'区代'},"
			+ "{'text':'报单','value':'报单'},"
			+ "{'text':'直销','value':'直销'},"
			+ "{'text':'分销','value':'分销'}]";
	public static final String CRM_COMTRACT_TYPE = "[{'text':'配送','value':'配送'},{'text':'经销','value':'经销'},{'text':'直销','value':'直销'},{'text':'体检','value':'体检'},{'text':'商业','value':'商业'},{'text':'大客户','value':'大客户'},{'text':'推广服务','value':'推广服务'}]";
	public static final String CRM_COMTRACT_TIME_TYPE = "[{'text':'新增拓展','value':'新增拓展'},{'text':'原有市场','value':'原有市场'}]";
	public static final String cooperationStr = "[{'text':'意向合作','value':'意向合作'},{'text':'合作中','value':'合作中'},{'text':'合作结束','value':'合作结束'}]";
	public static final String dealerLevelStr = "[{'text':'省代','value':'省代'},{'text':'区代','value':'区代'},{'text':'报单','value':'报单'},{'text':'直销','value':'直销'}]";
	public static final String dealerTypeStr = "[{'text':'业务经销商','value':'业务经销商'},{'text':'合同经销商','value':'合同经销商'}]";
	public static final String CONTRACT_SITUATION = "[{'text':'合同未签有回款','value':'合同未签有回款'},{'text':'已归档','value':'已归档'},{'text':'违约提前终止','value':'违约提前终止'},{'text':'到期终止(已续签)','value':'到期终止(已续签)'},{'text':'到期终止(待续签)','value':'到期终止(待续签)'}]";
	public static final String CONTRACT_TYPE = "[{'text':'分销','value':'分销'},{'text':'直销','value':'直销'}]";

	// IPM
	public static final String KNOWLEDGETYPE = "[{'id':'1','text':'--','value':''},{'id':'2','text':'专利','value':'专利'},{'id':'3','text':'商标','value':'商标'},{'id':'4','text':'版权','value':'版权'},{'id':'5','text':'其他','value':'其他'}]";
	public static final String KNOWLEDGETYPEGRID = "[{'id':'2','text':'专利','value':'专利'},{'id':'3','text':'商标','value':'商标'},{'id':'4','text':'版权','value':'版权'},{'id':'5','text':'其他','value':'其他'}]";
	public static final String TYPE = "[{'text':'--','value':''},{'text':'PCT','value':'PCT'},{'text':'发明','value':'发明'},{'text':'实用新型','value':'实用新型'},{'text':'外观设计','value':'外观设计'},{'text':'网站域名','value':'网站域名'},{'text':'软件及作品','value':'软件及作品'},{'text':'其他','value':'其他'}]";
	public static final String TYPE_GRID = "[{'text':'PCT','value':'PCT','group':'专利'},{'text':'发明','value':'发明','group':'专利'},{'text':'实用新型','value':'实用新型','group':'专利'},{'text':'外观设计','value':'外观设计','group':'专利'},{'text':'日本','value':'日本','group':'商标'},{'text':'美国','value':'美国','group':'商标'},{'text':'中国','value':'中国','group':'商标'},{'text':'网站域名','value':'网站域名','group':'版权'},{'text':'软件及作品','value':'软件及作品','group':'版权'},{'text':'','value':'','group':'其他'}]";
	public static final String NEWSTATUS = "[{'text':'--','value':''},{'text':'申请','value':'申请'},{'text':'受理','value':'受理'},{'text':'实审','value':'实审'},"
			+ "{'text':'办理登记','value':'办理登记'},{'text':'授权','value':'授权'},{'text':'放弃','value':'放弃'},{'text':'国际公布','value':'国际公布'}]";
	public static final String NEWSTATUS_IN = "[{'text':'--','value':''},{'text':'申请','value':'申请'},{'text':'检索','value':'检索'},{'text':'申请材料递交','value':'申请材料递交'},{'text':'同意申报','value':'同意申报'},"
			+ "{'text':'转技术秘密','value':'转技术秘密'},{'text':'建议放弃','value':'建议放弃'}]";
	public static final String NEWSTATUSGRID = "[{'text':'申请','value':'申请','group':'专利'},{'text':'受理','value':'受理','group':'专利'},{'text':'实审','value':'实审','group':'专利'},{'text':'办理登记','value':'办理登记','group':'专利'},{'text':'授权','value':'授权','group':'专利'},{'text':'放弃','value':'放弃','group':'专利'},{'text':'转让','value':'转让','group':'专利'},"
			+ "{'text':'国际公布','value':'国际公布','group':'专利'},{'text':'申请','value':'申请','group':'商标'},{'text':'受理','value':'受理','group':'商标'},{'text':'授权','value':'授权','group':'商标'},{'text':'申请','value':'申请','group':'版权'},{'text':'受理','value':'受理','group':'版权'},"
			+ "{'text':'授权','value':'授权','group':'版权'},{'text':'申请','value':'申请','group':'其他'},{'text':'受理','value':'受理','group':'其他'},{'text':'授权','value':'授权','group':'其他'}]";
	public static final String RENIMDLINE = "[{'text':'--','value':''},{'text':'已缴费','value':'已缴费'},{'text':'未缴费','value':'未缴费'},{'text':'已过期','value':'已过期'}]";
	public static final String RENIMDLINEGRID = "[{'text':'已缴费','value':'已缴费'},{'text':'未缴费','value':'未缴费'},{'text':'已过期','value':'已过期'}]";
	public static final String APPLYSTATUS = "[{'text':'--','value':''},{'text':'实施','value':'实施'},{'text':'转让','value':'转让'},{'text':'专利许可','value':'专利许可'},{'text':'质押','value':'质押'},{'text':'评估','value':'评估'},{'text':'专利入股','value':'专利入股'},{'text':'其他','value':'其他'}]";
	public static final String APPLYSTATUSGRID = "[{'text':'实施','value':'实施','group':'专利'},{'text':'转让','value':'转让','group':'专利'},{'text':'专利许可','value':'专利许可','group':'专利'},{'text':'质押','value':'质押','group':'专利'},{'text':'评估','value':'评估','group':'专利'},{'text':'专利入股','value':'专利入股','group':'专利'},{'text':'其他','value':'其他','group':'专利'},"
			+ "{'text':'实施','value':'实施','group':'商标'},{'text':'转让','value':'转让','group':'商标'},{'text':'质押','value':'质押','group':'商标'},{'text':'评估','value':'评估','group':'商标'},{'text':'其他','value':'其他','group':'商标'},"
			+ "{'text':'实施','value':'实施','group':'版权'},{'text':'转让','value':'转让','group':'版权'},{'text':'质押','value':'质押','group':'版权'},{'text':'评估','value':'评估','group':'版权'},{'text':'其他','value':'其他','group':'版权'},"
			+ "{'text':'实施','value':'实施','group':'其他'},{'text':'转让','value':'转让','group':'其他'},{'text':'质押','value':'质押','group':'其他'},{'text':'评估','value':'评估','group':'其他'},{'text':'其他','value':'其他','group':'其他'}]";
	public static final String CSGRID = "[{'text':'--','value':''},{'text':'0','value':'0'},{'text':'1','value':'1'},{'text':'2','value':'2'},{'text':'3','value':'3'},{'text':'4','value':'4'},{'text':'5','value':'5'},{'text':'6','value':'6'},{'text':'7','value':'7'}]";
	public static final String type[] = { "--", "PCT", "发明", "实用新型", "外观设计","网站域名", "软件及作品" };
	public static final String applystatus[] = { "--", "实施", "转让", "专利许可","质押", "评估", "专利入股", "其他" };
	public static final String newstatus[] = { "--", "申请", "受理", "实审", "办理登记","授权", "放弃", "国际公布" };
	public static final String typeTwo[] = { "--", "PCT", "发明", "实用新型", "外观设计" };
	public static final String applystatusTwo[] = { "--", "实施", "转让", "专利许可","质押", "评估", "专利入股", "其他" };
	public static final String newstatusTwo[] = { "--", "申请", "受理", "实审","办理登记", "授权", "放弃", "国际公布" };
	public static final String typeThree[] = { "--" };
	public static final String applystatusThree[] = { "--", "实施", "转让", "质押","评估", "其他" };
	public static final String newstatusThree[] = { "--", "申请", "受理", "授权" };
	public static final String typeFore[] = { "--", "网站域名", "软件及作品" };
	public static final String applystatusFore[] = { "--", "实施", "转让", "质押","评估", "其他" };
	public static final String newstatusFore[] = { "--", "申请", "受理", "授权" };
	public static final String applystatusFive[] = { "--", "实施", "转让", "质押","评估", "其他" };
	public static final String newstatusFive[] = { "--", "内部_申请", "内部_检索","内部_申请材料递交", "内部_同意申报", "外部_申请", "外部_受理", "外部_实审", "外部_授权" };
	public static final String modityNum = "[{'text':'--','value':''},{'text':'1','value':'1'},{'text':'2','value':'2'},{'text':'3','value':'3'},{'text':'4','value':'4'},{'text':'5','value':'5'}]";
	public static final String inCounty = "[{'text':'--','value':''},{'text':'中国','value':'中国'},{'text':'美国','value':'美国'},{'text':'英国','value':'英国'},{'text':'法国','value':'法国'}]";
	public static final String modityNumGRID = "[{'text':'1','value':'1'},{'text':'2','value':'2'},{'text':'3','value':'3'},{'text':'4','value':'4'},{'text':'5','value':'5'}]";
	public static final String inCountyGRID = "[{'text':'中国','value':'中国'},{'text':'美国','value':'美国'},{'text':'英国','value':'英国'},{'text':'法国','value':'法国'}]";
	public static final String KNOWBASE_TYPE = "[{'text':'--','value':''},{'text':'法律类','value':'法律类'},{'text':'技术类','value':'技术类'},{'text':'医学临床类','value':'医学临床类'}]";
	public static final String KNOWBASE_TYPE_DETAIL = "[{'text':'--','value':''},{'text':'法律','value':'法律'},{'text':'法规','value':'法规'},{'text':'通告','value':'通告'},{'text':'推荐标准','value':'推荐标准'},{'text':'强制标准','value':'强制标准'},{'text':'专类标准','value':'专类标准'},{'text':'标准指南','value':'标准指南'}]";
	public static final String KNOWBASE_STATE = "[{'text':'--','value':''},{'text':'试行','value':'试行'},{'text':'正式','value':'正式'}]";
	public static final String KNOWBASE_PRODUCT = "[{'text':'--','value':''},{'text':'纳库仑呼气分析仪 ','value':'纳库仑呼气分析仪 '},{'text':'纳库仑呼气氢分析仪','value':'纳库仑呼气氢分析仪'},{'text':'纳库仑呼气监护仪','value':'纳库仑呼气监护仪'},{'text':'远程分析仪','value':'远程分析仪'},{'text':'纳库仑一氧化碳检测器','value':'纳库仑一氧化碳检测器'},{'text':'纳库仑一氧化氮检测器','value':'纳库仑一氧化氮检测器'},{'text':'纳库仑氢气检测器','value':'纳库仑氢气检测器'}]";
	public static final String createType = "[{'text':'--','value':''},{'text':'发明','value':'发明'},{'text':'实用新型','value':'实用新型'},{'text':'外观','value':'外观'}]";
	public static final String gasType = "[{'text':'--','value':''},{'text':'NO','value':'NO'},{'text':'CO','value':'CO'},{'text':'H2S','value':'H2S'},{'text':'H2','value':'H2'},{'text':'CH4','value':'CH4'}]";
	public static final String keyTech = "[{'text':'--','value':''},{'text':'分仪器','value':'分仪器'},{'text':'采样器','value':'采样器'},{'text':'传感器','value':'传感器'}]";
	public static final String situation = "[{'text':'--','value':''},{'text':'有权','value':'有权'},{'text':'无权','value':'无权'},{'text':'公开','value':'公开'}]";
	
	public static final String DOC_TYPE = "[{'text':'--','value':''},{'text':'文献','value':'文献'},{'text':'技术标准','value':'技术标准'},{'text':'临床指南','value':'临床指南'},{'text':'专家共识','value':'专家共识'},{'text':'尚沃产品发表文献','value':'尚沃产品发表文献'}]";
	
	// EMA 资产管理系统
	public static final String ASSET_SITUATION = "[{'text':'--','value':''},{'text':'完好','value':'完好'},"
			+ "{'text':'损坏','value':'损坏'},{'text':'封存','value':'封存'},{'text':'报废','value':'报废'},{'text':'外借','value':'外借'},{'text':'购买中','value':'购买中'}]";
	public static final String ASSET_UNIT = "[{'text':'台','value':'台'},{'text':'把','value':'把'},{'text':'个','value':'个'},{'text':'套','value':'套'},{'text':'张','value':'张'}"
			+ ",{'text':'只','value':'只'},{'text':'盒','value':'盒'},{'text':'组','value':'组'},{'text':'副','value':'副'},{'text':'辆','value':'辆'}]";
	public static final String ASSET_USE = "[{'text':'--','value':''},{'text':'电器','value':'电器'},"
			+ "{'text':'工具','value':'工具'},{'text':'夹具','value':'夹具'},{'text':'家具','value':'家具'},{'text':'家俱','value':'家俱'},{'text':'模具','value':'模具'},{'text':'仪表','value':'仪表'},{'text':'设备','value':'设备'},{'text':'其他','value':'其他'}]";
	public static final String ASSET_MAINTAIN = "[{'text':'--','value':''},{'text':'√','value':'√'},{'text':'×','value':'×'}]";
	public static final String ASSET_TYPE = "[{'text':'--','value':''},{'text':'低值易耗品','value':'低值易耗品'},{'text':'固资-行政资产','value':'固资-行政资产'},{'text':'固资-生产设备','value':'固资-生产设备'},{'text':'固资-物流设备','value':'固资-物流设备'},{'text':'固资-研发设备','value':'固资-研发设备'},{'text':'固资-质量设备','value':'固资-质量设备'},{'text':'固资-模具夹具','value':'固资-模具夹具'}]";
	public static final String ASSET_USE_PLACE = "[{'text':'--','value':''},{'text':'厂内','value':'厂内'},{'text':'厂外','value':'厂外'}]";
	public static final String PLANSHAPE = "[{'text':'--','value':''},{'text':'检定','value':'检定'},{'text':'校准','value':'校准'}]";
	public static final String PLANRESULT = "[{'text':'--','value':''},{'text':'未计量','value':'未计量'},{'text':'正常(√)','value':'正常(√)'},{'text':'异常(×)','value':'异常(×)'}]";
	public static final String BAORESULT = "[{'text':'--','value':''},{'text':'未保养','value':'未保养'},{'text':'正常(√)','value':'正常(√)'},{'text':'异常(×)','value':'异常(×)'}]";
	public static final String PLANMONTH = "[{'text':'--','value':''},{'text':'6','value':'6'},{'text':'12','value':'12'},{'text':'36','value':'36'}]";
	public static final String ASSET_MES_TYPE = "[{'text':'CM1001','value':'CM1001'},{'text':'DA1103','value':'DA1103'},{'text':'DA2123','value':'DA2123'},{'text':'TM1100','value':'TM1100'},{'text':'TM1200','value':'TM1200'},{'text':'TM2120','value':'TM2020'},"
			+ "{'text':'SV-eCO-05','value':'SV-eCO-05'},{'text':'TM1105','value':'TM1105'},{'text':'TM2025','value':'TM2025'},{'text':'CA1401','value':'CA1401'},{'text':'CA2143','value':'CA2143'},{'text':'CA3478','value':'CA3478'},"
			+ "{'text':'CA3578','value':'CA3578'},{'text':'CA4458','value':'CA4458'},{'text':'CA2122','value':'CA2122'},{'text':'P200','value':'P200'},{'text':'SV-eNO-03','value':'SV-eNO-03'},{'text':'SV-eH2-02','value':'SV-eH2-02'},{'text':'SV-eNO-05','value':'SV-eNO-05'}]";
	
	// FILE
	public static final String FILE_TYPE = "[{'text':'--','value':''},{'text':'手册类','value':'手册类'},{'text':'程序类','value':'程序类'},{'text':'制度类','value':'制度类'},{'text':'表单类','value':'表单类'}]";
	public static final String FILE_STATE = "[{'text':'--','value':''},{'text':'正式','value':'正式'},{'text':'临时','value':'临时'},"
			+ "{'text':'作废','value':'作废'}]";
	public static final String FILE_SECRETLEVEL = "[{'text':'--','value':''},{'text':'绝密A','value':'绝密A'},{'text':'机密B','value':'机密B'},"
			+ "{'text':'秘密C','value':'秘密C'},{'text':'一般D','value':'一般D'},{'text':'公开E','value':'公开E'}]";
	public static final String FILE_VERSION = "[{'text':'--','value':''},{'text':'正式版','value':'正式版'},{'text':'试用版','value':'试用版'}]";
	public static final String FILE_RANGE = "[{'text':'--','value':''},{'text':'一','value':'一'},{'text':'二','value':'二'},"
			+ "{'text':'三','value':'三'},{'text':'四','value':'四'}]";
	public static final String OA_APPLYSTATE = "[{'text':'--','value':''},{'text':'草拟中','value':'草拟中'},{'text':'审批中','value':'审批中'},"
			+ "{'text':'驳回','value':'驳回'},{'text':'完成','value':'完成'}]";
	public static final String ACT_RESULT = "[{'text':'同意','value':'同意'},{'text':'驳回','value':'驳回'}]";
	public static final String ACT_STATE = "[{'text':'--','value':''},{'text':'草稿','value':'0'},{'text':'审批中','value':'1'},{'text':'完成','value':'2'}]";
	public static final String ACT_TYPE = "[{'text':'--','value':''},{'text':'共享文档流程','value':'SunvouFileCompanyCheck'},{'text':'研发文档流程','value':'研发文档流程'}]";

	// MES
	public static final String MES_STEP = "[{'text':'delivery','value':'delivery'},{'text':'preliminary','value':'preliminary'},{'text':'semifinished','value':'semifinished'},{'text':'finished','value':'finished'}]";
	public static final String KUAIDI_CODE = "[{'text':'顺丰速运','value':'SF'},{'text':'申通快递','value':'STO'},"
			+ "{'text':'韵达快递','value':'YD'},{'text':'EMS','value':'EMS'},"
			+ "{'text':'城市100','value':'CITY100'},{'text':'邦德','value':'DBL'},"
			+ "{'text':'百世汇通','value':'HTKY'},{'text':'圆通快递','value':'YTO'},"
			+ "{'text':'中通快递','value':'ZTO'}]";
	public static final String PRODUCTION_STATE_SEARCH = "[{'text':'--','value':''},{'text':'订单发货','value':'订单发货'},{'text':'更换出库','value':'更换出库'},"
			+ "{'text':'明确医院','value':'明确医院'},{'text':'退货入库','value':'退货入库'},{'text':'报废','value':'报废'},"
			+ "{'text':'待初测','value':'待初测'},{'text':'初测中','value':'初测中'},{'text':'初测合格','value':'初测合格'},{'text':'初测不合格','value':'初测不合格'},{'text':'初测待修','value':'初测待修'},{'text':'初测报废','value':'初测报废'},"
			+ "{'text':'半成品送检','value':'半成品送检'},"
			+ "{'text':'半检中','value':'半检中'},{'text':'半检合格','value':'半检合格'},{'text':'半检不合格','value':'半检不合格'},{'text':'半检待修','value':'半检待修'},{'text':'半检报废','value':'半检报废'},"
			+ "{'text':'出测中','value':'出测中'},{'text':'出测合格','value':'出测合格'},{'text':'出测不合格','value':'出测不合格'},{'text':'出测待修','value':'出测待修'},{'text':'出测报废','value':'出测报废'},"
			+ "{'text':'出检中','value':'出检中'},{'text':'出检合格','value':'出检合格'},{'text':'出检不合格','value':'出检不合格'},{'text':'出检待修','value':'出检待修'},{'text':'出检报废','value':'出检报废'}]";
}
