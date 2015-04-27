package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.helper.CustManagerHelper;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.biz.util.HftResponseUtil;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.ErrorInfo;
import com.ufufund.ufb.model.enums.TableName;
import com.ufufund.ufb.model.remote.hft.BankAuthRequest;
import com.ufufund.ufb.model.remote.hft.BankAuthResponse;
import com.ufufund.ufb.model.remote.hft.BankVeriRequest;
import com.ufufund.ufb.model.remote.hft.BankVeriResponse;
import com.ufufund.ufb.model.remote.hft.OpenAccountRequest;
import com.ufufund.ufb.model.remote.hft.OpenAccountResponse;
import com.ufufund.ufb.model.vo.Today;
import com.ufufund.ufb.remote.HftCustService;


@Service
public class CustManagerImpl extends ImplCommon implements CustManager {
	
	@Autowired
	private CustinfoMapper custinfoMapper;
	
	@Autowired
	private CustManagerValidator custManagerValidator;

	@Autowired
	private BankMapper bnankMapper;

	@Autowired
	private TradeNotesMapper tradeNotesMapper;
	
	@Autowired
	private WorkDayManager workDayManager;
	
	@Autowired
	private HftCustService hftCustService;
	
	@Autowired
	private CustManagerHelper helper;
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
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, MOBILE);
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
	public void register(RegisterAction loginAction) throws BizException {
		String processId = this.getProcessId(loginAction);
		// 先验证验证码
		custManagerValidator.validator(loginAction);
		// 查询手机号是否注册
		if(this.isMobileRegister(loginAction.getLoginCode())){
			throw new BizException(processId, ErrorInfo.ALREADY_REGISTER, MOBILE);
		}
		
		/*
		 * 插入客户信息表
		 */
		Custinfo custinfo = helper.toCustinfo(loginAction);
		custinfo.setCustno(custinfoMapper.getCustinfoSequence());
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

	@Override
	public void changePassword(ChangePasswordAction changePasswordAction) throws BizException {
		String processId = this.getProcessId(changePasswordAction);
		custManagerValidator.validator(changePasswordAction);
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(changePasswordAction.getMobile());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null ) {
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE);
		}
		custinfo.setPasswd(changePasswordAction.getLoginPassword());
		custinfoMapper.updateCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.CHANGE_PASSWORD.getValue());
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
		/*
		 * 先验证验证码
		 */
		//loginAction.setLoginType(Apkind.LOGININ);
		custManagerValidator.validator(loginAction);
		/*
		 * 加载信息
		 */
		Custinfo custinfo = new Custinfo();
		if (RegexUtil.isMobile(loginAction.getLoginCode())) {
			// 手机登录
			custinfo.setMobileno(loginAction.getLoginCode());
		} else if (RegexUtil.isIdCardNo(loginAction.getLoginCode())) {
			// 身份证登录
			custinfo.setIdno(loginAction.getLoginCode());
		} else {
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE);
		}
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null || "".equals(custinfo.getCustno())) {
			throw new BizException(processId, ErrorInfo.NO_IDCARDNO);
		}
		if (Constant.CUSTST$P.equals(custinfo.getCustst())) {
			throw new BizException(processId, ErrorInfo.FREEZE_USER);
		}
		custinfo.setLastlogintime("systime");// 当前时间
		
		//5次密码输错，冻结用户
		if (!loginAction.getLoginPassword().equals(custinfo.getPasswd())) {
			custinfo.setPasswderr(custinfo.getPasswderr() + 1);
			if (custinfo.getPasswderr() == 5) {
				custinfo.setCustst(Constant.CUSTST$P);
			}
			custinfoMapper.updateCustinfo(custinfo);
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD);
		}
		
		/*
		 * 登录
		 */
		custinfoMapper.updateCustinfo(custinfo);
		return custinfo;
	}

	/**
	 *  1 验证身份
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction) throws BizException {
		this.getProcessId(openAccountAction);
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		custManagerValidator.validatorOpenAccount1(openAccountAction);
		// 用户注册、冻结、已开户验证
		this.validatorOpenAccount(openAccountAction);
		return openAccountAction;
	}
	
	/**
	 *  2 银行快捷鉴权
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction) throws BizException {
		// 验证
		custManagerValidator.validator(openAccountAction);
		
		// 生成流水号
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		openAccountAction.setAccoreqSerial(tradeNotesMapper.getAccoreqSerialSeq());
		
		// 执行鉴权交易
		BankAuthRequest request = helper.toBankAuthRequest(openAccountAction);
		BankAuthResponse response = hftCustService.bankAuth(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		return openAccountAction;
	}
	
	/**
	 *  3 银行手机验证
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction) throws BizException {
		// 参数验证
		custManagerValidator.validator(openAccountAction);
		
		// 执行银行验证交易
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		BankVeriRequest request = helper.toBankVeriRequest(openAccountAction);
		BankVeriResponse response = hftCustService.bankVeri(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		openAccountAction.setProtocolno(response.getProtocolNo());
		return openAccountAction;
	}
	
	/**
	 *  4 开户
	 *  1 验证身份， 2 银行快捷鉴权, 3 银行手机验证 ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public void openAccount4(OpenAccountAction openAccountAction) throws BizException {
//		String processId =  this.getProcessId(openAccountAction);
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		custManagerValidator.validatorOpenAccount1(openAccountAction);
		// 用户注册、冻结、已开户验证
		this.validatorOpenAccount(openAccountAction);
		// 银行基本信息验证
		custManagerValidator.validator(openAccountAction);
		
		// 执行开户交易
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		OpenAccountRequest request = helper.toOpenAccountRequest(openAccountAction);
		OpenAccountResponse response = hftCustService.openAccount(request);
		
		// 处理返回异常码
		HftResponseUtil.dealResponseCode(response);
		
		// *** 开户成功，更新custinfo表的交易帐号、投资人姓名、证件类型、证件号、开户状态、交易密码
		openAccountAction.setTransactionAccountID(response.getTransactionAccountID());
		Custinfo custinfo = helper.toOpenAccountAction(openAccountAction);
		custinfoMapper.updateCustinfo(custinfo);
		
		Bankcardinfo bankcardinfodef = null;
		Bankcardinfo bankcardinfoqey = new Bankcardinfo();
		bankcardinfoqey.setCustno(custinfo.getCustno());
		List<Bankcardinfo> bankList = bnankMapper.getBankcardinfo(bankcardinfoqey);
		for(Bankcardinfo bankcardinfo : bankList){
			if(bankcardinfo.getBankno()!=null && bankcardinfo.getBankno().equals(openAccountAction.getBankno())){
				bankcardinfodef = bankcardinfo;
				break;
			}
		}
		if(bankcardinfodef==null){
			bankcardinfodef = helper.toBankcardinfo(openAccountAction);
			String bankSeq = bnankMapper.getBankcardinfoSequence();
			bankcardinfodef.setSerialid(bankSeq);
			bankcardinfodef.setState("Y");
			bnankMapper.insterBankcardinfo(bankcardinfodef);
			Changerecordinfo changerecordinfo1 = helper.toBankcardinfo(bankcardinfodef);
			changerecordinfo1.setApkind(Apkind.OPEN_ACCOUNT.getValue());
			changerecordinfo1.setRefserialno(openAccountAction.getSerialno());
			// **** 变更表
			tradeNotesMapper.insterChangerecordinfo(changerecordinfo1);
		}		
		
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setCustno(openAccountAction.getCustno());// char(10) not null comment '客户编号',
		tradeaccoinfo.setFundcorpno(openAccountAction.getMerchant().Value());// char(2) not null default '' comment '交易账号类型：归属基金公司',
		tradeaccoinfo.setBankserialid(bankcardinfodef.getSerialid());// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
		tradeaccoinfo.setTradeacco(openAccountAction.getTransactionAccountID());// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
		bnankMapper.insterTradeaccoinfo(tradeaccoinfo);

		// *** 插入流水表
		Fdacfinalresult fdacfinalresult = new  Fdacfinalresult();//helper.toFdacfinalresult(custinfo);
		fdacfinalresult.setCustno(custinfo.getCustno());
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setBankserialid(bankcardinfodef.getSerialid());
		fdacfinalresult.setTradeaccoid(openAccountAction.getTransactionAccountID());
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(openAccountAction.getSerialno());
		fdacfinalresult.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
		Changerecordinfo changerecordinfo2 = new Changerecordinfo();
		changerecordinfo2.setCustno(custinfo.getCustno());
		changerecordinfo2.setRecordafter(custinfo.toString());
		changerecordinfo2.setTablename(TableName.CUSTINFO.value());
		changerecordinfo2.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo2.setRefserialno(openAccountAction.getSerialno());
		// **** 变更表
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo2);	
		
		Changerecordinfo changerecordinfo3 = helper.toTradeaccoinfo(tradeaccoinfo);
		changerecordinfo3.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo3.setRefserialno(openAccountAction.getSerialno());
		// **** 变更表
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo3);
	}
	
	/**
	 *  用户注册、冻结、已开户验证
	 * @param openAccountAction
	 */
	private void validatorOpenAccount(OpenAccountAction openAccountAction) {
		// Custno 验证
		if(openAccountAction.getCustno()==null||"".equals(openAccountAction.getCustno())){
			throw new BizException(openAccountAction.getProcessId(), ErrorInfo.NO_IDCARDNO, "用户id");
		}
		// CustNo 用户是否注册验证
		Custinfo custinfo = this.getCustinfo(openAccountAction.getCustno());		
		if(custinfo==null){
			throw new BizException(openAccountAction.getProcessId(), ErrorInfo.NO_IDCARDNO, "用户id");
		}
		// Custst 用户是否冻结验证
		if(Constant.CUSTST$P.equals(custinfo.getCustst())){
			throw new BizException(openAccountAction.getProcessId(), ErrorInfo.FREEZE_USER, "用户id");
		}
		
		// 个人基本信息验证（用户名、身份证、交易密码、开户机构）
		// custManagerValidator.validatorOpenAccount1(openAccountAction);
		
		// Custst 用户是否开户验证
		if(!Constant.OPENACCOUNT$Y.equals(custinfo.getOpenaccount())){
			if (this.isIdCardNoRegister(openAccountAction.getIdno())) {
				throw new BizException(openAccountAction.getProcessId(), ErrorInfo.ALREADY_REGISTER, "用户证件号");
			}
		}
	}
	

	
	
	/**
	 * 根据缓存获取custno 获取客户信息 
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

	

}
