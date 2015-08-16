package com.ufufund.ufb.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ufufund.ufb.model.vo.MsgReport;
import com.ufufund.ufb.remote.bcloud.MessageSender;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MsgReportController {

	@Value("${ufb.bcould.receiver}")
	private String receiver;
	@Value("${ufb.bcould.pswd}")
	private String pswd;
	
	@RequestMapping(value="msg/report" , method=RequestMethod.GET)
	@ResponseBody
	public String receive(MsgReport msgReport){
		
		log.info("received msg report:"+msgReport);
		if(receiver.equals(msgReport.getReceiver()) && pswd.equals(msgReport.getPswd())){
			// 本站点的短消息回执报告
			// code ... 核对msgid和mobile，回写发送状态
			log.debug("status="+msgReport.getStatus());
			
		}
		
		return "0000";
	} 
	
}
