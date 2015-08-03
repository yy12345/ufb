package com.ufufund.ufb.remote.test.hftfund;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.hftfund.BalanceQueryRequest;
import com.ufufund.ufb.model.hftfund.BalanceQueryResponse;
import com.ufufund.ufb.model.hftfund.TransQueryRequest;
import com.ufufund.ufb.model.hftfund.TransQueryResponse;
import com.ufufund.ufb.remote.hftfund.HftQueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-ufb-remote.xml" })
public class HftQueryServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftQueryServiceTest.class);

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
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.TransQuery);
		request.setApplicationNo("20150522QQ0001");
		request.setTransactionAccountID("226000006703");
//		request.setAppSheetSerialNo("cccc0000111");
		request.setPageNo("1");
		request.setPageSize("10");

		TransQueryResponse response = hftQueryService.transQuery(request);
		LOG.debug("返回对象:" + response.toString());
	}

	/**
	 * 份额查询
	 * 
	 * @param request
	 * @return
	 */
//	@Test
	public void balanceQuery() {
		BalanceQueryRequest request = new BalanceQueryRequest();
		
		request.setVersion(Constant.HftSysConfig.Version);
		request.setMerchantId(Constant.HftSysConfig.MerchantId);
		request.setDistributorCode(Constant.HftSysConfig.DistributorCode);
		request.setBusinType(Constant.HftBusiType.BalanceQuery);
		request.setApplicationNo("201505210QQ0001");
		request.setTransactionAccountID("226000006703");
		request.setFundCode(BasicFundinfo.YFB.getFundCode());
		request.setPageNo("1");
		request.setPageSize("10");
		request.setShareClass("0");

		BalanceQueryResponse response = hftQueryService.balanceQuery(request);
		LOG.debug("返回对象:" + response.toString());
		
	}
}
