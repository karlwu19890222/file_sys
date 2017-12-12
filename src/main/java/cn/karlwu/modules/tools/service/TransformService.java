package cn.karlwu.modules.tools.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.karlwu.common.utils.file.Office2PDFUtil;


@Service
public class TransformService {
	
	@Value("${PDF2HtmlEX_HOME}")
	private  String PDF2HtmlEX_HOME;
	@Value("${OpenOffice_HOME}")
	private  String OpenOffice_HOME;
	
	public int offictToPdf(String sourceFile, String destFile) {
		int result = Office2PDFUtil.office2PDF(sourceFile, destFile,PDF2HtmlEX_HOME,OpenOffice_HOME);
		return result;
	}
	
}
