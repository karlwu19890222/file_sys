package cn.karlwu.modules.tools.service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import cn.karlwu.common.utils.DateUtils;
import cn.karlwu.common.utils.FtpUtil;

@Service
public class FtpService {
	
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${FILE_BASE_URL}")
	private String FILE_BASE_URL;
	@Value("${FTP_LOCAL_URL}")
	private String FTP_LOCAL_URL;
	
	@Autowired
	private TransformService transformService;
	
	public Map<Object, Object> uploadPicture(MultipartFile uploadFile) {
		Map<Object, Object> resultMap=new HashMap<>();
		try {
			
			String orgName=uploadFile.getOriginalFilename();
			String orgType=orgName.substring(orgName.lastIndexOf("."));
			String newName    =DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
			String newNamePDF =newName+".pdf";
			       newName    =newName+orgName.substring(orgName.lastIndexOf("."));
			
			/*上传附件*/       
			boolean result=FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, DateUtils.formatDate(new Date(), "/yyyy/MM/dd"), newName, uploadFile.getInputStream());
		    if(!result){
		    	resultMap.put("error", 1);
		    	resultMap.put("message", "File Upload Fail");
		    	return resultMap;
		    }
	    	 /*本地 文件转换*/
		    String urlOrg=FTP_LOCAL_URL+DateUtils.formatDate(new Date(), "\\yyyy\\MM\\dd")+"\\"+newName;
		    String urlPdf=FTP_LOCAL_URL+DateUtils.formatDate(new Date(), "\\yyyy\\MM\\dd")+"\\"+newNamePDF;
		    if(!".PDF.JPG.JPEG.PNG.RM.RMVB.WMV.AVI.MP4.3PG.MKV.RAR.ZIP.7-ZIP.ISO".contains(orgType.toUpperCase())){ //PDF和常用的图片放行
		       int num= transformService.offictToPdf(urlOrg, urlPdf);
		       if(num!=0){
		    	    resultMap.put("error", 1);
			    	resultMap.put("message", "Trans Fail");
			    	return resultMap;  
		       }
		    }	
		    resultMap.put("error", 0);
		    String urlSave=DateUtils.formatDate(new Date(), "/yyyy/MM/dd")+"/"+newName;
	    	resultMap.put("url",urlSave);
	    	resultMap.put("title",orgName);
	    	return resultMap;
		} catch (Exception e) {
			resultMap.put("error", 1);
	    	resultMap.put("message", "UnKnow Reason");
	    	return resultMap;
		}
	}
	public String  getServerFileUrl(){
		return FILE_BASE_URL;
	}
	
	public String  getLocalFileUrl(){
		return FTP_LOCAL_URL;
	}
}
