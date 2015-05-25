package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.RedeemVo;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class TradeController {
	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
	
	private static final String PAGE_PAY_INDEX = "trade/pay_index.htm";
	private static final String PAGE_CASH_INDEX = "trade/cash_index.htm";

	@Autowired
	private TradeManager tradeManager;
	
	@Autowired
	private TradeAccoManager tradeAccoManager;
	
	@Autowired
	private QueryManager queryManager;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	
	@RequestMapping(value="trade/pay_index")
	public String buyIndex(ApplyVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("curCard", tradeAccoList.get(0));
				model.addAttribute("cardList", tradeAccoList);
			}
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("profitArriveDay", DateUtil.convert(profitArriveDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
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
			String custno = UserHelper.getCustno();
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			vo.setFee(new BigDecimal("0.00"));
			vo.setShareclass(BasicFundinfo.YFB.getShareClass());
			vo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
			
			tradeManager.buyApply(vo);
			
			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
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
			// 获取交易账户列表
			List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			// 获取用户总资产
			Assets assets = queryManager.queryAssets(tradeAccoList);
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			
			
			model.addAttribute("total", assets.getTotal()); // 总资产
			model.addAttribute("available", assets.getAvailable()); //可用资产
			model.addAttribute("frozen", assets.getFrozen()); // 冻结资产
			model.addAttribute("totalDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));
			model.addAttribute("availableDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));
			model.addAttribute("frozenDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));

//			model.addAttribute("totalBalance", assets.getAvailable());
//			model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));
			BigDecimal cardAvailable = assets.getAccoList().get(0).getAvailable();
			BigDecimal cardRealAvailable = assets.getAccoList().get(0).getRealavailable();
//			BigDecimal cardFrozen = assets.getAccoList().get(0).getFrozen();
			model.addAttribute("cardAvailable", cardAvailable);
			model.addAttribute("cardAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardAvailable));
			model.addAttribute("cardRealAvailable", cardRealAvailable);
			model.addAttribute("cardRealAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardRealAvailable));
			
			model.addAttribute("card", assets.getAccoList().get(0));
			model.addAttribute("cardList", assets.getAccoList());
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_CASH_INDEX);
			return "error/user_error";
		}
		
		return "trade/cash_index";
	}
	
	@RequestMapping(value="trade/cash_result")
	public String redeemApply(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			vo.setFee(new BigDecimal("0.00"));
			vo.setShareclass(BasicFundinfo.YFB.getShareClass());
			vo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
			
			if("023".equals(vo.getApkind())){
				tradeManager.redeem(vo);
			}else if("024".equals(vo.getApkind())){
				tradeManager.realRedeem(vo);
			}else{
				throw new SysException("异常交易！");
			}
			
			model.addAttribute("APKIND", vo.getApkind());
			
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_CASH_INDEX);
			return "error/user_error";
		}
		
		model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
		return "trade/cash_result";
	}
	
//	private List<BankCardWithTradeAcco> genBankcardinfoList(){
//		List<BankCardWithTradeAcco> list = new ArrayList<BankCardWithTradeAcco>();
//		BankCardWithTradeAcco b1 = new BankCardWithTradeAcco();
//		b1.setSerialid("s_001");
//		b1.setBankno("002");
//		b1.setBankaccodisplay("6226095920226081");
//		b1.setTradeacco("TradeAcco001");
//		b1.setFundcorpno("01");
//		BankCardWithTradeAcco b2 = new BankCardWithTradeAcco();
//		b2.setSerialid("s_002");
//		b2.setBankno("004");
//		b2.setBankaccodisplay("6226095920226071");
//		b2.setTradeacco("TradeAcco002");
//		b2.setFundcorpno("01");
//		list.add(b1);
//		list.add(b2);
//		return list;
//	}
}
