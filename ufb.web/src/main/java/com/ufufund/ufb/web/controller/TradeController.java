package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.TradeManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.db.Apply;
import com.ufufund.ufb.model.db.Assets;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Redeem;
import com.ufufund.ufb.model.db.Today;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeQuery;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="family/ufb")
@Slf4j
public class TradeController {
	
	private static final String PAY_INDEX = "family/ufb/pay_index.htm";
	private static final String CASH_INDEX = "family/ufb/cash_index.htm";
	private static final String TRADEQUERY_INDEX = "family/ufb/query_index.htm";
	private static final String AUTOTRADE_INDEX = "family/ufb/autotrade_index.htm";
	private static final String AUTOTRADE_NAME = "自动充值";
	private static final String AUTOTRADEADD_INDEX = "family/ufb/autoadd_index.htm";
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
	@Autowired
	private CustManager custManager;
	
	
	/**
	 * 充值
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pay_index")
	public String payIndex(Apply vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno, 
					Constant.HftSysConfig.HftFundCorpno);
			
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
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "充值结果");
			model.addAttribute("message_url", PAY_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		
		return "ufb/pay_index";
	}

	/**
	 * 充值成功页	
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="pay_result")
	public String payResult(Apply vo, Model model){
		
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
		
		return "ufb/pay_result";
	}
	
	/**
	 * 取现
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="cash_index")
	public String cashIndex(Redeem vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno, 
					Constant.HftSysConfig.HftFundCorpno);
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
			}
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("curCard", tradeAccoList.get(0));
				model.addAttribute("cardList", tradeAccoList);
			}
			
			model.addAttribute("cardAvailable", cardAvailable);
			model.addAttribute("cardAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardAvailable));
			model.addAttribute("cardRealAvailable", cardRealAvailable);
			model.addAttribute("cardRealAvailableDisplay", NumberUtils.DF_CASH_CONMMA.format(cardRealAvailable));
			
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
		
		return "ufb/cash_index";
	}
	
	/**
	 * 取现成功页
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="cash_result")
	public String cashResult(Redeem vo, Model model){
		
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
		return "ufb/cash_result";
	}
	
	/**
	 * 交易明细查询
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="query_index")
	public String queryIndex(TradeQuery vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			
			String startappdate = null;
			String endappdate = null;
			String appdateindex = vo.getAppdateindex();
			String appcateindex = vo.getAppcateindex();
			String apptypeindex = vo.getApptypeindex();
			// 今天
			if("0".equals(appdateindex)){
				// 今天
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.DATE, -0);
			    startappdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			    endappdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}else if("1".equals(appdateindex)){
				// 最近1个月
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -1);
			    startappdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			    endappdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}else if("2".equals(appdateindex)){
				// 最近3个月
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -3);
			    startappdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			    endappdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}else if("3".equals(appdateindex)){
				// 1年
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.MONTH, -12);
			    startappdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			    endappdate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			}else{
				Calendar c = Calendar.getInstance();
			    c.add(Calendar.DATE, -0);
				if(vo.getStartappdate()!=null&&vo.getStartappdate()!=""){
					startappdate=vo.getStartappdate();
				}else{
					vo.setAppdateindex("0");
					startappdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				}
				if(vo.getEndappdate()!=null&&vo.getEndappdate()!=""){
					endappdate=vo.getEndappdate();
				}else{
					endappdate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				}
				
			}
			vo.setStartappdate(startappdate);
			vo.setEndappdate(endappdate);
			
			/** 交易类型 **/
			List<String> apkinds = new ArrayList<String>();
			if("0".equals(appcateindex)){
				apkinds.add("022"); // 充值
				apkinds.add("023"); // 取现
			}else if("1".equals(appcateindex)){
				apkinds.add("022"); // 充值
			}else if("2".equals(appcateindex)){
				apkinds.add("023"); // 取现
			}else if("3".equals(appcateindex)){
				apkinds.add("000"); // 其他
			}else{
				vo.setAppcateindex("0");
				apkinds.add("022"); // 充值
				apkinds.add("023"); // 取现
			}
			
