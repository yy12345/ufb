package com.ufufund.ufb.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtils extends org.apache.commons.lang3.math.NumberUtils{

	/**
	 * 0位小数格式化工具
	 */
	public static final NumberFormat NF_0FRACTION = NumberFormat
			.getNumberInstance();
	static {
		NF_0FRACTION.setMinimumFractionDigits(0);
		NF_0FRACTION.setMaximumFractionDigits(0);
	}

	/**
	 * 2位小数格式化工具
	 */
	public static final NumberFormat NF_2FRACTION = NumberFormat
			.getNumberInstance();
	static {
		NF_2FRACTION.setMinimumFractionDigits(2);
		NF_2FRACTION.setMaximumFractionDigits(2);
	}
	
	/**
     * 2位小数格式化工具，向下转换
     * 如：6999.9999转成6999.99
     */
    public static final NumberFormat NF_2FRACTION_DOWN = NumberFormat
            .getNumberInstance();
    static {
        NF_2FRACTION_DOWN.setMinimumFractionDigits(2);
        NF_2FRACTION_DOWN.setMaximumFractionDigits(2);
        NF_2FRACTION_DOWN.setRoundingMode(RoundingMode.DOWN);
    }

	/**
	 * 4位小数格式化工具
	 */
	public static final NumberFormat NF_4FRACTION = NumberFormat
			.getNumberInstance();
	static {
		NF_4FRACTION.setMinimumFractionDigits(4);
		NF_4FRACTION.setMaximumFractionDigits(4);
	}

	/**
	 * 3位小数格式化工具
	 */
	public static final NumberFormat NF_3FRACTION = NumberFormat
			.getNumberInstance();
	static {
		NF_3FRACTION.setMinimumFractionDigits(3);
		NF_3FRACTION.setMaximumFractionDigits(3);
	}

	/**
	 * 资金转换工具，转换后100 000
	 */
	public static final DecimalFormat DF_CASH = new DecimalFormat(
			"##0.00");

	/**
	 * 资金转换工具，转换后100000.00 
	 */
	public static final DecimalFormat DF_CASH_CONMMA = new DecimalFormat(
			",##0.00");
	
	/**
	 * 验证输入的信息{是否为纯数字，以及长度为length}
	 * @since
	 * @param str
	 * @param length
	 * @return true
	 * <br>创建时间：2015年3月30日
	 */
	public static boolean isFixedLengthNum(String str, int length) {
		
		if (str == null) {
			return false;
		} else {
			if (str.length() != length) {
				return false;
			}
			
			if (!org.apache.commons.lang3.StringUtils.isNumeric(str)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 转换成大写金额
	 * @since
	 * @param key
	 * @param max
	 * @param str
	 * @return
	 * <br>创建时间：2015年3月30日
	 */
	public static String Number2traditional(String key, String max, String str) {
		
		if (StringUtils.isBlank(str)) {
			str = "元";
		}
		
		
		return null;
	}
	
	/**
	 * 金额除以10000, 返回万为单位
	 * @param String
	 * @return
	 * <br>创建时间：2015年3月30日
	 */
	public static String NumberDividToW(String amt){
		String result = "0";
		if(StringUtils.isBlank(amt)){
		    return result;
		}
		BigDecimal oriamt = new BigDecimal(amt);
		BigDecimal aftamt = oriamt.divide(new BigDecimal(10000));
		result = aftamt.toString();
		
		return result;
	}
	
	/**
	 * 金额除以10000, 返回万为单位
	 * @param String
	 * @return
	 * <br>创建时间：2015年3月30日
	 */
	public static String NumberDividToW(BigDecimal amt){
		String result = "0";
		if(amt == null || amt.compareTo(BigDecimal.ZERO) == 0){
		    return result;
		}
		BigDecimal oriamt = amt;
		BigDecimal aftamt = oriamt.divide(new BigDecimal(10000));
		result = aftamt.toString();
		
		return result;
	}
	/**
	 * 金额乘以10000, 返回元为单位
	 * @param String
	 * @return
	 * <br>创建时间：2015年3月30日
	 */
	public static String NumberMultiply(String amt){
		String result = "0";
		if(StringUtils.isBlank(amt)){
		    return result;
		}
		BigDecimal oriamt = new BigDecimal(amt);
		BigDecimal aftamt = oriamt.multiply(new BigDecimal(10000));
		result = aftamt.toString();
		
		return result;
	}

}
