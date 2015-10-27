package com.ufufund.uft.web.controller;

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
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.ApplyVo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.AutotradeVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.RedeemVo;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.model.vo.TradeQueryVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="family/ufb")
@Slf4j
public class TradedController {
	private static final Logger LOG = LoggerFactory.getLogger(TradedController.class);
	
	private static final String PAY_INDEX = "family/ufb/pay_index.htm";
	private static final String PAY_INDEX_NAME = "充值";
	private static final String CASH_INDEX = "family/ufb/cash_index.htm";
	private static final String CASH_INDEX_NAME = "取现";
	
	@Autowired
	private TradeManager tradeManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private AutotradeManager autotradeManager;
	
	/**
	 * 充值
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pay_index")
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
			
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
					custno, 
					Constant.HftSysConfig.HftFundCorpno, 
					levels,
					tradeaccosts);
			
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
		
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAY_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		
		return "family/ufb/pay_index";
	}
		
	@RequestMapping(value="pay_result")
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
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAY_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		
		return "family/ufb/pay_result";
	}
	/**
	 * 取现
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="cash_index")
	public String cashIndex(RedeemVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			levels.add("0"); 
			levels.add("2"); 
			
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
					custno, 
					Constant.HftSysConfig.HftFundCorpno, 
					levels,
					tradeaccosts);
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
			BigDecimal cardAvailable=BigDecimal.ZERO;
			BigDecimal cardRealAvailable=BigDecimal.ZERO;
			if( assets.getAccoList().size()>0){
				 cardAvailable = assets.getAccoList().get(0).getAvailable();
				 cardRealAvailable = assets.getAccoList().get(0).getRealavailable();
				model.addAttribute("card", assets.getAccoList().get(0));
			}else{
				 model.addAttribute("card", null);
			}
			model.addAttribute("cardAvailable", cardAvailable);
			model.addAttribute("cardAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardAvailable));
			model.addAttribute("cardRealAvailable", cardRealAvailable);
			model.addAttribute("cardRealAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardRealAvailable));
			
			model.addAttribute("cardList", assets.getAccoList());
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "取现结果");
			model.addAttribute("message_url", CASH_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		
		return "family/ufb/cash_index";
	}
	@RequestMapping(value="cash_result")
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
				tradeManager.redeem(vo);
				today = workDayManager.getSysDayInfo();
				nextWorkDay = workDayManager.getNextWorkDay(today.getWorkday(), 1);
			
			model.addAttribute("today", DateUtil.convert(today.getDate(), DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			model.addAttribute("nextWorkDay", DateUtil.convert(nextWorkDay, DateUtil.DATE_PATTERN_1, DateUtil.DATE_PATTERN_2));
			
			
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "取现结果");
			model.addAttribute("message_url", CASH_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "family/ufb/cash_result";
	}
	/**
	 * 交易明细查询
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="query_index")
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
			vo.setStartappdate(startappdate.substring(0, 4)+"-"+startappdate.substring(4, 6)+"-"+startappdate.substring(6, 8));
			vo.setEndappdate(endappdate.substring(0, 4)+"-"+endappdate.substring(4, 6)+"-"+endappdate.substring(6, 8));
			
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
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			
			List<String> levels = new ArrayList<String>();
			levels.add("0"); 
			levels.add("2"); 
			
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(
					custno, 
					Constant.HftSysConfig.HftFundCorpno, 
					levels,
					tradeaccosts);
			// 获取用户总资产
			Assets assets = queryManager.queryAssets(tradeAccoList, BasicFundinfo.YFB.getFundCode());
			
			model.addAttribute("total", assets.getTotal()); // 总资产
			model.addAttribute("listIn", listIn);
			model.addAttribute("TradeQueryVo", vo);
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", CASH_INDEX);
			return "error/user_error";
		}
		
		return "family/ufb/query_index";
	}
	
	@RequestMapping(value="query_detail")
	public String queryDetail(TradeQueryVo vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			String serialno = vo.getSerialno();
			
			TradeRequest tradeRequest = queryManager.queryTradeRequest(custno, serialno);
			
			model.addAttribute("TradeRequest", tradeRequest);
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl",CASH_INDEX);
			return "error/user_error";
		}
		
		return "family/ufb/query_detail";
	}
	/**
	 * 添加自动充值第一步
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoTrade_add")
	public String autoTradeAdd(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("curCard", tradeAccoList.get(0));
				model.addAttribute("cardList", tradeAccoList);
			}
			
			// 从第二步返回
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				// autotradeVo.getTofundcorpno()
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "family/settingAutoFund"; 
		}
		return "family/ufb/autoFundStep1";
	}
	/**
	 * 自动充值第二步
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/autoTrade_preview")
	public String autoTradePreview(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 用户信息
			autotradeVo.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotradeVo.setTochargetype("A");
			// 充值周期
			autotradeVo.setCycle("MM");
			String nextdate = autotradeManager.getNextdate(autotradeVo.getCycle(), autotradeVo.getDat());
			nextdate=nextdate.substring(0,4)+"年"+nextdate.substring(4, 6)+"月"+nextdate.substring(6, 8)+"日";
			autotradeVo.setNextdate(nextdate);
			// 获取交易账户列表==20151001
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(s_custinfo.getCustno());
						
				if(null != tradeAccoList && tradeAccoList.size() > 0){
					model.addAttribute("curCard", tradeAccoList.get(0));
					model.addAttribute("cardList", tradeAccoList);
				}
				//20151001===
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "family/autoTrade_add.htm");
			return "error/user_error";
		}
		return "family/ufb/autoFundStep2";
	}
	/**
	 * 自动充值第三步
	 * @param autotradeVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="family/autoTrade_result")
	public String autoTradeResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			AddAutotradeAction action = new AddAutotradeAction();
			// 用户信息
			action.setCustno(s_custinfo.getCustno());
			// 银行卡
			action.setFrombankserialid(autotradeVo.getFrombankserialid());	
			// 货币信息
			action.setTofundcorpno(autotradeVo.getTofundcorpno());
			action.setTofundcode(autotradeVo.getTofundcode());
			action.setTochargetype("A");
			// 交易类型
			action.setTradetype(AutoTradeType.AUTORECHARGE);
			// 充值周期
			action.setType("E");
			action.setCycle("MM");
			action.setDat(autotradeVo.getDat());
			action.setNextdate(autotradeVo.getNextdate());
			// 充值金额
			action.setAutoamt(autotradeVo.getAutoamt());
			// 备注
			action.setSummary(autotradeVo.getSummary());
			// 交易密码
			action.setTradepwd(autotradeVo.getTradepwd());
			//申请的时间
			Calendar c = Calendar.getInstance();
		    c.add(Calendar.DATE, -0);
		    String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
		    //下次充值时间
		    String nextdate=autotradeVo.getNextdate();
		    
			model.addAttribute("today",today);
			model.addAttribute("nextWorkDay",nextdate);
			model.addAttribute("autoamt",autotradeVo.getAutoamt());
			model.addAttribute("summary",autotradeVo.getSummary());
			autotradeManager.addAutotrade(action);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("AutoTradeVo", autotradeVo);
			ServletHolder.forward("/family/autoFundStep2.htm");
			return "family/ufb/autoFundStep2";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			String errMes=ue.getMessage();
			model.addAttribute("message_title", "错误信息");
			model.addAttribute("message_url", "family/autoTrade_add.htm");
			model.addAttribute("message_content0", "新增自动充值计划失败!");
			model.addAttribute("message_content1", ue.getMessage());
			model.addAttribute("message_content2", "重新添加自动充值计划");
			model.addAttribute("message_content3", "温馨提示：");
			model.addAttribute("message_content4", "您的自动充值计划，提交失败，您可通过自动充值计划列表确认，如有问题请联系幼富通客服热线。");
			if("交易密码错误！".equals(errMes)){
				return "error/pay_result";
			}else{
				return "error/user_error";
			}
		}
		return "family/ufb/autoFundStep3";
	}
}
