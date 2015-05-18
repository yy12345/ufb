package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.web.filter.ServletHolder;
import com.ufufund.ufb.web.util.UserHelper;


@Controller
public class HelpController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	
	/**
	 * 帮助中心
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "help/{variable}")
	public String index(Model model, @PathVariable ("variable") String variable) {
		
		try{
			CustinfoVo s_custinfo = UserHelper.getCustinfoVo();
			if(null != s_custinfo){
				model.addAttribute("CustinfoVo", s_custinfo);
			}else{
				model.addAttribute("CustinfoVo", null);
			}
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			ServletHolder.forward("/home/index.htm");
			return "home/index";
		}
		return "help/" + variable;
	}
}
