package com.ufufund.ufb.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.CustManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.vo.Today;


@Service
public class CustManagerImpl extends ImplCommon implements CustManager {
	
	@Autowired
	private CustManagerValidator custManagerValidator;
	@Autowired
	private WorkDayManager workDayManager;
	@Autowired
	private CustManagerHelper custManagerHelper;
	@Autowired
	private CustinfoMapper custinfoMapper;
	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	
	/**
	 * 查询手机号是否注册
	 * 
	 * @param String
	 *            mobile
	 * @return
	 */
	@Override
	public boolean isMobileRegister(String mobile) throws BizException {
		String processId = this.getProcessId(mobile);
		boolean res = false;
		if (!RegexUtil.isMobile(mobile)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.MOBILE);
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(mobile);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}
 
	/**
	 * 注册
	 * 
	 * @param RegisterAction
	 * @return
	 */
	@Override
	public void register(RegisterAction registerAction) throws BizException {
		String processId = this.getProcessId(registerAction);
		custManagerValidator.validator(registerAction);
		// 查询手机号是否注册
		if(this.isMobileRegister(registerAction.getLoginCode())){
			throw new BizException(processId, ErrorInfo.ALREADY_REGISTER, MOBILE);
		}
		
		// 插入客户信息表
		Custinfo custinfo = custManagerHelper.toCustinfo(registerAction);
		custinfo.setCustno(custinfoMapper.getCustinfoSequence());
		registerAction.setCustNo(custinfo.getCustno());
		custinfoMapper.insertCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.REGISTER.getValue());
	}


	/**
	 * 查询该身份证是否注册
	 * 
	 * @param idCardNo
	 * @return
	 */
	public boolean isIdCardNoRegister(String idCardNo) throws BizException {
		String processId = this.getProcessId(idCardNo);
		boolean res = false;
		if (!RegexUtil.isIdCardNo(idCardNo)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,IDCARDNO);
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setIdno(idCardNo);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}

	/**
	 * 登录
	 * 
	 * @param RegisterAction
	 * @return
	 */
	@Override
	public Custinfo loginIn(LoginAction loginAction) throws BizException {
		String processId = this.getProcessId(loginAction);
		//loginAction.setLoginType(Apkind.LOGININ);
		custManagerValidator.validator(loginAction);
		/*
		 * 加载信息
		 */
		Custinfo custinfo = new Custinfo();
		if (RegexUtil.isMobile(loginAction.getLoginCode())) {
			// 手机登录
			custinfo.setMobileno(loginAction.getLoginCode());
			custinfo.setCustst(null);
		} else if (RegexUtil.isIdCardNo(loginAction.getLoginCode())) {
			// 身份证登录
			custinfo.setIdno(loginAction.getLoginCode());
			custinfo.setCustst(null);
		} else {
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE, BisConst.Register.LOGINCODE);
		}
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (null == custinfo || null == custinfo.getCustno() || "".equals(custinfo.getCustno())) {
			throw new BizException(processId, ErrorInfo.NO_IDCARDNO, BisConst.Register.LOGINCODE);
		}
		if (Constant.Custinfo.CUSTST$P.equals(custinfo.getCustst())) {
			//冻结状态
			throw new BizException(processId, ErrorInfo.FREEZE_USER, BisConst.Register.LOGINCODE);
		}
		custinfo.setLastlogintime("systime");// 最后登录时间
		
		//5次密码输错，冻结用户
		if (!EncryptUtil.md5(loginAction.getLoginPassword()).equals(custinfo.getPasswd())) {
			custinfo.setPasswderr(custinfo.getPasswderr() + 1);
			if (custinfo.getPasswderr() == 5) {
				custinfo.setCustst(Constant.Custinfo.CUSTST$P);
			}
			custinfoMapper.updateCustinfo(custinfo);
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD, BisConst.Register.LOGINPASSWORD);
		}
		
		// 登录
		custinfoMapper.updateCustinfo(custinfo);
		return custinfo;
	}

	/**
	 * 根据缓存获取custno获取客户信息 
	 * 
	 * @param custno
	 * @return
	 */
	public Custinfo getCustinfo(String custno) throws BizException {
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		return custinfo;
	}
	
	private void insterSerialno(Custinfo custinfo,String apkind) throws BizException {
		String seq = tradeNotesMapper.getFdacfinalresultSeq();
		/*
		 * 插入流水表
		 */
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		fdacfinalresult.setCustno(custinfo.getCustno());
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(apkind);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		/*
		 * 
		 * 插入变动记录表
		 */
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(custinfo.getCustno());
		changerecordinfo.setRecordafter(custinfo.toString());
		changerecordinfo.setTablename(TableName.CUSTINFO.value());
		changerecordinfo.setApkind(apkind);
		changerecordinfo.setRefserialno(seq);
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);
	}

	@Override
	public void changePassword(ChangePasswordAction changePasswordAction) throws BizException {
		String processId = this.getProcessId(changePasswordAction);
		String actionType = changePasswordAction.getActionType();
		/** 校验数据有效性 **/
		custManagerValidator.validator(changePasswordAction);
		
		/** 验证原始密码 **/
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(changePasswordAction.getCustno());
		if("TRADE".equals(actionType)){
			// 交易密码
			custinfo.setTradepwd(EncryptUtil.md5(changePasswordAction.getPassword0()));
		}else if("LOGIN".equals(actionType)){
			// 登入密码
			custinfo.setPasswd(EncryptUtil.md5(changePasswordAction.getPassword0()));
		}else{
			// 找回交易密码
		}
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null ) {
			if("TRADE".equals(actionType)){
				throw new BizException(processId, ErrorInfo.WRONG_TRADE_PASSWORD, BisConst.Register.TRADEPWD0);
			}else{
				throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD, BisConst.Register.LOGINPASSWORD0);
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
}
