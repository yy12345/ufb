package com.ufufund.ufb.biz.manager;

import java.util.List;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.vo.StudentVo;

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
	 * 检查输入的身份证是否已注册
	 * @param idCardNo
	 * @return 
	 */
	public boolean isIdNoBindByTradeAcco(String fundcorpno, String invtp, String level, String idno);
	
	/**
	 * 检查是否已设置交易密码
	 * @param custno
	 * @return 
	 */
	public boolean isTradePwdSet(String custno);
	
	/**
	 * 查询手机号是否注册
	 * @param mobile
	 * @return 
	 */
	public boolean isMobileRegister(String mobile);
	
	
	public boolean isIdnoRegister(String idno);
	/**
	 * 根据缓存获取custno
	 * 获取客户信息 判断是否具有身份证
	 * 没有 必须完善个人信息绑卡
	 * @param custno
	 * @return 
	 */
	public Custinfo getCustinfo(String custno);
	
	
	public Custinfo getCustinfoMapping(String orgNo, String oprNo);
	
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
	public StudentVo  queryOrgsByCid(String cid);
	
	public Custinfo getCustInfoByMobileno(String mobileno);
	
	public void insertBankCardAndTradeAcco(OpenAccountAction openAccountAction);
	public OrgQuery queryOrgBankInfo(String custno);
}
