package cn.karlwu.common.mail;




public class MailContent {
	
	/*public  String assetBuyModel(String title,List<EmaAsset> list) {
		String  string="<!DOCTYPE html>";
	    string+="<html>";
	    string+="		<head>";
	    string+="			<meta charset='UTF-8'>";
		string+="		</head>";
		string+="		<body>";
		string=      "<h4>Dear Sir：</h4>";
        string+=			" <p>"+title+"</p>";
        string+=				" <table style='border-collapse:collapse;'>";
        string+=				 	"<thead style='background-color: #0066FF;'>";
        string+=				 		"<th style='border: 1px solid #000000;width: 200px;text-align: center;'>编号</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 300px;text-align: center;'>名称</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>状态</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>请购部门</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>请购人</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>购买时间</th>";
        string+=				 	"</thead>";
        string+=				 	"<tbody>";
        for (EmaAsset asset : list) {
    	   string+=	                 "<tr>"; 
	       string+=				 		"<td style='border: 1px solid #000000;width: 200px;text-align: center;'>"+asset.getAssetid()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 300px;text-align: center;'>"+asset.getName()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getSituation()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getSavedept()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getSaveperson()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getBuytime()+"</td>";
	       string+=	                  "</tr>"; 
	    }
        string+=				 	"</tbody>";
        string+=				 "</table>";
        string+=	            " <p> 您可以登录内网了解详情<a href=\'http://www.sunvou.cn\'>http://www.sunvou.cn</a></p>";
        string+=				" <p>以上，请知晓!</p>";
        string+=				" <p>系统管理员</p>";
        string+=				" <p>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</p>";
		string+="		</body>";
		string+="			</html>";
		return string;
	}
	
	public  String assetJIModel(String title,List<EmaAsset> list) {
		String  string="<!DOCTYPE html>";
	    string+="<html>";
	    string+="		<head>";
	    string+="			<meta charset='UTF-8'>";
		string+="		</head>";
		string+="		<body>";
		string=      "<h4>Dear Sir：</h4>";
        string+=			" <p>"+title+"</p>";
        string+=				" <table style='border-collapse:collapse;'>";
        string+=				 	"<thead style='background-color: #0066FF;'>";
        string+=				 		"<th style='border: 1px solid #000000;width: 200px;text-align: center;'>编号</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 300px;text-align: center;'>名称</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>状态</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>上次计量时间</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>有效周期</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>下次计量时间</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>保管部门</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>保管人</th>";
        string+=				 	"</thead>";
        string+=				 	"<tbody>";
        for (EmaAsset asset : list) {
    	   string+=	                 "<tr>"; 
	       string+=				 		"<td style='border: 1px solid #000000;width: 200px;text-align: center;'>"+asset.getAssetid()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 300px;text-align: center;'>"+asset.getName()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getSituation()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getCorrecttime()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getCorrectcycle()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getCorrectnexttime()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getSavedept()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getSaveperson()+"</td>";
	       string+=	                  "</tr>"; 
	    }
        string+=				 	"</tbody>";
        string+=				 "</table>";
        string+=	            " <p> 您可以登录内网了解详情<a href=\'http://www.sunvou.cn\'>http://www.sunvou.cn</a></p>";
        string+=				" <p>以上，请知晓!</p>";
        string+=				" <p>系统管理员</p>";
        string+=				" <p>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</p>";
		string+="		</body>";
		string+="			</html>";
		return string;
	}
	
	public  String assetBAOModel(String title,List<EmaAsset> list) {
        String  string="<!DOCTYPE html>";
	    string+="<html>";
	    string+="		<head>";
	    string+="			<meta charset='UTF-8'>";
		string+="		</head>";
		string+="		<body>";
		string=      "<h4>Dear Sir：</h4>";
        string+=			" <p>"+title+"</p>";
        string+=				" <table style='border-collapse:collapse;'>";
        string+=				 	"<thead style='background-color: #0066FF;'>";
        string+=				 		"<th style='border: 1px solid #000000;width: 200px;text-align: center;'>编号</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 300px;text-align: center;'>名称</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>状态</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>上次保养时间</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>有效周期</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>下次保养时间</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>保管部门</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>保管人</th>";
        string+=				 	"</thead>";
        string+=				 	"<tbody>";
        for (EmaAsset asset : list) {
    	   string+=	                 "<tr>"; 
	       string+=				 		"<td style='border: 1px solid #000000;width: 200px;text-align: center;'>"+asset.getAssetid()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 300px;text-align: center;'>"+asset.getName()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getSituation()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getMaintetime()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+asset.getMaintecycle()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getMaintenexttime()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getSavedept()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+asset.getSaveperson()+"</td>";
	       string+=	                  "</tr>"; 
	    }
        string+=				 	"</tbody>";
        string+=				 "</table>";
        string+=	            " <p> 您可以登录内网了解详情<a href=\'http://www.sunvou.cn\'>http://www.sunvou.cn</a></p>";
        string+=				" <p>以上，请知晓!</p>";
        string+=				" <p>系统管理员</p>";
        string+=				" <p>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</p>";
		string+="		</body>";
		string+="			</html>";
        return string;
	}

	public String IpmModel(String title,List<IpmKnowledge> before30DaysList) {
        String  string="<!DOCTYPE html>";
	    string+="<html>";
	    string+="		<head>";
	    string+="			<meta charset='UTF-8'>";
		string+="		</head>";
		string+="		<body>";
		string=      "<h4>Dear Sir：</h4>";
        string+=			" <p>"+title+"</p>";
        string+=				" <table style='border-collapse:collapse;'>";
        string+=				 	"<thead style='background-color: #0066FF;'>";
        string+=				 		"<th style='border: 1px solid #000000;width: 200px;text-align: center;'>授权公告号/注册号</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 300px;text-align: center;'>知识产权名称</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>知识产权类型</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>类别</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>申请号</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>次缴费日期</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>下次缴费金额</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 120px;text-align: center;'>失效日期</th>";
        string+=				 	"</thead>";
        string+=				 	"<tbody>";
        for (IpmKnowledge ipmKnowledge : before30DaysList) {
    	   string+=	                 "<tr>"; 
	       string+=				 		"<td style='border: 1px solid #000000;width: 200px;text-align: center;'>"+ipmKnowledge.getRegisternumber()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 300px;text-align: center;'>"+ipmKnowledge.getKonwledgename()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+ipmKnowledge.getKonwledgetype()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+ipmKnowledge.getType()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+ipmKnowledge.getApplynumber()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+ipmKnowledge.getNextpaytime()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+ipmKnowledge.getRenimdline()+"</td>";
	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+ipmKnowledge.getLsoetime()+"</td>";
	       string+=	                  "</tr>"; 
	    }
        string+=				 	"</tbody>";
        string+=				 "</table>";
        string+=	            " <p> 您可以登录内网了解详情<a href=\'http://www.sunvou.cn\'>http://www.sunvou.cn</a></p>";
        string+=				" <p>以上，请知晓!</p>";
        string+=				" <p>系统管理员</p>";
        string+=				" <p>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</p>";
		string+="		   </body>";
		string+="			</html>";
        return string;
	}

	public String companyFileNoticeForNow(String title, WorkFlow actProcess) {
		String  string="<!DOCTYPE html>";
	    string+="<html style='font-family:幼圆,Arial,Helvetica,sans-serif;font-size:16px'>";
	    string+="		<head>";
	    string+="			<meta charset='UTF-8'>";
		string+="		</head>";
		string+="		<body>";
		string=      "<h4>Dear Sir：</h4>";
        string+=			" <p>"+title+"</p>";
        string+=				" <table style='border-collapse:collapse;'>";
        string+=				 	"<thead style='background-color: #0066FF;'>";
        string+=				 		"<th style='border: 1px solid #000000;width: 200px;text-align: center;'>类别</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 300px;text-align: center;'>名称</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>状态</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>申请原因</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>申请人</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 180px;text-align: center;'>申请时间</th>";
        string+=				 	"</thead>";
        string+=				 	"<tbody>";
        string+=	                 "<tr>"; 
	    string+=				 		"<td style='border: 1px solid #000000;width: 200px;text-align: center;'>"+actProcess.getName()+"</td>";
	    string+=				 		"<td style='border: 1px solid #000000;width: 300px;text-align: center;'>"+actProcess.getTitle()+"</td>";
	    string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+actProcess.getState()+"</td>";
	    string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+actProcess.getReason()+"</td>";
	    string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+actProcess.getApplyuser()+"</td>";
	    string+=				 		"<td style='border: 1px solid #000000;width: 180px;text-align: center;'>"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(actProcess.getStarttime())+"</td>";
	    string+=	                  "</tr>"; 
        string+=				 	"</tbody>";
        string+=				 "</table>";
        string+=	            " <p> 您可以登录内网了解详情<a href=\'http://www.sunvou.cn\'>http://www.sunvou.cn</a></p>";
        string+=				" <p>以上，请知晓!</p>";
        string+=				" <p>系统管理员</p>";
        string+=				" <p>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</p>"; 
		string+="		</body>";
		string+="</html>";
        return string;
	}

	public String contractModel(String title,List<CrmContractExport> reasonContracts) {
		String  string="<!DOCTYPE html>";
	    string+="<html style='font-family:幼圆,Arial,Helvetica,sans-serif;font-size:16px'>";
	    string+="		<head>";
	    string+="			<meta charset='UTF-8'>";
		string+="		</head>";
		string+="		<body>";
		string=      "<h4>Dear Sir：</h4>";
        string+=			" <p>"+title+"</p>";
        string+=				" <table style='border-collapse:collapse;'>";
        string+=				 	"<thead style='background-color: #0066FF;'>";
        string+=				 		"<th style='border: 1px solid #000000;width: 200px;text-align: center;'>编号</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 300px;text-align: center;'>区域</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>合同类型</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>业务经销</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 100px;text-align: center;'>合同经销商</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 180px;text-align: center;'>直销单位</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 180px;text-align: center;'>合同起始时间</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 180px;text-align: center;'>合同结束时间</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 180px;text-align: center;'>合同文件状态</th>";
        string+=				 		"<th style='border: 1px solid #000000;width: 180px;text-align: center;'>合同跟进人</th>";
        string+=				 	"</thead>";
        string+=				 	"<tbody>";
        for (CrmContractExport contract : reasonContracts) {
     	   string+=	                 "<tr>"; 
 	       string+=				 		"<td style='border: 1px solid #000000;width: 200px;text-align: center;'>"+contract.getNumber()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 300px;text-align: center;'>"+contract.getProvince()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+contract.getContractType()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 100px;text-align: center;'>"+contract.getTimeType()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+contract.getContractDealerName()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+contract.getHospitalName()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+contract.getStartTime()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+contract.getEndTime()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+contract.getSituation()+"</td>";
 	       string+=				 		"<td style='border: 1px solid #000000;width: 120px;text-align: center;'>"+contract.getUserNames()+"</td>";
 	       string+=	                  "</tr>"; 
 	    }
        string+=				 	"</tbody>";
        string+=				 "</table>";
        string+=	            " <p> 您可以登录内网了解详情<a href=\'http://www.sunvou.cn\'>http://www.sunvou.cn</a></p>";
        string+=				" <p>以上，请知晓!</p>";
        string+=				" <p>系统管理员</p>";
        string+=				" <p>"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"</p>"; 
		string+="		</body>";
		string+="</html>";
        return string;
	}*/
	
}

