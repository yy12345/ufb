package com.ufufund.ufb.web.controller;

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
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.vo.CustinfoVo;
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
	 * 获取短信验证码
	 * @param
	 * @return
	 */
	@RequestMapping(value = "setting/getMsgCode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> sendMsgCode(String verifycode){
		//msgType: 注册REGISTER、找回登录密码GETLOGINPWD
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
					// 获取短信验证码
					String template = "";
					// 发送短信
					MsgCodeUtils.sendMsg(template);
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

				// 校验短信验证码
				MsgCodeUtils.validate(msgcode);
				
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
					model.addAttribute("cardList_Y", tradeAccoList_Y);
				} else {
					model.addAttribute("cardList_Y", null);
				}
				
				// 获取交易账户列表
				List<BankCardWithTradeAcco> tradeAccoList_N = 
						bankCardManager.getBankCardWithTradeAccoList(s_custinfo.getCustno(), "N");
				if(null != tradeAccoList_N && tradeAccoList_Y.size() > 0){
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
	
	
}
