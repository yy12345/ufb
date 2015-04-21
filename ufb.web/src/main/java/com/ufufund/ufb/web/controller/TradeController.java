package com.ufufund.ufb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;

@Controller
public class TradeController {

	@Autowired
	private TradeManager tradeManager;
	
	@RequestMapping(value="buy/index")
	public String buyIndex(ApplyVo vo, Model model){
		
		// code...
		
		return "trade/buy_index.vm";
	}
	
	@RequestMapping(value="buy/apply")
	public String buyApply(ApplyVo vo, Model model){
		
		// code...
		tradeManager.buyApply(vo);
		
		return "trade/buy_apply.vm";
	}
	
	@RequestMapping(value="redeem/index")
	public String redeemIndex(RedeemVo vo, Model model){
		
		// code...
		
		return "trade/redeem_index.vm";
	}
	
	@RequestMapping(value="redeem/apply")
	public String redeemApply(RedeemVo vo, Model model){
		
		tradeManager.redeem(vo);
		
		return "trade/redeem_apply.vm";
	}
}
