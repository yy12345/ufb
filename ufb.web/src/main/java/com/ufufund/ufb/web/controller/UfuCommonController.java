package com.ufufund.ufb.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.db.BankCardbin;
import com.ufufund.ufb.web.util.MsgCodeUtils;
import com.ufufund.ufb.web.util.VerifyCodeUtils;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="common")
@Slf4j
public class UfuCommonController {

	
	@Autowired
	private BankBaseManager bankBaseManager;
	
	
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
}
