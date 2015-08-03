package com.ufufund.ufb.remote.test.chinapay;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.model.chinapay.QueryTrans;
import com.ufufund.ufb.model.chinapay.Response;
import com.ufufund.ufb.model.chinapay.TransDetail;
import com.ufufund.ufb.remote.chinapay.ChinapayService;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
@Slf4j
public class ChinapayServiceTest {
	
	private static final String RECKON_ACCOUNT = "001";

	@Autowired
	private ChinapayService chinapayService;
	
	/***
	 * 账户验证测试
	 */
//	@Test
	public void checkAccount(){
		
		TransDetail tDetail = new TransDetail();
		tDetail.setBANK_CODE("105");
		tDetail.setACCOUNT_NO("6227001823260036733");
		tDetail.setACCOUNT_NAME("吴小龄");
		tDetail.setID_TYPE("0");
		tDetail.setID("441509876512014787");
		tDetail.setTEL("13602459062");
		
		Response response = chinapayService
				.checkAccount(String.valueOf(System.currentTimeMillis()), tDetail);
		log.info("返回对象："+response);
		
		if("0000".equals(response.getINFO().getRET_CODE())){
			if("0000".equals(response.getBODY().getRET_DETAIL().get(0).getRET_CODE())){
				log.info("认证成功！");
			}else{
				log.info("认证失败：code="+response.getBODY().getRET_DETAIL().get(0).getRET_CODE()
						+", msg="+response.getBODY().getRET_DETAIL().get(0).getERR_MSG());
			}
		}else{
			log.error("系统异常：code="+response.getINFO().getRET_CODE()
					+", msg="+response.getINFO().getERR_MSG());
		}
	}
	
	/**
	 * 批量代收测试
	 */
//	@Test
	public void testCollectMoney4Batch(){
		
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		
		TransDetail tDetail = new TransDetail();
		tDetail.setSN(1);
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("4462270332418000");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setAMOUNT(10000);
		tDetail.setRECKON_ACCOUNT(RECKON_ACCOUNT);
		tDetailList.add(tDetail);
		
		tDetail = new TransDetail();
		tDetail.setSN(2);
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("44622848008122690");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setAMOUNT(20000);
//		tDetail.setRECKON_ACCOUNT("002");
		tDetailList.add(tDetail);
		
		Response response = chinapayService
				.collectMoney(String.valueOf(System.currentTimeMillis()), tDetailList, "10900");
		log.info("返回对象："+response);
		
		if("0000".equals(response.getINFO().getRET_CODE())){
			log.info("订单接收成功！");
		}else{
			log.error("系统异常：code="+response.getINFO().getRET_CODE()
					+", msg="+response.getINFO().getERR_MSG());
		}
	}
	
	/**
	 * 批量代付测试
	 */
//	@Test
	public void testpayMoney4Batch(){
		
		List<TransDetail> tDetailList = new ArrayList<TransDetail>();
		
		TransDetail tDetail = new TransDetail();
		tDetail.setSN(1);
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("4462270332418000");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setAMOUNT(10000);
//		tDetail.setRECKON_ACCOUNT(RECKON_ACCOUNT);
		tDetailList.add(tDetail);
		
		tDetail = new TransDetail();
		tDetail.setSN(2);
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("44622848008122690");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setAMOUNT(20000);
//		tDetail.setRECKON_ACCOUNT("002");
		tDetailList.add(tDetail);
		
		Response response = chinapayService
				.payMoney(String.valueOf(System.currentTimeMillis()), tDetailList, "09000");
		log.info("返回对象："+response);
		
		if("0000".equals(response.getINFO().getRET_CODE())){
			log.info("订单接收成功！");
		}else{
			log.error("系统异常：code="+response.getINFO().getRET_CODE()
					+", msg="+response.getINFO().getERR_MSG());
		}
	}
	
	/**
	 * 实时代收测试（单笔）
	 */
//	@Test
	public void testCollectMoney4Real(){
		
		TransDetail tDetail = new TransDetail();
		tDetail.setSN(1);
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("44622848008122690");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setAMOUNT(10000);
		tDetail.setRECKON_ACCOUNT(RECKON_ACCOUNT);
		
		Response response = chinapayService
				.collectMoney(String.valueOf(System.currentTimeMillis()), tDetail, "10900");
		log.info("返回对象："+response);
		
		String failInfoCode = "0001,0002,1000,1001,1002,2004,3045,3097";
		if("0000".equals(response.getINFO().getRET_CODE())){
			if("0000".equals(response.getBODY().getRET_DETAIL().get(0).getRET_CODE())){
				log.info("交易成功！");
			}else{
				log.info("交易失败！");
			}
		}else if(failInfoCode.indexOf(response.getINFO().getRET_CODE()) > 0){
			log.info("交易失败！");
		}else{
			log.info("交易处理中！");
		}
	}
	
	/**
	 * 实时代付测试（单笔）
	 */
//	@Test
	public void testPayMoney4Real(){
		TransDetail tDetail = new TransDetail();
		tDetail.setSN(1);
		tDetail.setBANK_CODE("102");
		tDetail.setACCOUNT_NO("44622848008122690");
		tDetail.setACCOUNT_NAME("栾望水");
		tDetail.setAMOUNT(10000);
		
		Response response = chinapayService
				.payMoney(String.valueOf(System.currentTimeMillis()), tDetail, "09000");
		log.info("返回对象："+response);
		
		String failInfoCode = "0001,0002,1000,1001,1002,2004,3045,3097";
		if("0000".equals(response.getINFO().getRET_CODE())){
			if("0000".equals(response.getBODY().getRET_DETAIL().get(0).getRET_CODE())){
				log.info("交易成功！");
			}else{
				log.info("交易失败！");
			}
		}else if(failInfoCode.indexOf(response.getINFO().getRET_CODE()) > 0){
			log.info("交易失败！");
		}else{
			log.info("交易处理中！");
		}
	}
	
//	@Test
	public void queryTrans(){
		
		QueryTrans queryTrans = new QueryTrans();
		queryTrans.setQUERY_SN("1438327580649");
		queryTrans.setQUERY_REMARK("幼富宝代收付查询");
		
		Response response = chinapayService.queryTrans(queryTrans);
		log.info("返回对象："+response);
		
		String failInfoCode = "0001,0002,1002,2002,2004,2006";
		if("0000".equals(response.getINFO().getRET_CODE())){
			if("0000".equals(response.getBODY().getRET_DETAIL().get(0).getRET_CODE())){
				log.info("交易成功！");
			}else{
				log.info("交易失败！");
			}
		}else if(failInfoCode.indexOf(response.getINFO().getRET_CODE()) > 0){
			log.info("交易失败！");
		}else{
			log.info("交易处理中！");
		}
	}
	
//	@Test
	public void testDownSettleFile(){
		
		String date = "20150526";
		String type = "S";
		chinapayService.downSettleFile(date, type);
	}
}
