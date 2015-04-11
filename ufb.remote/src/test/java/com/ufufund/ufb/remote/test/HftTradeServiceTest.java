package com.ufufund.ufb.remote.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ufufund.ufb.remote.service.HftTradeService;
import com.ufufund.ufb.remote.xml.pojo.BuyApplyRequest;
import com.ufufund.ufb.remote.xml.pojo.BuyApplyResponse;
import com.ufufund.ufb.remote.xml.pojo.BuyNotifyRequest;
import com.ufufund.ufb.remote.xml.pojo.BuyNotifyResponse;
import com.ufufund.ufb.remote.xml.pojo.CancelRequest;
import com.ufufund.ufb.remote.xml.pojo.CancelResponse;
import com.ufufund.ufb.remote.xml.pojo.FrozenRequest;
import com.ufufund.ufb.remote.xml.pojo.FrozenResponse;
import com.ufufund.ufb.remote.xml.pojo.RealRedeemRequest;
import com.ufufund.ufb.remote.xml.pojo.RealRedeemResponse;
import com.ufufund.ufb.remote.xml.pojo.RedeemRequest;
import com.ufufund.ufb.remote.xml.pojo.RedeemResponse;
import com.ufufund.ufb.remote.xml.pojo.SubApplyRequest;
import com.ufufund.ufb.remote.xml.pojo.SubApplyResponse;
import com.ufufund.ufb.remote.xml.pojo.TransferRequest;
import com.ufufund.ufb.remote.xml.pojo.TransferResponse;
import com.ufufund.ufb.remote.xml.pojo.UnFrozenRequest;
import com.ufufund.ufb.remote.xml.pojo.UnFrozenResponse;

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
	@Test
	public void subApply(SubApplyRequest request){
	}
	
	/**
	 * 申购
	 * @param request
	 * @return
	 */
	@Test
	public void buyApply(BuyApplyRequest request){
	}
	
	/**
	 * 普通赎回
	 * @param request
	 * @return
	 */
	@Test
	public void redeem(RedeemRequest request){
	}
	
	/**
	 * 快速赎回
	 * @param request
	 * @return
	 */
	@Test
	public void realRedeem(RealRedeemRequest request){
	}
	
	/**
	 * 撤单
	 * @param request
	 * @return
	 */
	@Test
	public void cancel(CancelRequest request){
	}
	
	/**
	 * 认申购扣款时，支付通知
	 * @param request
	 * @return
	 */
	@Test
	public void buyNotify(BuyNotifyRequest request){
	}
	
	/**
	 * 冻结
	 * @param request
	 * @return
	 */
	@Test
	public void frozen(FrozenRequest request){
	}
	
	/**
	 * 解冻
	 * @param request
	 * @return
	 */
	@Test
	public void unfrozen(UnFrozenRequest request){
	}
	
	/**
	 * 快速过户
	 * @param request
	 * @return
	 */
	@Test
	public void transfer(TransferRequest request){
	}
}
