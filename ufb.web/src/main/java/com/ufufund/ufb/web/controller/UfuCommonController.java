package com.ufufund.ufb.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.exception.SysException;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.vo.BankCardVo;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.MsgCodeUtils.MsgCode;
import com.ufufund.ufb.web.util.UserHelper;
import com.ufufund.ufb.web.util.VerifyCodeUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="common")
@Slf4j
public class UfuCommonController {

	
	@Autowired
	private BankBaseManager bankBaseManager;
	@Autowired
	private CustManager custManager;
	@Autowired
	private BankCardManager bankCardManager;
	
	/**
	 * 检验图形验证码是否正确
	 * @param verifycode
	 * @return
	 */
	@RequestMapping(value = "verifycode_check")
	@ResponseBody
	public Map<String,Object> verifycodeCheck(String verifycode) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			VerifyCodeUtils.check(verifycode);
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
	 * 检验短信验证码是否正确
	 * @param msgcode
	 * @return
	 */
	@RequestMapping(value = "msgcode_check")
	@ResponseBody
	public Map<String,Object> msgcodeCheck(String msgcode, String mobileno) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			MsgCodeUtils.check(msgcode, mobileno);
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
	 * 检验短信验证码是否正确
	 * 场景：
	 * 	1.未登录状态时，传送mobileno参数；
	 * 	2.登录状态时，可不传送mobileno参数
	 * @param msgcode
	 * @return
	 */
	@RequestMapping(value = "msgcode_send")
	@ResponseBody
	public Map<String,Object> msgcodeSend(String mobileno, String verifycode) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			CustinfoVo custinfo = UserHelper.getCustinfoVo();
			if(StringUtils.isBlank(mobileno)){
				mobileno = custinfo.getMobileno();
			}
			
			VerifyCodeUtils.validate(verifycode);
			MsgCodeUtils.sendMsg(mobileno, "0J001");
			resultMap.put("errCode", "0000");
			
			// this is test code ... removed later...
			MsgCode msgcode = (MsgCode)ServletHolder.getSession().getAttribute("MSGCODE");
			resultMap.put("msgcode", msgcode.getMsgCode());
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
	 * 检验手机号码是否已注册
	 * @param msgcode
	 * @return
	 */
	@RequestMapping(value = "isMobileRegister")
	@ResponseBody
	public Map<String,Object> isMobileRegister(String mobileno) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			boolean isMobileRegister = custManager.isMobileRegister(mobileno);
			resultMap.put("errCode", "0000");
			resultMap.put("isMobileRegister", isMobileRegister);
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
	 * 检验交易密码是否与登录密码相同
	 * @param msgcode
	 * @return
	 */
	@RequestMapping(value = "isTradePwdSame")
	@ResponseBody
	public Map<String,Object> isTradePwdSame(String password,String type) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			String custno = UserHelper.getCustno();
			
			 if(StringUtils.isBlank(custno)){
				throw new UserException("用户未登录！");
			}
			Custinfo custinfo = custManager.getCustinfo(custno);
			boolean same=false;
			if(type.equals("1")){
				 same = EncryptUtil.md5(password).equals(custinfo.getTradepwd());
			}
			if(type.equals("2")){
				same = EncryptUtil.md5(password).equals(custinfo.getLoginpwd());
			}
			
			resultMap.put("errCode", "0000");
			resultMap.put("same", same);
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
	 * 未登录状态检验交易密码是否与登录密码相同
	 * @param msgcode
	 * @return
	 */
	@RequestMapping(value = "isTradePwdSameUnLoign")
	@ResponseBody
	public Map<String,Object> isTradePwdSameUnLoign(String password,String mobileno) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
		    Custinfo custInfo=  custManager.getCustInfoByMobileno(mobileno);
			boolean	same = EncryptUtil.md5(password).equals(custInfo.getTradepwd());
			
			resultMap.put("errCode", "0000");
			resultMap.put("same", same);
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
	 * 根据bin编码读取银行卡bin
	 * @param bin
	 * @return
	 */
	@RequestMapping(value="cardbin_read")
	@ResponseBody
	public Map<String,Object> cardbinRead(String bin){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			BankCardbin cardbin = bankBaseManager.getBankCardbin(bin);
			
			resultMap.put("errCode", "0000");
			resultMap.put("cardbin", cardbin);
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
	 * 海富通银行鉴权
	 * @param bankCardVo
	 * @return
	 */
	@RequestMapping(value = "bank_auth")
	@ResponseBody
	public Map<String,String> bankAuth(BankCardVo bankCardVo){

		Map<String,String> resultMap = new HashMap<String,String>();
		try{
			// 1.注册场景
			CustinfoVo custinfoVo = (CustinfoVo)ServletHolder.getSession().getAttribute("register_vo");
			// 2.登录场景
			if(custinfoVo == null)custinfoVo = UserHelper.getCustinfoVo();
			
			OpenAccountAction openAccountAction = new OpenAccountAction();
			openAccountAction.setBankno(bankCardVo.getBankno());//银行编号
			openAccountAction.setBankacnm(custinfoVo.getInvnm());//银行用户名
			openAccountAction.setBankidtp("0");					//银行证件类型
			openAccountAction.setBankidno(custinfoVo.getIdno());//银行证件号
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
	
}
