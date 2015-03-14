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

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.common.exception.BizException;

@Controller
public class AjaxCustController {

	private static final Logger LOG = LoggerFactory.getLogger(AjaxCustController.class);
	
	@Autowired
	private CustManager custManager;
	
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
