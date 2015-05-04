package com.ufufund.ufb.remote.test;

import java.math.BigDecimal;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.remote.hft.BuyApplyRequest;
import com.ufufund.ufb.model.remote.hft.BuyApplyResponse;
import com.ufufund.ufb.model.remote.hft.BuyNotifyRequest;
import com.ufufund.ufb.model.remote.hft.BuyNotifyResponse;
import com.ufufund.ufb.model.remote.hft.CancelRequest;
import com.ufufund.ufb.model.remote.hft.CancelResponse;
import com.ufufund.ufb.model.remote.hft.FrozenRequest;
import com.ufufund.ufb.model.remote.hft.FrozenResponse;
import com.ufufund.ufb.model.remote.hft.RealRedeemRequest;
import com.ufufund.ufb.model.remote.hft.RealRedeemResponse;
import com.ufufund.ufb.model.remote.hft.RedeemRequest;
import com.ufufund.ufb.model.remote.hft.RedeemResponse;
import com.ufufund.ufb.model.remote.hft.SubApplyRequest;
import com.ufufund.ufb.model.remote.hft.SubApplyResponse;
import com.ufufund.ufb.model.remote.hft.TransferRequest;
import com.ufufund.ufb.model.remote.hft.TransferResponse;
import com.ufufund.ufb.model.remote.hft.UnFrozenRequest;
import com.ufufund.ufb.model.remote.hft.UnFrozenResponse;
import com.ufufund.ufb.remote.HftTradeService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftTradeServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftTradeServiceTest.class);
	
//	@Autowired
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
		request.setApplicationNo("20150410AA0001");
		request.setTransactionAccountID("225000006320");
		request.setFundCode("025010");
		request.setApplicationAmount(new BigDecimal("1000.00"));
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
		request.setApplicationNo("20150410AA0002");
		request.setTransactionAccountID("225000006320");
		request.setFundCode("025020");
		request.setApplicationAmount(new BigDecimal("1000.00"));
		request.setShareClass("0");
		request.setAutoFrozen("1");
		
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
		request.setApplicationNo("20150410AA0006");
		request.setTransactionAccountID("225000006320");
		request.setFundCode("025020");
		request.setApplicationVol(new BigDecimal("2000.00"));
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
		request.setApplicationNo("20150411AA0008");
		request.setTransactionAccountID("225000006320");
		request.setFundCode("225010");
		request.setApplicationVol(new BigDecimal("2000.00"));
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
		request.setApplicationNo("20150410AA0003");
		request.setTransactionAccountID("225000006320");
		request.setOriginalAppSheetNo("20140923001563");
		
		CancelResponse response = hftTradeService.cancel(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 认申购扣款时，支付通知
	 * @param request
	 * @return
	 */
//	@Test
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
	public void frozen() {
		FrozenRequest request = new FrozenRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Frozen);
		request.setTransactionAccountID("225000006320");
		request.setApplicationNo("20150409AA0004");
		request.setFundCode("025020");
		request.setApplicationVol(new BigDecimal("1000.00"));
		request.setShareClass("0");

		FrozenResponse response = hftTradeService.frozen(request);
		LOG.debug(response.toString());
	}

	/**
	 * 解冻
	 * @param request
	 * @return
	 */
//	@Test
	public void unfrozen() {
		UnFrozenRequest request = new UnFrozenRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Unfrozen);
		request.setTransactionAccountID("225000006320");
		request.setApplicationNo("20150408AA0001");
		request.setOriginalAppSheetNo("20150416001736"); 
		request.setApplicationVol(new BigDecimal("1000.00"));

		UnFrozenResponse response = hftTradeService.unfrozen(request);
		LOG.debug(response.toString());
	}

	/**
	 * 快速过户
	 * @param request
	 * @return
	 */
//    @Test
	public void transfer() {
		TransferRequest request = new TransferRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.Transfer);
		request.setApplicationNo("2015041100003");
		request.setTransactionAccountID("8888");
		request.setFundCode("888888");
		request.setApplicationVol(new BigDecimal("111111"));

		TransferResponse response = hftTradeService.transfer(request);
		LOG.debug(response.toString());
	}
    
}