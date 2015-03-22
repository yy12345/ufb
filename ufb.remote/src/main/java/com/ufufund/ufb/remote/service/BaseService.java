package com.ufufund.ufb.remote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ufufund.ufb.common.utils.HttpClientUtils;
import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.remote.xml.base.MessageRequest;
import com.ufufund.ufb.remote.xml.base.MessageResponse;
import com.ufufund.ufb.remote.xml.base.Requestbody;
import com.ufufund.ufb.remote.xml.base.Responsebody;

public class BaseService {
	// 使用实例的类名记录日志
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Value("${hft_requestUrl}")
	private String requestUrl = "";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T send(Object request, Class reqClazz, Class resClazz){
		
		String requestXml = JaxbUtil.toXml(request, MessageRequest.class, Requestbody.class, reqClazz);
		
		String responseXml = HttpClientUtils.httpsPost(requestUrl, requestXml);
		
		responseXml = JaxbUtil.buildResponseXml(responseXml, resClazz);
		return (T) JaxbUtil.toBean(responseXml, MessageResponse.class, Responsebody.class, resClazz);
	}
}
