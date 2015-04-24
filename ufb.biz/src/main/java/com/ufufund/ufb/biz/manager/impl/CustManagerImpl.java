package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.Merchant.MerchantFund;
import com.ufufund.ufb.biz.Merchant.OpenAccount;
import com.ufufund.ufb.biz.common.ImplCommon;
import com.ufufund.ufb.biz.convert.BankConvert;
import com.ufufund.ufb.biz.convert.CustConvert;
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.manager.WorkDayManager;
import com.ufufund.ufb.biz.manager.impl.validator.CustManagerValidator;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.SequenceUtil;
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
import com.ufufund.ufb.model.vo.Today;


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
	
//	@Autowired
//	private HftCustService hftCustService;
	
	@Autowired
	private MerchantFund merchantFund;
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
		/*
		 * 先验证验证码
		 */
		custManagerValidator.validator(loginAction);
		if(this.isMobileRegister(loginAction.getLoginCode())){
			throw new BizException(processId, ErrorInfo.ALREADY_REGISTER);
		}
		/*
		 * 插入客户信息表
		 */
		Custinfo custinfo = CustConvert.convertCustinfo(loginAction);
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
		// TODO Auto-generated method stub
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
			custinfo.setMobileno(loginAction.getLoginCode());
		} else if (RegexUtil.isIdCardNo(loginAction.getLoginCode())) {
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
	 *  1 验证身份， 2 银行快捷鉴权,3 银行手机验证  ，4 开户
	 * 
	 * @param OpenAccount
	 * @return
	 */
	public OpenAccountAction openAccount1(OpenAccountAction openAccountAction) throws BizException {
		this.getProcessId(openAccountAction);
		this.validatorOpenAccount1(openAccountAction);
		return openAccountAction;
	}
	
	public OpenAccountAction openAccount2(OpenAccountAction openAccountAction) throws BizException {
		String processId =  this.getProcessId(openAccountAction);
		// 验证银行编码、银行开户户名、银行证件类型、银行证件号码、银行卡号、银行开户手机号是否空
		custManagerValidator.validator(openAccountAction);
		// 合作平台申请单编号 UFT生成
		// SequenceUtil.getSerial() 
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		// 请求序列号 给通联的流水号 UFT生成
		openAccountAction.setAccoreqSerial(tradeNotesMapper.getAccoreqSerialSeq());
		
		/*
		 * 进行XML接口 银行快捷鉴权
		 */
		OpenAccount openAccount = merchantFund.bankAuth(openAccountAction);
		
		/*
		 * 返回码转换
		 */
		if(!"0000".equals(openAccount.getReturncode())){
			throw new BizException(processId, openAccount.getReturnMsg(), openAccount.getReturncode());
		}
		openAccountAction.setOtherserial(openAccount.getOtherserial());
		openAccountAction.setProtocolno(openAccount.getProtocolno());
		return openAccountAction;
	}
	
	public OpenAccountAction openAccount3(OpenAccountAction openAccountAction) throws BizException {
		String processId = this.getProcessId(openAccountAction);
		custManagerValidator.validator(openAccountAction);
		if (openAccountAction.getMobileAutoCode().length()>6 ||
		    !RegexUtil.isDigits(openAccountAction.getMobileAutoCode())) {
			// 手机验证码
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG, IDENTIFYING);
		}
		if (RegexUtil.isNull(openAccountAction.getOtherserial())) {
			// 对方序列号
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY, "对方序列号");
		}
	
		/*
		 * 进行XML接口 银行快捷验证
		 */
		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		OpenAccount openAccount = merchantFund.bankVeri(openAccountAction);
		
		/*
		 * 返回码转换
		 */
		if(!"0000".equals(openAccount.getReturncode())){
			throw new BizException(processId, openAccount.getReturnMsg(), openAccount.getReturncode());
		}
		
		return openAccountAction;
	}
	
	
	public void openAccount4(OpenAccountAction openAccountAction) throws BizException {
		String processId =  this.getProcessId(openAccountAction);
		this.validatorOpenAccount1(openAccountAction);
		custManagerValidator.validator(openAccountAction);
		/*
		 * 进行XML接口 开户
		 */
		//TODO GR
//		openAccountAction.setSerialno(tradeNotesMapper.getFdacfinalresultSeq());
		OpenAccount openAccount = merchantFund.openAccount(openAccountAction);
		/*说
		 * 返回码转换
		 */
		if(!"0000".equals(openAccount.getReturncode())){
			throw new BizException(processId,openAccount.getReturncode());
		}
		openAccountAction.setTransactionAccountID(openAccount.getTransactionAccountID());
		//TransactionAccountID
		Custinfo custinfo = CustConvert.convertOpenAccountAction(openAccountAction);
		custinfoMapper.updateCustinfo(custinfo);
		Fdacfinalresult fdacfinalresult = new  Fdacfinalresult();//CustConvert.convertFdacfinalresult(custinfo);
		String seq = openAccountAction.getSerialno();
		
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
			bankcardinfodef = BankConvert.converBankcardinfo(openAccountAction);
			String bankSeq = bnankMapper.getBankcardinfoSequence();
			bankcardinfodef.setSerialid(bankSeq);
			bnankMapper.insterBankcardinfo(bankcardinfodef);
			Changerecordinfo changerecordinfo1 = BankConvert.convertBankcardinfo(bankcardinfodef);
			changerecordinfo1.setApkind(Apkind.OPEN_ACCOUNT.getValue());
			changerecordinfo1.setRefserialno(seq);
			
			//TODO GR
//			tradeNotesMapper.insterChangerecordinfo(changerecordinfo1);
		}		
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setCustno(openAccountAction.getCustno());// char(10) not null comment '客户编号',
		tradeaccoinfo.setFundcorpno(openAccountAction.getMerchant().Value());// char(2) not null default '' comment '交易账号类型：归属基金公司',
		tradeaccoinfo.setBankserialid(bankcardinfodef.getSerialid());// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
		tradeaccoinfo.setTradeacco(openAccountAction.getTransactionAccountID());// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
		bnankMapper.insterTradeaccoinfo(tradeaccoinfo);
		/*
		 * 插入流水表
		 */
		fdacfinalresult.setCustno(custinfo.getCustno());
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setBankserialid(bankcardinfodef.getSerialid());
		fdacfinalresult.setTradeaccoid(openAccountAction.getTransactionAccountID());
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(custinfo.getCustno());
		changerecordinfo.setRecordafter(custinfo.toString());
		changerecordinfo.setTablename(TableName.CUSTINFO.value());
		changerecordinfo.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo.setRefserialno(seq);
		
		Changerecordinfo changerecordinfo2 = BankConvert.convertTradeaccoinfo(tradeaccoinfo);
		changerecordinfo2.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo2.setRefserialno(seq);
		//TODO GR
