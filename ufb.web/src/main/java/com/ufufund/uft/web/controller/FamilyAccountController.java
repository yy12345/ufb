package com.ufufund.uft.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.FundManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.biz.manager.org.OrgQueryManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.FundInfo;
import com.ufufund.ufb.model.db.FundNav;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.BasicFundinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.QueryCustplandetail;
import com.ufufund.ufb.model.vo.QueryOrgStudent;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 家庭版注册、登录页
 * @author ayis
 * 2015年10月23日
 */
@Controller
@RequestMapping(value="family/account")
@Slf4j
public class FamilyAccountController {
	
	private static final String FAMILY_HOME = "family/home.htm";
	private static final String FAMILY_HOME_NAME = "幼富通首页";
	private static final String REGISTER_INDEX = "family/account/register_index.htm";
	private static final String SETTING_CARD = "family/setting/card_index.htm";
	private static final String SETTING_CARD_NAME = "我的银行卡";
	
	private static final String REGISTER_MOBILE = "register_mobile";
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	
	/**
	 * 家庭版：登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login")
	@ResponseBody
	public Map<String,Object> login(CustinfoVo custinfoVo, Model model) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			// 参数校验
			VerifyCodeUtils.validate(custinfoVo.getVerifycode());
			// 登录
			LoginAction loginAction = new LoginAction();
			loginAction.setLoginCode(custinfoVo.getMobileno());
			loginAction.setLoginPassword(custinfoVo.getLoginpwd());
			Custinfo custinfo = custManager.loginIn(loginAction);
			custinfoVo = this.convertCustInfo2Vo(custinfo);
			// 保存用户会话
			UserHelper.saveCustinfoVo(custinfoVo);
			
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
	
	/**
	 * 家庭版：退出登录
	 * @return
	 */
	@RequestMapping(value="logout")
	public String logout(){
		ServletHolder.getSession().invalidate();
		return "redirect:/family/index.htm";
	}
	
	/**
	 * 家庭版：注册，首页-手机验证
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="register_index")
	public String registerIndex(CustinfoVo custinfoVo, Model model){
		return "family/account/register_index";
	}
	
	/**
	 * 家庭版：注册，密码填写
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_passwd")
	@ResponseBody
	public Map<String,Object> registerPasswd(CustinfoVo custinfoVo, Model model) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			// 后台验证手机验证码
			MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			ServletHolder.getSession().setAttribute(REGISTER_MOBILE, custinfoVo.getMobileno());
			
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
	
	/**
	 * 家庭版：注册，绑定银行卡
	 * @param custinfoVo
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_card")
	public String registerCard(CustinfoVo custinfoVo,BankCardVo bankCardVo, Model model) {
		
		try{
			//获得用户的注册的信息并进行校验
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(custinfoVo.getTradepwd());
			registerAction.setTradepwd2(custinfoVo.getTradepwd2());
			custManager.validateFamily(registerAction);
			/*//用户注册
			custManager.register(registerAction);
			// 注册成功，保存用户至session
		    custinfoVo.setCustno(registerAction.getCustno());
			UserHelper.saveCustinfoVo(custinfoVo);*/
			//获得所有的银行
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			//支持幼富通的银行===20151013
			List<BankBaseInfo> yftBankList= new ArrayList<BankBaseInfo>();
			//其它的银行
			List<BankBaseInfo> qtBankList= new ArrayList<BankBaseInfo>();
			for(BankBaseInfo bankinfo:bankBaseList){
				if("2".equals(bankinfo.getLevel())){
					qtBankList.add(bankinfo);
				}
				else if("1".equals(bankinfo.getLevel())){
					yftBankList.add(bankinfo);
				}
			}
			
