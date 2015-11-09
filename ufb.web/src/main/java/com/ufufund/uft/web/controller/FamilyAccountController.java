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
	
	private static final String REGISTER_VO = "register_vo";
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private BankCardManager bankCardManager;
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
			ServletHolder.getSession().removeAttribute(REGISTER_VO);
		}catch(UserException ue){
			log.warn(ue.getMessage(), ue);
			model.addAttribute("message_title", "操作失败");
			model.addAttribute("message_content", ue.getMessage());
			return "error/error";
		}
		return "family/account/register_success";
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
