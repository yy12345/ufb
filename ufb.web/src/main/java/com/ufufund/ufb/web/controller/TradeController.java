package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.CancelVo;
import com.ufufund.ufb.model.vo.RedeemVo;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.model.vo.TradeQueryVo;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class TradeController {
	private static final Logger LOG = LoggerFactory.getLogger(TradeController.class);
	
	private static final String PAGE_PAY_INDEX = "trade/pay_index.htm";
	private static final String PAGE_CASH_INDEX = "trade/cash_index.htm";
	
	private static final String PAGE_ORG_PAY_INDEX = "trade/orgPayIndex.htm";
	private static final String PAGE_ORG_CASH_INDEX = "trade/orgCashIndex.htm";

	@Autowired
	private TradeManager tradeManager;
	
	@Autowired
	private TradeAccoManager tradeAccoManager;
	
	@Autowired
	private QueryManager queryManager;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	
	@RequestMapping(value="trade/pay_index")
	public String payIndex(ApplyVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			levels.add("0"); 
			levels.add("2");
			
//			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
//					custno, 
//					Constant.HftSysConfig.HftFundCorpno, 
//					levels,
//					tradeaccosts);
			
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
//			if(null != tradeAccoList && tradeAccoList.size() > 0){
//				model.addAttribute("curCard", tradeAccoList.get(0));
//				model.addAttribute("cardList", tradeAccoList);
//			}
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("profitArriveDay", DateUtil.convert(profitArriveDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
		
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			//model.addAttribute("errorMsg", ue.getMessage());
			//model.addAttribute("returnUrl", PAGE_PAY_INDEX);
			
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAGE_PAY_INDEX);
			model.addAttribute("message_content0", "充值失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的充值，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			return "error/user_error";
		}
		
		return "trade/pay_index";
	}
	
	@RequestMapping(value="trade/orgPayIndex")
	public String orgPayIndex(ApplyVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			levels.add("1"); 
			
//			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
//					custno, 
//					Constant.HftSysConfig.HftFundCorpno, 
//					levels,
//					tradeaccosts);
			
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
//			if(null != tradeAccoList && tradeAccoList.size() > 0){
//				model.addAttribute("curCard", tradeAccoList.get(0));
//				model.addAttribute("cardList", tradeAccoList);
//			}
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("profitArriveDay", DateUtil.convert(profitArriveDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
		
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			//model.addAttribute("errorMsg", ue.getMessage());
			//model.addAttribute("returnUrl", PAGE_ORG_PAY_INDEX);
			
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAGE_ORG_PAY_INDEX);
			model.addAttribute("message_content0", "充值失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的充值，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		
		return "trade/orgPayIndex";
	}
	
	@RequestMapping(value="trade/pay_result")
	public String payResult(ApplyVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			vo.setFee(new BigDecimal("0.00"));
			vo.setShareclass(BasicFundinfo.YFB.getShareClass());
			vo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
			
			tradeManager.buyApply(vo);
			
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("profitArriveDay", DateUtil.convert(profitArriveDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAGE_PAY_INDEX);
			model.addAttribute("message_content0", "充值失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的充值，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		
		return "trade/pay_result";
	}
	
	@RequestMapping(value="trade/orgPayResult")
	public String orgPayResult(ApplyVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			vo.setFee(new BigDecimal("0.00"));
			vo.setShareclass(BasicFundinfo.YFB.getShareClass());
			vo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
			vo.setLevel("1");
			tradeManager.buyApply(vo);
			
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			String profitArriveDay = DateUtil.getNextDay(nextWorkDay, 1);
			
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("profitArriveDay", DateUtil.convert(profitArriveDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAGE_ORG_PAY_INDEX);
			model.addAttribute("message_content0", "充值失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的充值，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		
		return "trade/pay_result";
	}
	
	@RequestMapping(value="trade/cash_index")
	public String cashIndex(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			levels.add("0"); 
			levels.add("2"); 
			
//			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
//					custno, 
//					Constant.HftSysConfig.HftFundCorpno, 
//					levels,
//					tradeaccosts);
			// 获取用户总资产
//			Assets assets = queryManager.queryAssets(tradeAccoList, BasicFundinfo.YFB.getFundCode());
			Assets assets = null;
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			
			
			model.addAttribute("total", assets.getTotal()); // 总资产
			model.addAttribute("available", assets.getAvailable()); //可用资产
			model.addAttribute("frozen", assets.getFrozen()); // 冻结资产
			model.addAttribute("totalDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));
			model.addAttribute("availableDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));
			model.addAttribute("frozenDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));
			
			BigDecimal cardAvailable = assets.getAccoList().get(0).getAvailable();
			BigDecimal cardRealAvailable = assets.getAccoList().get(0).getRealavailable();
			model.addAttribute("cardAvailable", cardAvailable);
			model.addAttribute("cardAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardAvailable));
			model.addAttribute("cardRealAvailable", cardRealAvailable);
			model.addAttribute("cardRealAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardRealAvailable));
			
			model.addAttribute("card", assets.getAccoList().get(0));
			model.addAttribute("cardList", assets.getAccoList());
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "取现结果");
			model.addAttribute("message_url", PAGE_CASH_INDEX);
			model.addAttribute("message_content0", "取现失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的取现，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		
		return "trade/cash_index";
	}
	
	@RequestMapping(value="trade/orgCashIndex")
	public String orgCashIndex(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			levels.add("1"); 
			
			List<TradeAccoinfoOfMore> tradeAccoList = null;
//			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
//					custno, 
//					Constant.HftSysConfig.HftFundCorpno, 
//					levels,
//					tradeaccosts);
			// 获取用户总资产
			Assets assets = queryManager.queryAssets(tradeAccoList, BasicFundinfo.YFB.getFundCode());
			// 获取工作日信息等
			Today today = workDayManager.getSysDayInfo();
			String nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			
			
			model.addAttribute("total", assets.getTotal()); // 总资产
			model.addAttribute("available", assets.getAvailable()); //可用资产
			model.addAttribute("frozen", assets.getFrozen()); // 冻结资产
			model.addAttribute("totalDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));
			model.addAttribute("availableDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));
			model.addAttribute("frozenDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));
			
			BigDecimal cardAvailable = assets.getAccoList().get(0).getAvailable();
			BigDecimal cardRealAvailable = assets.getAccoList().get(0).getRealavailable();
			model.addAttribute("cardAvailable", cardAvailable);
			model.addAttribute("cardAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardAvailable));
			model.addAttribute("cardRealAvailable", cardRealAvailable);
			model.addAttribute("cardRealAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardRealAvailable));
			
			model.addAttribute("card", assets.getAccoList().get(0));
			model.addAttribute("cardList", assets.getAccoList());
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "取现结果");
			model.addAttribute("message_url", PAGE_ORG_CASH_INDEX);
			model.addAttribute("message_content0", "取现失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的取现，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		
		return "trade/orgCashIndex";
	}
	
	@RequestMapping(value="trade/cash_result")
	public String cashResult(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			vo.setFee(new BigDecimal("0.00"));
			vo.setShareclass(BasicFundinfo.YFB.getShareClass());
			vo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
			
			Today today = null;
			String nextWorkDay = null;
			if("023".equals(vo.getApkind())){
				tradeManager.redeem(vo);
				today = workDayManager.getSysDayInfo();
				nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
				
			}else if("024".equals(vo.getApkind())){
				tradeManager.realRedeem(vo);
				today = workDayManager.getSysDayInfo();
				nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			}else{
				throw new SysException("异常交易！");
			}
			
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
			model.addAttribute("APKIND", vo.getApkind());
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "取现结果");
			model.addAttribute("message_url", PAGE_CASH_INDEX);
			model.addAttribute("message_content0", "取现失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的取现，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		return "trade/cash_result";
	}
	
	@RequestMapping(value="trade/orgCashResult")
	public String orgCashResult(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			vo.setFee(new BigDecimal("0.00"));
			vo.setShareclass(BasicFundinfo.YFB.getShareClass());
			vo.setDividmethod(BasicFundinfo.YFB.getDividMethod());
			vo.setLevel("1");
			
			Today today = null;
			String nextWorkDay = null;
			if("023".equals(vo.getApkind())){
				tradeManager.redeem(vo);
				today = workDayManager.getSysDayInfo();
				nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			}else if("024".equals(vo.getApkind())){
				tradeManager.realRedeem(vo);
				today = workDayManager.getSysDayInfo();
				nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			}else{
				throw new SysException("异常交易！");
			}
			
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
			
			model.addAttribute("APKIND", vo.getApkind());
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			
			model.addAttribute("message_title", "取现结果");
			model.addAttribute("message_url", PAGE_ORG_CASH_INDEX);
			model.addAttribute("message_content0", "取现失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "返回");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的取现，提交失败，您可通过资金明细列表确认，如有问题请联系幼富通客服热线。");
			
			return "error/user_error";
		}
		return "trade/cash_result";
	}

	
	@RequestMapping(value="trade/query_index.htm")
	public String queryIndex(TradeQueryVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			String startappdate = null;
			String endappdate = null;
			String appdateindex = vo.getAppdateindex();
			String appcateindex = vo.getAppcateindex();
			String apptypeindex = vo.getApptypeindex();
			// 交易明细显示
			
			// 今天
			if("0".equals(appdateindex)){
				// 今天
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.DATE, -0);
			    startappdate = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
			}else if("1".equals(appdateindex)){
				// 最近1个月
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -1);
			    startappdate = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
			}else if("2".equals(appdateindex)){
				// 最近3个月
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -3);
			    startappdate = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
			}else if("3".equals(appdateindex)){
				// 1年
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -12);
			    startappdate = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
			}else{
				vo.setAppdateindex("0");
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.DATE, -0);
			    startappdate = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
			}
			endappdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
			vo.setStartappdate(startappdate);
			vo.setEndappdate(endappdate);
			
			/** 交易类型 **/
			List<String> apkinds = new ArrayList<String>();
			if("0".equals(appcateindex)){
				apkinds.add("022"); // 充值
				apkinds.add("023"); // 取现
				apkinds.add("024"); // 快速取现
			}else if("1".equals(appcateindex)){
				apkinds.add("022"); // 充值
			}else if("2".equals(appcateindex)){
				apkinds.add("023"); // 取现
				apkinds.add("024"); // 快速取现
			}else if("3".equals(appcateindex)){
				apkinds.add("000"); // 其他
			}else{
				vo.setAppcateindex("0");
				apkinds.add("022"); // 充值
				apkinds.add("023"); // 取现
				apkinds.add("024"); // 快速取现
			}
			
			/** 交易状态 **/
			List<String> states = new ArrayList<String>();
			if("0".equals(apptypeindex)){
				states.add("Y"); // 
				states.add("F"); // 
				states.add("I"); // 
			}else if("1".equals(apptypeindex)){
				states.add("I"); // 
			}else if("2".equals(apptypeindex)){
				states.add("Y"); // 
			}else if("3".equals(apptypeindex)){
				states.add("F"); // 
			}else{
				vo.setApptypeindex("0");
				states.add("Y"); // 
				states.add("F"); // 
				states.add("I"); // 
			}
			
			List<TradeRequest> listIn = queryManager.qryTradeList(
					custno, 
					apkinds,
					states,
					startappdate, 
					endappdate,
					0, 
					10
					);
			model.addAttribute("listIn", listIn);
			model.addAttribute("TradeQueryVo", vo);
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_CASH_INDEX);
			return "error/user_error";
		}
		
		return "trade/query_index";
	}
	
	@RequestMapping(value="trade/query_detail.htm")
	public String queryDetail(TradeQueryVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			String serialno = vo.getSerialno();
			
			TradeRequest tradeRequest = queryManager.queryTradeRequest(custno, serialno);
			
			model.addAttribute("TradeRequest", tradeRequest);
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_CASH_INDEX);
			return "error/user_error";
		}
		
		return "trade/query_detail";
	}
	
	
	@RequestMapping(value="trade/cancel_result")
	public String canclApply(CancelVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			vo.setCustno(custno);
			vo.setFundcode(BasicFundinfo.YFB.getFundCode());
			
			tradeManager.cancel(vo);
//			
//			if("023".equals(vo.getApkind())){
//				tradeManager.redeem(vo);
//			}else if("024".equals(vo.getApkind())){
//				tradeManager.realRedeem(vo);
//			}else{
//				throw new SysException("异常交易！");
//			}
//			
			model.addAttribute("tmp", "success");
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", PAGE_CASH_INDEX);
			return "error/user_error";
		}
		return "trade/query_detail";
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
