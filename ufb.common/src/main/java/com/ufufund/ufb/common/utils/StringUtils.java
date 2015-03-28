package com.ufufund.ufb.common.utils;



import java.lang.reflect.Field;
import java.text.NumberFormat;

public class StringUtils extends org.apache.commons.lang3.StringUtils{
	/**
	 * 格式化输出
	 * @since
	 * @param s
	 * @param type
	 * @return
	 * <br><b>作者： 周靖</b>
	 * <br>创建时间：2014-6-17 下午4:42:24
	 */
	public static String format(String key,String s,String type){
		if(s == null|| "".equals(s)
		|| key == null || "".equals(key)){
			return "";
		}
		if(type == null){
			return s;
		}
		
		if("LogFormat".equals(type)){
			return key.toUpperCase()+"=["+s+"] ";
		}else{
			format(s,type);
		}
		return s;
	}
	
	public static String format(String s,String type){
		if(s == null|| "".equals(s)){
			return "";
		}
		if(type == null){
			return s;
		}
		if("PwdFormat".equals(type)){
			return "******";
		}else if("CardDisplay".equals(type)){
			return s.substring(s.length()-4, s.length());
		}
		return s;
	}
	
	
	/**
	 * 验证输入的信息{是否为纯数字，以及长度为length}
	 * @since
	 * @param str
	 * @param length
	 * @return true
	 * <br>创建时间：2014年6月17日 下午5:18:34
	 */
	public static boolean isFixedLengthNum(String str, int length) {
		
//		if (str == null) {
//			throw new BizException("您输入的信息不能为空！");
//		} else {
//			if (str.length() != length) {
//				throw new BizException("您输入的信息:"+ str +" 非法，长度必须为" + length + "!");
//			}
//			
//			if (!org.apache.commons.lang3.StringUtils.isNumeric(str)) {
//				throw new BizException("您输入的信息:"+ str +" 非法，必须为纯数字！");
//			}
//		}
		
		return true;
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
