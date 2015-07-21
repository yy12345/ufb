package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.AutotradeManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangeAutoStateAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.ModifyAutotradeAction;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.AutoTradeType;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.AutotradeVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.TradeAccoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;


@Controller
public class SettingController {
	private static final Logger LOG = LoggerFactory.getLogger(SettingController.class);
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private QueryManager queryManager;
	@Autowired
	private AutotradeManager autotradeManager;
	@Autowired
	private WorkDayManager workDayManager;
	
	@RequestMapping(value="setting/settingAccount")
	public String setAccount(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrgnm(s_custinfo.getOrgnm()); 
			custinfoVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingAccount";
		}
		return "setting/settingAccount";
	}
	
	@RequestMapping(value="setting/settingPassword")
	public String setPassword(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrgnm(s_custinfo.getOrgnm()); 
			custinfoVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			
			model.addAttribute("TAB", "1");
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingPassword";
		}
		return "setting/settingPassword";
	}
	
	@RequestMapping(value="setting/settingLoginPwd")
	public String setLoginPwd(String password0, String password1, String password2, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			model.addAttribute("TAB", "1");
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("LOGIN");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword0(password0);
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改登录密码 **/
			custManager.changePassword(changePasswordAction);
				
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("TAB", "1S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.LOGINPWD0.equals(ems)){
				model.addAttribute("errMsg_login_password0", e.getMessage());
			}else
			if(BisConst.Register.LOGINPWD.equals(ems)){
				model.addAttribute("errMsg_login_password1", e.getMessage());
			}else
			if(BisConst.Register.LOGINPWD2.equals(ems)){
				model.addAttribute("errMsg_login_password2", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("login_password0", password0);
			model.addAttribute("login_password1", password1);
			model.addAttribute("login_password2", password2);
			model.addAttribute("CustinfoVo", s_custinfo);
			return "setting/settingPassword";
		}
		return "setting/settingPassword";
	}
	
	@RequestMapping(value="setting/settingTradePwd")
	public String setTradePwd(String password0, String password1, String password2, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			model.addAttribute("TAB", "2");
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADE");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword0(password0);
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("TAB", "2S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.TRADEPWD0.equals(ems)){
				model.addAttribute("errMsg_trade_password0", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD.equals(ems)){
				model.addAttribute("errMsg_trade_password1", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD2.equals(ems)){
				model.addAttribute("errMsg_trade_password2", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("trade_password0", password0);
			model.addAttribute("trade_password1", password1);
			model.addAttribute("trade_password2", password2);
			model.addAttribute("CustinfoVo", s_custinfo);
			return "setting/settingPassword";
		}
		return "setting/settingPassword";
	}
	
	/**
	 * 获取手机验证码
	 * @param
	 * @return
	 */
	@RequestMapping(value = "setting/getMsgCode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> sendMsgCode(String verifycode){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// 校验验证码
				VerifyCodeUtils.validate(verifycode);
				
				// 查询手机号是否注册
				boolean isMobileRegister = custManager.isMobileRegister(s_custinfo.getMobileno());
				if(!isMobileRegister){
					// 错误 手机号未注册
					resultMap.put("errCode", "errMsg_mobileno");
					resultMap.put("errMsg", "手机号未注册");
				}else{
					// 获取手机验证码
					String template = "";
					// 发送短信
					MsgCodeUtils.sendMsg(template, s_custinfo.getMobileno());
					resultMap.put("errCode", "0000");
					resultMap.put("errMsg", "短信已发送");
					//TODO 测试用
					resultMap.put("TODO", MsgCodeUtils.getMsgCode());
				}
			}else{
				//TODO 错误
				resultMap.put("errCode", "errMsg_mobileno");
				resultMap.put("errMsg", "登录超时!");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems)) {
				resultMap.put("errCode", "errMsg_mobileno");
				resultMap.put("errMsg", e.getMessage());
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				resultMap.put("errCode", "errMsg_verifycode");
				resultMap.put("errMsg", e.getMessage());
			}else{
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", e.getMessage());
			}
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	@RequestMapping(value="setting/settingTradePwdBack")
	public String setTradePwdBack(String password1, String password2, String msgcode, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			model.addAttribute("TAB", "3");
			// 校验手机验证码
			MsgCodeUtils.validate(msgcode, s_custinfo.getMobileno());
			
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setActionType("TRADEBACK");
			changePasswordAction.setCustno(s_custinfo.getCustno());
			changePasswordAction.setPassword1(password1);
			changePasswordAction.setPassword2(password2);
			/** 修改交易密码 **/
			custManager.changePassword(changePasswordAction);
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("TAB", "3S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.TRADEPWD.equals(ems)){
				model.addAttribute("errMsg_trade_password1_back", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD2.equals(ems)){
				model.addAttribute("errMsg_trade_password2_back", e.getMessage());
			}else
			if(BisConst.Register.MSGCODE.equals(ems)){
				model.addAttribute("errMsg_msgcode", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("msgcode", msgcode);
			model.addAttribute("trade_password1", password1);
			model.addAttribute("trade_password2", password2);
			model.addAttribute("CustinfoVo", s_custinfo);
			return "setting/settingPassword";
		}
		return "setting/settingPassword";
	}
	
	@RequestMapping(value="setting/settingCard")
	public String setCard(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			custinfoVo.setCustno(s_custinfo.getCustno());;                      
			custinfoVo.setMobileno(s_custinfo.getMobileno());                    
			custinfoVo.setInvtp(s_custinfo.getInvtp()); 
			custinfoVo.setInvnm(s_custinfo.getInvnm());        
			custinfoVo.setIdtp(s_custinfo.getIdtp());     
			custinfoVo.setIdno(s_custinfo.getIdno());             
			custinfoVo.setOrgnm(s_custinfo.getOrgnm()); 
			custinfoVo.setOrgbusiness(s_custinfo.getOrgbusiness()); 
			custinfoVo.setCustst(s_custinfo.getCustst());
			custinfoVo.setLevel(s_custinfo.getLevel());
			
			// 获取交易账户列表
			List<String> tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("Y"); // 
			tradeaccosts.add("N"); // 
			List<TradeAccoinfoOfMore> tradeAccoList_Y = 
					tradeAccoManager.getTradeAccoList(s_custinfo.getCustno(), null, null, tradeaccosts);
			if(null != tradeAccoList_Y && tradeAccoList_Y.size() > 0){
				// 获取用户总资产
				Assets assets = queryManager.queryAssets(tradeAccoList_Y, null);
				List<TradeAccoVo> list_y =  assets.getAccoList();
				
				model.addAttribute("cardList_Y", list_y);
			} else {
				model.addAttribute("cardList_Y", null);
			}
			
			// 获取交易账户列表
			//List<BankCardWithTradeAcco> tradeAccoList_N = 
			//		bankCardManager.getBankCardWithTradeAccoList(s_custinfo.getCustno(), "N");
		
			tradeaccosts = new ArrayList<String>();
			tradeaccosts.add("C"); // 
			tradeaccosts.add("F"); // 
			List<TradeAccoinfoOfMore> tradeAccoList_N = 
					tradeAccoManager.getTradeAccoList(s_custinfo.getCustno(), null, null, tradeaccosts);
			if(null != tradeAccoList_N && tradeAccoList_N.size() > 0){
				model.addAttribute("cardList_N", tradeAccoList_N);
			} else {
				model.addAttribute("cardList_N", null);
			}
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingCard";
		}
		return "setting/settingCard";
	}
	

	@RequestMapping(value="setting/settingMainCard")
	public String setMainCard(String bankacco, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			// 短信验证
			bankCardManager.setBankCardMainFlag(
					s_custinfo.getCustno(), 
					null, 
					"N");
			bankCardManager.setBankCardMainFlag(
					s_custinfo.getCustno(), 
					ServletHolder.getRequest().getParameter("bankacco"), 
					"Y");
			
//			ServletHolder.forward("/setting/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingCard";
		}
		return "setting/settingCard";
	}
	
	@RequestMapping(value="setting/settingUnbindCard")
	public String setUnbindCard(String bankacco, String tradeacco, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 短信验证
			TradeAccoVo tradeAccoVo = queryManager.queryAssets(tradeacco, null);
			BigDecimal total = tradeAccoVo.getTotal();
			BigDecimal available = tradeAccoVo.getAvailable();
			BigDecimal realavailable = tradeAccoVo.getRealavailable();
			BigDecimal frozen = tradeAccoVo.getFrozen();
			
			if (total.compareTo(BigDecimal.ZERO) > 0
					|| available.compareTo(BigDecimal.ZERO) > 0
					|| realavailable.compareTo(BigDecimal.ZERO) > 0
					|| frozen.compareTo(BigDecimal.ZERO) > 0) {
				
				throw new UserException("对不起，您的银行卡有资金交易，暂时不能解绑！");
			}
			bankCardManager.unbindBankCard(
					s_custinfo.getCustno(), 
					ServletHolder.getRequest().getParameter("bankacco"), 
					"N");
			
			ServletHolder.forward("/setting/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			return "setting/settingCard";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("returnUrl", "setting/settingCard.htm");
			return "error/user_error";
		}
		return "setting/settingCard";
	}
	
	@RequestMapping(value="setting/settingActiveCard")
	public String setActiveCard(String bankacco, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			// 短信验证
			bankCardManager.unbindBankCard(
					s_custinfo.getCustno(), 
					ServletHolder.getRequest().getParameter("bankacco"), 
					"Y");
			
			ServletHolder.forward("/setting/settingCard.htm");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingCard";
		}
		return "setting/settingCard";
	}
	
	@RequestMapping(value="setting/autoTrade_index")
	public String autoTradeIndex(Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 获取自动充值计划列表
			List<Autotrade> list = autotradeManager.getAutotradeList(s_custinfo.getCustno());
			model.addAttribute("LIST", list);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/autoTrade_index";
		}
		return "setting/autoTrade_index";
	}
	

	@RequestMapping(value="setting/autoTrade_add")
	public String autoTradeAdd(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			//List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
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
			return "setting/autoTrade_index";
		}
		return "setting/autoFundStep1";
	}
	
	@RequestMapping(value="setting/autoTrade_preview")
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
			autotradeVo.setNextdate(nextdate);
			
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
//			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("SessionVo", s_custinfo);
//			return "setting/autoFundStep1";
			
