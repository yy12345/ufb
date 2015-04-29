package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.enums.Level;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;


@Controller
public class CustController {
	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankBaseManager bankBaseManager;
	
	@RequestMapping(value="cust/register")
	public String getPage(CustinfoVo custinfoVo, Model model){
		if(null == custinfoVo.getInvtp()){
			//个人注册开户
			custinfoVo.setInvtp("0");
			//经办人身份
			custinfoVo.setLevel("1");
		}
		model.addAttribute("CustinfoVo", custinfoVo);
		return "cust/registerPage";
	}
	
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
				// Session登录
				model.addAttribute("CustinfoVo", s_custinfo);
				return "cust/registerSuccessPage";
			}
			
			// 校验验证码
			VerifyCodeUtils.validate(custinfoVo.getVerifycode());
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
			// 注册成功，保存用户至session
			UserHelper.saveCustinfoVo(custinfoVo);
			
			model.addAttribute("CustinfoVo", custinfoVo);
			
		}catch (BizException e){
			// TODO
			LOG.error(e.getErrmsg(), e);
			
			if("手机号".equals(e.getOtherInfo()) || "账号".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_mobileno", e.getMessage());
			}else
			if("验证码".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_verifycode", e.getMessage());
			}else
			if("密码".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_pswpwd", e.getMessage());
			}else
			if("确认密码".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_pswpwd2", e.getMessage());
			}else
			if("机构名称".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_organization", e.getMessage());
			}else
			if("营业执照".equals(e.getOtherInfo())){
				model.addAttribute("errMsg_business", e.getMessage());
			}else{
				model.addAttribute("errMsg", e.getMessage());
			}
			
			model.addAttribute("CustinfoVo", custinfoVo);
			return "cust/registerPage";
		}
		return "cust/registerSuccessPage";
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
			
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				// Session登录
				loginAction.setLoginCode(s_custinfo.getMobileno());
				loginAction.setLoginPassword(s_custinfo.getPswpwd());
			}else{
				// 普通登录
				loginAction.setLoginCode(custinfoVo.getMobileno());
				loginAction.setLoginPassword(custinfoVo.getPswpwd());
				
				// 校验验证码
				VerifyCodeUtils.validate(custinfoVo.getVerifycode());
			}
			
			// 登录
			Custinfo custinfo = custManager.loginIn(loginAction);
			s_custinfo = new CustinfoVo();
			s_custinfo.setCustno(custinfo.getCustno());;                      
			s_custinfo.setMobileno(custinfo.getMobileno());                    
			s_custinfo.setInvtp(custinfo.getInvtp()); 
			s_custinfo.setInvnm(custinfo.getInvnm());        
			s_custinfo.setIdtp(custinfo.getIdtp());     
			s_custinfo.setIdno(custinfo.getIdno());             
			s_custinfo.setPswpwd(custinfo.getPasswd());                      
			s_custinfo.setPswpwd2(custinfo.getPasswd());                     
			s_custinfo.setTradepwd(custinfo.getTradepwd());                    
			s_custinfo.setTradepwd2(custinfo.getTradepwd());
			s_custinfo.setOrganization(custinfo.getOrganization()); 
			s_custinfo.setBusiness(custinfo.getBusiness()); 
			s_custinfo.setCustst(custinfo.getCustst());
			s_custinfo.setLevel(custinfo.getLevel());
			s_custinfo.setOpenaccount(custinfo.getOpenaccount());
			// 登录成功，保存用户至session
			UserHelper.saveCustinfoVo(s_custinfo);
			
			model.addAttribute("CustinfoVo", s_custinfo);
		}catch (BizException e){
			// TODO 调到登录页面
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
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
}
