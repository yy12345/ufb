package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.action.LoginAction;
import com.ufufund.ufb.action.OpenAccountAction;
import com.ufufund.ufb.model.model.Custinfo;
import com.ufufund.ufb.service.CustInterface;

public interface CustManager extends CustInterface{
	
	/**
	 * 查询手机号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isMobileRegister(String mobile) throws Exception;
		
	/**
	 * 注册
	 * @param LoginIn
	 * @return 
	 */
	public void register(LoginAction loginAction) throws Exception;
	
	/**
	 * 登录 写入身份证到SESSION 没有就没有实名认证和绑卡 必须先开户绑卡
	 * @param LoginIn
	 * @return 
	 */
	public void loginIn(LoginAction loginAction) throws Exception;
	
	
	
	
	
	
	
	
	/**
	 * 根据缓存获取custno
	 * 获取客户信息 判断是否具有身份证
	 * 没有 必须完善个人信息绑卡
	 * @param custno
	 * @return 
	 */
	public Custinfo getCustinfo(String custno) throws Exception;
	
	
	/**
	 * 检查输入的身份证是否已注册
	 * @param idCardNo
	 * @return 
	 */
	public boolean isIdCardNoRegister(String idCardNo) throws Exception;
	
	
//	/**
//	 * 没有身份证信息的绑卡
//	 * @param custno
//	 * @return 
//	 */
//	public void openAccountFirst(OpenAccountAction openAccountAction,CustinfoAction custinfoAction) throws Exception;
	
	
	/**
	 *  绑卡
	 *  3 开户绑卡
	 * @param OpenAccount
	 * @return 
	 */
	public void openAccount(OpenAccountAction openAccountAction) throws Exception;
	
	
	
	
	/**
	 *  修改客户信息
	 * @param OpenAccount
	 * @return 
	 */
	public void updateCustinfo(Custinfo custinfo) throws Exception;
	
	
//	/**
//	 * 插入客户信息表
//	 * @param Custinfo
//	 * @return 
//	 */
//	public void insterCustinfo(Custinfo custinfo) throws Exception;
	
	
}
