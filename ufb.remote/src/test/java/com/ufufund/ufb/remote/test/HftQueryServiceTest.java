package com.ufufund.ufb.remote.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.remote.service.HftQueryService;
import com.ufufund.ufb.remote.xml.pojo.BalanceQueryRequest;
import com.ufufund.ufb.remote.xml.pojo.BalanceQueryResponse;
import com.ufufund.ufb.remote.xml.pojo.TransQueryRequest;
import com.ufufund.ufb.remote.xml.pojo.TransQueryResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-ufb-remote.xml"})
public class HftQueryServiceTest {
	private static Logger LOG = LoggerFactory.getLogger(HftQueryServiceTest.class);

	@Autowired
	private HftQueryService hftQueryService;
	
	/**
	 * 交易查询
	 * @param request
	 * @return
	 */
	public TransQueryResponse transQuery(TransQueryRequest request){
		return super.send(request, TransQueryResponse.class);
	}
	
	/**
	 * 份额查询
	 * @param request
	 * @return
	 */
	public BalanceQueryResponse balanceQuery(BalanceQueryRequest request){
		return super.send(request, BalanceQueryResponse.class);
	}
}
