package cn.karlwu.common.utils.file;
import java.io.File;
public class FileUtils {
	/**
	 * 判断文件是否存在
	 * @param path
	 * @return
	 */
	public static boolean isFileExists(String path) {
		File file = new File(path);
		if (file.exists()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断目录是否存在，不存在就创建
	 * 
	 * @param path
	 */
	public static void checkDir(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	/**
	 * 计算文件大小
	 * @param size
	 * @return
	 */
	public static String formatFileSize(long size){
		long temp = size / 1024;
		if(temp < 1024){
			return temp+" KB";
		}else{
			temp = temp / 1024;
			if(temp < 1024){
				return temp+" M";
			}else{
				temp = temp / 1024;
				return temp+" G";
			}
		}
	}
	
	public static boolean deleteFile(String filePath){
		if(isFileExists(filePath)){
			File file = new File(filePath);
			if(file.delete()){
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

}
