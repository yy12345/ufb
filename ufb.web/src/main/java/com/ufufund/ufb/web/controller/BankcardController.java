package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.ufufund.ufb.biz.manager.BankManager;
//import com.ufufund.ufb.model.Area;


@Controller
public class BankcardController {
	private static final Logger LOG = LoggerFactory.getLogger(BankcardController.class);
	
//	@Autowired
	private BankManager bankManager;

}
