package com.ufufund.ufb.remote.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.remote.service.HftTradeService;
import com.ufufund.ufb.remote.xml.pojo.BuyApplyRequest;
import com.ufufund.ufb.remote.xml.pojo.BuyApplyResponse;
import com.ufufund.ufb.remote.xml.pojo.BuyNotifyRequest;
import com.ufufund.ufb.remote.xml.pojo.BuyNotifyResponse;
import com.ufufund.ufb.remote.xml.pojo.CancelRequest;
import com.ufufund.ufb.remote.xml.pojo.CancelResponse;
import com.ufufund.ufb.remote.xml.pojo.FrozenRequest;
import com.ufufund.ufb.remote.xml.pojo.RealRedeemRequest;
import com.ufufund.ufb.remote.xml.pojo.RealRedeemResponse;
import com.ufufund.ufb.remote.xml.pojo.RedeemRequest;
import com.ufufund.ufb.remote.xml.pojo.RedeemResponse;
import com.ufufund.ufb.remote.xml.pojo.SubApplyRequest;
import com.ufufund.ufb.remote.xml.pojo.SubApplyResponse;
import com.ufufund.ufb.remote.xml.pojo.TransferRequest;
import com.ufufund.ufb.remote.xml.pojo.UnFrozenRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftTradeServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftTradeServiceTest.class);
	
	@Autowired
	private HftTradeService hftTradeService;
	
	/**
	 * 认购
	 * @param request
	 * @return
	 */
//	@Test
	public void subApply(){
		
		SubApplyRequest request = new SubApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.SubApply);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setFundCode("001002");
		request.setApplicationAmount(new BigDecimal("100.50"));
		request.setShareClass("0");
		
		SubApplyResponse response = hftTradeService.subApply(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 申购
	 * @param request
	 * @return
	 */
//	@Test
	public void buyApply(){
		BuyApplyRequest request = new BuyApplyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BuyApply);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setFundCode("001002");
		request.setApplicationAmount(new BigDecimal("100.50"));
		request.setShareClass("0");
		request.setAutoFrozen("0");
		
		BuyApplyResponse response = hftTradeService.buyApply(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 普通赎回
	 * @param request
	 * @return
	 */
//	@Test
	public void redeem(){
		RedeemRequest request = new RedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Redeem);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setFundCode("001002");
		request.setApplicationVol(new BigDecimal("100"));
		request.setShareClass("0");
		
		RedeemResponse response = hftTradeService.redeem(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 快速赎回
	 * @param request
	 * @return
	 */
//	@Test
	public void realRedeem(){
		RealRedeemRequest request = new RealRedeemRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.RealRedeem);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setFundCode("001002");
		request.setApplicationVol(new BigDecimal("100"));
		request.setShareClass("0");
		
		RealRedeemResponse response = hftTradeService.realRedeem(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 撤单
	 * @param request
	 * @return
	 */
//	@Test
	public void cancel(){
		CancelRequest request = new CancelRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Cancel);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setOriginalAppSheetNo("20150410CC0001");
		
		CancelResponse response = hftTradeService.cancel(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 认申购扣款时，支付通知
	 * @param request
	 * @return
	 */
	@Test
	public void buyNotify(){
		BuyNotifyRequest request = new BuyNotifyRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BuyNotify);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setAppSheetSerialNo("xxx000011111");
		
		BuyNotifyResponse response = hftTradeService.buyNotify(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 冻结
	 * @param request
	 * @return
	 */
//	@Test
	public void frozen(FrozenRequest request){
	}
	
	/**
	 * 解冻
	 * @param request
	 * @return
	 */
//	@Test
	public void unfrozen(UnFrozenRequest request){
	}
	
	/**
	 * 快速过户
	 * @param request
	 * @return
	 */
//	@Test
	public void transfer(TransferRequest request){
	}
}
