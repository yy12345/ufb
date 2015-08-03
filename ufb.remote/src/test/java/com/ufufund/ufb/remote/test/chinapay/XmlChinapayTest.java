package com.ufufund.ufb.remote.test.chinapay;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.model.chinapay.QueryTrans;
import com.ufufund.ufb.model.chinapay.Request;
import com.ufufund.ufb.model.chinapay.RequestBody;
import com.ufufund.ufb.model.chinapay.RequestInfo;
import com.ufufund.ufb.model.chinapay.TransDetail;
import com.ufufund.ufb.model.chinapay.TransSum;

public class XmlChinapayTest {

	@Test
	public void toXml(){
		
		Request req = new Request();
		RequestInfo reqInfo = new RequestInfo();
		RequestBody reqBody = new RequestBody();
		TransSum tSum = new TransSum();
		TransDetail tDetail = new TransDetail();
		
		reqInfo.setTRX_CODE("100001");
		reqInfo.setVERSION("05");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setLEVEL("5");
		reqInfo.setUSER_NAME("operator");
		reqInfo.setUSER_PASS("operator");
		reqInfo.setREQ_SN("20151022000044612312");
		reqInfo.setSIGNED_MSG("ssssssssssssssss");
		
		tSum.setMERCHANT_ID("001053110000001");
		tSum.setBUSINESS_CODE("");
		
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("44622848008122698");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setID_TYPE("0");
		tDetail.setID("320722197708036301");
		tDetail.setTEL("15211827360");
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		tDetailList.add(tDetail);
		
		reqBody.setTRANS_SUM(tSum);
		reqBody.setTRANS_DETAIL(tDetailList);
		
		/** 查询测试  - begin  **/
		QueryTrans qtrans = new QueryTrans();
		qtrans.setQUERY_SN("0001111122222000");
		qtrans.setQUERY_REMARK("查询测试");
		reqBody.setQUERY_TRANS(qtrans);
		/** 查询测试  - end  **/
		
		req.setINFO(reqInfo);
		req.setBODY(reqBody);
		
		String xml = JaxbUtil.toXml(req, "gbk", Request.class);
		
		System.out.println(xml);
	}
	
//	@Test
	public void toBean(){
		
		String xml = "<?xml version=\"1.0\" encoding=\"gbk\" standalone=\"yes\"?>   "
				+"<GZELINK>                                                 "
				+"    <INFO>                                                "
				+"        <TRX_CODE>100001</TRX_CODE>                       "
				+"        <VERSION>05</VERSION>                             "
				+"        <DATA_TYPE>2</DATA_TYPE>                          "
				+"        <LEVEL>5</LEVEL>                                  "
				+"        <USER_NAME>operator</USER_NAME>                   "
				+"        <USER_PASS>operator</USER_PASS>                   "
				+"        <REQ_SN>20151022000044612312</REQ_SN>             "
				+"        <SIGNED_MSG>ssssssssssssssss</SIGNED_MSG>         "
				+"    </INFO>                                               "
				+"    <BODY>                                                "
				+"        <TRANS_SUM>                                       "
				+"            <BUSINESS_CODE></BUSINESS_CODE>               "
				+"            <MERCHANT_ID>001053110000001</MERCHANT_ID>    "
				+"            <TOTAL_SUM>0</TOTAL_SUM>                      "
				+"        </TRANS_SUM>                                      "
				+"        <TRANS_DETAILS>                                   "
				+"            <TRANS_DETAIL>                                "
				+"                <BANK_CODE>102</BANK_CODE>                "
				+"                <ACCOUNT_TYPE>00</ACCOUNT_TYPE>           "
				+"                <ACCOUNT_NO>44622848008122698</ACCOUNT_NO>"
				+"                <ACCOUNT_NAME>栾望水</ACCOUNT_NAME>       "
				+"                <ID_TYPE>0</ID_TYPE>                      "
				+"                <ID>320722197708036301</ID>               "
				+"            </TRANS_DETAIL>                               "
				+"        </TRANS_DETAILS>                                  "
				+"    </BODY>                                               "
				+"</GZELINK>                                                ";

		Class[] clazzes = new Class[]{Request.class, RequestInfo.class, 
				RequestBody.class, TransSum.class, TransDetail.class};
		Request req = JaxbUtil.toBean(xml, clazzes);

		System.out.println(req);
	}
}
