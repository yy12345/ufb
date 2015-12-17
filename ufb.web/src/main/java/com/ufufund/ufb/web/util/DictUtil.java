package com.ufufund.ufb.web.util;

import java.util.HashMap;
import java.util.Map;

public class DictUtil {

	private static Map<String,Map<String,String>> globalMapping = new HashMap<String,Map<String,String>>();
	private static Map<String,String> dic1 = new HashMap<String,String>();
	private static Map<String,String> dic2 = new HashMap<String,String>();
	static {
		globalMapping.put("org_item_price.type", dic1);
		dic1.put("01", "保育费");
		dic1.put("02", "膳食费");
		dic1.put("03", "短期培训班费");
		dic1.put("04", "代收代缴费");
		dic1.put("00", "其它");
		
		globalMapping.put("org_item_price.chage_type", dic2);
		dic2.put("1", "月");
		dic2.put("2", "学期");
		dic2.put("3", "学年");
	}
	
	/**
	 * 获取字典项值
	 * @param name 字典名称
	 * @param key 字典项key
	 * @return 成功，返回：字典项value；否则，返回空串
	 */
	public static String get(String name, String key){
		Map<String,String> dic = globalMapping.get(name);
		if(dic != null){
			return dic.get(key);
		}
		return "";
	}
}