//		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);	
//		tradeNotesMapper.insterChangerecordinfo(changerecordinfo2);
//		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
	}
	
	
	
	
	/*
	 * 开户身份验证
	 * 
	 */
	private void validatorOpenAccount1(OpenAccountAction openAccountAction) {
		if(openAccountAction.getCustno()==null||"".equals(openAccountAction.getCustno())){
			throw new BizException(openAccountAction.getProcessId(), ErrorInfo.NO_IDCARDNO, "用户id");
		}
		Custinfo custinfo = this.getCustinfo(openAccountAction.getCustno());		
		if(custinfo==null){
			throw new BizException(openAccountAction.getProcessId(), ErrorInfo.NO_IDCARDNO, "用户id");
		}
		if(Constant.CUSTST$P.equals(custinfo.getCustst())){
			throw new BizException(openAccountAction.getProcessId(), ErrorInfo.FREEZE_USER, "用户id");
		}
		custManagerValidator.validatorOpenAccount1(openAccountAction);
		if(!Constant.OPENACCOUNT$Y.equals(custinfo.getCustst())){
			if (this.isIdCardNoRegister(openAccountAction.getIdno())) {
				throw new BizException(openAccountAction.getProcessId(), ErrorInfo.ALREADY_REGISTER, "用户id");
			}
		}
	}
	

	
	
	/**
	 * 根据缓存获取custno 获取客户信息 
	 * 
	 * @param custno
	 * @return
	 */
	private Custinfo getCustinfo(String custno) throws BizException {
		Custinfo custinfo = new Custinfo();
		custinfo.setCustno(custno);
		custinfo = custinfoMapper.getCustinfo(custinfo);
		return custinfo;
	}
//	
	
	
	
	private void insterSerialno(Custinfo custinfo,String apkind) throws BizException {
		/*
		 * 插入流水表
		 */
		//TODO GR
//		String seq = tradeNotesMapper.getFdacfinalresultSeq();
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		fdacfinalresult.setCustno(custinfo.getCustno());
		Today today = workDayManager.getSysDayInfo();
		fdacfinalresult.setWorkdate(today.getWorkday());
		fdacfinalresult.setApdt(today.getDate());
		fdacfinalresult.setAptm(today.getTime());
		//TODO GR
//		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(apkind);
		/*
		 * 
		 * 插入变动记录表
		 */
		Changerecordinfo changerecordinfo = new Changerecordinfo();
		changerecordinfo.setCustno(custinfo.getCustno());
		changerecordinfo.setRecordafter(custinfo.toString());
		changerecordinfo.setTablename(TableName.CUSTINFO.value());
		changerecordinfo.setApkind(apkind);
		//TODO GR
//		changerecordinfo.setRefserialno(seq);
//		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);
//		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
	}

	

}
