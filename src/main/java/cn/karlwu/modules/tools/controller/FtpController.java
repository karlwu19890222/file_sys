package cn.karlwu.modules.tools.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.karlwu.common.utils.file.FileUtils;
import cn.karlwu.modules.common.service.CommonOperationLogService;
import cn.karlwu.modules.tools.service.FtpService;

@Controller
@RequestMapping("/ftp")
public class FtpController {

	@Autowired
	private FtpService ftpService;
	@Autowired
	private CommonOperationLogService commonOperationLogService;

	@RequestMapping("/upload")
	@ResponseBody
	public Map<Object, Object> pictureUpload(MultipartFile uploadFile) {
		Map<Object, Object> result = ftpService.uploadPicture(uploadFile);
		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String knowbase(String fileUrl,String name,String objectId, Model model,HttpServletRequest request,HttpServletResponse response ) {
		commonOperationLogService.insert(objectId, "查看附件", name, "");
		String fileType = fileUrl.substring(fileUrl.lastIndexOf("."),fileUrl.length());
		if (".JPG.JPEG.PNG".contains(fileType.toUpperCase())) {
			String filePath  = ftpService.getServerFileUrl()+fileUrl;
			model.addAttribute("imgUrl", filePath);
			return "/view/img";
		}else if(".RM.RMVB.WMV.AVI.MP4.3PG.MKV.RAR.ZIP.7-ZIP.ISO".contains(fileType.toUpperCase())){
			return "/view/error";
		}else {
			model.addAttribute("fileName", fileUrl);
			return "/view/pdf";
		}
	}
	

	@RequestMapping(value = "/down", method = RequestMethod.GET)
	public void down(HttpServletRequest request,HttpServletResponse response, String fileUrl,String name,String objectId) throws IOException {
		commonOperationLogService.insert(objectId, "下载附件", name, "");
		request.setCharacterEncoding("UTF-8");
		String filePath = ftpService.getLocalFileUrl() + fileUrl;
		//String extension = fileUrl.substring(fileUrl.lastIndexOf("."));
		OutputStream outStream = null;
		FileInputStream in = null;
		try {
			// 如果文件导出成功
			if (FileUtils.isFileExists(filePath)) {
				response.setContentType("application/x-download");
				String userAgent = request.getHeader("USER-AGENT").toLowerCase();
				if (!userAgent.contains("firefox")) {
					name = URLEncoder.encode(name, "UTF-8");
				} else {
					name = new String(name.getBytes("UTF-8"),	"ISO-8859-1");
				}
				//response.addHeader("Content-Disposition","attachment;filename=" + name+extension);
				response.addHeader( "Content-Disposition", "attachment;filename=" + new String( (name).getBytes("gb2312"), "ISO8859-1" ) ); 
				outStream = response.getOutputStream();
				in = new FileInputStream(filePath);
				byte[] b = new byte[1024];
				int count = 0;
				while ((count = in.read(b)) > 0) {
					outStream.write(b, 0, count);
				}
			} else {
				response.sendRedirect("errordownload.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
			if (outStream != null) {
				outStream.flush();
				outStream.close();
			}
		}
	}
	
	@RequestMapping(value = "/pdfRead")
    @ResponseBody
    public void pdfStreamHandeler(String filePath,String fileName, HttpServletRequest request, HttpServletResponse response) {
		String pathOfPdf = fileName.subSequence(0, fileName.lastIndexOf(".")) + ".pdf";
		filePath  = ftpService.getLocalFileUrl()+pathOfPdf;
        File file = new File(filePath);
        byte[] data = null;
        try {
            FileInputStream input = new FileInputStream(file);
            data = new byte[input.available()];
            input.read(data);
            response.getOutputStream().write(data);
            input.close();
        } catch (Exception e) {
        }
    }
	  
}
