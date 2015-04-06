package com.ufufund.ufb.common.utils;



import java.lang.reflect.Field;
import java.text.NumberFormat;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
	
	
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
	 * 输出对象的字段属性
	 * @param object
	 * @return
	 */
	public static String getString(Object object) {
		try {
			if (null == object) {
				return null;
			} else {
				Field[] field = object.getClass().getDeclaredFields();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < field.length; i++) {
					field[i].setAccessible(true);
					if (i == field.length - 1) {
						sb.append(field[i].getName() + ": "
								+ field[i].get(object) + ".");
					} else {
						sb.append(field[i].getName() + ": "
								+ field[i].get(object) + ", ");
					}
				}
				return sb.toString();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
