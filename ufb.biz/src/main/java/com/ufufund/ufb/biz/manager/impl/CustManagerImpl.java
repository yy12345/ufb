package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankCardManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.SequenceManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.CustManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.common.constant.BisConst;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.exception.UserException;
import com.ufufund.ufb.common.utils.EncryptUtil;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.StringUtils;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Student;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.vo.StudentVo;
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
	@Autowired
	private SequenceManager sequenceManager;
	@Autowired
	private BankCardManager bankCardManager;
	
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
		if (RegexUtil.isNull(mobile)) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.MOBILE);
		}
		if (!RegexUtil.isMobile(mobile)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.MOBILE);
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
	public boolean isIdnoRegister(String idno) throws BizException {
		String processId = this.getProcessId(idno);
		boolean res = false;
		if (RegexUtil.isNull(idno)) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.IDNO);
		}
//		if (!RegexUtil.isIdCardNo(idno)) {
//			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, BisConst.Register.IDNO);
//		}
		Custinfo custinfo = new Custinfo();
		custinfo.setIdno(idno.trim());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo != null && custinfo.getCustno() != null && !"".equals(custinfo.getCustno())) {
			res = true;
		}
		return res;
	}
	
	/**
	 * 检查是否已设置交易密码
	 * 
	 * @param String
	 *            mobile
	 * @return
	 */
	@Override
	public boolean isTradePwdSet(String custno) throws BizException {
		String processId = this.getProcessId(custno);
		boolean res = false;
		if (RegexUtil.isNull(custno)) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, BisConst.Register.MOBILE);
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.isTradePwdSet(custinfo);
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
	@Transactional
	public String register(RegisterAction registerAction, OpenAccountAction openAccountAction) throws BizException {
//		custManagerValidator.validator(registerAction);
		
		// 添加用户信息
		Custinfo custinfo = custManagerHelper.toCustinfo(registerAction, openAccountAction);
		custinfo.setCustno(sequenceManager.getCustinfoSequence());
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


	/**
	 * 查询该身份证是否注册
	 * 
	 * @param idCardNo
	 * @return
	 */
	public boolean isIdNoBindByTradeAcco(String fundcorpno, String invtp, String level, String idno) throws BizException {
		String processId = this.getProcessId(idno);
		boolean res = false;
		if (!RegexUtil.isIdCardNo(idno)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG,BisConst.Register.IDNO);
		}
		Custinfo custinfo = custinfoMapper.isIdNoBindByTradeAcco(fundcorpno, invtp, level, idno);
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
		// 登录验证 登录账号、密码空校验
		custManagerValidator.validator(loginAction);

		Custinfo custinfo = new Custinfo();
		if (RegexUtil.isMobile(loginAction.getLoginCode())) {
			// 手机登录
			custinfo.setMobileno(loginAction.getLoginCode());
			custinfo.setLoginpwd(EncryptUtil.md5(loginAction.getLoginPassword()));
			custinfo.setCustst(null);
		} else if (RegexUtil.isIdCardNo(loginAction.getLoginCode())) {
			// 身份证登录
			custinfo.setIdno(loginAction.getLoginCode());
			custinfo.setLoginpwd(EncryptUtil.md5(loginAction.getLoginPassword()));
			custinfo.setCustst(null);
		} else {
			// 登录账号无效
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE, BisConst.Register.LOGINCODE);
		}
		
		// 后台获取用户信息
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (null == custinfo || null == custinfo.getCustno() || "".equals(custinfo.getCustno())) {
			// 登录账号无效
			throw new BizException(processId, ErrorInfo.NO_IDCARDNO, BisConst.Register.LOGINCODE);
		}
		if (Constant.Custinfo.CUSTST$P.equals(custinfo.getCustst())) {
			// 登录账号已经被冻结
			throw new BizException(processId, ErrorInfo.FREEZE_USER, BisConst.Register.LOGINCODE);
		}
		
		//5次密码输错，冻结用户
		if (!EncryptUtil.md5(loginAction.getLoginPassword()).equals(custinfo.getLoginpwd())) {
			custinfo.setPasswderr(custinfo.getPasswderr() + 1);
			if (custinfo.getPasswderr() == 5) {
				custinfo.setCustst(Constant.Custinfo.CUSTST$P);
			}
			custinfoMapper.updateCustinfo(custinfo);
			// 登录密码不正确
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD, BisConst.Register.LOGINPWD);
		}
		
		// 登录 更新
		custinfo.setLastlogintime("systime");// 最后登录时间
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
	
	/**
	 * 根据缓存获取family,org获取客户信息 
	 * 
	 * @param custno
	 * @return
	 */
	public Custinfo getCustinfoMapping(String orgNo, String oprNo) throws BizException {
		String custno = custinfoMapper.getCustinfoMapping(orgNo, oprNo);
		if(null == custno){
			return null;
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		return custinfo;
	}
	
	/**
	 * 插入流水表、更新变动表
	 * @param custinfo
	 * @param apkind
	 * @throws BizException
	 */
	private void insterSerialno(Custinfo custinfo, String apkind) throws BizException {
		String seq = sequenceManager.getFdacfinalresultSeq();
		/*
		 * 插入流水表
		 */
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setCustno(custinfo.getCustno());
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(apkind);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		/*
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
				custinfo.setLoginpwd(EncryptUtil.md5(changePasswordAction.getPassword0()));
			} 
		}else{
			// 找回交易密码
		}
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null ) {
			if("TRADE".equals(actionType)){
				throw new BizException(processId, ErrorInfo.WRONG_TRADE_PASSWORD, BisConst.Register.TRADEPWD0);
			}else{
				throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD, BisConst.Register.LOGINPWD0);
			}
		}
		
		// 校验是否与登录密码一致
		String md5 = EncryptUtil.md5(changePasswordAction.getPassword1());
		if("TRADE".equals(actionType)){
			// 交易密码
			if(md5.equals(custinfo.getLoginpwd())){
				// 交易密码不能和登录密码相同
				throw new BizException(processId, ErrorInfo.CANNOTEQUALPWD, BisConst.Register.TRADEPWD);
			}
		}else if("LOGIN".equals(actionType)){
			// 登入密码
			if(md5.equals(custinfo.getTradepwd())){
				// 交易密码不能和登录密码相同
				throw new BizException(processId, ErrorInfo.CANNOTEQUALTRADEPWD, BisConst.Register.LOGINPWD);
			}
		}else{
			// 找回交易密码
			if(md5.equals(custinfo.getLoginpwd())){
				// 交易密码不能和登录密码相同
				throw new BizException(processId, ErrorInfo.CANNOTEQUALPWD, BisConst.Register.TRADEPWD);
			}
		}
		
		/** 修改密码 **/
		if("TRADE".equals(actionType)){
			// 交易密码
			custinfo.setTradepwd(EncryptUtil.md5(changePasswordAction.getPassword1()));
		}else if("LOGIN".equals(actionType)){
			// 登入密码
			custinfo.setLoginpwd(EncryptUtil.md5(changePasswordAction.getPassword1()));
		}else{
			// 找回交易密码
			custinfo.setTradepwd(EncryptUtil.md5(changePasswordAction.getPassword1()));
		}
		custinfoMapper.updateCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.CHANGE_PASSWORD.getValue());
	}

	@Override
	public void validateFamily(RegisterAction registerAction) throws BizException {
		String processId = this.getProcessId(registerAction);
		custManagerValidator.validator(registerAction);
		
		// 查询手机号是否注册
		if(this.isMobileRegister(registerAction.getLogincode())){
			throw new UserException("手机号码已注册！");
		}
		if(this.isIdnoRegister(registerAction.getIdno())){
			throw new UserException("身份证号码已注册！");
		}
	}

	@Override
	public List<Student> queryStudentsByCustno(String custno) throws BizException {
		return custinfoMapper.queryStudentsByCustno(custno);
	}

	@Override
	public StudentVo queryOrgsByCid(String cid) throws BizException {
		return custinfoMapper.queryOrgsByCid(cid);
	}

	@Override
	public Custinfo getCustInfoByMobileno(String mobileno) throws BizException {
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(mobileno.trim());
		return custinfoMapper.getCustinfo(custinfo);
		
	}

	@Override
	public void insertBankCardAndTradeAcco(OpenAccountAction openAccountAction) throws BizException {
		// 添加银行卡
		openAccountAction.setCustno(openAccountAction.getCustno());
		String bankSerialid = bankCardManager.addBankCardinfo(openAccountAction);
		// 添加幼富宝基金交易账户
		bankCardManager.addTradeaccoinfo(openAccountAction, bankSerialid);
	}
}
