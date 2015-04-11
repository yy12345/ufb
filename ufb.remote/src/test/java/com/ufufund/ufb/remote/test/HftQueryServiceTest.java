package com.ufufund.ufb.remote.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.remote.service.HftQueryService;
import com.ufufund.ufb.remote.xml.pojo.BalanceQueryRequest;
import com.ufufund.ufb.remote.xml.pojo.BalanceQueryResponse;
import com.ufufund.ufb.remote.xml.pojo.TransQueryRequest;
import com.ufufund.ufb.remote.xml.pojo.TransQueryResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-remote.xml" })
public class HftQueryServiceTest {
	private static Logger LOG = LoggerFactory
			.getLogger(HftQueryServiceTest.class);

	@Autowired
	private HftQueryService hftQueryService;

	/**
	 * 交易查询
	 * 
	 * @param request
	 * @return
	 */
	@Test
	public void transQuery() {
		TransQueryRequest request = new TransQueryRequest();

		request.setTransactionAccountID("");
		request.setAppSheetSerialNo("");
		request.setPageNo("1");
		request.setPageSize("10");

		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.TransQuery);
		request.setApplicationNo("20150410CC0001");
		request.setExtension("");

		TransQueryResponse response = hftQueryService.transQuery(request);
		LOG.debug("返回对象:" + response.toString());
	}

	/**
	 * 份额查询
	 * 
	 * @param request
	 * @return
	 */
	@Test
	public void balanceQuery() {
		BalanceQueryRequest request = new BalanceQueryRequest();
		
		request.setTransactionAccountID("");
		request.setFundCode("");
		request.setPageNo("1");
		request.setPageSize("10");
		request.setShareClass("10");

		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.TransQuery);
		request.setApplicationNo("20150410CC0001");
		request.setExtension("");

		BalanceQueryResponse response = hftQueryService.balanceQuery(request);
		LOG.debug("返回对象:" + response.toString());
		
	}
}
