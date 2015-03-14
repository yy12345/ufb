package com.ufufund.ufb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.impl.CustManagerImpl;
import com.ufufund.ufb.model.action.LoginAction;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.Invtp;
import com.ufufund.ufb.model.vo.CustinfoVo;
//import com.ufufund.ufb.model.Area;


@Controller
public class CustController {
	private static final Logger LOG = LoggerFactory.getLogger(CustController.class);
	
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
	
	@RequestMapping(value = "cust/register_person")
	public String registerPerson(CustinfoVo custinfo, Model model) {
		
		try{

			// 查询手机号是否注册
			boolean isMobileRegister = custManager.isMobileRegister(custinfo.getMobileno());
			
			if(isMobileRegister){
				throw new BizException("手机号已注册。");
			}
			
			//校验短信验证码
			boolean checkMsgvalicaode = false;//checkMsgvalicaode(custinfo.getMsgvalicode());
			if(!checkMsgvalicaode){
				throw new BizException("手机验证码无效。");
			}

			//校验登陆密码
			
			// 注册
			LoginAction loginAction = new LoginAction();
			loginAction.setLoginCode(custinfo.getMobileno());
			loginAction.setLoginPassword(custinfo.getPswpwd());
			loginAction.setLoginPassword2(custinfo.getPswpwd2());
			loginAction.setInvtp(Invtp.PERSONAL);
			//loginAction.setGrouptp(Grouptp.PERSONAL);

			custManager.register(loginAction);
			
			//model.addAttribute("tips", tips);
			//model.addAttribute("bulterVO", bulterVO);
			//model.addAttribute("accIndexVO", vo);
			//model.addAttribute("trdAccList", trdAccList);
		
		}catch (BizException e){
			LOG.error(e.getErrmsg(), e);
			model.addAttribute("errMsg", e.getMessage());
			model.addAttribute("returnUrl", "register_index.htm");
			return "error/error";
		}

		return "cust/register_person";
	}

}
