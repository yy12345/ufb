package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.action.CustinfoAction;
import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.service.CustInterface;

public interface CustManager extends CustInterface{
	

	
	
	/**
	 * 查询手机号是否注册
	 * @param CustinfoAction custinfoAction
	 * @return 
	 */
	public Custinfo getCustinfo(CustinfoAction custinfoAction) throws Exception;
	
	
	/**
	 * 注册
	 * @param LoginIn
	 * @return 
	 */
	public void register(LoginAction loginAction) throws Exception;
	
	
	/**
	 * 登录
	 * @param LoginIn
	 * @return 
	 */
	public void loginIn(LoginAction loginAction) throws Exception;
	
	
	
	
	/**
	 * 插入客户信息表
	 * @param Custinfo
	 * @return 
	 */
	public void insterCustinfo(Custinfo custinfo) throws Exception;
	
	
	/**
	 *  开户
	 * @param OpenAccount
	 * @return 
	 */
	public void openAccount(OpenAccountAction openAccountAction) throws Exception;
	
	
}
