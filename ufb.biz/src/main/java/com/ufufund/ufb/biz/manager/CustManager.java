package com.ufufund.ufb.biz.manager;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.service.CustInterface;

public interface CustManager extends CustInterface{
	
	/**
	 * 注册
	 * @param RegisterAction loginAction
	 * @return 
	 */
	public void register(RegisterAction loginAction) throws BizException;

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
	 * 查询手机号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isMobileRegister(String mobile) throws BizException;
	
	/**
	 * 根据缓存获取custno
	 * 获取客户信息 判断是否具有身份证
	 * 没有 必须完善个人信息绑卡
	 * @param custno
	 * @return 
	 */
	public Custinfo getCustinfo(String custno) throws BizException;
	
	
	public Custinfo getCustinfoMapping(String orgNo, String oprNo) throws BizException;
	
	/**
	 * 修改密码
	 * @param ChangePasswordAction changePasswordAction
	 * @return 
	 */
	public void changePassword(ChangePasswordAction changePasswordAction) throws BizException;
//	/**
//	 * 没有身份证信息的绑卡
//	 * @param custno
//	 * @return 
//	 */
//	public void openAccountFirst(OpenAccountAction openAccountAction,CustinfoAction custinfoAction) throws Exception;
//	/**
//	 * 插入客户信息表
//	 * @param Custinfo
//	 * @return 
//	 */
//	public void insterCustinfo(Custinfo custinfo) throws Exception;
	
}
