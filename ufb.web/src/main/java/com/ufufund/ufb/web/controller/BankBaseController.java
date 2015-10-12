package com.ufufund.ufb.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.BankBaseManager;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.model.db.BankCardbin;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="bank")
@Slf4j
public class BankBaseController {
	
	@Autowired
	private BankBaseManager bankBaseManager;
	
	@RequestMapping(value="cardbin_read", method=RequestMethod.GET)
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
