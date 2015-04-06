package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.service.CustInterface;

public interface CustManager extends CustInterface{
	
	/**
	 * 查询手机号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isMobileRegister(String mobile) throws BizException;
		
	/**
	 * 注册
	 * @param RegisterAction loginAction
	 * @return 
	 */
	public void register(RegisterAction loginAction) throws BizException;
	
	
	
	/**
	 * 修改密码
	 * @param ChangePasswordAction changePasswordAction
	 * @return 
	 */
	public void changePassword(ChangePasswordAction changePasswordAction) throws BizException;
	
	
	
	
	/**
	 * 登录 
	 * @param LoginAction
	 * @return  Custinfo
	 */
	public Custinfo loginIn(LoginAction loginAction) throws BizException;
	
	
	
	
	/**
	 * 检查输入的身份证是否已注册
	 * @param idCardNo
	 * @return 
	 */
	public boolean isIdCardNoRegister(String idCardNo) throws BizException;
	
	
	
	/**
	 *  
	 *  开户绑卡 1 验证身份， 2 银行快捷鉴权,3 银行手机验证  ，4 开户
	 * @param OpenAccountAction
	 * @return 
	 */
	public void openAccount1(OpenAccountAction openAccountAction) throws BizException;
	public void openAccount2(OpenAccountAction openAccountAction) throws BizException;
	public void openAccount3(OpenAccountAction openAccountAction) throws BizException;
	public void openAccount4(OpenAccountAction openAccountAction) throws BizException;
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
////	/**
////	 * 根据缓存获取custno
////	 * 获取客户信息 判断是否具有身份证
////	 * 没有 必须完善个人信息绑卡
////	 * @param custno
////	 * @return 
////	 */
////	public Custinfo getCustinfo(String custno) throws BizException;
//	
//	
//
//	
////	/**
////	 * 没有身份证信息的绑卡
////	 * @param custno
////	 * @return 
////	 */
////	public void openAccountFirst(OpenAccountAction openAccountAction,CustinfoAction custinfoAction) throws Exception;
//	
//	
//	
//	
////	/**
////	 * 插入客户信息表
////	 * @param Custinfo
////	 * @return 
////	 */
////	public void insterCustinfo(Custinfo custinfo) throws Exception;
	
	
}
