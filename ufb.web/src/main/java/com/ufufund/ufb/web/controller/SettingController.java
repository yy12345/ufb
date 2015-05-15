package com.ufufund.ufb.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;


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
	
	@RequestMapping(value="setting/settingTradePwd")
	public String setTradePwd(String password0, String password1, String password2, Model model){
		try{
			model.addAttribute("TAB", "1");
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
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
			model.addAttribute("TAB", "1S");
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			String ems = e.getOtherInfo();
			if(BisConst.Register.TRADEPWD0.equals(ems)){
				model.addAttribute("errMsg_password0", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD.equals(ems)){
				model.addAttribute("errMsg_password1", e.getMessage());
			}else
			if(BisConst.Register.TRADEPWD2.equals(ems)){
				model.addAttribute("errMsg_password2", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			model.addAttribute("password0", password0);
			model.addAttribute("password1", password1);
			model.addAttribute("password2", password2);
			
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
