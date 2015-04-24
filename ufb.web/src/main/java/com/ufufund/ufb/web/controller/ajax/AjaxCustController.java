package com.ufufund.ufb.web.controller.ajax;

import java.util.HashMap;
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
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.VerifyCodeUtils;

@Controller
public class AjaxCustController {

	private static final Logger LOG = LoggerFactory.getLogger(AjaxCustController.class);
	
	@Autowired
	private CustManager custManager;
	
	/**
	 * 银行快捷鉴权
	 * @param bankCardVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "ajaxbankcard/addBankCardAuth", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> addBankCardAuth(BankCardVo bankCardVo, Model model){

		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setBankno(bankCardVo.getBankNo());//银行编号
			openAccountAction.setBankacnm(bankCardVo.getBankAcnm());//银行用户名
			bankCardVo.setBankIdtp("0");//银行证件类型
			openAccountAction.setBankidtp(bankCardVo.getBankIdtp());//银行证件类型
			openAccountAction.setBankidno(bankCardVo.getBankIdno());//银行证件号
			openAccountAction.setBankacco(bankCardVo.getBankAcco());//银行卡号码
			openAccountAction.setBankmobile(bankCardVo.getBankMobile());//银行手机号
			
			//调用银行快捷鉴权
			custManager.openAccount2(openAccountAction);
			
			resultMap.put("errCode", "0000");
			resultMap.put("errMsg", "银行卡鉴权成功");
			// 对方序列号
			resultMap.put("otherserial", openAccountAction.getAccoreqSerial());
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", e.getOtherInfo());
			resultMap.put("errMsg", e.getMessage());
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}

		return resultMap;
	}
	
	/**
	 * 获取短信验证码
	 * @param
	 * @return
	 */
	@RequestMapping(value = "ajaxcust/chk_msgcode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> sendMsgCode(String msgType){
		//msgType: 注册REGISTER、找回登录密码GETLOGINPWD
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			// 获取短信验证码
			String msg = "";
			
			// 发送短信
			MsgCodeUtils.sendMsg(msg);
			
			resultMap.put("errCode", "0000");
			resultMap.put("VerifyCode" + "_" + msgType, "verifyCode");
			
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 校验图形验证码
	 * @param verifyCode
	 * @return
	 */
	@RequestMapping(value = "ajaxcust/chk_verifycode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> checkVerifyCode(String verifycode, Model model){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			
			// 校验图形验证码
			boolean checkVerifyCode = VerifyCodeUtils.validate(verifycode);
			
			if(!checkVerifyCode){
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", "验证码无效。");
			}else{
				resultMap.put("errCode", "0000");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 校验短信验证码
	 * @param msgCode
	 * @return
	 */
	@RequestMapping(value = "ajaxcust/chk_msgcode", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> checkMsgCode(String msgCode, Model model){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			
			// 校验短信验证码
			boolean checkMsgCode = MsgCodeUtils.validate(msgCode);
			
			if(!checkMsgCode){
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", "短信验证码无效。");
			}else{
				resultMap.put("errCode", "0000");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 查询手机号是否注册
	 * @param mobileno
	 * @return
	 */
	@RequestMapping(value = "ajaxcust/is_mobile_register", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> isMobileRegister(String mobileno, Model model){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			// 查询手机号是否注册
			boolean isMobileRegister = custManager.isMobileRegister(mobileno);
			
			if(isMobileRegister){
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", "手机号已注册。");
			}else{
				resultMap.put("errCode", "0000");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 检查身份证是否已注册
	 * @param idCardNo
	 * @return
	 */
	@RequestMapping(value = "ajaxcust/is_idcardno_register", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> isIdCardNoRegister(String idCardNo, Model model){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			// 检查身份证是否已注册
			boolean isIdCardNoRegister = custManager.isIdCardNoRegister(idCardNo);
			
			if(isIdCardNoRegister){
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", "身份证已注册。");
			}else{
				resultMap.put("errCode", "0000");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
	
	/**
	 * 检查银行卡是否已注册
	 * @param idCardNo
	 * @return
	 */
	@RequestMapping(value = "ajaxcust/is_bankcardno_register", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,String> isbankcardRegister(String bankCardNo, Model model){
		Map<String,String> resultMap = new HashMap<String,String>();
		try {
			// 检查银行卡是否已注册
			boolean isbankCardNoRegister = true;//custManager.isbankCardNoRegister(bankCardNo);
			
			if(isbankCardNoRegister){
				resultMap.put("errCode", "9999");
				resultMap.put("errMsg", "银行卡已注册。");
			}else{
				resultMap.put("errCode", "0000");
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", e.getMessage());
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			resultMap.put("errCode", "9999");
			resultMap.put("errMsg", "系统出现异常！");
		}
		return resultMap;
	}
}
