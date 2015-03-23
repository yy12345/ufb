package com.ufufund.ufb.remote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.ufufund.ufb.common.utils.HttpClientUtil;
import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.remote.xml.base.MessageRequest;
import com.ufufund.ufb.remote.xml.base.MessageResponse;
import com.ufufund.ufb.remote.xml.base.Requestbody;
import com.ufufund.ufb.remote.xml.base.Responsebody;

public class HftBaseService {
	// 使用实例的类名记录日志
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	// http交易的编码
	private static final String ENCODING = "utf-8";
	
	@Value("${hft_requestUrl}")
	private String requestUrl = "";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected <T> T send(Object  request, Class reqClazz, Class resClazz){
		
		/** 组装request的message对象 **/
		Requestbody requestbody = new Requestbody();
		requestbody.setRequest(request);
		
		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setId(SequenceUtil.getSerialId4Message());
		messageRequest.setRequestbody(requestbody);
		messageRequest.setSignature("");
		
		// 生成发送报文
		String requestXml = JaxbUtil.toXml(messageRequest, MessageRequest.class, Requestbody.class, reqClazz);
		// 获取响应报文
		String responseXml = HttpClientUtil.httpsPost(requestUrl, requestXml, ENCODING);
		
		/** 获取xml的绑定对象 **/ 
		responseXml = JaxbUtil.buildResponseXml(responseXml, resClazz);
		MessageResponse messageResponse = JaxbUtil.toBean(responseXml, MessageResponse.class, Responsebody.class, resClazz);
		T t = (T) messageResponse.getResponsebody().getResponse();
		return t;
	}
}
