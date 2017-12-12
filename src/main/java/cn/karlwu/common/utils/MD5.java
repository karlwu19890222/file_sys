package cn.karlwu.common.utils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MD5 {
	public static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat df_standard = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat df_yyMMdd = new SimpleDateFormat("yyMMdd");
	public static DecimalFormat decimalFormatTwo = new DecimalFormat("#.##");
	public static DecimalFormat decimalFormatOne = new DecimalFormat("#.#");
	public static DecimalFormat decimalFormatThree = new DecimalFormat("#.###");

	/**
	 * 密码加密
	 * 
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		// MD5加密
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// 得到加密对象
		md.update(str.getBytes());
		// 进行加密，并返回字符数组
		byte[] bytes = md.digest();
		// 字节数组转换成十六进制字符串，最终密文
		String md5_str = "";
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xff;
			if (v < 16) {
				md5_str = md5_str + "0";
			}
			md5_str = md5_str + Integer.toHexString(v);
		}
		return md5_str;
	}

	public static int caluModifyDays(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date modifyDate;
		try {
			modifyDate = df.parse(time);
			long modifyDay = modifyDate.getTime();
			Date currentDate = new Date();
			long currentDay = currentDate.getTime();
			return (int) ((currentDay - modifyDay) / 86400000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 9999;
	}

	public static int caluSignDays(String time) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date signDate;
		try {
			signDate = df.parse(time);
			long signDay = signDate.getTime();
			Date currentDate = new Date();
			long currentDay = currentDate.getTime();
			return (int) ((signDay - currentDay) / 86400000);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 9999;
	}


	/**
	 * 小数点保留位
	 * 
	 * @param temp
	 * @param length
	 * @return
	 */
	public static double adjustDouble(double temp) {
		BigDecimal b = new BigDecimal(Double.toString(temp));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
