package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.impl.CustManagerImpl;
//import com.ufufund.ufb.model.Area;


@Controller
public class CustController {
	private static final Logger log = LoggerFactory.getLogger(CustController.class);
	
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
		return "test/test_index";
	}

}
