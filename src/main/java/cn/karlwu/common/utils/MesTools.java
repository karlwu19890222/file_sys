package cn.karlwu.common.utils;

import java.util.List;


/**  
 * Copyright © 2017karlwu. All rights reserved.
 * @Title: MesTools.java
 * @Prject: Sunvou_Main
 * @Package: com.sunvou.common.utils
 * @Description: TODO
 * @author: Karl  
 * @date: 2017年9月19日 上午9:16:36
 * @version: V1.0  
 */
public class MesTools {
	
	public static double SquareErrorWays(String testRecordResult,double sums,Integer size) {
		double offest = 0;
		offest = offest+ Math.pow(Double.parseDouble(testRecordResult) - sums, 2);
		offest = Math.sqrt(offest / size);
		double resultRep = offest / sums;
		return resultRep;
	}
	
	
    public static Integer getMaxNumBetweenTwo(Integer num01,Integer num02) {
    	if (num01>num02) {
			return num01;
		}else {
			return num02;
		}
	}
    
    public static Integer getMinNumBetweenTwo(Integer num01,Integer num02) {
    	if (num01>num02) {
			return num02;
		}else {
			return num01;
		}
	}
    
    
    public static Integer getMaxNumBetweenList(List<Integer> nums) {
    	Integer max=0;
    	for (Integer integer : nums) {
			if(integer>max){
				max=integer;
			}
		}
    	return max;
	}
    
    public static Integer getMinNumBetweenList(List<Integer> nums) {
    	Integer min=0;
    	for (Integer integer : nums) {
			if(integer<min){
				min=integer;
			}
		}
    	return min;
	}
    
    public static String getAverage(List<Integer> nums) {
    	Integer size=nums.size();
    	Integer sums=0;
    	for (Integer integer : nums) {
    		sums+=integer;
		}
    	return MD5.decimalFormatThree.format(sums/size);
	}
}

