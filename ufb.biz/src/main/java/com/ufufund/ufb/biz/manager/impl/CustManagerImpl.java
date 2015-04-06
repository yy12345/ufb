package com.ufufund.ufb.biz.manager.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufufund.ufb.biz.convert.BankConvert;
import com.ufufund.ufb.biz.convert.CustConvert;
import com.ufufund.ufb.biz.exception.BizException;
import com.ufufund.ufb.biz.manager.BankManager;
import com.ufufund.ufb.biz.manager.CustManager;
import com.ufufund.ufb.biz.validator.CustManagerValidator;
import com.ufufund.ufb.common.constant.Constant;
import com.ufufund.ufb.common.utils.RegexUtil;
import com.ufufund.ufb.common.utils.ThreadLocalUtil;
import com.ufufund.ufb.dao.BankMapper;
import com.ufufund.ufb.dao.CustinfoMapper;
import com.ufufund.ufb.dao.TradeNotesMapper;
import com.ufufund.ufb.model.action.OpenAccountAction;
import com.ufufund.ufb.model.action.cust.ChangePasswordAction;
import com.ufufund.ufb.model.action.cust.LoginAction;
import com.ufufund.ufb.model.action.cust.RegisterAction;
import com.ufufund.ufb.model.db.Bankcardinfo;
import com.ufufund.ufb.model.db.Changerecordinfo;
import com.ufufund.ufb.model.db.Custinfo;
import com.ufufund.ufb.model.db.DateInfo;
import com.ufufund.ufb.model.db.Fdacfinalresult;
import com.ufufund.ufb.model.db.Tradeaccoinfo;
import com.ufufund.ufb.model.enums.Apkind;
import com.ufufund.ufb.model.enums.ErrorInfo;

@Service
public class CustManagerImpl implements CustManager {
	
	private final static String MOBILE = "手机号";
	private final static String IDCARDNO = "身份证";
	private final static String IDENTIFYING = "验证码";
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CustinfoMapper custinfoMapper;

	@Autowired
	private BankMapper bnankMapper;

	@Autowired
	private TradeNotesMapper tradeNotesMapper;

