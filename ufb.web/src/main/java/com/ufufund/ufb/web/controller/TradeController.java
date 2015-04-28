package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.RedeemVo;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class TradeController {
	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
	
	private static final String PAGE_PAY_INDEX = "trade/pay_index.htm";

	@Autowired
	private TradeManager tradeManager;
	
	@Autowired
	private BankCardManager bankCardManager;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	
	@RequestMapping(value="trade/pay_index")
	public String buyIndex(ApplyVo vo, Model model){
		
		
		try{
			String custno = UserHelper.getCustno();
			// 获取用户的银行卡列表
//			List<Bankcardinfo> bankCardList = bankCardManager.getBankcardinfoList(custno);
			List<Bankcardinfo> bankCardList = genBankcardinfoList();
			
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
			model.addAttribute("curCard", bankCardList.get(0));
			model.addAttribute("cardList", bankCardList);
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
//			String custno = UserHelper.getCustno();
			String custno = "cu20150427001";
			vo.setCustno(custno);
			vo.setAppvol(vo.getAppamt());
			vo.setTradeacco("tranAcco001");
			
			tradeManager.buyApply(vo);
			
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_PAY_INDEX);
			return "error/user_error";
		}
		
		return "trade/pay_result";
	}
	
	@RequestMapping(value="trade/cash_index")
	public String redeemIndex(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取用户的银行卡列表
//			List<Bankcardinfo> bankCardList = bankCardManager.getBankcardinfoList(custno);
			List<Bankcardinfo> bankCardList = genBankcardinfoList();
			
			// 获取用户总资产
			// code...
			BigDecimal totalBalance = new BigDecimal("50000.00");
			
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			
			model.addAttribute("totalBalance", totalBalance);
			model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(totalBalance));
			model.addAttribute("curCard", bankCardList.get(0));
			model.addAttribute("cardList", bankCardList);
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_PAY_INDEX);
			return "error/user_error";
		}
		
		return "trade/cash_index";
	}
	
	@RequestMapping(value="trade/cash_result")
	public String redeemApply(RedeemVo vo, Model model){
		
		try{
//			String custno = UserHelper.getCustno();
			String custno = "cu20150427001";
			vo.setCustno(custno);
			vo.setAppvol(vo.getAppamt());
			vo.setTradeacco("tranAcco002");
			
			tradeManager.redeem(vo);
			
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_PAY_INDEX);
			return "error/user_error";
		}
		
		return "trade/cash_result";
	}
	
	private List<Bankcardinfo> genBankcardinfoList(){
		List<Bankcardinfo> list = new ArrayList<Bankcardinfo>();
		Bankcardinfo b1 = new Bankcardinfo();
		b1.setSerialid("s_001");
		b1.setBankno("01");
		b1.setBankaccodisplay("6226095920226081");
		Bankcardinfo b2 = new Bankcardinfo();
		b2.setSerialid("s_002");
		b2.setBankno("02");
		b2.setBankaccodisplay("6226095920226071");
		list.add(b1);
		list.add(b2);
		return list;
	}
}
