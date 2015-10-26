package com.ufufund.ufb.remote.chinapay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gnete.security.crypt.Crypt;
import com.gnete.security.crypt.CryptException;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.HttpClientUtils;
import com.ufufund.ufb.common.utils.JaxbUtil;
import com.ufufund.ufb.model.chinapay.QueryTrans;
import com.ufufund.ufb.model.chinapay.Request;
import com.ufufund.ufb.model.chinapay.RequestBody;
import com.ufufund.ufb.model.chinapay.RequestInfo;
import com.ufufund.ufb.model.chinapay.Response;
import com.ufufund.ufb.model.chinapay.TransDetail;
import com.ufufund.ufb.model.chinapay.TransSum;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChinapayService {

	private static final String ENCODING = "gbk";
	
	@Value("${ufb.chinapay.requestUrl}")
	private String requestUrl;
	@Value("${ufb.chinapay.username}")
	private String username;
	@Value("${ufb.chinapay.userpass}")
	private String userpass;
	@Value("${ufb.chinapay.merchantId}")
	private String merchantId;
	@Value("${ufb.chinapay.publicKey}")
	private String publicKey;
	@Value("${ufb.chinapay.privateKey}")
	private String privateKey;
	@Value("${ufb.chinapay.privateKeyPwd}")
	private String privateKeyPwd;
	
	private String downUrl = "http://59.41.103.98:333/gzdsf/GetSettFile.do";
	private String settleFileDir = "E:/workspace/ufufund/cp_data/";
	
	private static final String TRX_CODE_100009 = "100009";  // 账户验证
	private static final String TRX_CODE_300004 = "300004";  // 代付白名单（商户签约）
	private static final String TRX_CODE_100001 = "100001";  // 批量代收
	private static final String TRX_CODE_100002 = "100002";  // 批量代付
	private static final String TRX_CODE_100004 = "100004";  // 实时代收
	private static final String TRX_CODE_100005 = "100005";  // 实时代付
	private static final String TRX_CODE_200001 = "200001";	 // 交易查询
	
	
	/**
	 * 银行卡账户验证接口 
	 * @param req
	 * @return
	 */
	public Response checkAccount(String serialno, TransDetail tDetail){
		
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_100009);
		reqInfo.setVERSION("05");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(serialno);
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// body/sum
		TransSum tSum = new TransSum();
		tSum.setMERCHANT_ID(merchantId);
		reqBody.setTRANS_SUM(tSum);
		
		// body/details
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		tDetailList.add(tDetail);
		reqBody.setTRANS_DETAIL(tDetailList);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 代付签约接口
	 * @param serialno
	 * @param tDetail
	 * @return
	 */
	public Response paySign(String serialno, TransDetail tDetail){
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_300004);
		reqInfo.setVERSION("04");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(serialno);
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// body/sum
		TransSum tSum = new TransSum();
		tSum.setMERCHANT_ID(merchantId);
		tSum.setTOTAL_ITEM(1);
		reqBody.setTRANS_SUM(tSum);
		
		// body/details
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		tDetailList.add(tDetail);
		reqBody.setTRANS_DETAIL(tDetailList);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 批量代收接口
	 * @param serialno
	 * @param tDetailList
	 * @return
	 */
	public Response collectMoney(String serialno, List<TransDetail> tDetailList, String businessCode){
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_100001);
		reqInfo.setVERSION("04");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(serialno);
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// body/sum
		int totalSum = 0;
		for(TransDetail tDetail : tDetailList){
			totalSum += tDetail.getAMOUNT();
		}
		TransSum tSum = new TransSum();
		tSum.setBUSINESS_CODE(businessCode);
		tSum.setMERCHANT_ID(merchantId);
		tSum.setTOTAL_ITEM(tDetailList.size());
		tSum.setTOTAL_SUM(totalSum);
		reqBody.setTRANS_SUM(tSum);
		
		// body/details
		reqBody.setTRANS_DETAIL(tDetailList);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 批量代付接口
	 * @param serialno
	 * @param tDetailList
	 * @return
	 */
	public Response payMoney(String serialno, List<TransDetail> tDetailList, String businessCode){
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_100002);
		reqInfo.setVERSION("04");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(serialno);
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// body/sum
		int totalSum = 0;
		for(TransDetail tDetail : tDetailList){
			totalSum += tDetail.getAMOUNT();
		}
		TransSum tSum = new TransSum();
		tSum.setBUSINESS_CODE(businessCode);
		tSum.setMERCHANT_ID(merchantId);
		tSum.setTOTAL_ITEM(tDetailList.size());
		tSum.setTOTAL_SUM(totalSum);
		reqBody.setTRANS_SUM(tSum);
		
		// body/details
		reqBody.setTRANS_DETAIL(tDetailList);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 实时代收接口
	 * @param serialno
	 * @param tDetailList
	 * @return
	 */
	public Response collectMoney(String serialno, TransDetail tDetail, String businessCode){
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_100004);
		reqInfo.setVERSION("04");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(serialno);
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// body/sum
		TransSum tSum = new TransSum();
		tSum.setBUSINESS_CODE(businessCode);
		tSum.setMERCHANT_ID(merchantId);
		tSum.setTOTAL_ITEM(1);
		tSum.setTOTAL_SUM(tDetail.getAMOUNT());
		reqBody.setTRANS_SUM(tSum);
		
		// body/details
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		tDetailList.add(tDetail);
		reqBody.setTRANS_DETAIL(tDetailList);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 实时代付接口
	 * @param serialno
	 * @param tDetailList
	 * @return
	 */
	public Response payMoney(String serialno, TransDetail tDetail, String businessCode){
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_100005);
		reqInfo.setVERSION("04");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(serialno);
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// body/sum
		TransSum tSum = new TransSum();
		tSum.setBUSINESS_CODE(businessCode);
		tSum.setMERCHANT_ID(merchantId);
		tSum.setTOTAL_ITEM(1);
		tSum.setTOTAL_SUM(tDetail.getAMOUNT());
		reqBody.setTRANS_SUM(tSum);
		
		// body/details
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		tDetailList.add(tDetail);
		reqBody.setTRANS_DETAIL(tDetailList);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 交易结果查询（单笔）
	 * @param queryTrans
	 * @return
	 */
	public Response queryTrans(QueryTrans queryTrans){
		
		Request req = new Request();
		RequestBody reqBody = new RequestBody();
		RequestInfo reqInfo = new RequestInfo();
		
		// info
		reqInfo.setTRX_CODE(TRX_CODE_200001);
		reqInfo.setVERSION("04");
		reqInfo.setDATA_TYPE("2");
		reqInfo.setUSER_NAME(username);
		reqInfo.setUSER_PASS(userpass);
		reqInfo.setREQ_SN(String.valueOf(System.currentTimeMillis()));
		reqInfo.setSIGNED_MSG("");
		req.setINFO(reqInfo);
		
		// querytrans
		reqBody.setQUERY_TRANS(queryTrans);
		req.setBODY(reqBody);
		
		return send(req);
	}
	
	/**
	 * 下载对账文件
	 * @param date 日期
	 * @param type 收付类型：S-代收；F代付
	 */
	public void downSettleFile(String date, String type){
		
		String reqtime = DateUtil.format(new Date(), DateUtil.FULL_PATTERN_1);
		String reqUrl = downUrl + "?SETT_DATE="+date+"&SETT_NO=01&SF_TYPE="+type+"&USER_NAME="+username+"&MERCHANT_ID="+merchantId
				+"&REQ_TIME="+reqtime+"&SIGNED_MSG=";
		
		String signData = date+"|01|"+username+"|"+merchantId+"|"+reqtime;
		String signMsg = sign(signData);
		reqUrl += signMsg;
		
		log.info("下载请求报文："+reqUrl);
		String filename = null;
		if("S".equals(type)){
			filename = date + "01_ds.txt";
		}else if("F".equals(type)){
			filename = date + "01_df.txt";
		}
		String filepath = settleFileDir+"/"+date+"/"+filename;
		HttpClientUtils.down(reqUrl, filepath);
		
	}
	
	private Response send(Request req){
		
		String reqXml = JaxbUtil.toXml(req, ENCODING, Request.class);
		// 签名
		String signData = reqXml.replace("<SIGNED_MSG></SIGNED_MSG>", "");
		String signMsg = sign(signData);
		reqXml = reqXml.replace("<SIGNED_MSG></SIGNED_MSG>", "<SIGNED_MSG>"+signMsg+"</SIGNED_MSG>");
		
		log.info("请求报文："+reqXml);
		String responseXml = HttpClientUtils.post(requestUrl, reqXml, ENCODING);
		log.info("响应报文："+responseXml);
		
		Response response = JaxbUtil.toBean(responseXml, Response.class);
		
		// 验签
		String verifyData = responseXml.substring(0, responseXml.indexOf("<SIGNED_MSG>"))
				+ responseXml.substring(responseXml.indexOf("</SIGNED_MSG>")+13);
		if(!verify(verifyData, response.getINFO().getSIGNED_MSG())){
			log.error("银联-验签响应报文失败！");
			throw new SysException("银联-验签响应报文失败！");
		}
		return response;
	}
	
	private String sign(String signData){
		try {
			Crypt crypt = new Crypt(ENCODING);
			return crypt.sign(signData, privateKey, privateKeyPwd);
		} catch (CryptException e) {
			log.error("签名异常："+e.getMessage(), e);
			throw new SysException("银联-请求报文签名异常！");
		}
	}
	
	private boolean verify(String verifyData, String signMsg){
		try {
			Crypt crypt = new Crypt(ENCODING);
			return crypt.verify(verifyData,signMsg, publicKey);
		} catch (CryptException e) {
			log.error("验签异常："+e.getMessage(), e);
			throw new SysException("银联-验签响应报文异常！");
		}
	}
	
}
