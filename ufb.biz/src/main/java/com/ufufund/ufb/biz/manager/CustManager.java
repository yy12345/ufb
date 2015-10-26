package com.ufufund.ufb.biz.manager;

import java.util.List;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.CustinfoVo;
import com.ufufund.ufb.model.vo.StudentVo;
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
	public boolean isIdNoBindByTradeAcco(String fundcorpno, String invtp, String level, String idno) throws BizException;
	
	/**
	 * 检查是否已设置交易密码
	 * @param custno
	 * @return 
	 */
	public boolean isTradePwdSet(String custno) throws BizException;
	
	/**
	 * 查询手机号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isMobileRegister(String mobile) throws BizException;
	
	
	public boolean isIdnoRegister(String idno) throws BizException;
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
	/**
	 * 验证信息
	 * @param RegisterAction loginAction
	 * @return 
	 */
	public void validateFamily(RegisterAction loginAction) throws BizException;
	
	/**
	 * 根据用户编号查询学生信息
	 * @param String custno
	 * @return 
	 */
	public List<Student>  queryStudentsByCustno(String custno) throws BizException;
	
	/**
	 * 根据班级编号查询机构信息
	 * @param String cid
	 * @return 
	 */
	public StudentVo  queryOrgsByCid(String cid) throws BizException;
	
	public Custinfo getCustInfoByMobileno(String mobileno) throws BizException;
}
