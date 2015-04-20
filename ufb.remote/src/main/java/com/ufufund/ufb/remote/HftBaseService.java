package com.ufufund.ufb.remote;

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
import org.springframework.beans.factory.annotation.Value;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.HttpClientUtils;
import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.model.remote.hft.AbstractRequest;
import com.ufufund.ufb.model.remote.hft.AbstractResponse;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.MessageResponse;
import com.ufufund.ufb.model.remote.hft.Responsebody;

/**
 * 海富通remote接口基类
 * @author ayis
 * 2015年3月28日
 */
public class HftBaseService {
	// 使用实例的类名记录日志
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	// http交易的编码
	private static final String ENCODING = "utf-8";
	
//	@Value("${hft_requestUrl}")
	private String requestUrl = "http://60.191.25.162:12002/ecg/ecsg/prepositionaccess";
	
//	@Value("hft_signKey")
	private String signKey = "8db4a013a8b515349c307f1e448ce836";

	/**
	 * 海富通remote请求基础方法
	 * @param request 请求对象
	 * @param responseClazz
	 * @return 若通信失败或验签失败，会返回null；否则返回包含响应码的响应对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T send(Object  request, Class responseClazz){
		
		/** 解析request参数 **/
		Map<String, String> params = Object2Map(request);
		String sign = sign(params, ENCODING, signKey);
		params.put("sign", sign);
		
		// 发送请求
		LOG.info("请求参数："+params);
		String responseXml = HttpClientUtils.post(requestUrl, params, ENCODING);
		LOG.info("响应报文："+responseXml);
		
		if(StringUtils.isBlank(responseXml)){
			return null;
		}
		
		/** 解析返回的xml报文 **/ 
		responseXml = JaxbUtil.buildResponseXml(responseXml, responseClazz);
		MessageResponse messageResponse = JaxbUtil.toBean(responseXml, MessageResponse.class, Responsebody.class, responseClazz);
		T response = (T) messageResponse.getResponsebody().getResponse();
		
		// 验签
		Map<String, String> resMap = Object2Map(response);
		String sign1 = sign(resMap, ENCODING, signKey);
		if(!sign1.equals(messageResponse.getSignature())){
			LOG.error("验签失败：serialno="+((AbstractRequest)request).getApplicationNo()+" responseXml="+responseXml);
			return null;
		}
		return response;
	}
	
	
	/**
	 * request、response对象转换为map
	 * @param obj pojo对象
	 * @return
	 */
	private Map<String, String> Object2Map(Object obj){
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
					if(!"assets".equals(f.getName())){
						map.put(f.getName(), f.get(obj)== null?"":f.get(obj).toString());
					}
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
	 * 请求签名、响应验签
	 * @param map
	 * @param signKey
	 * @return
	 */
	private String sign(Map<String, String> map, String charset, String signKey){
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
