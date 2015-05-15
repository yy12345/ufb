package com.ufufund.ufb.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.QueryManager;
import com.ufufund.ufb.biz.manager.TradeAccoManager;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.utils.NumberUtils;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.TradeRequest;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.Level;
import com.ufufund.ufb.model.vo.Assets;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;


@Controller
public class CustController {
	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	
	@Autowired
	private CustManager custManager;
	
	@RequestMapping(value="cust/register")
	public String getRegistPage(CustinfoVo custinfoVo, Model model){
		UserHelper.removeCustinfoVo();
		if(null == custinfoVo.getInvtp()){
			//个人注册开户
			custinfoVo.setInvtp("0");
			//经办人身份
			custinfoVo.setLevel("1");
		}
		model.addAttribute("CustinfoVo", custinfoVo);
		return "cust/registerPage";
	}
	
//	@RequestMapping(value="login/index")
//	public String getLoginPage(CustinfoVo custinfoVo, Model model){
//		UserHelper.removeCustinfoVo();
//		model.addAttribute("CustinfoVo", custinfoVo);
//		return "login/indexPage";
//	}
	
	
	/**
	 * 注册用户
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/register_org")
	public String registerOrg(CustinfoVo custinfoVo, Model model) {
		
		try{
			custinfoVo.setInvtp(Invtp.PERSONAL.getValue()); // 个人
			custinfoVo.setLevel(Level.OPERATOR.getValue()); // 经办人
			
			// 防止重复注册
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				if(s_custinfo.getMobileno().equals(custinfoVo.getMobileno())){
					// Session登录
					model.addAttribute("CustinfoVo", s_custinfo);
					return "cust/registerSuccessPage";
				}
			}
			
			// 校验验证码
			// VerifyCodeUtils.validate(custinfoVo.getVerifycode());
			// 校验短信验证码
			// MsgCodeUtils.validate(custinfo.getMsgcode());
			
			// 注册对象封装 
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLoginCode(custinfoVo.getMobileno());
			registerAction.setLoginPassword(custinfoVo.getPswpwd());
			registerAction.setLoginPassword2(custinfoVo.getPswpwd2());
			registerAction.setInvtp(Invtp.PERSONAL);// 个人
			registerAction.setLevel(Level.OPERATOR); // 经办人
			registerAction.setOrganization(custinfoVo.getOrganization());
			registerAction.setBusiness(custinfoVo.getBusiness());
			// 注册
			custManager.register(registerAction);
			// TODO 保存custno [比较怪，应付一下]
			custinfoVo.setCustno(registerAction.getCustNo());
			// 注册成功，保存用户至session
			UserHelper.saveCustinfoVo(custinfoVo);
			
			model.addAttribute("CustinfoVo", custinfoVo);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems)
				|| BisConst.Register.BANKMOBILE.equals(ems)) {
				//
				model.addAttribute("errMsg_mobileno", e.getMessage());
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				//
				model.addAttribute("errMsg_verifycode", e.getMessage());
			} else if (BisConst.Register.LOGINPASSWORD.equals(ems)) {
				//
				model.addAttribute("errMsg_pswpwd", e.getMessage());
			} else if (BisConst.Register.LOGINPASSWORD2.equals(ems)) {
				//
				model.addAttribute("errMsg_pswpwd2", e.getMessage());
			} else if (BisConst.Register.ORGANIZATION.equals(ems)) {
				//
				model.addAttribute("errMsg_organization", e.getMessage());
			} else if (BisConst.Register.BUSINESS.equals(ems)) {
				//
				model.addAttribute("errMsg_business", e.getMessage());
			} else {
				model.addAttribute("errMsg", e.getMessage());
			}
			
			model.addAttribute("CustinfoVo", custinfoVo);
			return "cust/registerPage";
		}
		return "cust/registerSuccessPage";
	}
	
	/**
	 * 首页、广告页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "home/index")
	public String index(Model model) {
		
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				model.addAttribute("CustinfoVo", s_custinfo);
			}else{
				model.addAttribute("CustinfoVo", null);
			}
		}catch (BizException e){
			// TODO 调到登录页面
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "home/indexPage";
	}
	
	/**
	 * 登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/login")
	public String loginIn(CustinfoVo custinfoVo, Model model) {
		
		try{
			LoginAction loginAction = new LoginAction();
			// 普通登录
			loginAction.setLoginCode(custinfoVo.getMobileno());
			loginAction.setLoginPassword(custinfoVo.getPswpwd());
			
			// 校验验证码 
			// TODO 测试好加上
			// VerifyCodeUtils.validate(custinfoVo.getVerifycode());
			
			// 登录
			Custinfo custinfo = custManager.loginIn(loginAction);
			custinfoVo = new CustinfoVo();
			custinfoVo.setCustno(custinfo.getCustno());;                      
			custinfoVo.setMobileno(custinfo.getMobileno());                    
			custinfoVo.setInvtp(custinfo.getInvtp()); 
			custinfoVo.setInvnm(custinfo.getInvnm());        
			custinfoVo.setIdtp(custinfo.getIdtp());     
			custinfoVo.setIdno(custinfo.getIdno());             
			custinfoVo.setPswpwd(custinfo.getPasswd()); // 注意，页面上不能放密码信息                  
			custinfoVo.setPswpwd2(custinfo.getPasswd()); // 注意，页面上不能放密码信息                         
			custinfoVo.setTradepwd(custinfo.getTradepwd()); // 注意，页面上不能放密码信息                             
			custinfoVo.setTradepwd2(custinfo.getTradepwd()); // 注意，页面上不能放密码信息         
			custinfoVo.setOrganization(custinfo.getOrganization()); 
			custinfoVo.setBusiness(custinfo.getBusiness()); 
			custinfoVo.setCustst(custinfo.getCustst());
			custinfoVo.setLevel(custinfo.getLevel());
			custinfoVo.setOpenaccount(custinfo.getOpenaccount());
			// 登录成功，保存用户至session
			UserHelper.saveCustinfoVo(custinfoVo);
			
			if("Y".equals(custinfoVo.getOpenaccount())){
				List<BankCardWithTradeAcco> tradeAccoList 
				= tradeAccoManager.getTradeAccoList(custinfoVo.getCustno());
				Assets assets = queryManager.queryAssets(tradeAccoList);
				model.addAttribute("totalBalance", assets.getTotal()); // 总资产
				model.addAttribute("availableBalance", assets.getAvailable()); //可用资产
				model.addAttribute("frozenBalance", assets.getFrozen()); // 冻结资产
				model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));
				model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));
				model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));
			
				model.addAttribute("curCard", assets.getAccoList().get(0));
				model.addAttribute("cardList", assets.getAccoList());
				
				// 充值
				List<TradeRequest> list022 = queryManager.qryRecentTradeList(custinfoVo.getCustno(), "022", 4);
				// 取现
				List<TradeRequest> list023 = queryManager.qryRecentTradeList(custinfoVo.getCustno(), "024", 4);
				
				model.addAttribute("list022", list022);
				model.addAttribute("list023", list023);
			} else {
				model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
				model.addAttribute("curCard", null);
				model.addAttribute("cardList", null);
			}
			
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			// TODO 调到登录页面
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems)
				|| BisConst.Register.LOGINCODE.equals(ems)) {
				//
				model.addAttribute("errMsg_mobileno", e.getMessage());
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				//
				model.addAttribute("errMsg_verifycode", e.getMessage());
			} else if (BisConst.Register.LOGINPASSWORD.equals(ems)) {
				//
				model.addAttribute("errMsg_pswpwd", e.getMessage());
			} else {
				model.addAttribute("errMsg", e.getMessage());
			}
					
			model.addAttribute("CustinfoVo", custinfoVo);
			//return "login/indexPage";
			return "home/indexPage";
		}
		return "cust/indexPage";
	}
	
	/**
	 * 登出
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/logout")
	public String logOut(Model model) {
		
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登出
				UserHelper.removeCustinfoVo();
			}
		}catch (BizException e){
			// TODO 调到登录页面
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "home/indexPage";
	}
	
//	/**
//	 * 找回登录密码Page
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value="password/getLoginPwdPage" , method=RequestMethod.GET)
//	public String getLoginPwdPage(Model model){
//		
//		return "password/getLoginPwdChk";
//	}
//	
//	/**
//	 * 找回登录密码Check
//	 * @param custinfoVo
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "password/getLoginPwdChk")
//	public String getLoginPwdChk(CustinfoVo custinfoVo, Model model) {
//		
//		try{
//			//AjaxCustController.sendMsgCode
//			//msgType: 注册REGISTER、找回登录密码GETLOGINPWD
//			
//			// 校验验证码
//			boolean checkVerifyCode = VerifyCodeUtils.validate(custinfoVo.getVerifycode());
//			if(!checkVerifyCode){
//				throw new BizException("验证码无效。");
//			}
//
//			// 查询手机号是否注册
//			boolean isMobileRegister = custManager.isMobileRegister(custinfoVo.getMobileno());
//			if(!isMobileRegister){
//				throw new BizException("手机号未注册。");
//			}
//			
//			// 校验短信验证码
//			boolean checkMsgCode = MsgCodeUtils.validate(custinfoVo.getMsgcode());
//			if(!checkMsgCode){
//				throw new BizException("短信验证码无效。");
//			}
//			
//		}catch (BizException e){
//			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("errMsg", e.getMessage());
//			model.addAttribute("returnUrl", "password/getLoginPwdChk");
//			return "error/error";
//		}
//
//		return "password/getLoginPwdSet";
//	}
//	
//	/**
//	 * 找回登录密码Set
//	 * @param custinfoVo
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "password/getLoginPwdSet")
//	public String getLoginPwdSet(CustinfoVo custinfoVo, Model model) {
//		
//		try{
////			// 校验验证码
////			boolean checkVerifyCode = VerifyCodeUtils.validate("GETLOGINPWD", custinfoVo.getVerifycode());
////			if(!checkVerifyCode){
////				throw new BizException("验证码无效。");
////			}
////
////			// 查询手机号是否注册
////			boolean isMobileRegister = custManager.isMobileRegister(custinfoVo.getMobileno());
////			if(!isMobileRegister){
////				throw new BizException("手机号未注册。");
////			}
////			
////			// 校验短信验证码
////			boolean checkMsgCode = MsgCodeUtils.validate(custinfoVo.getMsgcode());
////			if(!checkMsgCode){
////				throw new BizException("短信验证码无效。");
////			}
//			
//			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
//			changePasswordAction.setMobile(custinfoVo.getMobileno());
//			changePasswordAction.setLoginPassword(custinfoVo.getPswpwd());
//			changePasswordAction.setLoginPassword2(custinfoVo.getPswpwd2());
//			custManager.changePassword(changePasswordAction);
//			
//		}catch (BizException e){
//			LOG.error(e.getErrmsg(), e);
//			model.addAttribute("errMsg", e.getMessage());
//			model.addAttribute("returnUrl", "password/getLoginPwdSet");
//			return "error/error";
//		}
//
//		return "password/getLoginPwdSuccess";
//	}
	
	@Autowired
	private QueryManager queryManager;

	@Autowired
	private TradeAccoManager tradeAccoManager;
	
	/**
	 * 注册后直接登录
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "session/login")
	public String custLogin(CustinfoVo custinfoVo, Model model) {
		
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
				
				if("Y".equals(s_custinfo.getOpenaccount())){
					List<BankCardWithTradeAcco> tradeAccoList 
					= tradeAccoManager.getTradeAccoList(s_custinfo.getCustno());
					Assets assets = queryManager.queryAssets(tradeAccoList);
					model.addAttribute("totalBalance", assets.getTotal()); // 总资产
					model.addAttribute("availableBalance", assets.getAvailable()); //可用资产
					model.addAttribute("frozenBalance", assets.getFrozen()); // 冻结资产
					model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getTotal()));
					model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getAvailable()));
					model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(assets.getFrozen()));
				
					model.addAttribute("curCard", assets.getAccoList().get(0));
					model.addAttribute("cardList", assets.getAccoList());
					
					// 充值
					List<TradeRequest> list022 = queryManager.qryRecentTradeList(s_custinfo.getCustno(), "022", 4);
					// 取现
					List<TradeRequest> list023 = queryManager.qryRecentTradeList(s_custinfo.getCustno(), "024", 4);
					
					model.addAttribute("list022", list022);
					model.addAttribute("list023", list023);
				} else {
					model.addAttribute("totalBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
					model.addAttribute("availableBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
					model.addAttribute("frozenBalanceDisplay", NumberUtils.DF_CASH_CONMMA.format(0));
					model.addAttribute("curCard", null);
					model.addAttribute("cardList", null);
				}
				
			}else{
				return "home/indexPage";
			}
			model.addAttribute("CustinfoVo", custinfoVo);
		}catch (BizException e){
			// TODO 调到登录页面
			LOG.error(e.getErrmsg(), e);
			
			String ems = e.getOtherInfo();
			if (BisConst.Register.MOBILE.equals(ems)
				|| BisConst.Register.LOGINCODE.equals(ems)) {
				//
				model.addAttribute("errMsg_mobileno", e.getMessage());
			} else if (BisConst.Register.VERIFYCODE.equals(ems)) {
				//
				model.addAttribute("errMsg_verifycode", e.getMessage());
			} else if (BisConst.Register.LOGINPASSWORD.equals(ems)) {
				//
				model.addAttribute("errMsg_pswpwd", e.getMessage());
			} else {
				model.addAttribute("errMsg", e.getMessage());
			}
					
			model.addAttribute("CustinfoVo", custinfoVo);
			return "home/indexPage";
		}
		return "cust/indexPage";
	}
}
