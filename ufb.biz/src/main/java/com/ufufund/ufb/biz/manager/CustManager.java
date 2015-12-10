package com.ufufund.ufb.biz.manager;

import java.util.List;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.Student;

public interface CustManager{
	
	/**
	 * 注册
	 * @param RegisterAction loginAction
	 * @return 
	 */
	public String register(RegisterAction loginAction, OpenAccountAction openAccountAction);

	/**
	 * 登录 
	 * @param LoginAction
	 * @return  Custinfo
	 */
	public Custinfo loginIn(Custinfo custinfo);
	
	/**
	 * 查询手机号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isMobileRegister(String mobile);
	
	/**
	 * 查询身份证号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isIdnoRegister(String idno);
	
	/**
	 * 根据缓存获取custno
	 * @param custno
	 * @return 
	 */
	public Custinfo getCustinfo(String custno);
	
	/**
	 * 修改密码
	 * @param ChangePasswordAction changePasswordAction
	 * @return 
	 */
	public void changePassword(ChangePasswordAction changePasswordAction);
	
	/**
	 * 验证信息
	 * @param RegisterAction loginAction
	 * @return 
	 */
	public void validateFamily(RegisterAction loginAction);
	
	/**
	 * 根据用户编号查询学生信息
	 * @param String custno
	 * @return 
	 */
	public List<Student>  queryStudentsByCustno(String custno);
	
	/**
	 * 根据班级编号查询机构信息
	 * @param String cid
	 * @return 
	 */
	public Student  queryOrgsByCid(String cid);
	
	/**
	 * 根据手机号查询用户信息
	 * @param mobile
	 * @return 
	 */
	public Custinfo getCustInfoByMobileno(String mobileno);
	
	/**
	 * 根据机构id查询银行信息
	 * @param orgid
	 * @return 
	 */
	public OrgQuery queryOrgBankInfo(String orgid);
}
