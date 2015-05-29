package com.ufufund.ufb.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.ufufund.ufb.common.utils.DateUtil;
import com.ufufund.ufb.model.action.cust.AddAutotradeAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.db.Autotrade;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.AutotradeVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.Today;
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
	private QueryManager queryManager;
	
	@RequestMapping(value="setting/settingAccount")
	public String setAccount(CustinfoVo custinfoVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				custinfoVo.setCustno(s_custinfo.getCustno());;                      
				custinfoVo.setMobileno(s_custinfo.getMobileno());                    
				custinfoVo.setInvtp(s_custinfo.getInvtp()); 
				custinfoVo.setInvnm(s_custinfo.getInvnm());        
				custinfoVo.setIdtp(s_custinfo.getIdtp());     
				custinfoVo.setIdno(s_custinfo.getIdno());             
				custinfoVo.setOrganization(s_custinfo.getOrganization()); 
				custinfoVo.setBusiness(s_custinfo.getBusiness()); 
				custinfoVo.setCustst(s_custinfo.getCustst());
				custinfoVo.setLevel(s_custinfo.getLevel());
				custinfoVo.setOpenaccount(s_custinfo.getOpenaccount());
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingAccount";
		}
		model.addAttribute("CustinfoVo", custinfoVo);
		model.addAttribute("SessionVo", custinfoVo);
		return "setting/settingAccount";
	}
	
	@RequestMapping(value="setting/settingPassword")
	public String setPassword(CustinfoVo custinfoVo, Model model){
		try{
			model.addAttribute("TAB", "1");
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				custinfoVo.setCustno(s_custinfo.getCustno());;                      
				custinfoVo.setMobileno(s_custinfo.getMobileno());                    
				custinfoVo.setInvtp(s_custinfo.getInvtp()); 
				custinfoVo.setInvnm(s_custinfo.getInvnm());        
				custinfoVo.setIdtp(s_custinfo.getIdtp());     
				custinfoVo.setIdno(s_custinfo.getIdno());             
				custinfoVo.setOrganization(s_custinfo.getOrganization()); 
				custinfoVo.setBusiness(s_custinfo.getBusiness()); 
				custinfoVo.setCustst(s_custinfo.getCustst());
				custinfoVo.setLevel(s_custinfo.getLevel());
				custinfoVo.setOpenaccount(s_custinfo.getOpenaccount());
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingPassword";
		}
		model.addAttribute("CustinfoVo", custinfoVo);
		model.addAttribute("SessionVo", custinfoVo);
		return "setting/settingPassword";
	}
	
	@RequestMapping(value="setting/settingLoginPwd")
	public String setLoginPwd(String password0, String password1, String password2, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			model.addAttribute("TAB", "1");
			if(null != s_custinfo){
				ChangePasswordAction changePasswordAction = new ChangePasswordAction();
				changePasswordAction.setActionType("LOGIN");
				changePasswordAction.setCustno(s_custinfo.getCustno());
				changePasswordAction.setPassword0(password0);
				changePasswordAction.setPassword1(password1);
				changePasswordAction.setPassword2(password2);
				/** 修改登录密码 **/
				custManager.changePassword(changePasswordAction);
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("SessionVo", s_custinfo);
			model.addAttribute("TAB", "1S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.LOGINPASSWORD0.equals(ems)){
				model.addAttribute("errMsg_login_password0", e.getMessage());
			}else
			if(BisConst.Register.LOGINPASSWORD.equals(ems)){
				model.addAttribute("errMsg_login_password1", e.getMessage());
			}else
			if(BisConst.Register.LOGINPASSWORD2.equals(ems)){
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
			if(null != s_custinfo){
				ChangePasswordAction changePasswordAction = new ChangePasswordAction();
				changePasswordAction.setActionType("TRADE");
				changePasswordAction.setCustno(s_custinfo.getCustno());
				changePasswordAction.setPassword0(password0);
				changePasswordAction.setPassword1(password1);
				changePasswordAction.setPassword2(password2);
				/** 修改交易密码 **/
				custManager.changePassword(changePasswordAction);
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("SessionVo", s_custinfo);
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
			if(null != s_custinfo){

				// 校验手机验证码
				MsgCodeUtils.validate(msgcode, s_custinfo.getMobileno());
				
				ChangePasswordAction changePasswordAction = new ChangePasswordAction();
				changePasswordAction.setActionType("TRADEBACK");
				changePasswordAction.setCustno(s_custinfo.getCustno());
				changePasswordAction.setPassword1(password1);
				changePasswordAction.setPassword2(password2);
				/** 修改交易密码 **/
				custManager.changePassword(changePasswordAction);
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("SessionVo", s_custinfo);
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
			if(null != s_custinfo){
				// Session登录
				custinfoVo.setCustno(s_custinfo.getCustno());;                      
				custinfoVo.setMobileno(s_custinfo.getMobileno());                    
				custinfoVo.setInvtp(s_custinfo.getInvtp()); 
				custinfoVo.setInvnm(s_custinfo.getInvnm());        
				custinfoVo.setIdtp(s_custinfo.getIdtp());     
				custinfoVo.setIdno(s_custinfo.getIdno());             
				custinfoVo.setOrganization(s_custinfo.getOrganization()); 
				custinfoVo.setBusiness(s_custinfo.getBusiness()); 
				custinfoVo.setCustst(s_custinfo.getCustst());
				custinfoVo.setLevel(s_custinfo.getLevel());
				custinfoVo.setOpenaccount(s_custinfo.getOpenaccount());
				
				// 获取交易账户列表
				List<BankCardWithTradeAcco> tradeAccoList_Y = 
						bankCardManager.getBankCardWithTradeAccoList(s_custinfo.getCustno(), "Y");
				if(null != tradeAccoList_Y && tradeAccoList_Y.size() > 0){
					
					// 获取用户总资产
					Assets assets = queryManager.queryAssets(tradeAccoList_Y);
					List<TradeAccoVo> list_y =  assets.getAccoList();
					
					model.addAttribute("cardList_Y", list_y);
				} else {
					model.addAttribute("cardList_Y", null);
				}
				
				// 获取交易账户列表
				List<BankCardWithTradeAcco> tradeAccoList_N = 
						bankCardManager.getBankCardWithTradeAccoList(s_custinfo.getCustno(), "N");
				if(null != tradeAccoList_N && tradeAccoList_N.size() > 0){
					model.addAttribute("cardList_N", tradeAccoList_N);
				} else {
					model.addAttribute("cardList_N", null);
				}
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingCard";
		}
		
		model.addAttribute("CustinfoVo", custinfoVo);
		model.addAttribute("SessionVo", custinfoVo);
		return "setting/settingCard";
	}
	

	@RequestMapping(value="setting/settingMainCard")
	public String setMainCard(String bankacco, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// 短信验证
				bankCardManager.setBankCardMainFlag(
						s_custinfo.getCustno(), 
						null, 
						"N");
				bankCardManager.setBankCardMainFlag(
						s_custinfo.getCustno(), 
						ServletHolder.getRequest().getParameter("bankacco"), 
						"Y");
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingCard";
		}
		ServletHolder.forward("/setting/settingCard.htm");
		return "setting/settingCard";
	}
	
	@RequestMapping(value="setting/settingUnbindCard")
	public String setUnbindCard(String bankacco, String tradeacco, Model model){
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		try{
			if(null != s_custinfo){
				// 短信验证
				TradeAccoVo tradeAccoVo = queryManager.queryAssets(tradeacco);
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
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			return "setting/settingCard";
		}catch(UserException ue){
			LOG.warn(ue.getCodeMsg());
			model.addAttribute("errorMsg", ue.getMessage());
			model.addAttribute("CustinfoVo", s_custinfo);
			model.addAttribute("returnUrl", "setting/settingCard.htm");
			return "error/user_error";
		}
		ServletHolder.forward("/setting/settingCard.htm");
		return "setting/settingCard";
	}
	
	@RequestMapping(value="setting/settingActiveCard")
	public String setActiveCard(String bankacco, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// 短信验证
				bankCardManager.unbindBankCard(
						s_custinfo.getCustno(), 
						ServletHolder.getRequest().getParameter("bankacco"), 
						"Y");
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingCard";
		}
		ServletHolder.forward("/setting/settingCard.htm");
		return "setting/settingCard";
	}
	
	@Autowired
	AutotradeManager autotradeManager;
	
	@Autowired
	TradeAccoManager tradeAccoManager;
	
	@Autowired
	WorkDayManager workDayManager;
	
	@RequestMapping(value="setting/settingAutoTrade")
	public String setAutoTrade(Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				
				// 获取自动充值计划列表
				List<Autotrade> list = autotradeManager.getAutotrade(s_custinfo.getCustno());
				model.addAttribute("LIST", list);
				
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
			model.addAttribute("SessionVo", s_custinfo);
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingAutoTrade";
		}
		return "setting/settingAutoTrade";
	}
	

	@RequestMapping(value="setting/addAutoTrade")
	public String addAutoTrade(Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){String custno = UserHelper.getCustno();
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
			
			model.addAttribute("SessionVo", UserHelper.getCustinfoVo());} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingAutoTrade";
		}
		return "setting/addAutoTrade";
	}
	
	@RequestMapping(value="setting/addAutoTradeConfirm")
	public String addAutoTradeConfirm(AutotradeVo autotradeVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				
				autotradeVo.setCustno(s_custinfo.getCustno());
//				autotradeVo.setApkind(Apkind.AUTORECHARGE.getValue());
				autotradeVo.setCycle("MM");
				autotradeVo.setTofundcode(BasicFundinfo.YFB.getFundCode());
				autotradeVo.setTofundcorpno(Constant.HftSysConfig.HftFundCorpno);
				
				String nextdate = autotradeManager.getNextdate(autotradeVo.getCycle(), autotradeVo.getDat());
				autotradeVo.setNextdate(nextdate);
				
				
				model.addAttribute("AutoTradeVo", autotradeVo);
				model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
				
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/settingAutoTrade";
		}
		return "setting/addAutoTradeConfirm";
	}
	
	@RequestMapping(value="setting/addAutoTradeSubmit")
	public String addAutoTradeSubmit(AutotradeVo autotradeVo, Model model){
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				
				AddAutotradeAction action = new AddAutotradeAction();
				
				action.setCustno(autotradeVo.getCustno());
				action.setApkind(Apkind.AUTORECHARGE);
				action.setType("E");
				action.setCycle(autotradeVo.getCycle());
				action.setDat(autotradeVo.getDat());
				action.setTofundcorpno(autotradeVo.getTofundcorpno());
				action.setTofundcode(autotradeVo.getTofundcode());
				action.setTochargetype("A");
				action.setTobankserialid(autotradeVo.getTobankserialid());				
				action.setTobankacco(autotradeVo.getTobankacco());
				action.setToaccoid(autotradeVo.getToaccoid());
				action.setTotradeacco(autotradeVo.getTotradeacco());
				action.setAutoamt(autotradeVo.getAutoamt());
				action.setNextdate(autotradeVo.getNextdate());
				action.setSummary(autotradeVo.getSummary());
				
				autotradeManager.addAutotrade(action);
				
				model.addAttribute("SessionVo", UserHelper.getCustinfoVo());
				
			} else{
				ServletHolder.forward("/home/index.htm");
				return "home/index";
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			return "setting/addAutoTradeConfirm";
		}
		ServletHolder.forward("/setting/settingAutoTrade.htm");
		return "setting/settingAutoTrade";
	}
	
}