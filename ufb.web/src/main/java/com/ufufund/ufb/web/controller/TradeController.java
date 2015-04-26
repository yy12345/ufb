package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;
import com.ufufund.ufb.model.vo.Today;

@Controller
public class TradeController {
	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
	
	private static final String PAGE_PAY_INDEX = "trade/pay_index.htm";

	@Autowired
	private TradeManager tradeManager;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@RequestMapping(value="trade/pay_index")
	public String buyIndex(ApplyVo vo, Model model){
		
		
		try{
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("profitArriveDay", DateUtil.convert(profitArriveDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_PAY_INDEX);
			return "error/user_error";
		}
		
		return "trade/pay_index";
	}
	
	@RequestMapping(value="trade/pay_result")
	public String buyApply(ApplyVo vo, Model model){
		
		try{
			
			tradeManager.buyApply(vo);
			
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_PAY_INDEX);
			return "error/user_error";
		}
		
		return "trade/pay_result";
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
