package com.ufufund.ufb.remote.test.hftfund;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.hftfund.NotifyPaidRequest;
import com.ufufund.ufb.model.hftfund.NotifyPaidResponse;
import com.ufufund.ufb.model.hftfund.NotifyUploadedRequest;
import com.ufufund.ufb.model.hftfund.NotifyUploadedResponse;
import com.ufufund.ufb.remote.hftfund.HftNotifyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftNotifyServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftNotifyServiceTest.class);
	
	@Autowired
	private HftNotifyService hftNotifyService;

	/**
	 * 认申购扣款时，支付通知
	 * @param request
	 * @return
	 */
//	@Test
	public void paid(){
		NotifyPaidRequest request = new NotifyPaidRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BuyNotify);
		request.setApplicationNo("20150410CC0010");
		request.setTransactionAccountID("0001");
		request.setAppSheetSerialNo("xxx000011111");
		
		NotifyPaidResponse response = hftNotifyService.paid(request);
		LOG.debug("返回对象:" + response.toString());
	}
	
	/**
	 * 机构开户影印资料上传完成通知
	 * @param request
	 * @return
	 */
	@Test
	public void uploaded(){
		NotifyUploadedRequest request = new NotifyUploadedRequest();
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.uploadedNotify);
		request.setApplicationNo("20150608CC0001");
		request.setTransactionAccountID("0001");
		request.setAppSheetSerialNo("xxx000011111");
		
		NotifyUploadedResponse response = hftNotifyService.uploaded(request);
		LOG.debug("返回对象:" + response.toString());
	}
}