			if(StringUtils.isBlank(bankCardVo.getBankno())){
				// 默认第一个
				bankCardVo.setBankno(yftBankList.get(0).getBankno());
			} 
			model.addAttribute("qtBankList", qtBankList);
			model.addAttribute("bankList", yftBankList);
			//===20151013
			model.addAttribute("CustinfoVo", custinfoVo);
			model.addAttribute("BankCardVo", bankCardVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("back_module", FAMILY_HOME_NAME);
			return "error/error";
		}
		return "family/account/register_card";
	}
	
	/**
	 * 家庭版：注册，成功页
	 * @param custinfoVo
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_success")
	public String registerSuccess(CustinfoVo custinfoVo, BankCardVo bankCardVo, Model model) {
		
		try{
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(custinfoVo.getMobileno());
			registerAction.setLoginpwd(custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(custinfoVo.getTradepwd());
			registerAction.setTradepwd2(custinfoVo.getTradepwd2());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setCustst("N");
			custManager.validateFamily(registerAction);
			//用户注册
			custManager.register(registerAction);
			// 注册成功，保存用户至session
		    custinfoVo.setCustno(registerAction.getCustno());
		    custinfoVo.setInvtp(Invtp.PERSONAL.getValue());
			UserHelper.saveCustinfoVo(custinfoVo);
				//绑卡、开户
			CustinfoVo s_custno=UserHelper.getCustinfoVo();
		    	bankCardVo.setBankidtp("0"); // 身份证绑卡
				OpenAccountAction openAccountAction = new OpenAccountAction();
				/** 开户所属基金单位 **/
				openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
				/** 开户标志 **/
				if(s_custno.getIdno() !=null && s_custno.getInvnm() != null){
					openAccountAction.setOpenaccoflag(true);
				}else{
					openAccountAction.setOpenaccoflag(false);
				}
				// 个人
				openAccountAction.setCustno(registerAction.getCustno());
				openAccountAction.setInvnm(bankCardVo.getBankacnm());
				openAccountAction.setIdno(bankCardVo.getBankidno());
				/** 家庭**/
				openAccountAction.setLevel(Invtp.PERSONAL.getValue());
				openAccountAction.setTradepwd(custinfoVo.getTradepwd());
				openAccountAction.setTradepwd2(custinfoVo.getTradepwd2());
				// 银行
				openAccountAction.setBankno(bankCardVo.getBankno());
				openAccountAction.setBankacnm(bankCardVo.getBankacnm());
				openAccountAction.setBankacco(bankCardVo.getBankacco());
				openAccountAction.setBankidtp(bankCardVo.getBankidtp());
				openAccountAction.setBankidno(bankCardVo.getBankidno());
				openAccountAction.setBankmobile(bankCardVo.getBankmobile());
				openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
				openAccountAction.setBankcitynm(StringUtils.isNotBlank(bankCardVo.getBankcitynm())?bankCardVo.getBankcitynm():null);
				openAccountAction.setBankprovincenm(StringUtils.isNotBlank(bankCardVo.getBankprovincenm())?bankCardVo.getBankprovincenm():null);
				openAccountAction.setBankadd(StringUtils.isNotBlank(bankCardVo.getBankadd())?bankCardVo.getBankadd():null);
				openAccountAction.setOtherserial(bankCardVo.getOtherserial());
				/** 需要验证手机验证码标志 **/
				openAccountAction.setCheckautocodeflag(true);
				
				/** 银行手机验证，并带回Serialno、Protocolno的值 **/
				bankCardManager.openAccount3(openAccountAction);
				//其他的银行卡的银联验证
				String banklevel=bankCardManager.getLevelByBankno(openAccountAction.getBankno());
				if("2".equals(banklevel)){
					bankCardManager.checkYinLian(openAccountAction);  	
				}
				/** 开户 **/
				bankCardManager.openAccountPerson(openAccountAction);
				
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("back_module", FAMILY_HOME_NAME);
			return "error/error";
		}
		return "family/account/register_success";
	}
	
	
	/**
	 * 银行卡:升级幼富宝卡
	 * @param model
	 * @return
	 */
	@RequestMapping(value="card_update")
	public String cardUpdate(BankCardVo bankCardVo,String bankacco, String serialid,Model model){
		
		try{
			 CustinfoVo custinfoVo=UserHelper.getCustinfoVo();
			 String ivnname=custinfoVo.getInvnm();
			 String idno=custinfoVo.getIdno();
			 //用户信息
			 model.addAttribute("ivnname", ivnname);
			 model.addAttribute("idno", idno);
			 //获得所有的银行卡
				List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
				//支持幼富通的银行===20151013
				List<BankBaseInfo> yftBankList= new ArrayList<BankBaseInfo>();
				//其它的银行
				List<BankBaseInfo> qtBankList= new ArrayList<BankBaseInfo>();
				for(BankBaseInfo bankinfo:bankBaseList){
					if("2".equals(bankinfo.getLevel())){
						qtBankList.add(bankinfo);
					}
					else if("1".equals(bankinfo.getLevel())){
						yftBankList.add(bankinfo);
					}
				}
				String banknbinno="";
				 if(null!=bankacco&&!"".equals(bankacco)){//重新绑定的交易账号
					 bankCardVo.setBankacco(bankacco);
					BankCardbin banknbin=bankBaseManager.getBankCardbin(bankacco.substring(0, 6));
					if(null!=banknbin){
						banknbinno=banknbin.getBankno();
					}
				 }
				 if(""!=banknbinno&&null!=banknbinno){
					 bankCardVo.setBankno(banknbinno);
				 }else if(StringUtils.isBlank(bankCardVo.getBankno())){
						 // 默认第一个
						 bankCardVo.setBankno(yftBankList.get(0).getBankno());
					 } 
				 
				model.addAttribute("bankList", yftBankList);
				model.addAttribute("qtBankList", qtBankList);
				//获得用户是否有幼富通卡
				List<String> tradeaccosts = new ArrayList<String>();
				tradeaccosts.add("Y"); // 
				tradeaccosts.add("N"); // 
				
				List<String> levels = new ArrayList<String>();
				levels.add("0"); 
				List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(
						custinfoVo.getCustno(),
						null,//Constant.HftSysConfig.HftFundCorpno, 
						levels,
						tradeaccosts);
				String isufbCard="N";
				if(null!=hft_family_trade&& hft_family_trade.size() > 0){
					for(TradeAccoinfoOfMore tradeacco:hft_family_trade){
						if(tradeacco.getFundcorpno().equals("01")){
							isufbCard="Y";
							break;
						}
					}
				}
				else{
					isufbCard="Y";
				}
				model.addAttribute("hasTadeacco", isufbCard);
				if(""!=serialid&&null!=serialid){
					model.addAttribute("serialid", serialid);
				}
				
				model.addAttribute("BankCardVo", bankCardVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", SETTING_CARD);
			model.addAttribute("back_module", SETTING_CARD_NAME);
			return "error/error";
		}
		return "family/ufb/addBankCard";
	}
	
	/**
	 * 银行卡:升级成功页
	 * @param bankCardVo
	 * @param serialid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="card_result")
	public String cardResult(BankCardVo bankCardVo, String serialid,Model model){
		
		CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		bankCardVo.setBankidtp("0"); // 身份证绑卡
		try{		
			
		        if(""!=serialid&&null!=serialid){
					bankCardManager.unbindBankCard(
							s_custinfo.getCustno(), 
							ServletHolder.getRequest().getParameter("serialid"), 
							"Y");
	            }else{
					//验证手机验证码
					OpenAccountAction openAccountAction = new OpenAccountAction();
					/** 开户所属基金单位 **/
					openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
					/** 开户标志 **/
					if(s_custinfo.getIdno() !=null && s_custinfo.getInvnm() != null){
						openAccountAction.setOpenaccoflag(true);
					}else{
						openAccountAction.setOpenaccoflag(false);
					}
					// 个人
					openAccountAction.setCustno(s_custinfo.getCustno());
					openAccountAction.setInvnm(bankCardVo.getBankacnm());
					openAccountAction.setIdno(bankCardVo.getBankidno());
					/** 家庭**/
					openAccountAction.setLevel(Invtp.PERSONAL.getValue());
					openAccountAction.setTradepwd(s_custinfo.getTradepwd());
					openAccountAction.setTradepwd2(s_custinfo.getTradepwd2());
					// 银行
					openAccountAction.setBankno(bankCardVo.getBankno());
					openAccountAction.setBankacnm(bankCardVo.getBankacnm());
					openAccountAction.setBankacco(bankCardVo.getBankacco());
					openAccountAction.setBankidtp(bankCardVo.getBankidtp());
					openAccountAction.setBankidno(bankCardVo.getBankidno());
					openAccountAction.setBankmobile(bankCardVo.getBankmobile());
					openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
					openAccountAction.setOtherserial(bankCardVo.getOtherserial());
					openAccountAction.setBankcitynm(StringUtils.isNotBlank(bankCardVo.getBankcitynm())?bankCardVo.getBankcitynm():null);
					openAccountAction.setBankprovincenm(StringUtils.isNotBlank(bankCardVo.getBankprovincenm())?bankCardVo.getBankprovincenm():null);
					openAccountAction.setBankadd(StringUtils.isNotBlank(bankCardVo.getBankadd())?bankCardVo.getBankadd():null);
					openAccountAction.setOtherserial(bankCardVo.getOtherserial());
					//String banklevel=bankCardManager.getLevelByBankno(bankCardVo.getBankno());//add
					 
					/** 需要验证手机验证码标志 **/
					openAccountAction.setCheckautocodeflag(true);
						
					bankCardManager.openAccount3(openAccountAction);
					//其他的银行卡的银联验证
					String banklevel=bankCardManager.getLevelByBankno(openAccountAction.getBankno());
					if("2".equals(banklevel)){
						bankCardManager.checkYinLian(openAccountAction);  	
					}
					/** 开户 **/
					bankCardManager.openAccountPerson(openAccountAction);
					
		       } 
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", SETTING_CARD);
			model.addAttribute("back_module", SETTING_CARD_NAME);
			return "error/error";
		}
		return "redirect:/"+SETTING_CARD;
	}
	
	private CustinfoVo convertCustInfo2Vo(Custinfo custinfo){
		if(null == custinfo){
			return null;
		}
		CustinfoVo custinfoVo = new CustinfoVo();
		custinfoVo.setCustno(custinfo.getCustno());;                      
		custinfoVo.setMobileno(custinfo.getMobileno());                    
		custinfoVo.setInvtp(custinfo.getInvtp()); 
		custinfoVo.setInvnm(custinfo.getInvnm());        
		custinfoVo.setIdtp(custinfo.getIdtp());     
		custinfoVo.setIdno(custinfo.getIdno());             
		custinfoVo.setLoginpwd(custinfo.getLoginpwd()); // 注意，页面上不能放密码信息                  
		custinfoVo.setLoginpwd2(custinfo.getLoginpwd()); // 注意，页面上不能放密码信息                         
		custinfoVo.setTradepwd(custinfo.getTradepwd()); // 注意，页面上不能放密码信息                             
		custinfoVo.setTradepwd2(custinfo.getTradepwd()); // 注意，页面上不能放密码信息         
		custinfoVo.setOrgnm(custinfo.getOrgnm()); 
		custinfoVo.setOrgbusiness(custinfo.getOrgbusiness()); 
		custinfoVo.setCustst(custinfo.getCustst());
		custinfoVo.setLevel(custinfo.getLevel());
		return custinfoVo;
	}
	
}
