package com.ufufund.uft.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;
//import com.ufufund.ufb.model.db.BankCardWithTradeAcco;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.util.UserHelper;

@Controller
public class CusterController {
	private static final Logger LOG = LoggerFactory.getLogger(CusterController.class);
	
	/**
	 * 首页、广告页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "family/home")
	public String index(Model model) {
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			return "error/error";
		}
		return "family/home";
	}
}