//			LOG.warn(e.getCodeMsg());
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_add.htm");
			return "error/user_error";
		}
		return "setting/autoFundStep2";
	}
	
	@RequestMapping(value="setting/autoTrade_result")
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
			
			autotradeManager.addAutotrade(action);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/autoFundStep2";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_add.htm");
			return "error/user_error";
		}
		return "setting/autoFundStep3";
		//ServletHolder.forward("/setting/autoTrade_index.htm");
		//return "setting/autoTrade_index";
	}
	
	@RequestMapping(value="setting/autoTrade_update")
	public String autoTradeUpdate(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			// 获取交易账户列表
			//List<BankCardWithTradeAcco> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			List<TradeAccoinfoOfMore> tradeAccoList = tradeAccoManager.getTradeAccoList(custno);
			
			if(null != tradeAccoList && tradeAccoList.size() > 0){
				model.addAttribute("cardList", tradeAccoList);
			}
			
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
//			RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
//			HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest(); 
//			String uri = request.getRequestURI();
//			StringBuffer url = request.getRequestURL();
			
			if("u2".equals(autotradeVo.getStep())){
				model.addAttribute("AutoTradeVo", autotradeVo);
			}else{
				Autotrade autotrade = autotradeManager.getAutotrade(autotradeVo.getAutoid());
				model.addAttribute("AutoTradeVo", autotrade);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/autoTrade_index";
		}
		return "setting/autoFundStepU1";
	}
	
	@RequestMapping(value="setting/autoTradeUpdate_preview")
	public String autoTradeUpdate_preview(AutotradeVo autotradeVo, Model model){
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
			autotradeVo.setNextdate(nextdate);
			
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
//			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("SessionVo", s_custinfo);
//			return "setting/autoFundStepU1";
			
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_index.htm");
			return "error/user_error";
		}
		return "setting/autoFundStepU2";
	}
	
	@RequestMapping(value="setting/autoTradeUpdate_result")
	public String autoTradeUpdateResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ModifyAutotradeAction action = new ModifyAutotradeAction();
			// autoid
			action.setAutoid(autotradeVo.getAutoid());
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
			
			autotradeManager.modifyAutotrade(action);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("SessionVo", s_custinfo);
			return "setting/autoFundStepU2";
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
//			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_add.htm");
			return "error/user_error";
		}
		return "setting/autoFundStepU3";
		//ServletHolder.forward("/setting/autoTrade_index.htm");
		//return "setting/autoTrade_index";
	}
	
	@RequestMapping(value="setting/autoTradeStatus_update")
	public String autoTradeStatusUpdate(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			if(null != s_custinfo){
				String custno = UserHelper.getCustno();
				
				ChangeAutoStateAction action = new ChangeAutoStateAction();
				action.setAutoid(autotradeVo.getAutoid());
				action.setState(Constant.Autotrade.STATE$P); //STATE$N,STATE$P,STATE$C
				
				autotradeManager.changestatus(action);
				// 跳转确认页
//				model.addAttribute("SessionVo", s_custinfo);
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("SessionVo", s_custinfo);
			return "setting/autoTrade_index";
		}
		ServletHolder.forward("/setting/autoTrade_index.htm");
		return "setting/autoTrade_index";
	}
	
	
	@RequestMapping(value="setting/autoTrade_pause")
	public String autoTradePause(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			String custno = UserHelper.getCustno();
			
			String frombankserialid = autotradeVo.getFrombankserialid();
			if(null != frombankserialid && frombankserialid.length() > 0){
				TradeAccoinfoOfMore tradeAccoinfoOfMore = 
						tradeAccoManager.getTradeAcco(custno, Constant.HftSysConfig.HftFundCorpno, frombankserialid);
				model.addAttribute("curCard", tradeAccoinfoOfMore);
			}
			
			if("u2".equals(autotradeVo.getStep())){
				model.addAttribute("AutoTradeVo", autotradeVo);
			}else{
				Autotrade autotrade = autotradeManager.getAutotrade(autotradeVo.getAutoid());
				model.addAttribute("AutoTradeVo", autotrade);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/autoTrade_index";
		}
		return "setting/autoFundStepP1";
	}
	
	@RequestMapping(value="setting/autoTradePause_preview")
	public String autoTradePause_preview(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			// 用户信息
			autotradeVo.setCustno(s_custinfo.getCustno());
			// 货币信息
			autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
			autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
			autotradeVo.setTochargetype("A");
			
			// 跳转确认页
			model.addAttribute("AutoTradeVo", autotradeVo);
		}catch (BizException e){
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_index.htm");
			return "error/user_error";
		}
		return "setting/autoFundStepP2";
	}
	
	@RequestMapping(value="setting/autoTradePause_result")
	public String autoTradePauseResult(AutotradeVo autotradeVo, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			ChangeAutoStateAction action = new ChangeAutoStateAction();
			
			action.setCustno(s_custinfo.getCustno());// 用户信息
			action.setAutoid(autotradeVo.getAutoid());
			action.setState(Constant.Autotrade.STATE$P); //STATE$N,STATE$P,STATE$C
			action.setTradepwd(autotradeVo.getTradepwd());
			autotradeManager.changestatus(action);
		}catch (BizException e){
//			LOG.warn(e.getCodeMsg());
			model.addAttribute("errorMsg", e.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_index.htm");
			return "error/user_error";
			
		}catch(UserException ue){
			LOG.warn(ue.getMessage(), ue);
//			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("returnUrl", "setting/autoTrade_add.htm");
			return "error/user_error";
		}
		return "setting/autoFundStepP3";
	}
	
}
