package com.ufufund.ufb.web.util;

import java.util.HashMap;
import java.util.Map;

public class DictUtil {

	private static Map<String,Map<String,String>> globalMapping = new HashMap<String,Map<String,String>>();
	private static Map<String,String> dic1 = new HashMap<String,String>();
	static {
		globalMapping.put("org_plan_detail.state", dic1);
		dic1.put("1", "草稿");
		dic1.put("2", "等待缴费");
		dic1.put("3", "等待扣款");
		dic1.put("4", "扣款失败");
		dic1.put("5", "扣款成功");
		dic1.put("6", "手工补缴");
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
