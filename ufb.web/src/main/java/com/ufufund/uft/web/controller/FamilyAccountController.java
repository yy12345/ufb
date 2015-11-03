package com.ufufund.uft.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.ChinapayManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.BankBaseInfo;
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.TradeAccoinfoOfMore;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
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
	private static final String SETTING_CARD = "family/setting/card_index.htm";
	private static final String SETTING_CARD_NAME = "我的银行卡";
	
	private static final String REGISTER_VO = "register_vo";
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private TradeAccoManager tradeAccoManager;
	@Autowired
	private ChinapayManager chinapayManager;
	
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
	public String registerPasswd(CustinfoVo custinfoVo, Model model) {
		try{
			// 后台验证手机验证码
			MsgCodeUtils.validate(custinfoVo.getMsgcode(), custinfoVo.getMobileno());
			ServletHolder.getSession().setAttribute(REGISTER_VO, custinfoVo);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			model.addAttribute("message_url", FAMILY_HOME);
			model.addAttribute("back_module", FAMILY_HOME_NAME);
			return "error/error";
		}
		return "family/account/register_passwd";
	}
	
	/**
	 * 家庭版：注册，绑定银行卡
	 * @param custinfoVo
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_card")
	public String registerCard(CustinfoVo custinfoVo,  Model model) {
		
		try{
			// 缓存表单提交数据
			CustinfoVo r_custinfoVo = (CustinfoVo)ServletHolder.getSession().getAttribute(REGISTER_VO);
			if(r_custinfoVo == null ){
				throw new UserException("您访问的页面已失效，请返回至首页，重新访问！");
			}else{
				r_custinfoVo.setLoginpwd(custinfoVo.getLoginpwd());
				r_custinfoVo.setLoginpwd2(custinfoVo.getLoginpwd2());
				r_custinfoVo.setTradepwd(custinfoVo.getTradepwd());
				r_custinfoVo.setTradepwd2(custinfoVo.getTradepwd2());
				r_custinfoVo.setInvnm(custinfoVo.getInvnm());
				r_custinfoVo.setIdno(custinfoVo.getIdno());
				ServletHolder.getSession().setAttribute(REGISTER_VO, r_custinfoVo);
			}
			
			//获得所有的银行
			List<BankBaseInfo> bankBaseList = bankBaseManager.getBankBaseInfoList(null);
			// 支持幼富宝的银行
			List<BankBaseInfo> ufbBankList= new ArrayList<BankBaseInfo>();
			// 仅支持幼富通的银行
			List<BankBaseInfo> uftBankList= new ArrayList<BankBaseInfo>();
			for(BankBaseInfo bankinfo:bankBaseList){
				if("2".equals(bankinfo.getLevel())){
					uftBankList.add(bankinfo);
				}
				else if("1".equals(bankinfo.getLevel())){
					ufbBankList.add(bankinfo);
				}
			}
			 
			model.addAttribute("ufbBankList", ufbBankList);
			model.addAttribute("uftBankList", uftBankList);
			model.addAttribute("custinfoVo", r_custinfoVo);
			model.addAttribute("firstBank", ufbBankList.get(0));
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
	
	@RequestMapping(value = "bank_auth")
	@ResponseBody
	public Map<String,String> bankAuth(BankCardVo bankCardVo){

		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			// 缓存表单提交数据
			CustinfoVo r_custinfoVo = (CustinfoVo)ServletHolder.getSession().getAttribute(REGISTER_VO);
			if(r_custinfoVo == null ){
				throw new UserException("9998", "您访问的页面已失效，请返回至首页，重新访问！");
			}
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setBankno(bankCardVo.getBankno());//银行编号
			openAccountAction.setBankacnm(r_custinfoVo.getInvnm());//银行用户名
			openAccountAction.setBankidtp("0");					//银行证件类型
			openAccountAction.setBankidno(r_custinfoVo.getIdno());//银行证件号
			openAccountAction.setBankacco(bankCardVo.getBankacco());//银行卡号码
			openAccountAction.setBankmobile(bankCardVo.getBankmobile());//银行手机号
			
			// 海富通快捷鉴权
			bankCardManager.openAccount2(openAccountAction);
			// 对方序列号
			ServletHolder.getSession().setAttribute("otherserial", openAccountAction.getAccoreqserial());
			
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
	 * 家庭版：注册，成功页
	 * @param custinfoVo
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "register_success")
	public String registerSuccess(BankCardVo bankCardVo, Model model) {
		
		try{
			// 缓存表单提交数据
			CustinfoVo r_custinfoVo = (CustinfoVo)ServletHolder.getSession().getAttribute(REGISTER_VO);
			String otherserial = (String) ServletHolder.getSession().getAttribute("otherserial");
			if(r_custinfoVo == null ){
				throw new UserException("您访问的页面已失效，请返回至首页，重新访问！");
			}
			
			// 注册信息验证
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLogincode(r_custinfoVo.getMobileno());
			registerAction.setLoginpwd(r_custinfoVo.getLoginpwd());
			registerAction.setLoginpwd2(r_custinfoVo.getLoginpwd2());
			registerAction.setTradepwd(r_custinfoVo.getTradepwd());
			registerAction.setTradepwd2(r_custinfoVo.getTradepwd2());
			registerAction.setIdno(r_custinfoVo.getIdno());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setCustst("N");
			custManager.validateFamily(registerAction);
			
			// 对象组装
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setFundcorpno(Constant.HftSysConfig.HftFundCorpno);
			openAccountAction.setBankno(bankCardVo.getBankno());
			openAccountAction.setBankacnm(r_custinfoVo.getInvnm());
			openAccountAction.setBankacco(bankCardVo.getBankacco());
			openAccountAction.setBankidtp("0");
			openAccountAction.setBankidno(r_custinfoVo.getIdno());
			openAccountAction.setBankmobile(bankCardVo.getBankmobile());
			openAccountAction.setMobileautocode(bankCardVo.getMobileautocode());
//			openAccountAction.setBankcitynm(StringUtils.isNotBlank(bankCardVo.getBankcitynm())?bankCardVo.getBankcitynm():null);
//			openAccountAction.setBankprovincenm(StringUtils.isNotBlank(bankCardVo.getBankprovincenm())?bankCardVo.getBankprovincenm():null);
//			openAccountAction.setBankadd(StringUtils.isNotBlank(bankCardVo.getBankadd())?bankCardVo.getBankadd():null);
			openAccountAction.setOtherserial(otherserial);
//			openAccountAction.setCheckautocodeflag(true);
			
			String banklevel = bankBaseManager.getLevelByBankno(openAccountAction.getBankno());
			// 幼富宝卡，海富通开户
			if("1".equals(banklevel)){
				bankCardManager.openAccount3(openAccountAction);
				bankCardManager.openAccount4(openAccountAction);
			}
			
			// 银联账户验证
			chinapayManager.checkAccount(openAccountAction);
			
			// 用户注册
			openAccountAction.setInvnm(r_custinfoVo.getInvnm());
			openAccountAction.setIdtp("0");
			openAccountAction.setIdno(r_custinfoVo.getIdno());
			String custno = custManager.register(registerAction, openAccountAction);
			
			// 注册成功，保存用户至session
			Custinfo custinfo = custManager.getCustinfo(custno);
			UserHelper.saveCustinfoVo(this.convertCustInfo2Vo(custinfo));
				
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
				
				List<TradeAccoinfoOfMore> hft_family_trade = tradeAccoManager.getTradeAccoList(
						custinfoVo.getCustno(),
						null,//Constant.HftSysConfig.HftFundCorpno, 
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
//					String banklevel=bankCardManager.getLevelByBankno(openAccountAction.getBankno());
//					if("2".equals(banklevel)){
//						bankCardManager.checkYinLian(openAccountAction);  	
//					}
//					/** 开户 **/
//					bankCardManager.openAccountPerson(openAccountAction);
					
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
//		custinfoVo.setOrgnm(custinfo.getOrgnm()); 
//		custinfoVo.setOrgbusiness(custinfo.getOrgbusiness()); 
		custinfoVo.setCustst(custinfo.getCustst());
		custinfoVo.setLevel(custinfo.getLevel());
		return custinfoVo;
	}
	
}
