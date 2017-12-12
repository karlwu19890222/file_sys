package cn.karlwu.common.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * 直接用OpenOffice将Word、Excel文档转换成html字符串的工具类
 * 
 */
public class Doc2HtmlUtil
{

	/**
	 * 将office文档转换成html文档
	 * 已测试*.doc、*.xls文件
	 * 转换*.pdf文件失败
	 * @param docFile 需要转换的word文档
	 * @param filepath 转换之后html的存放路径
	 * @return 转换之后的html文件
	 */
	@SuppressWarnings("unused")
	public static File convert(File docFile, String filepath,String PDF2HtmlEX_HOME,String OpenOffice_HOME )
	{
		// 创建保存html的文件
		File htmlFile = new File(filepath + "/" + new Date().getTime() + ".html");
		Properties prop = new Properties();
		Process pro = null;
		// 创建Openoffice连接
		OpenOfficeConnection con = new SocketOpenOfficeConnection(8100);
		try
		{
			// 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
			if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
				OpenOffice_HOME += "\\";
			}
			// 启动OpenOffice的服务
			String command = OpenOffice_HOME + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
			pro = Runtime.getRuntime().exec(command);
			// 连接
			con.connect();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  catch (ConnectException e)
		{
			System.out.println("获取OpenOffice连接失败...");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 创建转换器
		DocumentConverter converter = new OpenOfficeDocumentConverter(con);
		// 转换文档向html
		converter.convert(docFile, htmlFile);
		// 关闭openoffice连接
		con.disconnect();
		// 关闭OpenOffice服务的进程
		//if(pro != null)
			pro.destroy();
		return htmlFile;
	}

	/**
	 * 将office转换成html文件，并且获取html文件代码。
	 * 已测试*.doc、*.xls文件
	 * 转换*.pdf文件失败
	 * 
	 * @param docFile
	 *            需要转换的文档
	 * @param filepath
	 *            文档中图片的保存位置
	 * @return 转换成功的html代码
	 */
	public static String office2HtmlString(File docFile, String filepath,String PDF2HtmlEX_HOME,String OpenOffice_HOME )
	{
		// 转换word文档
		File htmlFile = convert(docFile, filepath,PDF2HtmlEX_HOME,OpenOffice_HOME);
		// 获取html文件流
		StringBuffer htmlSb = new StringBuffer();
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(htmlFile),"GBK"));
			while (br.ready())
			{
				htmlSb.append(br.readLine());
			}
			br.close();
			// 删除html临时文件
			//htmlFile.delete();
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		// HTML文件字符串
		String htmlStr = htmlSb.toString();
		// 把html中的table样式修改
		htmlStr = htmlStr.replaceAll("VOID", "BOX");
		// 修改后的样式写到html中
		try
		{
			FileWriter fw=new FileWriter(htmlFile);
			fw.write(htmlStr);
			fw.flush();
			fw.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return htmlStr;
		// 返回经过清洁的html文本
		//return clearFormat(htmlStr, filepath);
	}

	/**
	 * 清除一些不需要的html标记
	 * 
	 * @param htmlStr
	 *            带有复杂html标记的html语句
	 * @return 去除了不需要html标记的语句
	 */
	protected static String clearFormat(String htmlStr, String docImgPath)
	{
		// 获取body内容的正则
		String bodyReg = "<BODY .*</BODY>";
		Pattern bodyPattern = Pattern.compile(bodyReg);
		Matcher bodyMatcher = bodyPattern.matcher(htmlStr);
		if (bodyMatcher.find())
		{
			// 获取BODY内容，并转化BODY标签为DIV
			htmlStr = bodyMatcher.group().replaceFirst("<BODY", "<DIV").replaceAll("</BODY>", "</DIV>");
		}
		// 调整图片地址
		htmlStr = htmlStr.replaceAll("<IMG SRC=\"", "<IMG SRC=\"" + docImgPath + "/");
		// 把<P></P>转换成</div></div>保留样式
		// content = content.replaceAll("(<P)([^>]*>.*?)(<\\/P>)",
		// "<div$2</div>");
		// 把<P></P>转换成</div></div>并删除样式
		htmlStr = htmlStr.replaceAll("(<P)([^>]*)(>.*?)(<\\/P>)", "<p$3</p>");
		// 删除不需要的标签
		htmlStr = htmlStr.replaceAll(
				"<[/]?(font|FONT|span|SPAN|xml|XML|del|DEL|ins|INS|meta|META|[ovwxpOVWXP]:\\w+)[^>]*?>", "");
		// 删除不需要的属性
		htmlStr = htmlStr.replaceAll(
				"<([^>]*)(?:lang|LANG|class|CLASS|style|STYLE|size|SIZE|face|FACE|[ovwxpOVWXP]:\\w+)=(?:'[^']*'|\"\"[^\"\"]*\"\"|[^>]+)([^>]*)>",
				"<$1$2>");
		return htmlStr;
	}

}