			/** 交易状态 **/
			List<String> states = new ArrayList<String>();
			if("0".equals(apptypeindex)){
				states.add("Y");  
				states.add("F");  
				states.add("I");   
			}else if("1".equals(apptypeindex)){
				states.add("I");  
			}else if("2".equals(apptypeindex)){
				states.add("Y");   
			}else if("3".equals(apptypeindex)){
				states.add("F");   
			}else{
				vo.setApptypeindex("0");
				states.add("Y");  
				states.add("F"); 
				states.add("I");   
			}
			List<TradeRequest> listIn = queryManager.qryTradeList(
					custno, 
					apkinds,
					states,
					startappdate.replace("-", ""), 
					endappdate.replace("-", ""),
					0, 
					400
					);
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno, 
					Constant.HftSysConfig.HftFundCorpno);
			// 获取用户总资产
			Assets assets = queryManager.queryAssets(tradeAccoList, BasicFundinfo.YFB.getFundCode());
			
			model.addAttribute("total", assets.getTotal()); // 总资产
			model.addAttribute("listIn", listIn);
			model.addAttribute("TradeQueryVo", vo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "交易明细查询结果");
			model.addAttribute("message_url", TRADEQUERY_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", "返回");
			return "error/error";
		}
		return "ufb/query_index";
	}
	
	/**
	 * 交易明细查询
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="query_detail")
	public String queryDetail(TradeQuery vo, Model model){
		
		try{
			String custno = UserHelper.getCustno();
			String serialno = vo.getSerialno();
			
			TradeRequest tradeRequest = queryManager.queryTradeRequest(custno, serialno);
			
			model.addAttribute("TradeRequest", tradeRequest);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "交易明细详情查询结果");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "ufb/query_detail";
	}
	
	/**
	 * 自动业务管理
	 * @param Custinfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autotrade_index")
	public String autotradeIndex(Custinfo Custinfo,String TAB, Model model){
		Custinfo custinfo = UserHelper.getCustinfo();
		try{
			// 获取自动充值计划列表
			List<Autotrade> list = autotradeManager.getAutotradeList(custinfo.getCustno());
			List<Autotrade> clist=autotradeManager.getAutotradeCList(custinfo.getCustno());
			
			// 获取自动缴费的信息  
			List<Autotrade> autoPayList=autotradeManager.getCashtradeList(custinfo.getCustno(),null);
			
			model.addAttribute("LIST", list);
			model.addAttribute("CLIST", clist);
			model.addAttribute("autoPayList", autoPayList);
			if(StringUtils.isNotBlank(TAB)){
				model.addAttribute("TAB", TAB);
			}
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autotrade_index";
	}
	/**
	 * 自动缴费查看详情页面
	 * @param model
	 * @param autoid
	 * @return
	 */
	@RequestMapping(value="autopay_detail")
	public String autopayDetail(String autoid,Model model){
		Custinfo custinfo = UserHelper.getCustinfo();
		try{
			List<Autotrade> autoPayList=autotradeManager.getCashtradeList(custinfo.getCustno(),autoid);
			Autotrade	autotrade = new Autotrade();
			if(autoPayList.size()>0&&null!=autoPayList){
				autotrade=autoPayList.get(0);
			}
			model.addAttribute("autotrade", autotrade);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "自动缴费结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autopay_detail";
	}
	
	/**
	 * 添加自动充值第一步
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoadd_index")
	public String autoAddIndex(Autotrade Autotrade, Model model){
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("curCard", tradeAccoList.get(0));
				model.addAttribute("cardList", tradeAccoList);
			}
			
			// 从第二步返回
			String frombankserialid = Autotrade.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			model.addAttribute("Autotrade", Autotrade);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "添加自动充值结果");
			model.addAttribute("message_url", AUTOTRADEADD_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autoadd_index";
	}
	
	/**
	 * 自动充值第二步
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoadd_preview")
	public String autoAddPreview(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			// 用户信息
			Autotrade.setCustno(s_custinfo.getCustno());
			// 货币信息
			Autotrade.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			Autotrade.setTofundcode(BasicFundinfo.YFB.getFundCode());
			Autotrade.setTochargetype("A");
			// 充值周期
			Autotrade.setCycle("MM");
			String nextdate = autotradeManager.getNextdate(Autotrade.getCycle(), Autotrade.getDat());
			nextdate=nextdate.substring(0,4)+"年"+nextdate.substring(4, 6)+"月"+nextdate.substring(6, 8)+"日";
			Autotrade.setNextdate(nextdate);
			// 获取交易账户列表 
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(s_custinfo.getCustno());
						
				if(null != tradeAccoList && tradeAccoList.size() > 0){
					model.addAttribute("curCard", tradeAccoList.get(0));
					model.addAttribute("cardList", tradeAccoList);
				}
			// 跳转确认页
			model.addAttribute("Autotrade", Autotrade);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "添加自动充值结果");
			model.addAttribute("message_url", AUTOTRADEADD_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autoadd_preview";
	}
	
	/**
	 * 自动充值第三步
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoadd_success")
	public String autoAddSuccess(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			AddAutotradeAction action = new AddAutotradeAction();
			// 用户信息
			action.setCustno(s_custinfo.getCustno());
			// 银行卡
			action.setFrombankserialid(Autotrade.getFrombankserialid());	
			// 货币信息
			action.setTofundcorpno(Autotrade.getTofundcorpno());
			action.setTofundcode(Autotrade.getTofundcode());
			action.setTochargetype("A");
			// 交易类型
			action.setTradetype(AutoTradeType.AUTORECHARGE);
			// 充值周期
			action.setType("E");
			action.setCycle("MM");
			action.setDat(Autotrade.getDat());
			action.setNextdate(Autotrade.getNextdate());
			// 充值金额
			action.setAutoamt(Autotrade.getAutoamt());
			// 备注
			action.setSummary(Autotrade.getSummary());
			// 交易密码
			action.setTradepwd(Autotrade.getTradepwd());
			//申请的时间
			Calendar c = Calendar.getInstance();
		    c.add(Calendar.DATE, -0);
		    String today = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(c.getTime());
		    //下次充值时间
		    String nextdate=Autotrade.getNextdate();
		    
			model.addAttribute("today",today);
			model.addAttribute("nextWorkDay",nextdate);
			model.addAttribute("autoamt",Autotrade.getAutoamt());
			model.addAttribute("summary",Autotrade.getSummary());
			autotradeManager.addAutotrade(action);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "添加自动充值结果");
			model.addAttribute("message_url", AUTOTRADEADD_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autoadd_success";
	}
	
	/**
	 * 修改自动充值 第一步
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoupdate_index")
	public String autoUpdateIndex(Autotrade Autotrade, Model model){
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("cardList", tradeAccoList);
			}
			
			String frombankserialid = Autotrade.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			if("u2".equals(Autotrade.getStep())){
				model.addAttribute("Autotrade", Autotrade);
			}else{
				Autotrade autotrade = autotradeManager.getAutotrade(Autotrade.getAutoid());
				model.addAttribute("Autotrade", autotrade);
			}
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autoupdate_index";
	}
	
	/**
	 * 修改自动充值 第二步
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoupdate_preview")
	public String autoUpdatePreview(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			// 用户信息
			Autotrade.setCustno(s_custinfo.getCustno());
			// 货币信息
			Autotrade.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			Autotrade.setTofundcode(BasicFundinfo.YFB.getFundCode());
			Autotrade.setTochargetype("A");
			// 充值周期
			Autotrade.setCycle("MM");
			String nextdate = autotradeManager.getNextdate(Autotrade.getCycle(), Autotrade.getDat());
			Autotrade.setNextdate(nextdate);
			
			// 跳转确认页
			model.addAttribute("Autotrade", Autotrade);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autoupdate_preview";
	}
	
	/**
	 * 修改自动充值 第三步
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autoupdate_success")
	public String autoUpdateSuccess(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			ModifyAutotradeAction action = new ModifyAutotradeAction();
			// autoid
			action.setAutoid(Autotrade.getAutoid());
			// 用户信息
			action.setCustno(s_custinfo.getCustno());
			// 银行卡
			action.setFrombankserialid(Autotrade.getFrombankserialid());	
			// 货币信息
			action.setTofundcorpno(Autotrade.getTofundcorpno());
			action.setTofundcode(Autotrade.getTofundcode());
			action.setTochargetype("A");
			// 交易类型
			action.setTradetype(AutoTradeType.AUTORECHARGE);
			// 充值周期
			action.setType("E");
			action.setCycle("MM");
			action.setDat(Autotrade.getDat());
			action.setNextdate(Autotrade.getNextdate());
			// 充值金额
			action.setAutoamt(Autotrade.getAutoamt());
			// 备注
			action.setSummary(Autotrade.getSummary());
			// 交易密码
			action.setTradepwd(Autotrade.getTradepwd());
			
			autotradeManager.modifyAutotrade(action);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "修改自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/autoupdate_success";
	}
	
	/**
	 * 暂停自动充值
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auto_pause")
	public String autoPause(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			String frombankserialid = Autotrade.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(s_custinfo.getCustno(), Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			Autotrade autotrade = autotradeManager.getAutotrade(Autotrade.getAutoid());
			// 用户信息
			autotrade.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotrade.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotrade.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotrade.setTochargetype("A");
			
			// 跳转确认页
			model.addAttribute("Autotrade", autotrade);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "暂停自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/auto_pause";
	}
	
	/**
	 * 暂停自动充值结果
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autopause_result")
	public String autoPauseResult(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			ChangeAutoStateAction action = new ChangeAutoStateAction();
			
			action.setCustno(s_custinfo.getCustno());// 用户信息
			action.setAutoid(Autotrade.getAutoid());
			action.setState(Constant.Autotrade.STATE$P); //STATE$N,STATE$P,STATE$C
			action.setTradepwd(Autotrade.getTradepwd());
			autotradeManager.changestatus(action);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "暂停自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		ServletHolder.forward("/family/ufb/autotrade_index.htm?TAB=PR");
		return "ufb/autotrade_index";
	}
	
	/**
	 * 终止自动充值计划
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="auto_stop")
	public String autoStop(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			String frombankserialid = Autotrade.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(s_custinfo.getCustno(), Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			Autotrade autotrade = autotradeManager.getAutotrade(Autotrade.getAutoid());
			// 用户信息
			autotrade.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotrade.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotrade.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotrade.setTochargetype("A");
			
			// 跳转确认页
			model.addAttribute("Autotrade", autotrade);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "终止自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		return "ufb/auto_stop";
	}
	
	/**
	 * 终止自动充值计划结果
	 * @param Autotrade
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autostop_result")
	public String autoStopResult(Autotrade Autotrade, Model model){
		Custinfo s_custinfo = UserHelper.getCustinfo();
		try{
			ChangeAutoStateAction action = new ChangeAutoStateAction();
			
			action.setCustno(s_custinfo.getCustno());// 用户信息
			action.setAutoid(Autotrade.getAutoid());
			action.setState(Constant.Autotrade.STATE$C); //STATE$N,STATE$P,STATE$C
			action.setTradepwd(Autotrade.getTradepwd());
			autotradeManager.changestatus(action);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "终止自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		ServletHolder.forward("/family/ufb/autotrade_index.htm?TAB=CR");
		return "ufb/autotrade_index";
	}
	
	/**
	 * 删除自动充值计划
	 */
	@RequestMapping(value="auto_delete")
	public String autoDelete(Autotrade Autotrade, Model model){
		try{
			String custno = UserHelper.getCustno();
			
			String frombankserialid = Autotrade.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				autotradeManager.deleteAutotrade(custno, frombankserialid, Autotrade.getAutoid());
			}
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "删除自动充值结果");
			model.addAttribute("message_url", AUTOTRADE_INDEX);
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("back_module", AUTOTRADE_NAME);
			return "error/error";
		}
		ServletHolder.forward("/family/ufb/autotrade_index.htm?TAB=DR");
		return "ufb/autotrade_index";
	}
	
	/**
	 * 暂停自动缴费
	 * @param autoid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="autopay_stop")
	@ResponseBody
	public Map<String,String> autopayStop(String autoid,Model model){
		Map<String,String> resultMap = new HashMap<String, String>();
		
		Custinfo custinfo = UserHelper.getCustinfo();
		try{
			ChangeAutoStateAction action = new ChangeAutoStateAction();
			Custinfo s_custinfo=custManager.getCustinfo(custinfo.getCustno());
			
			action.setCustno(s_custinfo.getCustno());
			action.setAutoid(autoid);
			action.setState(Constant.Autotrade.STATE$P);
			action.setTradepwd(s_custinfo.getTradepwd());
			autotradeManager.changestatus(action);
			resultMap.put("errCode", "0000");
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			resultMap.put("errCode", ue.getCode());
			resultMap.put("errMsg", ue.getMessage());
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
}