	/**
	 * 查询手机号是否注册
	 * 
	 * @param String
	 *            mobile
	 * @return
	 */
	public boolean isMobileRegister(String mobile) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		boolean res = false;
		log.debug(processId+ "查询手机号是否注册  mobile :" + mobile);
		if (!RegexUtil.isMobile(mobile)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(), MOBILE);
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
	 * 查询该身份证是否注册
	 * 
	 * @param idCardNo
	 * @return
	 */
	public boolean isIdCardNoRegister(String idCardNo) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ "查询该身份证是否注册  idCardNo :" + idCardNo);
		boolean res = false;
		if (!RegexUtil.isIdCardNo(idCardNo)) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(),IDCARDNO);
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
	 * 注册
	 * 
	 * @param RegisterAction
	 * @return
	 */
	public void register(RegisterAction loginAction) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 注册 :" + loginAction.toString());
		/*
		 * 先验证验证码
		 */
		CustManagerValidator custManagerValidator = new CustManagerValidator(processId);
		custManagerValidator.validator(loginAction);
		if(this.isMobileRegister(loginAction.getLoginCode())){
			throw new BizException(processId, ErrorInfo.ALREADY_REGISTER.value());
		}
		/*
		 * 插入客户信息表
		 */
		Custinfo custinfo = CustConvert.convertCustinfo(loginAction);
		custinfo.setCustno(custinfoMapper.getCustinfoSequence());
		custinfoMapper.insertCustinfo(custinfo);
		this.insterSerialno(custinfo, Apkind.REGISTER.getValue());
	}
	
	

	@Override
	public void changePassword(ChangePasswordAction changePasswordAction) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 修改密码 :" + changePasswordAction.toString());
		CustManagerValidator custManagerValidator = new CustManagerValidator(processId);
		custManagerValidator.validator(changePasswordAction);
		if (!RegexUtil.isMobile(changePasswordAction.getMobile())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(), MOBILE);
		}
		Custinfo custinfo = new Custinfo();
		custinfo.setMobileno(changePasswordAction.getMobile());
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null ) {
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE.value());
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
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 登录 :" + loginAction.toString());
		// TODO Auto-generated method stub
		/*
		 * 先验证验证码
		 */
		//loginAction.setLoginType(Apkind.LOGININ);
		CustManagerValidator custManagerValidator = new CustManagerValidator(processId);
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
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_CODE.value());
		}
		custinfo = custinfoMapper.getCustinfo(custinfo);
		if (custinfo == null || custinfo.getCustno() == null || "".equals(custinfo.getCustno())) {
			throw new BizException(processId, ErrorInfo.NO_IDCARDNO.value());
		}
		if (Constant.CUSTST$P.equals(custinfo.getCustst())) {
			throw new BizException(processId, ErrorInfo.FREEZE_USER.value());
		}
		custinfo.setLastlogintime("systime");// 当前时间
		if (!loginAction.getLoginPassword().equals(custinfo.getPasswd())) {
			custinfo.setPasswderr(custinfo.getPasswderr() + 1);
			if (custinfo.getPasswderr() == 5) {
				custinfo.setCustst(Constant.CUSTST$P);
			}
			custinfoMapper.updateCustinfo(custinfo);
			throw new BizException(processId, ErrorInfo.WRONG_LOGIN_PASSWORD.value());
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
	public void openAccount1(OpenAccountAction openAccountAction) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 验证身份  :" + openAccountAction.toString());
		CustManagerValidator custManagerValidator = new CustManagerValidator(processId);
		this.validator(openAccountAction, custManagerValidator, processId);
	}
	
	public void openAccount2(OpenAccountAction openAccountAction) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 银行快捷鉴权  :" + openAccountAction.toString());
		CustManagerValidator custManagerValidator = new CustManagerValidator(processId);
		custManagerValidator.validator(openAccountAction);
		/*
		 * 进行XML接口	银行快捷鉴权
		 */
		
	}
	

	public void openAccount3(OpenAccountAction openAccountAction) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 银行手机验证  :" + openAccountAction.toString());
		if (RegexUtil.isNull(openAccountAction.getMobileAutoCode())) {
			throw new BizException(processId, ErrorInfo.NECESSARY_EMPTY.value(),IDENTIFYING);
		}
		if (openAccountAction.getMobileAutoCode().length()>10||
		    !RegexUtil.isDigits(openAccountAction.getMobileAutoCode())) {
			throw new BizException(processId, ErrorInfo.FIELD_FORMAT_WRONG.value(),IDENTIFYING);
		}
		/*
		 * 进行XML接口 银行快捷验证
		 */
	}
	
	
	public void openAccount4(OpenAccountAction openAccountAction) throws BizException {
		String processId = ThreadLocalUtil.getProccessId();
		log.debug(processId+ " 开户  :" + openAccountAction.toString());
		CustManagerValidator custManagerValidator = new CustManagerValidator(processId);
		this.validator(openAccountAction, custManagerValidator, processId);
		custManagerValidator.validator(openAccountAction);
		/*
		 * 进行XML接口 开户
		 */
		Custinfo custinfo = CustConvert.convertOpenAccountAction(openAccountAction);
		custinfoMapper.updateCustinfo(custinfo);
		Fdacfinalresult fdacfinalresult = new  Fdacfinalresult();//CustConvert.convertFdacfinalresult(custinfo);
		String seq = tradeNotesMapper.getFdacfinalresultSeq();
		
		Bankcardinfo bankcardinfodef = null;
		List<Bankcardinfo> bankList = bnankMapper.getBankcardinfo(custinfo.getCustno());
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
			tradeNotesMapper.insterChangerecordinfo(changerecordinfo1);
		}		
		Tradeaccoinfo tradeaccoinfo = new Tradeaccoinfo();
		tradeaccoinfo.setCustno(openAccountAction.getCustno());// char(10) not null comment '客户编号',
		tradeaccoinfo.setFundcorpno("hft");// char(2) not null default '' comment '交易账号类型：归属基金公司',
		tradeaccoinfo.setBankserialid(bankcardinfodef.getSerialid());// varchar(24) not null comment '银行账号serialid(银行账号表pk)',
		tradeaccoinfo.setTradeacco("hftAcco");// varchar(17) not null comment '交易账号(基金公司返回的交易账号)',
		bnankMapper.insterTradeaccoinfo(tradeaccoinfo);
		/*
		 * 插入流水表
		 */
		fdacfinalresult.setCustno(custinfo.getCustno());
		DateInfo dateInfo = tradeNotesMapper.getDateInfo();
		fdacfinalresult.setBankserialid(bankcardinfodef.getSerialid());
		fdacfinalresult.setTradeaccoid("hftAcco");
		fdacfinalresult.setWorkdate(dateInfo.getWorkdate());
		fdacfinalresult.setApdt(dateInfo.getApdt());
		fdacfinalresult.setAptm(dateInfo.getAptm());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		
		Changerecordinfo changerecordinfo = CustConvert.convertChangerecordinfo(custinfo);
		changerecordinfo.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo.setRefserialno(seq);
		Changerecordinfo changerecordinfo2 = BankConvert.convertTradeaccoinfo(tradeaccoinfo);
		changerecordinfo2.setApkind(Apkind.OPEN_ACCOUNT.getValue());
		changerecordinfo2.setRefserialno(seq);
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);	
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo2);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
	}
	
	
	
	
	/*
	 * 开户身份验证
	 * 
	 */
	private void validator(OpenAccountAction openAccountAction,
						  CustManagerValidator custManagerValidator,
						  String processId) {
		Custinfo custinfo = this.getCustinfo(openAccountAction.getCustno());		
		if(custinfo==null){
			throw new BizException(processId, ErrorInfo.NO_IDCARDNO.value());
		}
		if(Constant.CUSTST$P.equals(custinfo.getCustst())){
			throw new BizException(processId, ErrorInfo.FREEZE_USER.value());
		}
		
		custManagerValidator.necessaryOpenAccount1(openAccountAction);
		if(!Constant.OPENACCOUNT$Y.equals(custinfo.getCustst())){
			if (this.isIdCardNoRegister(openAccountAction.getIdno())) {
				throw new BizException(processId, ErrorInfo.ALREADY_REGISTER.value());
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
	
	private void insterSerialno(Custinfo custinfo,String apkind) throws BizException {
		/*
		 * 插入流水表
		 */
		String seq = tradeNotesMapper.getFdacfinalresultSeq();
		Fdacfinalresult fdacfinalresult = new Fdacfinalresult();
		fdacfinalresult.setCustno(custinfo.getCustno());
		DateInfo dateInfo = tradeNotesMapper.getDateInfo();
		fdacfinalresult.setWorkdate(dateInfo.getWorkdate());
		fdacfinalresult.setApdt(dateInfo.getApdt());
		fdacfinalresult.setAptm(dateInfo.getAptm());
		fdacfinalresult.setSerialno(seq);
		fdacfinalresult.setApkind(apkind);
		/*
		 * 
		 * 插入变动记录表
		 */
		Changerecordinfo changerecordinfo = CustConvert.convertChangerecordinfo(custinfo);
		changerecordinfo.setApkind(apkind);
		changerecordinfo.setRefserialno(seq);
		tradeNotesMapper.insterChangerecordinfo(changerecordinfo);
		tradeNotesMapper.insterFdacfinalresult(fdacfinalresult);
		
	}

	

}
