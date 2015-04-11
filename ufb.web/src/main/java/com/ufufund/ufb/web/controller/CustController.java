package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
//import com.ufufund.ufb.model.Area;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.VerifyCodeUtils;


@Controller
public class CustController {
	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	
	@Autowired
	private CustManager custManager;
	
	@RequestMapping(value="test/index" , method=RequestMethod.GET)
	public String getPage(Model model){
		
//		String custNo = "c_01";
//		Area area =  new  Area();//custManager.getAreaByCustNo(custNo);
//		
//		log.info("--------------:"+area);
//		model.addAttribute("custNo", custNo);
//		model.addAttribute("area", area);
		return "cust/register_person";
	}
	
	/**
	 * 注册用户
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/register_person")
	public String registerPerson(CustinfoVo custinfoVo, Model model) {
		
		try{
//			// 校验验证码
//			boolean checkVerifyCode = VerifyCodeUtils.validate("GETLOGINPWD", custinfoVo.getVerifycode());
//			if(!checkVerifyCode){
//				throw new BizException("验证码无效。");
//			}
//
			// 查询手机号是否注册
			boolean isMobileRegister = custManager.isMobileRegister(custinfoVo.getMobileno());
			if(isMobileRegister){
				throw new BizException("手机号已注册。");
			}
//			
//			// 校验短信验证码
//			boolean checkMsgCode = MsgCodeUtils.validate(custinfo.getMsgcode());
//			if(!checkMsgCode){
//				throw new BizException("短信验证码无效。");
//			}
			
			// 注册
			RegisterAction registerAction = new RegisterAction();
			registerAction.setLoginCode(custinfoVo.getMobileno());
			registerAction.setLoginPassword(custinfoVo.getPswpwd());
			registerAction.setLoginPassword2(custinfoVo.getPswpwd2());
			registerAction.setInvtp(Invtp.PERSONAL);
			//registerAction.setGrouptp(Grouptp.PERSONAL);
			
			custManager.register(registerAction);
			
			//model.addAttribute("tips", tips);
			//model.addAttribute("bulterVO", bulterVO);
			//model.addAttribute("accIndexVO", vo);
			//model.addAttribute("trdAccList", trdAccList);
		
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "../test/index.htm");
			return "error/error";
		}

		return "cust/register_person";
	}
	
	/**
	 * 登录 写入身份证到SESSION 没有就没有实名认证和绑卡 必须先开户绑卡
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cust/login")
	public String loginIn(CustinfoVo custinfoVo, Model model) {
		
		try{
			
//			// 校验验证码
//			boolean checkVerifyCode = VerifyCodeUtils.validate(custinfo.getVerifycode());
//			if(!checkVerifyCode){
//				throw new BizException("验证码无效。");
//			}
			
			LoginAction loginAction = new LoginAction();
			loginAction.setLoginCode(custinfoVo.getMobileno());
			loginAction.setLoginPassword(custinfoVo.getPswpwd());
			
			// 登录 写入身份证到SESSION 没有就没有实名认证和绑卡 必须先开户绑卡
			Custinfo custinfo = custManager.loginIn(loginAction);
			ServletHolder.getSession().setAttribute("CUSTINFO", custinfo);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "../test/index.htm");
			return "error/error";
		}

		return "cust/login_home";
	}
	
	/**
	 * 找回登录密码Page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="password/getLoginPwdPage" , method=RequestMethod.GET)
	public String getLoginPwdPage(Model model){
		
		return "password/getLoginPwdChk";
	}
	
	/**
	 * 找回登录密码Check
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "password/getLoginPwdChk")
	public String getLoginPwdChk(CustinfoVo custinfoVo, Model model) {
		
		try{
			//AjaxCustController.sendMsgCode
			//msgType: 注册REGISTER、找回登录密码GETLOGINPWD
			
			// 校验验证码
			boolean checkVerifyCode = VerifyCodeUtils.validate(custinfoVo.getVerifycode());
			if(!checkVerifyCode){
				throw new BizException("验证码无效。");
			}

			// 查询手机号是否注册
			boolean isMobileRegister = custManager.isMobileRegister(custinfoVo.getMobileno());
			if(!isMobileRegister){
				throw new BizException("手机号未注册。");
			}
			
			// 校验短信验证码
			boolean checkMsgCode = MsgCodeUtils.validate(custinfoVo.getMsgcode());
			if(!checkMsgCode){
				throw new BizException("短信验证码无效。");
			}
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "password/getLoginPwdChk");
			return "error/error";
		}

		return "password/getLoginPwdSet";
	}
	
	/**
	 * 找回登录密码Set
	 * @param custinfoVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "password/getLoginPwdSet")
	public String getLoginPwdSet(CustinfoVo custinfoVo, Model model) {
		
		try{
//			// 校验验证码
//			boolean checkVerifyCode = VerifyCodeUtils.validate("GETLOGINPWD", custinfoVo.getVerifycode());
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
			
			ChangePasswordAction changePasswordAction = new ChangePasswordAction();
			changePasswordAction.setMobile(custinfoVo.getMobileno());
			changePasswordAction.setLoginPassword(custinfoVo.getPswpwd());
			changePasswordAction.setLoginPassword2(custinfoVo.getPswpwd2());
			custManager.changePassword(changePasswordAction);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "password/getLoginPwdSet");
			return "error/error";
		}

		return "password/getLoginPwdSuccess";
	}
	
	/**
	 * 绑卡-Page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCardPage" , method=RequestMethod.GET)
	public String addBankCardPage(Model model){

		return "bankcard/addBankCardPage";
	}
	
	
	/**
	 * 绑卡-验证身份
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCardInit" , method=RequestMethod.GET)
	public String addBankCard1(BankCardVo bankCardVo, Model model){
		
		try{
			//营业执照
			//幼教机构
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setCustno(bankCardVo.getCustNo());
			openAccountAction.setInvnm(bankCardVo.getBankAcnm());
			openAccountAction.setBankidtp(bankCardVo.getBankIdtp());
			openAccountAction.setBankidno(bankCardVo.getBankIdno());
			//TODO 重复
			openAccountAction.setIdno(bankCardVo.getBankIdno());
			openAccountAction.setTradepwd(bankCardVo.getTradePwd());
			openAccountAction.setTradepwd2(bankCardVo.getTradePwd2());
			custManager.openAccount1(openAccountAction);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "password/getLoginPwdSet");
			return "bankcard/addBankCardPage";
		}

		return "bankcard/addBankCardAuthPage";
	}
	
	/**
	 * 绑卡-银行卡绑定(鉴权)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankAuth" , method=RequestMethod.GET)
	public String addBankCard2(BankCardVo bankCardVo, Model model){
		
		try{
			//TODO ajax
//			// 校验验证码
//			boolean checkVerifyCode = VerifyCodeUtils.validate(bankCardVo.getVerifycode());
//			if(!checkVerifyCode){
//				throw new BizException("验证码无效。");
//			}
			
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setBankno(bankCardVo.getBankNo());
			openAccountAction.setBankacnm(bankCardVo.getBankAcnm());
			openAccountAction.setBankidtp(bankCardVo.getBankIdtp());
			openAccountAction.setBankidno(bankCardVo.getBankIdno());
			openAccountAction.setBankacco(bankCardVo.getBankAcco());
			openAccountAction.setBankmobile(bankCardVo.getBankMobile());
			
			custManager.openAccount2(openAccountAction);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "password/getLoginPwdSet");
			return "bankcard/addBankCardAuthPage";
		}

		return "bankcard/addBankCardAuthPage";
	}
	
	/**
	 * 绑卡-银行卡绑定(验证) + 开户
	 * @param model
	 * @return
	 */
	@RequestMapping(value="bankcard/addBankCardChk" , method=RequestMethod.GET)
	public String addBankCard3(BankCardVo bankCardVo, Model model){
		
		try{
//			// 校验验证码
//			boolean checkVerifyCode = VerifyCodeUtils.validate(bankCardVo.getVerifycode());
//			if(!checkVerifyCode){
//				throw new BizException("验证码无效。");
//			}
			
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setBankno(bankCardVo.getBankNo());
			openAccountAction.setBankacnm(bankCardVo.getBankAcnm());
			openAccountAction.setBankacco(bankCardVo.getBankAcco());
			openAccountAction.setBankidtp(bankCardVo.getBankIdtp());
			openAccountAction.setBankidno(bankCardVo.getBankIdno());
			openAccountAction.setBankmobile(bankCardVo.getBankMobile());
			
			custManager.openAccount3(openAccountAction);
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "password/getLoginPwdSet");
			return "bankcard/addBankCardAuthPage";
		}

		return "cust/openAccoPage";
	}
}
