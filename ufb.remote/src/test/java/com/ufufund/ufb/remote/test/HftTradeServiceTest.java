package com.ufufund.ufb.remote.test;

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
	public SubApplyResponse subApply(SubApplyRequest request){
		return super.send(request, SubApplyResponse.class);
	}
	
	/**
	 * 申购
	 * @param request
	 * @return
	 */
	public BuyApplyResponse buyApply(BuyApplyRequest request){
		return super.send(request, BuyApplyResponse.class);
	}
	
	/**
	 * 普通赎回
	 * @param request
	 * @return
	 */
	public RedeemResponse redeem(RedeemRequest request){
		return super.send(request, RedeemResponse.class);
	}
	
	/**
	 * 快速赎回
	 * @param request
	 * @return
	 */
	public RealRedeemResponse realRedeem(RealRedeemRequest request){
		return super.send(request, RealRedeemResponse.class);
	}
	
	/**
	 * 撤单
	 * @param request
	 * @return
	 */
	public CancelResponse cancel(CancelRequest request){
		return super.send(request, CancelResponse.class);
	}
	
	/**
	 * 认申购扣款时，支付通知
	 * @param request
	 * @return
	 */
	public BuyNotifyResponse buyNotify(BuyNotifyRequest request){
		return super.send(request, BuyNotifyResponse.class);
	}
	
	/**
	 * 冻结
	 * @param request
	 * @return
	 */
	public FrozenResponse frozen(FrozenRequest request){
		return super.send(request, FrozenResponse.class);
	}
	
	/**
	 * 解冻
	 * @param request
	 * @return
	 */
	public UnFrozenResponse unfrozen(UnFrozenRequest request){
		return super.send(request, UnFrozenResponse.class);
	}
	
	/**
	 * 快速过户
	 * @param request
	 * @return
	 */
	public TransferResponse transfer(TransferRequest request){
		return super.send(request, TransferResponse.class);
	}
}
