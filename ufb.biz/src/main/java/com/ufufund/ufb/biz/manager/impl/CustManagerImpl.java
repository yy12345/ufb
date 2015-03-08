package com.ufufund.ufb.biz.manager.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.action.CustinfoAction;
import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.biz.convert.CustConvert;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.validator.CustManagerValidator;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.enums.Apkind;
import com.ufufund.ufb.model.model.Custinfo;

@Service
public class CustManagerImpl implements CustManager{
	
	private static final Logger log = LoggerFactory.getLogger(CustManagerImpl.class);
	
	@Autowired
	private CustinfoMapper custinfoMapper;

	@Autowired
	private CustManagerValidator custManagerValidator;
	
	
	/**
	 * 查询手机号是否注册
	 * @param CustinfoAction custinfoAction
	 * @return 
	 */
	public Custinfo getCustinfo(CustinfoAction custinfoAction) throws Exception{
		Custinfo custinfo = custinfoMapper.getCustinfo(custinfoAction);
		return custinfo;
		
	}
	
	
	/**
	 * 注册
	 * @param LoginAction
	 * @return 
	 */
	public void register(LoginAction loginAction) throws Exception {
		/*
		 * 先验证验证码
		 * 
		 */
		loginAction.setLoginType(Apkind.REGISTER);		
		custManagerValidator.validator(loginAction);
		/*
		 * 插入客户信息表
		 */
		Custinfo custinfo = CustConvert.convertCustinfo(loginAction);
		this.insterCustinfo(custinfo);
		/*
		 * 插入流水表
		 */
		
	}
	
	/**
	 * 登录
	 * @param LoginAction
	 * @return 
	 */
	@Override
	public void loginIn(LoginAction loginAction) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * 先验证验证码
		 * 
		 */
		loginAction.setLoginType(Apkind.LOGININ);
		custManagerValidator.validator(loginAction);
		/*
		 * 加载信息
		 */
		
	}


	@Override
	public void insterCustinfo(Custinfo custinfo) throws Exception {
		// TODO Auto-generated method stub
		custinfoMapper.insterCustinfo(custinfo);
		/*
		 * 
		 * 插入变动记录表
		 */
		
	}
	
	/**
	 *  开户
	 * @param OpenAccount
	 * @return 
	 */
	@Override
	public void openAccount(OpenAccountAction openAccountAction) throws Exception{
		custManagerValidator.validator(openAccountAction);
		/*
		 * 进行XML接口开户鉴权验证
		 */
		
		Custinfo custinfo = CustConvert.convertCustinfo(openAccountAction);
		this.insterCustinfo(custinfo);
		/*
		 * 
		 */
		
	}

}
