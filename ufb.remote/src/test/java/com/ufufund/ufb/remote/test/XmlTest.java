package com.ufufund.ufb.remote.test;

import org.junit.Test;

import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.remote.xml.base.MessageRequest;
import com.ufufund.ufb.remote.xml.base.MessageResponse;
import com.ufufund.ufb.remote.xml.base.Requestbody;
import com.ufufund.ufb.remote.xml.base.Responsebody;
import com.ufufund.ufb.remote.xml.pojo.BankAuthRequest;
import com.ufufund.ufb.remote.xml.pojo.BankAuthResponse;

@SuppressWarnings("rawtypes")
public class XmlTest {

//	@Test
	public void testReqToXml(){
		
		BankAuthRequest br = new BankAuthRequest();
		br.setVersion("1.0.0");
		br.setMerchantId("000100059");
		br.setDistributorCode("001002");
		br.setBusinType("001");
		br.setApplicationNo("serial11120001222");
		br.setClearingAgencyCode("0009ccc");
		
		Requestbody rb = new Requestbody();
		rb.setRequest(br);
		
		MessageRequest mq = new MessageRequest();
		mq.setRequestbody(rb);
		mq.setSignature("asdfsadfsafsafsdfasasdfs");
		
		Class[] clazzes = new Class[]{MessageRequest.class, Requestbody.class, BankAuthRequest.class};
		String xml = JaxbUtil.toXml(mq, clazzes);
		System.out.println(xml);
	}
	
//	@Test
	public void testReqToBean(){
		
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+"<Message id=\"mq001001002\">                             "
				+"    <Requestbody>                                      "
				+"        <Request>"
				+"            <version>1.0.0</version>                 "
				+"            <merchantId>000100059</merchantId>       "
				+"            <distributorCode>001002</distributorCode>"
				+"            <businType>001</businType>                      "
				+"            <applicationNo>serial11120001222</applicationNo>"
				+"            <clearingAgencyCode>0009ccc</clearingAgencyCode>"
				+"        </Request>                                 "
				+"    </Requestbody>                                 "
				+"    <Signature>asdfsadfsafsafsdfasasdfs</Signature>"
				+"</Message>";
		
		xml = JaxbUtil.buildRequestXml(xml, BankAuthRequest.class);
		System.out.println(xml);
		
		Class[] clazzes = new Class[]{MessageRequest.class, Requestbody.class, BankAuthRequest.class};
		MessageRequest mq = JaxbUtil.toBean(xml, clazzes);
		
		System.out.println(mq);
	}
	
//	@Test
	public void testResToXml(){
		
		BankAuthResponse response = new BankAuthResponse();
		response.setVersion("1.0.0");
		response.setMerchantId("000100059");
		response.setDistributorCode("001002");
		response.setBusinType("001");
		response.setApplicationNo("serial11120001222");
		response.setReturnCode("0000");
		response.setReturnMsg("交易成功");
		response.setExtension("others...");
		
		Responsebody rb = new Responsebody();
		rb.setResponse(response);
		
		MessageResponse mr = new MessageResponse();
		mr.setResponsebody(rb);
		mr.setSignature("ssssssss----sssss");

		Class[] clazzes = new Class[]{MessageResponse.class, Responsebody.class, BankAuthResponse.class};
		String xml = JaxbUtil.toXml(mr, clazzes);
		
		System.out.println(xml);
	}
	
	@Test
	public void testResToBean(){
		
//		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>"
//				+"<Message id=\"000000011111\">                            "
//				+"    <Responsebody>                                     "
//				+"        <Response>"
//				+"            <version>1.0.0</version>                                                                "
//				+"            <merchantId>000100059</merchantId>                                                      "
//				+"            <distributorCode>001002</distributorCode>                                               "
//				+"            <businType>001</businType>                                                              "
//				+"            <applicationNo>serial11120001222</applicationNo>                                        "
//				+"            <returnCode>0000</returnCode>                                                           "
//				+"            <returnMsg>交易成功</returnMsg>                                                         "
//				+"            <extension>others...</extension>                                                        "
//				+"            <accoreqSerial>ac--111</accoreqSerial>                                                  "
//				+"        </Response>                                                                                 "
//				+"    </Responsebody>                                                                                 "
//				+"    <Signature>ssssssss----sssss</Signature>                                                        "
//				+"</Message>                                                                                          ";

		String xml = "<Message><Responsebody><Response><ReturnCode>5004</ReturnCode><ReturnMsg>参数检验出错</ReturnMsg></Response></Responsebody><Signature>17C51EEDB8F177E5B8FA02C08B802ED2</Signature></Message>";
		
		xml = JaxbUtil.buildResponseXml(xml, BankAuthResponse.class);
		System.out.println(xml);
		
		Class[] clazzes = new Class[]{MessageResponse.class, Responsebody.class, BankAuthResponse.class};
		MessageResponse mr = JaxbUtil.toBean(xml, clazzes);
		System.out.println(mr);

	}
	
//	@Test
	public void TestGetBoundTypeName(){
		
		String boundType = JaxbUtil.getBoundTypeName(BankAuthResponse.class);
		System.out.println("boundType="+boundType);
	}
	
}
