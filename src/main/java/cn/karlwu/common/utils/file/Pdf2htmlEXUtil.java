package cn.karlwu.common.utils.file;

/**
 * 用pdf2htmlEX工具把pdf文件转成html
 */
public class Pdf2htmlEXUtil
{
	
	public static boolean pdf2html(String pdfName, String htmlName,String PDF2HtmlEX_HOME,String OpenOffice_HOME)
	{
		if (PDF2HtmlEX_HOME == null){
			return false;
		}
		Runtime rt = Runtime.getRuntime();
		try{
			Process p = rt.exec(PDF2HtmlEX_HOME + " " + pdfName + " " + htmlName);
			
			System.out.println(pdfName);
			System.out.println(htmlName);
			
			StreamGobbler errorGobbler = new StreamGobbler(p.getErrorStream(), "ERROR");
			errorGobbler.start();
			StreamGobbler outGobbler = new StreamGobbler(p.getInputStream(), "STDOUT");
			outGobbler.start();
			p.waitFor();
			
			int w = p.waitFor();
			System.out.println(w);
			p.exitValue();
			
			int v = p.exitValue();
			System.out.println(v);
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
