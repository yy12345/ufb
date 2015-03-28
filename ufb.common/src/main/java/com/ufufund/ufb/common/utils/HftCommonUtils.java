package com.ufufund.ufb.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 海富通工具类
 * @author ayis
 * 2015年3月27日
 */
public class HftCommonUtils {
	private static final Logger LOG = LoggerFactory.getLogger(HftCommonUtils.class);
	
	/**
	 * request对象转换为map
	 * @param obj pojo对象
	 * @return
	 */
	public static Map<String, String> Object2Map(Object obj){
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(obj != null){
				// 父类字段
				Field[] superFields = obj.getClass().getSuperclass().getDeclaredFields();
				for(Field f : superFields){
					f.setAccessible(true);
					map.put(f.getName(), f.get(obj)== null?"":f.get(obj).toString());
				}
				// 当前类字段
				Field[] fields = obj.getClass().getDeclaredFields();
				for(Field f : fields){
					f.setAccessible(true);
					map.put(f.getName(), f.get(obj)== null?"":f.get(obj).toString());
				}
			}
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOG.error(e.getMessage(), e);
		}
		return map;
	}

	/**
	 * 请求参数签名
	 * @param map
	 * @param signKey
	 * @return
	 */
    public static String sign(Map<String, String> map, String charset, String signKey){
        StringBuffer sBuffer = new StringBuffer();
        List<String> msgList = new ArrayList<String>();
        // 拼装原始签名参数
        for(Entry<String, String> entry: map.entrySet()){
        	if(StringUtils.isBlank(entry.getValue())){
        		continue;
        	}
        	msgList.add(entry.getKey() + "=" + entry.getValue());
        }
        // 排序
        Collections.sort(msgList);
        for (int i = 0; i < msgList.size(); i++) {
            String msg = msgList.get(i);
            if (i > 0) {
                sBuffer.append("&");
            }
            sBuffer.append(msg);
        }
        //密钥
        sBuffer.append("&key="+signKey);
        //加密
        return EncryptUtil.md5(sBuffer.toString(), charset).toUpperCase();
    }
}
