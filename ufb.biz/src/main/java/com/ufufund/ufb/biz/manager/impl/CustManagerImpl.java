package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.CustManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.OrgQuery;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.db.Today;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.TableName;


@Service
public class CustManagerImpl implements CustManager {
	
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private CustinfoMapper custinfoMapper;
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	@Autowired
	private BankCardManager bankCardManager;
	@Autowired
	private CustManagerValidator custManagerValidator;
	@Autowired
	private CustManagerHelper custManagerHelper;
	
	@Override
	@Transactional
	public String register(RegisterAction registerAction, OpenAccountAction openAccountAction)  {
 		custManagerValidator.validator(registerAction);
		
		// 添加用户信息
	 	Custinfo custinfo = custManagerHelper.toCustinfo(registerAction, openAccountAction);
		custinfo.setCustno(SequenceUtil.getSerial());
		custinfoMapper.insertCustinfo(custinfo);
		// 添加银行卡
		openAccountAction.setCustno(custinfo.getCustno());
		String bankSerialid = bankCardManager.addBankCardinfo(openAccountAction);
		// 添加幼富宝基金交易账户
		if(!StringUtils.isBlank(openAccountAction.getTransactionaccountid())){
			bankCardManager.addTradeaccoinfo(openAccountAction, bankSerialid);
		}
		return custinfo.getCustno();
		 
	}
	
	@Override
	public Custinfo loginIn(Custinfo custinfo){
		
		// 登录验证 登录账号、密码空校验
		if(StringUtils.isBlank(custinfo.getMobileno())||StringUtils.isBlank(custinfo.getPasswd())){
			throw new UserException("系统异常！");
		}

		custinfo.setPasswd(EncryptUtil.md5(custinfo.getPasswd()));
		// 后台获取用户信息
		Custinfo cust = custinfoMapper.getCustinfo(custinfo);
		if (null == cust || null == cust.getCustno() || "".equals(cust.getCustno())){
			// 登录账号无效
			throw new UserException("登录账号无效！");
		}
		if ("5".equals(cust.getState())) {
			// 登录账号已经被冻结
			throw new UserException("系统异常！");
		}
		
		// 5次密码输错，冻结用户
		if (!custinfo.getPasswd().equals(cust.getPasswd())){
			cust.setPasswderr(cust.getPasswderr() + 1);
			if (cust.getPasswderr() == 5) {
				cust.setState("5");
			}
			custinfoMapper.updateCustinfo(cust);
			// 登录密码不正确
			throw new UserException("系统异常！");
		}
		
		// 登录 更新
		cust.setLastlogintime("systime");// 最后登录时间
		custinfoMapper.updateCustinfo(cust);
		return cust;
	}
	
	@Override
	public boolean isMobileRegister(String mobile)  {
		boolean res = false;
		if (RegexUtil.isNull(mobile)||!RegexUtil.isMobile(mobile)) {
			throw new UserException("系统异常！");
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(mobile.trim());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}
	
	@Override
	public boolean isIdnoRegister(String idno)  {
		boolean res = false;
		if (RegexUtil.isNull(idno)) {
			throw new UserException("系统异常！");
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setIdno(idno.trim());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}
	
	@Override
	public Custinfo getCustinfo(String custno)  {
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		return custinfo;
	}
	
	/**
	 * 插入流水表、更新变动表
	 * @param custinfo
	 * @param apkind
	 * @
	 */
	@Transactional
	private void insterSerialno(Custinfo custinfo, String apkind)  {
		String seq = SequenceUtil.getSerial();
		// 插入流水表
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setCustno(custinfo.getCustno());
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(apkind);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		// 插入变动记录表
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(custinfo.getCustno());
		changerecordinfo.setRecordafter(custinfo.toString());
		changerecordinfo.setTablename(TableName.CUSTINFO.value());
		changerecordinfo.setApkind(apkind);
		changerecordinfo.setRefserialno(seq);
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);
	}

	@Override
	@Transactional
	public void changePassword(ChangePasswordAction changePasswordAction)  {
		String actionType = changePasswordAction.getActionType();
		/** 校验数据有效性 **/
		String password0=changePasswordAction.getPassword0();
		if(!("").equals(password0)&&!(password0==null)){
			 custManagerValidator.validator(changePasswordAction);
		}
		/** 验证原始密码 **/
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(changePasswordAction.getCustno());
		if("TRADE".equals(actionType)){
			// 交易密码
			custinfo.setTradepwd(EncryptUtil.md5(changePasswordAction.getPassword0()));
		}else if("LOGIN".equals(actionType)){
			// 登入密码
			if(!("").equals(password0)&&!(password0==null)){ 
		 	custinfo.setPasswd(EncryptUtil.md5(changePasswordAction.getPassword0()));
			} 
		} 
		
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null ) {
			if("TRADE".equals(actionType)){
				throw new UserException("系统异常！");
			}else{
				throw new UserException("系统异常！");
			}
		}
		
		// 校验是否与登录密码一致
		String md5 = EncryptUtil.md5(changePasswordAction.getPassword1());
		if("TRADE".equals(actionType)){
			 if(md5.equals(custinfo.getPasswd())){
				 throw new UserException("系统异常！");
			} 
		}else if("LOGIN".equals(actionType)){
			if(md5.equals(custinfo.getTradepwd())){
				throw new UserException("系统异常！");
			}
		}else{
			 if(md5.equals(custinfo.getPasswd())){
				 throw new UserException("系统异常！");
			}
		}
		
		/** 修改密码 **/
		if("TRADE".equals(actionType)){
			// 交易密码
			custinfo.setTradepwd(EncryptUtil.md5(changePasswordAction.getPassword1()));
		}else if("LOGIN".equals(actionType)){
			// 登入密码
			 custinfo.setPasswd(EncryptUtil.md5(changePasswordAction.getPassword1()));
		}else{
			// 找回交易密码
			custinfo.setTradepwd(EncryptUtil.md5(changePasswordAction.getPassword1()));
		}
		custinfoMapper.updateCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.CHANGE_PASSWORD.getValue());
	}

	@Override
	public void validateFamily(RegisterAction registerAction)  {
		
		// 查询手机号是否注册
		if(this.isMobileRegister(registerAction.getLogincode())){
			throw new UserException("手机号码已注册！");
		}
		if(this.isIdnoRegister(registerAction.getIdno())){
			throw new UserException("身份证号码已注册！");
		}
	}

	@Override
	public List<Student> queryStudentsByCustno(String custno)  {
		return custinfoMapper.queryStudentsByCustno(custno);
	}

	@Override
	public Student queryOrgsByCid(String cid)  {
		return custinfoMapper.queryOrgsByCid(cid);
	}

	@Override
	public Custinfo getCustInfoByMobileno(String mobileno)  {
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(mobileno.trim());
		return custinfoMapper.getCustinfo(custinfo);
		
	}

	@Override
	public OrgQuery queryOrgBankInfo(String orgid) {
		return custinfoMapper.queryOrgBankInfo(orgid);
	}
}
