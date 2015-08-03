package com.ufufund.ufb.remote.test.hftfund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.model.hftfund.BalanceQueryAsset;
import com.ufufund.ufb.model.hftfund.BalanceQueryResponse;
import com.ufufund.ufb.model.hftfund.BankAuthResponse;
import com.ufufund.ufb.model.hftfund.MessageResponse;
import com.ufufund.ufb.model.hftfund.Responsebody;
import com.ufufund.ufb.model.hftfund.TransQueryAsset;
import com.ufufund.ufb.model.hftfund.TransQueryResponse;

@SuppressWarnings("rawtypes")
public class XmlHftfundTest {
	
	public static final String CHARSET = "utf-8";

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
		String xml = JaxbUtil.toXml(mr, CHARSET, clazzes);
		
		System.out.println(xml);
	}
	
//	@Test
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
	public void testList2Xml(){
		
		List<TransQueryAsset> assets = new ArrayList<TransQueryAsset>();
		TransQueryAsset asset1 = new TransQueryAsset();
		asset1.setAppSheetSerialNo("sheet-001");
		TransQueryAsset asset2 = new TransQueryAsset();
		asset2.setAppSheetSerialNo("sheet-002");
		TransQueryAsset asset3 = new TransQueryAsset();
		asset3.setAppSheetSerialNo("sheet-003");
		assets.add(asset1);
		assets.add(asset2);
		assets.add(asset3);
		
		TransQueryResponse tqs = new TransQueryResponse();
		tqs.setVersion("1.0.0");
		tqs.setApplicationNo("xxx-0001");
		tqs.setTotalRecord(assets.size());
		tqs.setAssets(assets);
		
		Responsebody rb = new Responsebody();
		rb.setResponse(tqs);
		
		MessageResponse mr = new MessageResponse();
		mr.setResponsebody(rb);
		mr.setSignature("ssssssss----sssss");
		
		Class[] clazzes = new Class[]{MessageResponse.class, Responsebody.class, TransQueryResponse.class};
		String xml = JaxbUtil.toXml(mr, CHARSET, clazzes);
		System.out.println(xml);
	}
	
//	@Test
	public void testXml2List(){
		
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>   "
				+"<Message>                                                                                             "
				+"    <Responsebody>                                                                                    "
				+"        <Response xsi:type=\"transQueryResponse\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+"            <Version>1.0.0</Version>                                                                  "
				+"            <ApplicationNo>xxx-0001</ApplicationNo>                                                   "
				+"            <TotalRecord>3</TotalRecord>                                                              "
				+"            <assetList>                                                                               "
				+"                <asset>                                                                               "
				+"                    <AppSheetSerialNo>sheet-001</AppSheetSerialNo>                                    "
				+"                </asset>                                                                              "
				+"                <asset>                                                                               "
				+"                    <AppSheetSerialNo>sheet-002</AppSheetSerialNo>                                    "
				+"                </asset>                                                                              "
				+"                <asset>                                                                               "
				+"                    <AppSheetSerialNo>sheet-003</AppSheetSerialNo>                                    "
				+"                </asset>                                                                              "
				+"            </assetList>                                                                              "
				+"        </Response>                                                                                   "
				+"    </Responsebody>                                                                                   "
				+"    <Signature>ssssssss----sssss</Signature>                                                          "
				+"</Message>                                                                                            ";
		
		Class[] clazzes = new Class[]{MessageResponse.class, Responsebody.class, TransQueryResponse.class};
		MessageResponse mq = JaxbUtil.toBean(xml, clazzes);
		
		System.out.println(mq);
	}
	
//	@Test
	public void testList2Xml2(){
		
		List<BalanceQueryAsset> assets = new ArrayList<BalanceQueryAsset>();
		BalanceQueryAsset asset1 = new BalanceQueryAsset();
		asset1.setAvailableVol(new BigDecimal("100.12345"));
		BalanceQueryAsset asset2 = new BalanceQueryAsset();
		asset2.setAvailableVol(new BigDecimal("200.12345"));
		BalanceQueryAsset asset3 = new BalanceQueryAsset();
		asset3.setAvailableVol(new BigDecimal("300.12345"));
		assets.add(asset1);
		assets.add(asset2);
		assets.add(asset3);
		
		BalanceQueryResponse bqs = new BalanceQueryResponse();
		bqs.setVersion("1.0.0");
		bqs.setApplicationNo("xxx-0001");
		bqs.setTotalRecord(assets.size());
		bqs.setAssets(assets);
		
		Responsebody rb = new Responsebody();
		rb.setResponse(bqs);
		
		MessageResponse mr = new MessageResponse();
		mr.setResponsebody(rb);
		mr.setSignature("ssssssss----sssss");
		
		Class[] clazzes = new Class[]{MessageResponse.class, Responsebody.class, BalanceQueryResponse.class};
		String xml = JaxbUtil.toXml(mr, CHARSET, clazzes);
		System.out.println(xml);
	}
	
	@Test
	public void testXml2List2(){
		
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>                                                     "
				+"<Message>                                                                                                "
				+"    <Responsebody>                                                                                       "
				+"        <Response xsi:type=\"balanceQueryResponse\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> "
				+"            <Version>1.0.0</Version>                                                                     "
				+"            <ApplicationNo>xxx-0001</ApplicationNo>                                                      "
				+"            <TotalRecord>3</TotalRecord>                                                                 "
				+"            <assetList>                                                                                  "
				+"                <asset>                                                                                  "
				+"                    <AvailableVol>100.12345</AvailableVol>                                               "
				+"                </asset>                                                                                 "
				+"                <asset>                                                                                  "
				+"                    <AvailableVol>200.12345</AvailableVol>                                               "
				+"                </asset>                                                                                 "
				+"                <asset>                                                                                  "
				+"                    <AvailableVol>300.12345</AvailableVol>                                               "
				+"                </asset>                                                                                 "
				+"            </assetList>                                                                                 "
				+"        </Response>                                                                                      "
				+"    </Responsebody>                                                                                      "
				+"    <Signature>ssssssss----sssss</Signature>                                                             "
				+"</Message>                                                                                               ";
	
		Class[] clazzes = new Class[]{MessageResponse.class, Responsebody.class, BalanceQueryResponse.class};
		MessageResponse mq = JaxbUtil.toBean(xml, clazzes);
		
		System.out.println(mq);
	}
	
	
	
}
