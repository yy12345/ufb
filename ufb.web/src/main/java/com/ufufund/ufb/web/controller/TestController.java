package com.ufufund.ufb.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.biz.manager.MobileMsgManager;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.web.util.MsgCodeUtils;

@Controller
public class TestController {
	
	@Autowired
	private MobileMsgManager mobileMsgManager;

	// 其它业务短信发送，比如学费通业务，绑卡成功
	public void others(){
		
		String custno = "cxxxx00002";
		String content = String.format(Constant.MsgTemplate.templateMap.get("0J002"), "招商银行", "0657");
		mobileMsgManager.sendMessage(custno, content);
	}
	
	@RequestMapping(value="test/index", method=RequestMethod.GET)
	@ResponseBody
	public String test(){
		
		String mobileNo = "15211827360";
		// 家长动态码短信
		MsgCodeUtils.sendMsg("0J001", mobileNo);
		// 幼儿园动态码短信
		MsgCodeUtils.sendMsg("0Y001", mobileNo);
		
		return "0000";
	}
}
