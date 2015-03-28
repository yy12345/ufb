package com.ufufund.ufb.remote.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.HftCommonUtils;
import com.ufufund.ufb.common.utils.HttpClientUtils;
import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.remote.xml.base.MessageResponse;
import com.ufufund.ufb.remote.xml.base.Responsebody;

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
	
	@Value("${hft_requestUrl}")
	private String requestUrl = "";
	
	@Value("hft_signKey")
	private String signKey = "123456111";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T send(Object  request, Class responseClazz){
		
		/** 解析request参数 **/
		Map<String, String> params = HftCommonUtils.Object2Map(request);
		String sign = HftCommonUtils.sign(params, ENCODING, signKey);
		params.put("sign", sign);
		// post
		String messageXml = HttpClientUtils.post(requestUrl, params, ENCODING);
		
		// 获取reponse验签明文
		String dataStr = messageXml.substring(messageXml.indexOf("<Response>"),
				messageXml.indexOf("</Response>") + "</Response>".length());
		
		/** 解析返回的xml报文 **/ 
		messageXml = JaxbUtil.buildResponseXml(messageXml, responseClazz);
		MessageResponse messageResponse = JaxbUtil.toBean(messageXml, MessageResponse.class, Responsebody.class, responseClazz);
		
		// 验签
		String sign1 = EncryptUtil.md5(signKey+dataStr+signKey, ENCODING);
		if(!sign1.equals(messageResponse.getSignature())){
			LOG.error("验签失败：messageXml="+messageXml);
			return null;
		}
		
		// 检验message报文的id
		// code ...
		
		return (T) messageResponse.getResponsebody().getResponse();
		
	}
	
	
}
